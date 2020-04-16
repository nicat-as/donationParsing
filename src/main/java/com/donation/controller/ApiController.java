package com.donation.controller;

import com.donation.domain.entity.Donation;
import com.donation.domain.entity.Type;
import com.donation.exception.ParserException;
import com.donation.service.DonationService;
import com.donation.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class ApiController {
    @Autowired
    private ParserService parserService;

    @Autowired
    private DonationService donationService;

    @GetMapping("/parse-type")
    public ResponseEntity<?> parseType() {
        List<Type> typeList = parserService.parseType();
        ResponseEntity<?> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        if (!typeList.isEmpty()) {
            responseEntity = new ResponseEntity<>(typeList, HttpStatus.OK);
        } else {
            throw new ParserException("Parser couldn't parse type " + typeList);
        }

        return responseEntity;
    }

/*    @GetMapping("/addDonation")
    public ResponseEntity<?> addDonation(){
        ResponseEntity<?> responseEntity ;
        List<Donation> donations = parserService.parseDonation();

        if (!donations.isEmpty()){
            donationService.addDonation(donations);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        }else{
            responseEntity= new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }*/

}

