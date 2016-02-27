package view;

import interfaces.Sizes;
import logic.OSName;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManager implements Sizes {

    private String rootDirectory;
    private String separator;

    File fileDirectory;
    ListView<String> directories;
    Stage stage;
    Label labelCurrentDirectory;
    ObservableList<String> directoriesToString;



    public FileManager(){
         setRootDirectoryAndSeparator();
    }

    private void setRootDirectoryAndSeparator(){
        if(OSName.isWindows()){
            rootDirectory = FIRST_ROOT_DIRECTORY_FOR_WINDOWS;
            separator = SEPARATOR_FOR_WINDOWS;
        }
        else {
            rootDirectory = FIRST_ROOT_DIRECTORY_FOR_LINUX;
            separator = SEPARATOR_FOR_LINUX;
        }
    }

    public void showStageFileManager(){

        stage = new Stage();
        stage.setTitle("File Manager");

        BorderPane rootBorderPane = new BorderPane();
        stage.setScene(new Scene(rootBorderPane, FILE_MANAGER_WINDOW_WIDTH,  FILE_MANAGER_WINDOW_HEIGHT));

        labelCurrentDirectory = initForTextDirectory();
        rootBorderPane.setTop(labelCurrentDirectory);

        setOurListOfDirectories(rootDirectory);

        MultipleSelectionModel<String> msModel = directories.getSelectionModel();
        msModel.selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        rootDirectory += newValue;
                        labelCurrentDirectory.setText("Current directory: " + rootDirectory);
                        directoriesToString = FXCollections.observableArrayList(findDirectories(rootDirectory));
                        directories.getItems().clear();
                        directories.setItems(directoriesToString);
                        rootDirectory += separator;
                    }
                }
        );

        rootBorderPane.setCenter(directories);

        HBox buttonHBox = buttonsPane();
        rootBorderPane.setBottom(buttonHBox);


        stage.show();
    }

    public void setOurListOfDirectories(String folder){
        directoriesToString = FXCollections.observableArrayList(findDirectories(folder));
        directories = new ListView<>(directoriesToString);
        directories.setPrefSize(400, 550);
    }

    public HBox buttonsPane(){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15, 15, 15, 430));
        hBox.setSpacing(10);

        Button selectFile = new Button("Select");
        selectFile.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);


        Button cancelFile = new Button("Cancel");
        cancelFile.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        cancelFile.setOnAction((ae)-> {
            stage.close();
        });

        hBox.getChildren().addAll(selectFile, cancelFile);
        return hBox;
    }

    private Label initForTextDirectory(){
        Label res = new Label();
        res.setPadding(new Insets(15, 0, 15, 0));
        res.setText("Current directory: " + rootDirectory);
        return res;
    }

    private List<String> findDirectories(String rootDirectory){
        List<String> elementsInDirectory = new ArrayList<>();
        File folder = new File(rootDirectory);
        File[] folders = folder.listFiles();
        for (File file: folders){
            elementsInDirectory.add(file.getName());
        }
        return elementsInDirectory;
    }
}
