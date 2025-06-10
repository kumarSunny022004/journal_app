package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(Users user){
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
