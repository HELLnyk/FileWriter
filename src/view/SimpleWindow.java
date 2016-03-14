package view;

import interfaces.Sizes;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.FileWriterListing;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class SimpleWindow extends Application implements Sizes {


    TextField textFieldFileForListing;
    FileWriterListing fileWriterListing;
    Label response;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Listing Creator ");

        FlowPane flowPane = createFlowPane();
        primaryStage.setScene(new Scene(flowPane, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT));

        Text heading = createHeading();
        HBox findFileSpace = createHBoxPane();
        HBox functionalButtons = createFunctionalButtons();

        response = new Label("");

        flowPane.getChildren().addAll(heading, findFileSpace, response, functionalButtons);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private FlowPane createFlowPane(){
        FlowPane flowPane = new FlowPane(Orientation.VERTICAL, 10, 10);
        flowPane.setAlignment(Pos.CENTER);
        return flowPane;
    }

    public Text createHeading(){
        String option = "Select the file which you want to create listening";
        Text optionText = new Text(option);
        optionText.setFont(Font.font("Arial", FontPosture.REGULAR, 17));
        return optionText;
    }

    private HBox createHBoxPane(){
        HBox hBox = new HBox();

        hBox.setPadding(new Insets(10, 0, 20, 0));
        hBox.setSpacing(10);


        textFieldFileForListing = new TextField();
        textFieldFileForListing.setPrefSize(270, 20);
        Button buttonFile = new Button("Find file");
        buttonFile.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonFile.setOnAction((ae) -> {
            FileManager fileManager = new FileManager(this);
            fileManager.showStageFileManager();
        });

        hBox.getChildren().addAll(textFieldFileForListing, buttonFile);
        return hBox;
    }

    private HBox createFunctionalButtons(){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_LEFT);
        hBox.setPadding(new Insets(15, 0, 15, 0));
        hBox.setSpacing(10);

        Button buttonOK = new Button("OK");
        buttonOK.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonOK.setOnAction((ae)->{
            fileWriterListing = new FileWriterListing(textFieldFileForListing.getText());
            response.setText("TXT file was created in the same directory, where input file exists");
            //openFile(fileWriterListing.getOutFileName());

        });

        Button buttonCANCEL = new Button("Cancel");
        buttonCANCEL.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        buttonCANCEL.setOnAction((ae)-> {
            textFieldFileForListing.setText("");
        });


        hBox.getChildren().addAll(buttonOK, buttonCANCEL);

        return hBox;
    }

    private void openFile(String fileName) throws NullPointerException{
        Desktop desktop = null;
        if(desktop.isDesktopSupported()){
            try {
                Desktop.getDesktop().open(new File(fileName));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

      //Runtime.getRuntime().exec("scratch", new File(fileName));
    }

}
