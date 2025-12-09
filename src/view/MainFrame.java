import javafx.application.Application;
import javafx.stage.Stage;

public class StoryGeneratorApp extends Application {
    //

    public void start(Stage stage) {
        Button generateStory = new Button("Generate");
        TextField storyPrompt = new TextField();

        HBox row1 = new HBox(10, textField);
        HBox row2 = new HBox(10, generateStory);
    }

    public static void main(String[] args) {
        launch(args);
    }
}