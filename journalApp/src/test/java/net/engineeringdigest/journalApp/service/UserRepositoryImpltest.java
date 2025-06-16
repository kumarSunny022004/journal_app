package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.Repository.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImpltest {


    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    public void testSaveNewUSer(){
         Assertions.assertNotNull(userRepository.getUserForSA());
    }
}
