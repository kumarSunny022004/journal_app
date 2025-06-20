package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("Health_check")
    public String health_check(){
        return "ok";
    }

    @PostMapping("/create_user")
    public void adduser(@RequestBody Users user){
        userService.saveNewUser(user);
    }
}
