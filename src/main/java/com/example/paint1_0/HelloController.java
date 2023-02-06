package com.example.paint1_0;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button buttonFreeDraw;
    @FXML
    private Button buttonRectangleDraw;

    @FXML
    private Button buttonHexagonDraw;
    @FXML
    private Button buttonPerfectCircle;
    @FXML
    private Button buttonOval;

    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField bSize;
    @FXML
    private Canvas canvas;

    private DrawShape currentDraw = null;

    HashMap<Button, DrawShape> hashMapOperaciones = new HashMap<>();


    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorPicker.setValue(Color.BLACK);
        initializeDraws(canvas.getGraphicsContext2D());
    }

    public void initializeDraws(GraphicsContext graphicsContext) {
        DrawRectangle drawRectangle = new DrawRectangle(graphicsContext);
        DrawFree drawFree = new DrawFree(graphicsContext);
        DrawHexagon drawHexagon = new DrawHexagon(graphicsContext);
        DrawPerfectCircle drawPerfectCircle = new DrawPerfectCircle(graphicsContext);
        DrawOval drawOval = new DrawOval(graphicsContext);

        hashMapOperaciones.put(buttonFreeDraw, drawFree);
        hashMapOperaciones.put(buttonRectangleDraw, drawRectangle);
        hashMapOperaciones.put(buttonHexagonDraw, drawHexagon);
        hashMapOperaciones.put(buttonPerfectCircle, drawPerfectCircle);
        hashMapOperaciones.put(buttonOval, drawOval);

        for (Button button : hashMapOperaciones.keySet()) {
            button.setOnAction(actionEvent -> {
                if (currentDraw != null){
                    currentDraw.release();
                }
                currentDraw = hashMapOperaciones.get(button);
                currentDraw.draw(colorPicker, bSize);
            });
        }
    }

    @FXML
    public void newcanvas() {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}