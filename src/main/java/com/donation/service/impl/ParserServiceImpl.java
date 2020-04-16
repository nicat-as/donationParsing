package com.donation.service.impl;

import com.donation.domain.TypeEnum;
import com.donation.domain.dto.ParserDto;
import com.donation.domain.entity.Type;
import com.donation.repository.TypeRepository;
import com.donation.service.ParserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParserServiceImpl implements ParserService {
    private final TypeRepository typeRepository;

    @Value("${parse.url}")
    private String url;

    public ParserServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }


    @Override
    public List<Type> parseType() {
        List<Type> typeList = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            String title = document.title();
            System.out.println(title);

            Elements types = document.getElementById("pills-tab").getElementsByAttribute("href");
            for (Element e : types) {
                String type = e.text();

                List<Type> findTypeName = typeRepository.findByTypeName(type);
                if (findTypeName.isEmpty()) {
                    Type typeObj = new Type();
                    typeObj.setTypeName(type);
                    typeObj = typeRepository.save(typeObj);
                    typeList.add(typeObj);
                    System.out.println(typeObj);
                } else {
                    typeList.add(findTypeName.get(0));
                }
            }
            Type unkonwn = new Type();
            unkonwn.setTypeName("unknown");
            typeRepository.save(unkonwn);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return typeList;
        }

    }

    @Override
    public List<ParserDto> parseAll() {
        List<ParserDto> parserDtoList = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements tabContents = doc.select("#pills-tabContent div");

            List<String> types = new ArrayList<>();
            doc.select("#pills-tab a").forEach(e -> types.add(e.attr("aria-controls")));


            for (Element e : tabContents) {
                String id = e.attr("id");
                if (types.contains(id)) {
                    Elements tr = e.select("div.custom-table tbody tr");

                    if (id.contains("genaral-foreign-in")) {
                        tr = e.select("tbody tr");
                        String name = tr.get(0).select("td").text();
                        if (name.isEmpty()) {
                            continue;
                        }
                        for (int i = 1; i < tr.size(); i++) {
                            String amount = tr.get(i).select("td").text();
                            ParserDto parserDto = fillParser(name, amount, id);
                            parserDtoList.add(parserDto);
                        }
                    } else {

                        if (id.contains("general") || id.contains("country-budget")) {
                            tr = e.select("tbody tr");
                        }

                        for (Element row : tr) {
                            Elements td = row.select("td");
                            if (!td.isEmpty() && !td.hasAttr("align")) {
                                String name = row.select("td").first().text();
                                if (name.isEmpty()) {
                                    continue;
                                }
                                String amount = row.select("td").last().text();
                                ParserDto parserDto = fillParser(name, amount, id);
                                parserDtoList.add(parserDto);
                            }
                        }

                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return parserDtoList;
        }

    }

    private int getTypeID(String type) {
        int value;
        if (type.contains("general") || type.contains("genaral")) {
            value = TypeEnum.GENERAL.getValue();
        } else if (type.contains("country")) {
            value = TypeEnum.GOVERNMENT.getValue();
        } else if (type.contains("legal")) {
            value = TypeEnum.LEGAL.getValue();
        } else if (type.contains("indivudal") || type.contains("individual")) {
            value = TypeEnum.INDIVIDUAL.getValue();
        } else {
            value = TypeEnum.UNKNOWN.getValue();
        }
        return value;
    }

    private String getCurrency(String amount) {
        amount = amount.replaceAll("[,\\s0-9]", "");
        if (!amount.isEmpty()) {
            return amount;
        } else {
            return "AZN";
        }
    }

    private String getAmount(String amount, String id) {
        int size = amount.split(",").length;
        if (id.contains("foreign") && size <= 2) {
            amount = amount.replaceAll("[\\sa-zA-Z]", "").replace(",", ".");
        } else {
            amount = amount.replaceAll("[,\\sa-zA-Z]", "");
        }
        return amount.trim();
    }

    private ParserDto fillParser(String name, String amount, String typeId) {
        ParserDto parserDto = new ParserDto();
        parserDto.setType(getTypeID(typeId));
        parserDto.setDonationName(name);
        BigDecimal bigDecimal = new BigDecimal(getAmount(amount, typeId));
        parserDto.setAmount(bigDecimal);
        parserDto.setCurrency(getCurrency(amount));
        return parserDto;
    }
}
