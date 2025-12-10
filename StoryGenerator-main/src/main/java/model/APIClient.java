/*package model;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class APIClient {
    private static final String API_URL = "https://api.openai.com/v1/completions";
    private String apiKey;

    public APIClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public String generateCompletion(String prompt) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(API_URL);
            post.setHeader("Authorization", "Bearer " + apiKey);
            post.setHeader("Content-Type", "application/json");
            String json = String.format("{\"prompt\": \"%s\", \"max_tokens\": 50}", prompt);
            post.setEntity(new StringEntity(json));
            try (CloseableHttpResponse response = httpClient.execute(post)) {
                return EntityUtils.toString(response.getEntity());
            }
        }
    }
}*/