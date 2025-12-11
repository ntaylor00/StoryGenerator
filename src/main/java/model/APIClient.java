package model;

import com.openai.models.images.Image;
import com.openai.models.images.ImageGenerateParams;
import com.openai.models.images.ImageModel;
import com.openai.models.responses.ResponseOutputText;
/*
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
*/
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

    public String textAdventure(String storyEdits) {
        OpenAIClient client = OpenAIOkHttpClient.fromEnv();

        ResponseCreateParams params = ResponseCreateParams.builder()
                .input("Respond to the following prompt as if it were a Text Adventure Game " + storyEdits)
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

    public String genImage(String prompt, String genre, double h, double w)
    {
        OpenAIClient client = OpenAIOkHttpClient.fromEnv();
        String rtrn = "";
        if(prompt != null && genre != null && !prompt.isEmpty())
        {
            //Building request
            if (prompt.length() > 4000) { throw new RuntimeException("Too many Characters, try a smaller prompt!");}
            ImageGenerateParams params = ImageGenerateParams.builder().prompt("Create an image in the" + genre +
                            " genre given the following prompt: " + prompt)
                    .model(ImageModel.DALL_E_3)
                    //.quality(ImageGenerateParams.Quality.HD)
                    //.style(ImageGenerateParams.Style.NATURAL)
                    .build();
            //Generating image
            var imageResponse = client.images().generate(params);

            //Gathering URL
            rtrn = imageResponse.data()
                    .orElseThrow(() -> new RuntimeException("No image data returned"))
                    .stream()
                    .findFirst()
                    .flatMap(Image::url)
                    .orElseThrow(() -> new RuntimeException("No URL returned for generated image"));
        }
        return rtrn;
    }
}
