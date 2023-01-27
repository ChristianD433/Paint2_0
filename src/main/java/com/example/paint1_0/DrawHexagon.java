package com.example.paint1_0;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

public class DrawHexagon extends DrawShape {
    public DrawHexagon(GraphicsContext graphicsContext) {
        super(graphicsContext);
    }

    boolean esRelleno = false;

    @Override
    public void draw(ColorPicker colorPicker, TextField bSize) {

        Canvas canvas = graphicsContext.getCanvas();
        canvas.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getX();
            y = mouseEvent.getY();
            if (mouseEvent.isSecondaryButtonDown()) esRelleno = true;
        });

        canvas.setOnMouseReleased(mouseEvent -> {
            double height = Math.abs(mouseEvent.getX() - x);

            double[] xPoints = new double[6];
            double[] yPoints = new double[6];
            for (int i = 0; i < 6; i++) {
                xPoints[i] = x + height / 0.75d * Math.cos(Math.PI / 3 * i + Math.PI / 6);
                yPoints[i] = y + height / 0.75d * Math.sin(Math.PI / 3 * i + Math.PI / 6);
            }
            if (esRelleno) {
                graphicsContext.setFill(colorPicker.getValue());
                graphicsContext.fillPolygon(xPoints, yPoints, 6);
                esRelleno = false;
            } else {
                size = Double.parseDouble(bSize.getText());
                graphicsContext.setLineWidth(size);
                graphicsContext.setStroke(colorPicker.getValue());
                graphicsContext.strokePolygon(xPoints, yPoints, 6);
            }

        });
    }

    @Override
    public void release() {
        graphicsContext.getCanvas().setOnMousePressed(null);
        graphicsContext.getCanvas().setOnMouseReleased(null);
    }
}
