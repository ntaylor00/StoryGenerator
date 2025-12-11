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
    private static final String API_URL = "https://api.openai.com/v1/completions";
    private static APIClient apiClient;

    public String generateStory(String prompt, String genre, String length) {
        OpenAIClient client = OpenAIOkHttpClient.fromEnv();

        // Set default story specifications
        if (prompt.isEmpty()) {
            prompt = "An adventure story";
        }
        if (genre.isEmpty()) {
            genre = "fantasy";
        }
        if (length.isEmpty()) {
            length = "short paragraph";
        }


        ResponseCreateParams params = ResponseCreateParams.builder()
                .input("Write a story of length " + length + " and genre "
                        + genre + " given the following prompt: " + prompt)
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

    public String editStory(String story) {
        //
        return "";
    }
}