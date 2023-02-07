
package com.example.paint1_0;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

public class DrawHeart extends DrawShape {
    private final String svgPathVacio = "M433.601,67.001c-24.7-24.7-57.4-38.2-92.3-38.2s-67.7,13.6-92.4,38.3l-12.9,12.9l-13.1-13.1" + "c-24.7-24.7-57.6-38.4-92.5-38.4c-34.8,0-67.6,13.6-92.2,38.2c-24.7,24.7-38.3,57.5-38.2,92.4c0,34.9,13.7,67.6,38.4,92.3" + "l187.8,187.8c2.6,2.6,6.1,4,9.5,4c3.4,0,6.9-1.3,9.5-3.9l188.2-187.5c24.7-24.7,38.3-57.5,38.3-92.4" + "C471.801,124.501,458.301,91.701,433.601,67.001z M414.401,232.701l-178.7,178l-178.3-178.3c-19.6-19.6-30.4-45.6-30.4-73.3" + "s10.7-53.7,30.3-73.2c19.5-19.5,45.5-30.3,73.1-30.3c27.7,0,53.8,10.8,73.4,30.4l22.6,22.6c5.3,5.3,13.8,5.3,19.1,0l22.4-22.4" + "c19.6-19.6,45.7-30.4,73.3-30.4c27.6,0,53.6,10.8,73.2,30.3c19.6,19.6,30.3,45.6,30.3,73.3" + "C444.801,187.101,434.001,213.101,414.401,232.701z";

    private final String svgPathRelleno = "M874.3199729919434 623.1900215148926c-1.8900000303983688-3.8999998569488525-126.81000709533691 -260.10000228881836-343.199987411499-77.69999742507935C228.90000343322754 863.8500022888184 868.2000160217285 1297.6800155639648" + " 874.2899894714355 1301.8200302124023c6.119999885559082-4.139999896287918 645.3900146484375-438.0000114440918 343.199987411499 -756.3899803161621-216.42000675201416-182.39999771118164-341.3099956512451 73.80000114440918-343.199987411499 77.69999742507935z";

    private String svgCaraFeliz = "m35,0 v35 h-35 v30 h35 v35 h30 v-35 h35 v-30 h-35 v-35 z";

    public DrawHeart(GraphicsContext graphicsContext) {
        super(graphicsContext);
    }

    @Override
    public void draw(ColorPicker colorPicker, TextField bSize) {
        Canvas canvas = graphicsContext.getCanvas();
        release();
        canvas.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getX();
            y = mouseEvent.getY();
            esRelleno = mouseEvent.isSecondaryButtonDown();
        });

        canvas.setOnMouseReleased(mouseEvent -> {
            x2 = mouseEvent.getX();
            y2 = mouseEvent.getY();

            String svgPath = esRelleno ? svgPathRelleno : svgPathVacio;
            double[] dimesiones = SVGDimensions.getSVGDimensions(svgPath);
            double relacionX = Math.abs(x - x2) / dimesiones[0];
            double relacionY = Math.abs(y - y2) / dimesiones[1];

            double startX = Math.min(x, x2);
            double startY = Math.min(y, y2);

            graphicsContext.beginPath();
            graphicsContext.translate(startX, startY);
            graphicsContext.appendSVGPath(SVGSizer.resizeSVG(svgPath, relacionX, relacionY));
            graphicsContext.setFill(colorPicker.getValue());
            graphicsContext.fill();
            graphicsContext.translate(-startX, -startY);
            graphicsContext.closePath();
        });
    }

    @Override
    public void release() {
        graphicsContext.getCanvas().setOnMousePressed(null);
        graphicsContext.getCanvas().setOnMouseReleased(null);

    }
}
