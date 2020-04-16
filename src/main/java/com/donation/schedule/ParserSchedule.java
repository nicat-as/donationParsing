package com.donation.schedule;

import com.donation.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ParserSchedule {

    @Autowired
    private AsyncService asyncService;

    @Value("${schedule.cron}")
    private String cron;


    @Scheduled(cron ="0 0 12-18 * * *")
    public void parseDonation(){
        asyncService.executeAsync();
    }



}
