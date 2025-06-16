package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
       try {
           Users user = userService.FindBbyusername(username);
           JournalEntry saved = journalEntryRepository.save(journalEntry);
           user.getJournalentries().add(saved);
           userService.saveUser(user);
       }catch (Exception e){
           System.out.println(e);

           throw new RuntimeException("some error has occured while processing",e);
       }
    }

    public void saveEntry(JournalEntry journalEntry){
     journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId Id){
        return journalEntryRepository.findById(Id);
    }

    @Transactional
    public  boolean delById(ObjectId Id, String username){
            boolean removed = false;
        try {
            Users users = userService.FindBbyusername(username);
            removed = users.getJournalentries().removeIf(x->x.getId().equals(Id));
            if(removed){
                userService.saveUser(users);
                journalEntryRepository.deleteById(Id);
            }
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An Error has Occured while deleting",e);
        }
        return removed;
    }

    public List<JournalEntry> findByUserName(String username){
        Users user = userService.FindBbyusername(username);
        return user.getJournalentries();
    }
}
