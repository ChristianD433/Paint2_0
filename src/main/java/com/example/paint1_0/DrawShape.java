package com.example.paint1_0;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

public abstract class DrawShape {
    protected GraphicsContext graphicsContext;
    protected double x = 0;
    protected double y = 0;
    protected double size = 12;


    public DrawShape(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;

    }

    public abstract void draw(ColorPicker colorPicker, TextField bSize);

    public abstract void release();

}
