package com.donation;

import com.donation.domain.dto.ParserDto;
import com.donation.domain.entity.Donation;
import com.donation.domain.entity.Type;
import com.donation.repository.DonationRepository;
import com.donation.service.ParserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@SpringBootTest
public class ParserServiceTest {
    @Autowired
    private ParserService parserService;

    @Autowired
    private DonationRepository donationRepository;

    @Test
    void testParser() {
        List<ParserDto> parserDtoList = parserService.parseAll();
        AtomicInteger ai = new AtomicInteger(0);

        parserDtoList.stream()
                .filter(p -> p.getType() == 2)
                .collect(Collectors.toList())
                .forEach(a-> System.out.println(ai.addAndGet(1) + ") " + a));


    }

/*    @Test
    void testDonationAdd(){
        Donation d = new Donation();
        d.setDonationName("Beynəlxalq Bank");
        Type t = new Type();
        t.setId(1);
        d.setType(t);
        d = donationRepository.save(d);
        System.out.println(d);
    }

    @Test
    void testDonationFetch(){
        Donation d = donationRepository.findByDonationName("Beynəlxalq Bank");
        System.out.println(d);
    }*/
}
