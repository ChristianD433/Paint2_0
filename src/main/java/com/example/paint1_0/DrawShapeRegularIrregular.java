package com.example.paint1_0;

import javafx.scene.canvas.GraphicsContext;

public abstract class DrawShapeRegularIrregular extends DrawShape {
    private boolean isRegular = true;

    public DrawShapeRegularIrregular(GraphicsContext graphicsContext, boolean isRegular) {
        super(graphicsContext);
        this.isRegular = isRegular;
    }

    public boolean isRegular() {
        return isRegular;
    }
}
