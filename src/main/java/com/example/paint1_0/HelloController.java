package com.example.paint1_0;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField bSize;
    @FXML
    private Canvas canvas;

    boolean toolSelected = false;
    GraphicsContext brushTool;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        brushTool = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(e -> {
            if (!bSize.getText().isEmpty()) {
                double size = Double.parseDouble(bSize.getText());
                double x = e.getX() - size / 2;
                double y = e.getY() - size / 2;

                if (toolSelected) {
                    brushTool.setFill(colorPicker.getValue());
                    brushTool.fillRect(x, y, size, size);
                }
            }
        });
    }

    @FXML
    public void newcanvas(ActionEvent e) {
        brushTool.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @FXML
    public void toolselected(ActionEvent e) {
        toolSelected = true;
    }
}