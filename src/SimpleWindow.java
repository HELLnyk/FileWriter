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
import javafx.stage.Stage;


public class SimpleWindow extends Application {


    TextField textFieldFileForListing;
    FileWriterListing fileWriterListing;
    Label response;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Listing Creator ");

        FlowPane flowPane = createFlowPane();
        primaryStage.setScene(new Scene(flowPane, 500, 200));

        Text heading = createHeading();
        HBox findFileSpace = createHBoxPane();
        HBox functionalButtons = createfunctionalButtons();

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
        buttonFile.setPrefSize(100 ,20);

        hBox.getChildren().addAll(textFieldFileForListing, buttonFile);
        return hBox;
    }

    private HBox createfunctionalButtons(){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_LEFT);
        hBox.setPadding(new Insets(15, 0, 15, 0));
        hBox.setSpacing(10);
        Button buttonOK = new Button("OK");
        buttonOK.setPrefSize(70, 20);
        buttonOK.setOnAction((ae)->{
            fileWriterListing = new FileWriterListing(textFieldFileForListing.getText());
            System.out.println(textFieldFileForListing.getText());

            response.setText("File .txt was created in such directory, where file for listing is existing ");
        });

        Button buttonCANCEL = new Button("Cancel");
        buttonCANCEL.setPrefSize(70, 20);

        buttonCANCEL.setOnAction((ae)-> {
            textFieldFileForListing.setText("");
        });


        hBox.getChildren().addAll(buttonOK, buttonCANCEL);

        return hBox;
    }


}
