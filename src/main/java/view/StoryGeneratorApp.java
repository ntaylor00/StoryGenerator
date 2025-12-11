package view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
        Button edit = new Button("Edit");
        Button reGen = new Button("Regenerate");
        Button restart = new Button("Restart");

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
        HBox buttonsRow = new HBox(10, generateStory, reGen, edit, restart);
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
            String prompt = storyPrompt.getText();
            String genre = storyGenre.getText();
            String length = storyLength.getText();
            String apiResponse = "";

            /*storyPrompt.clear();
            storyGenre.clear();
            storyLength.clear();*/

            try {
                apiResponse = storyGen.generateStory(prompt, genre, length);
            }
            catch (Exception exc) {
                System.err.println(exc);
            }
            promptResponse.appendText("-- Your Story: --\n ");
            promptResponse.appendText(apiResponse);
            promptResponse.appendText("\n");
        } );

        edit.setOnAction( e -> //FIXME
        {
            placeHolder();
            /* --- EDIT LLM INPUT ---
            1. If editable == false:
                - set editable = true
                - Instruct user to click 'edit' button when finished
                - allow user to edit
            2. If editable == true:
                - set editable = false
                - send user edits to LLM
             */
            /*promptResponse.setEditable(true);
            String storyEdits = promptResponse.getText();
            String apiResponse = "";

            try {
                apiResponse = storyGen.editStory(storyEdits);
            }
            catch (Exception exc) {
                System.err.println(exc);
            }*/


            //Maybe we hold the previous response as a string so we can manipulate it here?
            //placeHolder();
            //Should allow the user to edit the last input from the LLM
            //Update the LLM of these changes, and allow the user to respond after.
        } );

        reGen.setOnAction( e -> //FIXME
        {
            //placeHolder();
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

            //Should prompt the LLM to try generating a prompt again.
        } );

        restart.setOnAction( e -> //FIXME
        {
            promptResponse.clear();

            storyPrompt.clear();
            storyGenre.clear();
            storyLength.clear();
            placeHolder();
            //We would need this to tell the LLM to disregard all previous text/open up a new chat
        } );

        stage.show();
    }

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

    public static void main(String[] args) {
        launch(args);
    }
}