import org.apache.http.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class HttpRequest {

    public static void sendRequest() {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=f06225d6467200622b9c074ae1895ad6");

// add request headers
        request.addHeader("custom-key", "programming");

        try (CloseableHttpResponse response = httpclient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
