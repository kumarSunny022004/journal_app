package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Cache.AppCache;
import net.engineeringdigest.journalApp.Contants.Placeholders;
import net.engineeringdigest.journalApp.apiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private  String apiKey;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getTemperature(String city){
       WeatherResponse weatherResponse =  redisService.get("Weather_of"+city,WeatherResponse.class);
       if(weatherResponse!=null){
           return weatherResponse;
       }else{
           String finalApi = appCache.appCache.get(AppCache.keys.weather_api.toString()).replace(Placeholders.CITY,city).replace(Placeholders.API_KEY,apiKey);
           ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null,WeatherResponse.class);
           WeatherResponse body = response.getBody();
           if(body != null){
               redisService.set("Weather_of"+city,body,300l);
           }
           return body;
       }


    }

}
