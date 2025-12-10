package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StoryGeneratorApp extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Story Generator");

        Button generateStory = new Button("Generate");
        TextField storyPrompt = new TextField();
        storyPrompt.setPromptText("Enter your story prompt...");

        HBox row1 = new HBox(10, storyPrompt);
        HBox row2 = new HBox(10, generateStory);

        VBox root = new VBox(10, row1, row2);

        Scene scene = new Scene(root, 400, 150);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
