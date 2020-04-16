package com.donation.service.impl;

import com.donation.service.AsyncService;
import com.donation.thread.ParserThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;


@Service

public class AsyncServiceImpl implements AsyncService {

    @Autowired
    @Qualifier("executor")
    private TaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext applicationContext;


    @Override
    public void executeAsync() {
        ParserThread parserThread = applicationContext.getBean(ParserThread.class);
        taskExecutor.execute(parserThread);
    }
}
