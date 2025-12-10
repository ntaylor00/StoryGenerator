package model;

import com.openai.models.responses.ResponseOutputText;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.stream.Collectors;
import com.openai.models.responses.ResponseOutputItem;
import com.openai.models.responses.ResponseOutputMessage;
import com.openai.models.responses.ResponseOutputText;
import com.openai.models.ChatModel;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class APIClient {
    //OpenAIClient client = OpenAIOkHttpClient.fromEnv();
    private static final String API_URL = "https://api.openai.com/v1/completions";
    //private String apiKey;
    private static APIClient apiClient;

    /*public APIClient(String apiKey) {
        this.apiKey = apiKey;
    }*/

    /*public static APIClient createAPI() {
        if (apiClient == null) {
            apiClient = new APIClient(apiKey);
        }
        return apiClient;
    }*/

    /*public String generateCompletion(String prompt) throws Exception {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(API_URL);
            post.setHeader("Authorization", "Bearer " + apiKey);
            post.setHeader("Content-Type", "application/json");
            String json = String.format("{\"prompt\": \"%s\", \"max_tokens\": 50}", prompt);
            post.setEntity(new StringEntity(json));
            try (CloseableHttpResponse response = httpClient.execute(post)) {
                return EntityUtils.toString(response.getEntity());
            }
            catch (Exception e) {
                System.err.println(e);
            }
        }
        catch (Exception e) {
            System.err.println(e);
        }
        return "";
    }*/

    public String generateStory(String inputString) {
        OpenAIClient client = OpenAIOkHttpClient.fromEnv();
        /*OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey(System.getenv("OPENAI_API_KEY"))
                .build();*/


        ResponseCreateParams params = ResponseCreateParams.builder()
                .input("Write a one sentence story given the following prompt: " + inputString) //FIXME
                .model(ChatModel.GPT_4_1_MINI)
                .maxOutputTokens(200L)
                .build();

        Response response = client.responses().create(params);

        // Check for API error
        if (response.error().isPresent()) {
            throw new RuntimeException("OpenAI error: " + response.error().get());
        }

        // Extract story text from response
        String story = response.output().stream()
                .filter(ResponseOutputItem::isMessage)
                .map(ResponseOutputItem::asMessage)
                .flatMap((ResponseOutputMessage msg) -> msg.content().stream())
                .flatMap(content -> content.outputText().stream())
                .map(ResponseOutputText::text)
                .collect(Collectors.joining("\n"));

        return story;
    }



}