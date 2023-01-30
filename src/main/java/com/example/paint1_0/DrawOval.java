package com.example.paint1_0;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

public class DrawOval extends DrawShape {
    private boolean esRelleno = false;
    protected Double x2 = 0D;
    protected Double y2 = 0D;

    public DrawOval(GraphicsContext graphicsContext) {
        super(graphicsContext);
    }

    @Override
    public void draw(ColorPicker colorPicker, TextField bSize) {
        bSize.setOnAction(e -> size = Double.parseDouble(bSize.getText()));
        graphicsContext.getCanvas().setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getX();
            y = mouseEvent.getY();
            if (mouseEvent.isSecondaryButtonDown()) esRelleno = true;
        });


        graphicsContext.getCanvas().setOnMouseReleased(mouseEvent -> {
            x2 = mouseEvent.getX();
            y2 = mouseEvent.getY();

            double width = Math.abs(x - x2);
            double height = Math.abs(y - y2);
            double startX = Math.min(x, x2);
            double startY = Math.min(y, y2);

            graphicsContext.setLineWidth(Double.parseDouble(bSize.getText()));
            graphicsContext.setStroke(colorPicker.getValue());
            if (esRelleno) {
                graphicsContext.setFill(colorPicker.getValue());
                graphicsContext.fillOval(startX, startY, width, height);

                esRelleno = false;
            } else {
                graphicsContext.strokeOval(startX, startY, width, height);
            }

        });

    }

    @Override
    public void release() {
        graphicsContext.getCanvas().setOnMousePressed(null);
        graphicsContext.getCanvas().setOnMouseReleased(null);
    }
}
