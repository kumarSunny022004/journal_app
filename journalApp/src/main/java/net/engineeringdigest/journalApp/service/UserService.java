package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public static final PasswordEncoder passEncoder =  new BCryptPasswordEncoder();

    public boolean saveNewUser(Users user){
      try {
          user.setPassword(passEncoder.encode(user.getPassword()));
          user.setRoles(Arrays.asList("USER"));
          userRepository.save(user);
          return true;
      }catch (Exception e){
          log.info("Duplicate");
          return false;
      }
    }

    public void saveNewAdmin(Users user){
        user.setPassword(passEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userRepository.save(user);
    }

    public void saveUser(Users user){
        userRepository.save(user);
    }

    public List<Users> getAll(){
        return userRepository.findAll();
    }

    public Optional<Users> findById(ObjectId Id){
        return userRepository.findById(Id);
    }

    public void delById(ObjectId Id){
        userRepository.deleteById(Id);
    }

    public Users FindBbyusername(String Username){
        return userRepository.findByUsername(Username);
    }
}
