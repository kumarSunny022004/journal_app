package net.engineeringdigest.journalApp.apiResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class WeatherResponse {
    private Current current;

    // Getters and setters



@Getter
@Setter
    public static class Current {


        private int temperature;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;


        private int feelslike;





    }
}

