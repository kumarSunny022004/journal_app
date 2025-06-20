package net.engineeringdigest.journalApp.controller;


import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Cache.AppCache;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AppCache appCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private net.engineeringdigest.journalApp.utilis.JwtUtility jwtUtility;

    @GetMapping("/all_users")
    public ResponseEntity<?> getAALlUsers(){
        List<Users> user = userService.getAll();
        if(user!=null && !user.isEmpty()){
            return new ResponseEntity<>(user , HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/Signup")
    public void Signup(@RequestBody Users user){
        userService.saveNewAdmin(user);
    }

    @PostMapping("/Login")
    public ResponseEntity<String> Login(@RequestBody Users user){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtility.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt , HttpStatus.OK);
        }catch(Exception e){
            log.error("Error occured while Authentication", e);
            return new ResponseEntity<>("Inccorrect Credentials ",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/Reinstall")
    public void ClearAppCache(){
        appCache.init();
    }
}
