import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Cities {
    private final String city;
    private final Coordinates coord;

    @JsonCreator
    public Cities(@JsonProperty("city") String city,
                  @JsonProperty("coord")Coordinates coord) {
        this.city = city;
        this.coord = coord;
    }

    public String getCity() {
        return city;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public static class Coordinates {
        private final double lat;
        private final double lon;

        public Coordinates(@JsonProperty("lat") double lat,
                           @JsonProperty("lon") double lon) {
            this.lat = lat;
            this.lon = lon;
        }


        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }

    }
}
