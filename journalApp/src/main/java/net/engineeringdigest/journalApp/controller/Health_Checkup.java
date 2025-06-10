package net.engineeringdigest.journalApp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health_Checkup {

    @GetMapping("/okayish")
    public String check(){
        return "Nigga";
    }
}
