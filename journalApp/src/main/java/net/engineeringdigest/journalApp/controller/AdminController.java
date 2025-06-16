package net.engineeringdigest.journalApp.controller;


import net.engineeringdigest.journalApp.Cache.AppCache;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;

    @GetMapping("/all_users")
    public ResponseEntity<?> getAALlUsers(){
        List<Users> user = userService.getAll();
        if(user!=null && !user.isEmpty()){
            return new ResponseEntity<>(user , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create_new_admin")
    public void createnewAdmin(@RequestBody Users user){
        userService.saveNewAdmin(user);
    }

    @GetMapping("/Reinstall")
    public void ClearAppCache(){
        appCache.init();
    }
}
