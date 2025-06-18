package net.engineeringdigest.journalApp.Sceduler;

import net.engineeringdigest.journalApp.Cache.AppCache;
import net.engineeringdigest.journalApp.Enum.Sentiment;
import net.engineeringdigest.journalApp.Repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.Users;
import net.engineeringdigest.journalApp.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private EmailService emailService;


    @Autowired
    private AppCache appCache;


    @Scheduled(cron = "0 * * ? * *")
    public void fetchUsersandSendMail(){
        List<Users> users= userRepository.getUserForSA();
        for(Users user:users){
            List<JournalEntry> journalEntries = user.getJournalentries();
            List<Sentiment> sentiments = journalEntries.stream().filter(x->x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x->x.getSentiment()).collect(Collectors.toList());
            Map<Sentiment,Integer> SentimentsCount = new HashMap<>();
            for(Sentiment sentiment:sentiments){
            if(sentiment!=null){
                SentimentsCount.put(sentiment,SentimentsCount.getOrDefault(sentiment,0)+1);
            }

            Sentiment mostFrequentSentiment = null;
            int maxCount = 0;

            for(Map.Entry<Sentiment,Integer> entry:SentimentsCount.entrySet()){
                if(entry.getValue()>maxCount){
                    maxCount = entry.getValue();
                    mostFrequentSentiment = entry.getKey();
                }
            }
            if(mostFrequentSentiment!=null){
                emailService.sendMail(user.getEmail(),"Last 7 days",mostFrequentSentiment.toString());
            }
            }
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearCache(){
        appCache.init();
    }
}
