package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Stage.*;
import model.APIClient;

public class StoryGeneratorApp extends Application {
    private final APIClient storyGen = new APIClient();

    @Override
    public void start(Stage stage) {
        final int v = 400;
        final int v1 = 150;
        stage.setTitle("Story Generator");

        Button generateStory = new Button("Generate");
        //Button edit = new Button("Edit");
        Button reGen = new Button("Regenerate");
        Button restart = new Button("Restart");
        Button txtAdv = new Button("Text Adventure");
        Button image = new Button("Image Generation");

        TextArea storyPrompt = new TextArea();
        storyPrompt.setWrapText(true);
        storyPrompt.setPrefColumnCount(60);
        storyPrompt.setPrefRowCount(4);
        storyPrompt.setPromptText("Enter your story prompt...");

        TextArea storyGenre = new TextArea();
        storyGenre.setWrapText(true);
        storyGenre.setPrefColumnCount(15);
        storyGenre.setPrefRowCount(2);
        storyGenre.setPromptText("Choose a genre...");

        TextArea storyLength = new TextArea();
        storyLength.setWrapText(true);
        storyLength.setPrefColumnCount(41);
        storyLength.setPrefRowCount(2);
        storyLength.setPromptText("How long shall your story be?"); //FIXME add max length?

        TextArea promptResponse = new TextArea();
        promptResponse.setEditable(false);
        promptResponse.setWrapText(true);
        promptResponse.setPrefColumnCount(60);
        promptResponse.setPrefRowCount(20);
        promptResponse.setPromptText("Your story goes here...");

        HBox promptInput = new HBox(10, storyPrompt);
        promptInput.alignmentProperty().setValue(Pos.CENTER);
        HBox otherInput = new HBox(10, storyGenre, storyLength);
        otherInput.alignmentProperty().setValue(Pos.CENTER);
        HBox buttonsRow = new HBox(10, generateStory, reGen, restart, txtAdv, image);
        buttonsRow.alignmentProperty().setValue(Pos.CENTER);
        VBox input = new VBox(10, otherInput, promptInput, buttonsRow);

        HBox output = new HBox(10, promptResponse);
        output.alignmentProperty().setValue(Pos.CENTER);

        VBox root = new VBox(10, output, input);
        root.alignmentProperty().setValue(Pos.CENTER);

        stage.setScene(new Scene(root, v, v1));
        stage.setMinHeight(v1*4);
        stage.setMinWidth(v*2);
        stage.setMaxHeight(v1*4);
        stage.setMaxWidth(v*2);

        //Wiring
        generateStory.setOnAction( e ->
        {
            // If in regular mode
            if (storyLength.isEditable()) {
                String prompt = storyPrompt.getText();
                String genre = storyGenre.getText();
                String length = storyLength.getText();
                String apiResponse = "";

                try {
                    apiResponse = storyGen.generateStory(prompt, genre, length);
                }
                catch (Exception exc) {
                    System.err.println(exc);
                }
                promptResponse.appendText("-- Your Story: --\n ");
                promptResponse.appendText(apiResponse);
                promptResponse.appendText("\n");
            }

            // If in Text Adventure Mode
            else if (!storyLength.isEditable()) {
                String prompt = storyPrompt.getText();
                String apiResponse = "";
                try {
                    apiResponse = storyGen.textAdventure(prompt);
                }
                catch (Exception exc) {
                    System.err.println(exc);
                }

                promptResponse.appendText(apiResponse);
                promptResponse.appendText("\n\n");
                storyPrompt.clear();
            }
        } );

        txtAdv.setOnAction( e ->
        {
            promptResponse.clear();

            // If entering Text Adventure Mode:
            if (storyLength.isEditable()) {
                promptResponse.appendText("-- Text Adventure Mode --" +
                        "\n(Click \"Text Adventure\" button again to exit Text Adventure Mode)\n\n");
                storyLength.setEditable(false);

                String prompt = "Give a two sentence introduction to a text adventure game.";
                String apiResponse = "";
                try {
                    apiResponse = storyGen.textAdventure(prompt);
                }
                catch (Exception exc) {
                    System.err.println(exc);
                }

                promptResponse.appendText(apiResponse);
                promptResponse.appendText("\n\n");
                storyPrompt.clear();
            }
            // If exiting Text Adventure Mode:
            else if (!storyLength.isEditable()) {
                storyLength.setEditable(true);
            }
        } );

        reGen.setOnAction( e ->
        {
            // If in regular mode
            if (storyLength.isEditable()) {
                String prompt = storyPrompt.getText();
                String genre = storyGenre.getText();
                String length = storyLength.getText();
                String apiResponse = "";

                try {
                    apiResponse = storyGen.generateStory(prompt, genre, length);
                }
                catch (Exception exc) {
                    System.err.println(exc);
                }
                promptResponse.appendText("-- Your New Story: --\n ");
                promptResponse.appendText(apiResponse);
                promptResponse.appendText("\n");
            }

            // If in Text Adventure Mode
            else if (!storyLength.isEditable()) {
                String prompt = storyPrompt.getText();
                String apiResponse = "";
                try {
                    apiResponse = storyGen.textAdventure(prompt);
                }
                catch (Exception exc) {
                    System.err.println(exc);
                }

                promptResponse.appendText(apiResponse);
                promptResponse.appendText("\n\n");
                storyPrompt.clear();
            }
        } );

        restart.setOnAction( e ->
        {
            promptResponse.clear();
            storyPrompt.clear();
            storyGenre.clear();
            storyLength.clear();
        } );

        image.setOnAction(e ->
        {
            double h = 650; //genImage.getHeight();
            double w = 600; //genImage.getWidth();
            String prompt = storyPrompt.getText();
            String genre = storyGenre.getText();
            String URL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBgzTBW173XjkpEZZXs0MwFZidBQAKlGevEg&s";
            try
            {
                URL = storyGen.genImage(prompt, genre, h, w);
            }
            catch (Exception exc)
            {
                System.err.println(exc);
            }
            if(URL.isEmpty())
            {
                System.err.println("Empty prompt, whoopsie!");
            }

            try
            {
                Image genImage = new Image(URL.toString());
                ImageView imageView = new ImageView(genImage);
                imageView.setFitHeight(h);
                imageView.setFitWidth(w);
                imageView.setPreserveRatio(true);
                HBox hBox = new HBox(imageView);
                hBox.alignmentProperty().setValue(Pos.CENTER);
                imageGen(hBox, h, w);
            }
            catch(Exception exception)
            {
                System.err.println("Invalid URL generated, sorry!");
            }
        });

        stage.show();
    }

