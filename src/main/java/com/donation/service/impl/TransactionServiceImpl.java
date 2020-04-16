package com.donation.service.impl;

import com.donation.domain.dto.ParserDto;
import com.donation.domain.entity.Amount;
import com.donation.domain.entity.Donation;
import com.donation.domain.entity.Type;
import com.donation.service.AmountService;
import com.donation.service.DonationService;
import com.donation.service.ParserService;
import com.donation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private DonationService donationService;

    @Autowired
    private AmountService amountService;

    @Autowired
    private ParserService parserService;


    @Transactional
    @Override
    public void proceedTransaction() {
        try {
            List<ParserDto> parserDtoList = parserService.parseAll();
            HashSet<String> hashSet = new HashSet<>();

            AtomicInteger aInt = new AtomicInteger(0);

            for (ParserDto parserDto : parserDtoList) {
                Donation donation = fillDonation(parserDto.getDonationName(), parserDto.getType());
                if (!hashSet.add(donation.getDonationName())) {
                    Amount temp = amountService.getDonationByDonationName(donation.getDonationName());
                    donation.setId(temp.getDonations().getId());
                } else {
                    donation = donationService.addDonation(donation);
                }
                Amount amount = fillAmount(parserDto.getAmount(), parserDto.getCurrency(), donation.getId());
                amount = amountService.addAmount(amount);
                System.out.println(aInt.addAndGet(1) + ") " + amount);
            }

            System.out.println("Duplicates : " + hashSet);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Donation fillDonation(String donationName, int typeValue) {
        Donation donation = new Donation();
        Type type = new Type();
        type.setId(typeValue);
        donation.setDonationName(donationName);
        donation.setType(type);
        return donation;
    }

    private Amount fillAmount(BigDecimal amountValue, String currency, Long donationId) {
        Amount amount = new Amount();
        Donation donation = new Donation();
        donation.setId(donationId);
        amount.setAmount(amountValue);
        amount.setCurrency(currency);
        amount.setDonations(donation);
        return amount;
    }
}
