import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Serialization {

    private static final List<Map<String, String>> results = new ArrayList<>();

    HttpRequest processRequest;
    public Serialization(HttpRequest processRequest) {
        this.processRequest = processRequest;
    }

    public String ModifyResponse(String city_input) throws IOException {
        Cities selectedCity = findCity(city_input);
        if (selectedCity == null) {
            System.out.println("Miasto o nazwie '" + city_input + "' nie zostało znalezione.");
            return city_input;
        }

        double lat = selectedCity.getCoord().getLat();
        double lon = selectedCity.getCoord().getLon();

        String jsonString = processRequest.sendRequest(lat, lon);

        try {
            Weather weather = new ObjectMapper().readValue(jsonString, Weather.class);

            Weather dane = Weather.newBuilder()
                    .withTemperature(weather.getMain().getTemp())
                    .withPressure(weather.getMain().getPressure())
                    .withHumidity(weather.getMain().getHumidity())
                    .build();

            int temp = (int) Math.round(dane.getMain().getTemp() - 273.15);
            int pressure = dane.getMain().getPressure();
            int humidity = dane.getMain().getHumidity();


            // Zapisz wynik do mapy
            Map<String, String> cityResult = new HashMap<>();
            cityResult.put("name", city_input);
            cityResult.put("weather", String.format("Temperatura: %d°C, Cisnienie: %d hPa, Wilgotnosc: %d%%",
                    temp, pressure, humidity));
            results.add(cityResult);

            return String.format("Temperatura: %d°C, Ciśnienie: %d hPa, Wilgotność: %d%%", temp, pressure, humidity);
        } catch (JsonProcessingException e) {
            return "Error processing JSON response";
        }
    }

    static Cities findCity(String inputCity) {
        List<Cities> cities = ReadFile();
        if (cities == null) {
            return null;
        }

        for (Cities city : cities) {
            if (city.getCity().equalsIgnoreCase(inputCity)) {
                return city;
            }
        }
        return null;
    }

    public static List<Cities> ReadFile() {
        try {
            return new ObjectMapper().readValue(new File("src/main/resources/cities.json"), new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean CheckCity(String inputCity) {
        return findCity(inputCity) != null;
    }

    public static List<Map<String, String>> getResults() {
        return results;
    }
}

