package com.donation;

import com.donation.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class ParserApplication {

	@Autowired
	private AsyncService asyncService;

	public static void main(String[] args) {
		SpringApplication.run(ParserApplication.class, args);
	}


	@Bean(name = "executor")
	public TaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(30);
		executor.setMaxPoolSize(40);
		executor.setQueueCapacity(20);
		executor.setThreadNamePrefix("Parser");
		executor.initialize();
		return executor;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		asyncService.executeAsync();
	}

}
