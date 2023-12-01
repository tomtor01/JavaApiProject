import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private final Main main = new Main();

    public Main getMain() {
        return main;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        private double temp;
        private int pressure;
        private int humidity;

        @JsonProperty("temp")
        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        @JsonProperty("pressure")
        public int getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        @JsonProperty("humidity")
        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }
    }

    public static class WeatherBuilder {
        private final Weather weather = new Weather();

        public WeatherBuilder withTemperature(double temperature) {
            weather.getMain().setTemp(temperature);
            return this;
        }

        public WeatherBuilder withPressure(int pressure) {
            weather.getMain().setPressure(pressure);
            return this;
        }

        public WeatherBuilder withHumidity(int humidity) {
            weather.getMain().setHumidity(humidity);
            return this;
        }

        public Weather build() {
            return weather;
        }
    }

    public static WeatherBuilder newBuilder() {
        return new WeatherBuilder();
    }
}
