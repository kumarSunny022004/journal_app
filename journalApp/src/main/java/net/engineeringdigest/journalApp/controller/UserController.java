package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> showAll(){
       return userService.getAll();
    }


    @PostMapping
    public void adduser(@RequestBody Users user){
        userService.saveEntry(user);


    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody Users user){
        Users userInDb = userService.FindBbyusername(user.getUsername());
        if(userInDb != null){
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

}
