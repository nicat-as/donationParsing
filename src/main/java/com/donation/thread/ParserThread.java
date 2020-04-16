package com.donation.thread;

import com.donation.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ParserThread implements Runnable {
    @Autowired
    private TransactionService transactionService;

    @Override
    public void run() {
        process();
    }

    private void process(){
        transactionService.proceedTransaction();
    }
}
