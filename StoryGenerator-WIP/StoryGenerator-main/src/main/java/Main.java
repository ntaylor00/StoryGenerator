//package main.java;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class Main {
    public static void main(String[] args) {
        OpenAIClient client = OpenAIOkHttpClient.fromEnv();

        ResponseCreateParams params = ResponseCreateParams.builder()
                .input("Say this is a test")
                .model("gpt-5")
                .build();

        Response response = client.responses().create(params);
        System.out.println(response.toString());
    }
}

/*public class Main {
    public static void main(String[] args) {
        try {
            String apiKey = "YOUR_API_KEY_HERE";
            OpenAIClient client = new OpenAIClient(apiKey);
            String response = client.generateCompletion("Once upon a time");
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Extend OpenAIClient error handling
    public String generateCompletion(String prompt) {
        try {
            // existing code
        } catch (IOException e) {
            System.err.println("Network error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}*/

/*import java.util.Scanner;

//import model.APIClient.java;
import model.*;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String genre;
        String main_character;

        System.out.println("What genre of story would you like?");
        genre = scan.nextLine();

        System.out.println("Give a brief description for the main character" +
                            "(for example, \"A pirate wearing a fedora\"): ");
        main_character = scan.nextLine();

        //generate_story(genre, main_character);
    }
}*/