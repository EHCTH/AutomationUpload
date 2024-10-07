package infrastructure.github.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpClientService {
    public CloseableHttpResponse executePut(String url, String token, String jsonPayload) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPut httpPut = new HttpPut(url);
            httpPut.setHeader("Authorization", "token " + token);
            httpPut.setHeader("Content-Type", "application/json");
            StringEntity entity = new StringEntity(jsonPayload, StandardCharsets.UTF_8);
            httpPut.setEntity(entity);

            return httpClient.execute(httpPut);
        }
    }
}
