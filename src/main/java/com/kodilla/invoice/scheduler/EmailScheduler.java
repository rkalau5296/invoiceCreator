package com.kodilla.invoice.scheduler;

import com.kodilla.invoice.config.AdminConfig;
import com.kodilla.invoice.domain.Mail;
import com.kodilla.invoice.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private AdminConfig adminConfig;
    @Autowired
    private SimpleEmailService simpleEmailService;

    private static final String SUBJECT = "Tasks: Once a day email";
    private String setText = "Przypominam o wysłaniu codziennych płatności do zdefiniowanych kontrahentów. ";

    @Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void sendInformationEmail(){
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                setText)
        );
    }

}

