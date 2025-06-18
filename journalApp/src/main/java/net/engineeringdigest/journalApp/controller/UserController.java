package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.apiResponse.WeatherResponse;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.service.UserService;
import net.engineeringdigest.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public List<Users> showAll(){
       return userService.getAll();
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Users user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Users userInDb = userService.FindBbyusername(username);
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserbyId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/yo")
    public ResponseEntity<?> grettings(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse response = weatherService.getTemperature("Pune");
        String greetings = "";
        if(response!=null){
            greetings = " Weather feels like "+response.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi "+authentication.getName()+greetings,HttpStatus.OK);
    }
}
