package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;


public class UserServiceImplementationtest {

    @InjectMocks
    private UserDetailServiceimpl userDetailService;


    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks( this);
    }


    @Test
    public void loadUserByUsernametest(){
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(Users.builder().username("Ram").password("hwgfduwfgd").roles(new ArrayList<>()).build());
        UserDetails user = userDetailService.loadUserByUsername("Ram");
        Assertions.assertNotNull(user);
    }
}
