package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Component
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
           userService.saveEntry(user);
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

    public void delById(ObjectId Id, String username){
        Users users = userService.FindBbyusername(username);
        users.getJournalentries().removeIf(x->x.getId().equals(Id));
        userService.saveEntry(users);
        journalEntryRepository.deleteById(Id);
    }
}
