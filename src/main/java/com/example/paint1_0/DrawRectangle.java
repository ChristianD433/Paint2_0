package com.example.paint1_0;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class DrawRectangle extends DrawShape {
    protected Double x2 = 0D;
    protected Double y2 = 0D;
    boolean esRelleno = false;

    public DrawRectangle(GraphicsContext graphicsContext) {
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

            graphicsContext.setStroke(colorPicker.getValue());
            graphicsContext.setLineWidth(Double.parseDouble(bSize.getText()));


            if (esRelleno) {
                graphicsContext.setFill(colorPicker.getValue());
                graphicsContext.fillRect(startX, startY, width, height);
                graphicsContext.setFill(colorPicker.getValue());
                esRelleno = false;
            } else {
                graphicsContext.setFill(Color.WHITE);
                graphicsContext.strokeRect(startX, startY, width, height);
                graphicsContext.setFill(colorPicker.getValue());
            }
        });
    }

    @Override
    public void release() {
        graphicsContext.getCanvas().setOnMousePressed(null);
        graphicsContext.getCanvas().setOnMouseReleased(null);
    }
}
