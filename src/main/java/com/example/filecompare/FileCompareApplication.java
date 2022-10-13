package com.example.filecompare;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;


public class FileCompareApplication extends Application {

    public String filePath = null;

    public Path pathFile1 = null;
    public Path pathFile2 = null;

    public String file = "";
    public String file1Name;
    public String file2Name;

    public String file1Context;
    public String file2Context;

    TextArea file1Text = new TextArea(null);
    TextArea file2Text = new TextArea(null);

    String similarities;
    String differences;

    TextArea differenceFile1 = new TextArea();
    TextArea differenceFile2 = new TextArea();


    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("File Compare");

        Button addFileButton = new Button("Add File");
        Button compareFiles = new Button("Compare Files");



        SplitPane splitPane = new SplitPane();
        VBox leftVBox = new VBox(file1Text, addFileButton, differenceFile1);
        StackPane leftSide = new StackPane(leftVBox);

        VBox rightVBox = new VBox(file2Text, compareFiles, differenceFile2);
        StackPane rightSide = new StackPane(rightVBox);



        splitPane.getItems().addAll(leftSide, rightSide);

        AnchorPane pane = new AnchorPane();
        AnchorPane.setTopAnchor(splitPane, 15.0);
        AnchorPane.setRightAnchor(splitPane, 15.0);
        AnchorPane.setBottomAnchor(splitPane, 15.0);
        AnchorPane.setLeftAnchor(splitPane, 15.0);
        pane.getChildren().addAll(splitPane);






        addFileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int i = 1;
                while (i <= 2) {
                    if (i == 1) {
                        getFile();
                        file = Methods.getFile(filePath);
                        pathFile1 = Path.of(filePath);
                        file1Name = file;
                    } else {
                        getFile();
                        file = Methods.getFile(filePath);
                        pathFile1 = Path.of(filePath);
                        file2Name = file;
                    }
                    i++;
                }
                getFileText(file1Name, file2Name);
            }
        });

        compareFiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    differenceFile1.setText(Methods.filesCompareByLine(file1Name, file2Name)[1]);
                    differenceFile2.setText(Methods.filesCompareByLine(file1Name, file2Name)[2]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



        primaryStage.setScene(new Scene(pane, 800, 750));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch();
    }

    public void getFile() {
        TextInputDialog getFileDialog = new TextInputDialog();
        getFileDialog.setHeaderText(null);
        getFileDialog.setTitle("Add File");
        getFileDialog.setContentText("Please enter file path:");
        ButtonType getFileButton = new ButtonType("Add File");
        Label addFileLabel = new Label("Enter File Path: ");
        TextField addFileField = new TextField();
        VBox popUpSetUp = new VBox();
         getFileDialog.showAndWait();
         filePath = getFileDialog.getResult();
//        int i = 1;
//        while (i <= 2) {

    }
//        if (i == 1) {
//            file1Name = file;
//        } else {
//            file2Name = file;
//        }
//        i++;
//    }


    public void getFileText(String file1Name, String file2Name) {
        file1Context =Methods.readFile(file1Name);
        file2Context =Methods.readFile(file2Name);

        file1Text.setText(file1Context);
        file2Text.setText(file2Context);
    }
}