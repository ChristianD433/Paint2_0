package com.example.paint1_0;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;

import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button buttonFreeDraw;
    @FXML
    private Button buttonRectangleDraw;

    @FXML
    private Button buttonFullHexagonDraw;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private TextField bSize;
    @FXML
    private Canvas canvas;

    private DrawShape currentDraw = null;

    private ArrayList<DrawShape> listaDraws = new ArrayList<>();

    private int eleccion = 0;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        colorPicker.setValue(Color.BLACK);
        initializeListeners();
        initializeOperations(canvas.getGraphicsContext2D());
        cambioDibujo(0);
    }

    public void initializeOperations(GraphicsContext graphicsContext) {
        DrawRectangle drawRectangle = new DrawRectangle(graphicsContext);
        DrawFree drawFree = new DrawFree(graphicsContext);
        DrawHexagon drawHexagon = new DrawHexagon(graphicsContext);
        listaDraws.add(drawFree);
        listaDraws.add(drawRectangle);
        listaDraws.add(drawHexagon);
    }

    public void initializeListeners() {
        buttonFreeDraw.setOnAction(actionEvent -> {
            cambioDibujo(0);
        });
        buttonRectangleDraw.setOnAction(actionEvent -> {
            cambioDibujo(1);
        });
        buttonFullHexagonDraw.setOnAction(actionEvent -> {
            cambioDibujo(2);
        });
    }

    public void cambioDibujo(int opcionDibujo) {
        if (currentDraw != null) currentDraw.release();
        currentDraw = listaDraws.get(opcionDibujo);
        currentDraw.draw(colorPicker, bSize);
    }

    @FXML
    public void newcanvas(ActionEvent e) {
        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}