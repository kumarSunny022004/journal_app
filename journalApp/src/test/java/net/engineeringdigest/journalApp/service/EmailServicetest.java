package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServicetest {

    @Autowired
    private EmailService emailService;

    @Test
    public void emailTest(){
        emailService.sendMail("ayushmanns034@gmail.com","haal","Chaal");
    }


}
