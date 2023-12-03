import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MockTests {

    HttpRequest httpRequest;

    @Before
    public void setUp() {
        System.out.println("Run setUp");
        httpRequest = Mockito.mock(HttpRequest.class);
    }

    @Test
    public void mockTestSuccess1() throws IOException {
        HttpRequest mockHttpRequest = Mockito.mock(HttpRequest.class);
        Serialization serialization = new Serialization(mockHttpRequest);

        when(mockHttpRequest.sendRequest(anyDouble(), anyDouble()))
                .thenReturn("{\"coord\":{\"lon\":16.9299,\"lat\":52.4069},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":277.43,\"feels_like\":273.06,\"temp_min\":276.73,\"temp_max\":277.75,\"pressure\":991,\"humidity\":64},\"visibility\":10000,\"wind\":{\"speed\":6.17,\"deg\":260},\"clouds\":{\"all\":75},\"dt\":1700840158,\"sys\":{\"type\":2,\"id\":19661,\"country\":\"PL\",\"sunrise\":1700807307,\"sunset\":1700837375},\"timezone\":3600,\"id\":3088171,\"name\":\"Poznań\",\"cod\":200}");

        String result = serialization.ModifyResponse("Poznan");
        assertThat(result).isEqualTo("Temperatura: 4°C, Ciśnienie: 991 hPa, Wilgotność: 64%");
    }
    @Test
    public void mockTestFail() throws IOException {
        HttpRequest mockHttpRequest = Mockito.mock(HttpRequest.class);
        Serialization serialization = new Serialization(mockHttpRequest);

        when(mockHttpRequest.sendRequest(anyDouble(), anyDouble()))
                .thenReturn("{\"coord\":{\"lon\":16.9299,\"lat\":52.4069},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04n\"}],\"base\":\"stations\",\"main\":{\"temp\":277.43,\"feels_like\":273.06,\"temp_min\":276.73,\"temp_max\":277.75,\"pressure\":991,\"humidity\":64},\"visibility\":10000,\"wind\":{\"speed\":6.17,\"deg\":260},\"clouds\":{\"all\":75},\"dt\":1700840158,\"sys\":{\"type\":2,\"id\":19661,\"country\":\"PL\",\"sunrise\":1700807307,\"sunset\":1700837375},\"timezone\":3600,\"id\":3088171,\"name\":\"Poznań\",\"cod\":200}");

        String result = serialization.ModifyResponse("Poznan");
        assertThat(result).isEqualTo("Temperatura: 6°C, Ciśnienie: 991 hPa, Wilgotność: 64%");
    }

    @Test
    public void mockTestSuccess2() throws IOException {
        HttpRequest mockHttpRequest = Mockito.mock(HttpRequest.class);
        Serialization serialization = new Serialization(mockHttpRequest);

        when(mockHttpRequest.sendRequest(anyDouble(), anyDouble()))
                .thenReturn("{\"coord\":{\"lon\":21.0102,\"lat\":52.2267},\"weather\":[{\"id\":701,\"main\":\"Mist\",\"description\":\"mist\",\"icon\":\"50n\"}],\"base\":\"stations\",\"main\":{\"temp\":269.32,\"feels_like\":266.15,\"temp_min\":267.03,\"temp_max\":270.79,\"pressure\":1003,\"humidity\":83},\"visibility\":4700,\"wind\":{\"speed\":2.06,\"deg\":230},\"clouds\":{\"all\":75},\"dt\":1701442315,\"sys\":{\"type\":2,\"id\":2032856,\"country\":\"PL\",\"sunrise\":1701411729,\"sunset\":1701440872},\"timezone\":3600,\"id\":756135,\"name\":\"Warsaw\",\"cod\":200}");

        String result = serialization.ModifyResponse("Warsaw");
        assertThat(result).isEqualTo("Temperatura: -4°C, Ciśnienie: 1003 hPa, Wilgotność: 83%");
    }
}