package com.example.paint1_0;


import javafx.geometry.Bounds;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.SVGPath;


public class DrawFree extends DrawShape {
    double lastX = 0;
    double lastY = 0;

    public DrawFree(GraphicsContext graphicsContext) {
        super(graphicsContext);
    }

    @Override
    public void draw(ColorPicker colorPicker, TextField bSize) {

        graphicsContext.getCanvas().setOnMousePressed(mouseEvent -> {
            lastX = mouseEvent.getX();
            lastY = mouseEvent.getY();
        });

        graphicsContext.getCanvas().setOnMouseDragged(mouseEvent -> {
            size = Double.parseDouble(bSize.getText());
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            graphicsContext.setStroke(colorPicker.getValue());
            graphicsContext.setLineWidth(size);
            graphicsContext.strokeLine(lastX, lastY, x, y);
            lastX = x;
            lastY = y;
        });
    }


    @Override
    public void release() {
        graphicsContext.getCanvas().setOnMouseDragged(null);
        graphicsContext.getCanvas().setOnMousePressed(null);
    }

}
