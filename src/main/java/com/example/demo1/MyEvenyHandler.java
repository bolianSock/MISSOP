package com.example.demo1;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
class MyEventHandler {
    private double startX;
    private double startY;

    void eventHandler(javafx.scene.Node node) {

                    node.setOnMousePressed(e -> {
                        startX = e.getSceneX() - node.getTranslateX();
                        startY = e.getSceneY() - node.getTranslateY();
                    });
                    node.setOnMouseDragged(e -> {
                        node.setTranslateX(e.getSceneX() - startX);
                        node.setTranslateY(e.getSceneY() - startY);
                    });
    }
}