    //Used to display to the user the incompleteness of our program.
    public static void placeHolder()
    {
        Stage popUp = new Stage();
        popUp.setTitle("Wait a minute!");
        Label message = new Label("This feature hasn't been fully implemented yet.");
        Button ok = new Button("  Ok  ");
        ok.setOnAction(e -> { popUp.close(); });

        VBox popRoot = new VBox(10, message, ok);
        popRoot.alignmentProperty().setValue(Pos.CENTER);
        Scene scene = new Scene(popRoot, 300, 100);
        popUp.setScene(scene);
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setMaxHeight(100);
        popUp.setMaxWidth(300);
        popUp.showAndWait();
    }

    public static void imageGen(HBox hBox, double h, double w)
    {
        Stage imgWindow = new Stage();
        imgWindow.setTitle("Image Generated: ");
        //Label message = new Label("Image goes here");
        Button close = new Button("  Exit  ");
        close.setOnAction(e -> { imgWindow.close(); });


        VBox popRoot = new VBox(10, hBox, close);
        popRoot.alignmentProperty().setValue(Pos.CENTER);
        Scene scene = new Scene(popRoot, w + 25, h + 100);
        imgWindow.setScene(scene);
        imgWindow.initModality(Modality.APPLICATION_MODAL);
        imgWindow.setMinHeight(h + 100);
        imgWindow.setMinWidth(w + 25);
        imgWindow.setMaxHeight(h + 100);
        imgWindow.setMaxWidth(w + 25);
        imgWindow.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}