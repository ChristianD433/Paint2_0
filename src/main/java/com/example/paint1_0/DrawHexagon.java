package com.example.paint1_0;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

public class DrawHexagon extends DrawShape {
    public DrawHexagon(GraphicsContext graphicsContext) {
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
            double height = Math.abs(x2 - x);

            double[] xPoints = new double[6];
            double[] yPoints = new double[6];

            for (int i = 0; i < 6; i++) {

                xPoints[i] = ((x+x2)/2) + height  * Math.cos(Math.PI / 3 * i + Math.PI / 6);
                yPoints[i] = ((y+y2)/2) + height  * Math.sin(Math.PI / 3 * i + Math.PI / 6);
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
