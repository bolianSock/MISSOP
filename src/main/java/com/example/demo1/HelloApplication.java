package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.event.EventHandler;

import static java.lang.System.out;
import static java.lang.System.setOut;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.CheckBox;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    static ArrayList< Label > labels = new ArrayList<Label>();
    static int i=0;

    Window window;

    Color lighterDarck = Color.web("#2E3239") ;
    Color mainColor = Color.web("#23262C",1.0);
    @Override
    public void start(Stage stage) throws IOException {
       /* FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent root= (Parent)fxmlLoader.load();*/
        GridPane root = new GridPane();
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(30);
        root.getColumnConstraints().add(column1);
        ColumnConstraints column2= new ColumnConstraints();
        column2.setPercentWidth(70);
        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(100);
        root.getRowConstraints().add(row1);
        root.getColumnConstraints().add(column2);
        root.setGridLinesVisible(true);

        FileInputStream input = new FileInputStream("basket.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);

        Button btn = new Button("", imageView);

        btn.setMaxHeight(80);
        btn.setMaxWidth(50);
        BorderPane mainPane = new BorderPane();
        mainPane.setBottom(btn);
        mainPane.setAlignment(btn,  javafx.geometry.Pos.BOTTOM_LEFT);
        mainPane.setMargin(btn, new Insets(10));
        VBox mainVbox =new VBox();
        mainPane.setTop(mainVbox);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent Event) {
                TextField textField = new TextField("");
                Button addBtn = new Button("готово");
                addBtn.setPrefWidth(80);
                FlowPane root = new FlowPane(50, 50,textField,addBtn);
                Scene textFieldScene = new Scene(root);
                Stage stage =new Stage();
                stage.setScene(textFieldScene);
                stage.setTitle("Добавте задачу в корзину");
                stage.centerOnScreen();
                stage.setWidth(500);
                stage.setHeight(250);
                stage.show();

                addBtn.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent innerEvent) {


                        Label lbl = new Label(textField.getText());
                        labels.add(lbl);
                        mainVbox.setMargin(lbl, new Insets(10,5, 5, 20));
                        mainVbox.getChildren().add(lbl);
                        stage.close();

                    }
                });


            }
        });

        root.add( mainPane, 0, 0);
        MyEventHandler relocate = new MyEventHandler();

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                mainVbox.getChildren().forEach(relocate::eventHandler);}
        };

        mainPane.getTop().addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, eventHandler);


        ColumnConstraints columnIner1 = new ColumnConstraints();
        columnIner1.setPercentWidth(50);
        ColumnConstraints columnIner2 = new ColumnConstraints();
        columnIner2.setPercentWidth(50);
        RowConstraints rowIner1 = new RowConstraints();
        rowIner1.setPercentHeight(50);
        RowConstraints rowIner2 = new RowConstraints();
        rowIner2.setPercentHeight(50);



        GridPane innerGridPane= new GridPane();
        innerGridPane.getColumnConstraints().add(columnIner1);
        innerGridPane.getColumnConstraints().add(columnIner2);
        innerGridPane.getRowConstraints().add(rowIner1);
        innerGridPane.getRowConstraints().add(rowIner2);
        innerGridPane.setGridLinesVisible(true);

        root.add(innerGridPane ,1, 0,2,2);


        Scene scene = new Scene(root,850,800);

        stage.setTitle("Eisenhower-Matrix");
        stage.setScene(scene);
        double height= scene.getHeight();
        double width= scene.getWidth();
        stage.show();
        EventHandler<MouseEvent> innerGridEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                for(Label movinLbl :labels){


                    out.println(height *(double) (3 / 10));
                    if (relocate.getCurrentX() >= (width * (double)(3 / 10))) {
                        CheckBox taskChecBox = new CheckBox(movinLbl.getText());
                        taskChecBox.setSelected(false);
                        if (relocate.getCurrentX() >= width * (double)(13/ 20)) {
                            if (relocate.getCurrentY() >= height * (double) (5 / 10)) {
                                innerGridPane.add(taskChecBox, 0, 0);
                            } else {
                                innerGridPane.add(taskChecBox, 1, 0);
                            }
                            mainVbox.getChildren().remove(movinLbl);

                        } else {
                            innerGridPane.add(taskChecBox, 1, 1);
                            mainVbox.getChildren().remove(movinLbl);
                        }
                    }
                }
            }


        };
        innerGridPane.addEventFilter(MouseEvent.MOUSE_RELEASED, innerGridEventHandler );
    }

    public static void main(String[] args) {
        launch();
    }
}