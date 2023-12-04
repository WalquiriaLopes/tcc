package com.tcc.scheduller;

import com.tcc.service.TccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;

@Configuration
@EnableScheduling
public class Scheduller {

    @Autowired
    private TccService tccService;

    @Scheduled(cron = "0 0 22 * * *")
    @PostConstruct
    public void run(){
        tccService.execute();
    }

}
