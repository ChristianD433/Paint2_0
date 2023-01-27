package com.example.paint1_0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private boolean drawingRectangle = false;
    private double x1, y1, x2, y2;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField bSize;
    @FXML
    private Canvas canvas;

    boolean toolSelected = false;
    GraphicsContext graphicsContext;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        printSuelto();
    }

    @FXML
    public void newcanvas(ActionEvent e) {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @FXML
    public void toolselected(ActionEvent e) {
        toolSelected = true;
        drawingRectangle = false;
        printSuelto();
    }

    private void printSuelto() {
        graphicsContext = canvas.getGraphicsContext2D();
        canvas.setOnMouseDragged(mouseEvent -> {
            if (!bSize.getText().isEmpty()) {
                double size = Double.parseDouble(bSize.getText());
                double x = mouseEvent.getX() - size / 2;
                double y = mouseEvent.getY() - size / 2;

                if (toolSelected) {
                    graphicsContext.setFill(colorPicker.getValue());
                    graphicsContext.fillRect(x, y, size, size);
                }
            }
        });

        canvas.setOnMousePressed(mouseEvent -> {
            if (mouseEvent.isPrimaryButtonDown()) {
                x1 = mouseEvent.getX();
                y1 = mouseEvent.getY();
                drawingRectangle = true;
            } else {
                // Store the coordinates of the mouse press
                graphicsContext.beginPath();
                graphicsContext.moveTo(mouseEvent.getX(), mouseEvent.getY());
                graphicsContext.stroke();
            }
        });

        canvas.setOnMouseReleased(mouseEvent -> {
            if (drawingRectangle) {
                x2 = mouseEvent.getX();
                y2 = mouseEvent.getY();
                graphicsContext.setStroke(colorPicker.getValue());
                graphicsContext.setLineWidth(Double.parseDouble(bSize.getText()));
                graphicsContext.strokeRect(x1, y1, x2 - x1, y2 - y1);
                graphicsContext.setFill(Color.WHITE);
                drawingRectangle = false;
            }
        });
    }

    @FXML
    public void printRectangle(ActionEvent e) {
        toolSelected = false;
        drawingRectangle = true;
    }
}