package com.example.paint1_0;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

public class DrawTriangle extends DrawShape {
    public DrawTriangle(GraphicsContext graphicsContext) {
        super(graphicsContext);
    }

    @Override
    public void draw(ColorPicker colorPicker, TextField bSize) {

        Canvas canvas = graphicsContext.getCanvas();
        canvas.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getX();
            y = mouseEvent.getY();
            if (mouseEvent.isSecondaryButtonDown()) esRelleno = true;
        });

        canvas.setOnMouseReleased(mouseEvent -> {
            x2 = mouseEvent.getX();
            y2 = mouseEvent.getY();
            double[] xPoints = {x, x + (x2 - x) / 2, x2};
            double[] yPoints = {y2, y, y2};
            if (esRelleno) {
                graphicsContext.setFill(colorPicker.getValue());
                graphicsContext.fillPolygon(xPoints, yPoints, 3);
                esRelleno = false;
            } else {
                size = Double.parseDouble(bSize.getText());
                graphicsContext.setLineWidth(size);
                graphicsContext.setStroke(colorPicker.getValue());
                graphicsContext.strokePolygon(xPoints, yPoints, 3);
            }

        });
    }

    @Override
    public void release() {
        graphicsContext.getCanvas().setOnMousePressed(null);
        graphicsContext.getCanvas().setOnMouseReleased(null);
    }

}
