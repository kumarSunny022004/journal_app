package net.engineeringdigest.journalApp.Cache;

import net.engineeringdigest.journalApp.Repository.ConfigAppRepository;
import net.engineeringdigest.journalApp.entity.ConfigJournalAppEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class AppCache {

    public enum keys{
        weather_api;
    }

    @Autowired
    private ConfigAppRepository configAppRepository;

    public Map<String ,String> appCache;


    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configAppRepository.findAll();
        for(ConfigJournalAppEntity configJournalAppEntity:all){
            appCache.put(configJournalAppEntity.getKey(),configJournalAppEntity.getValue());
        }
    }
}
