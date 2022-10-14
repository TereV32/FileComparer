package com.example.filecompare;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;



public class FileCompareApplication extends Application {


    public Path pathFile1 = null;
    public Path pathFile2 = null;

    public String file = "";
    public String file1Name;
    public String file2Name;

    public String file1Context;
    public String file2Context;

    TextArea file1Text = new TextArea(null);
    TextArea file2Text = new TextArea(null);


    TextArea differenceFile1 = new TextArea();
    TextArea differenceFile2 = new TextArea();

    public FileChooser chooser = new FileChooser();
    public File chosenFile;


    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("File Compare");

        Button getFile1 = new Button("Add File 1");
        Button getFile2 = new Button("Add File 2");
        Button compareFiles = new Button("Compare Files");


        SplitPane splitPane = new SplitPane();

        VBox leftVBox = new VBox(file1Text, getFile1, differenceFile1);
        StackPane leftSide = new StackPane(leftVBox);

        VBox rightVBox = new VBox(file2Text, getFile2, differenceFile2, compareFiles);
        StackPane rightSide = new StackPane(rightVBox);



        splitPane.getItems().addAll(leftSide, rightSide);

        AnchorPane pane = new AnchorPane();
        AnchorPane.setTopAnchor(splitPane, 15.0);
        AnchorPane.setRightAnchor(splitPane, 15.0);
        AnchorPane.setBottomAnchor(splitPane, 15.0);
        AnchorPane.setLeftAnchor(splitPane, 15.0);
        pane.getChildren().addAll(splitPane);





        //Allows user to add two files, using getFile to get file path
        getFile1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chosenFile = chooser.showOpenDialog(primaryStage);
                pathFile1 = Path.of(String.valueOf(chosenFile));
                file1Name = String.valueOf(chosenFile);

                file1Context =Methods.readFile(file1Name);
                file1Text.setText(file1Context);
            }
        });

        getFile2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                chosenFile = chooser.showOpenDialog(primaryStage);
                pathFile2 = Path.of(String.valueOf(chosenFile));
                file2Name = String.valueOf(chosenFile);

                file2Context =Methods.readFile(file2Name);
                file2Text.setText(file2Context);
            }
        });


        //Compare files and displays difference by showing the lines that are different
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

}