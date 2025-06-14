package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class Userservicetest {

    @Autowired
    private UserRepository userRepository;

    @ParameterizedTest
    @ValueSource(strings ={
            "Ram",
            "vipul",
            "Shyam"
    })
    public void testUserName(String Name){
        assertNotNull( userRepository.findByUsername(Name),"Failed for "+Name);
    }
}
