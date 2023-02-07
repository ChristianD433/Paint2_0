package com.example.paint1_0;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SVGDimensions {

    public static double[] getSVGDimensions(String svgPath) {
        double[] dimensions = new double[2];
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;
        double x = 0d;
        double y = 0d;
        boolean isX = true;
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(svgPath);
        while (matcher.find()) {
            String number = matcher.group();
            if (!number.isEmpty()) {
                if (isX) {
                    x = Double.parseDouble(number);
                    minX = (int) Math.min(minX, x);
                    maxX = (int) Math.max(maxX, x);
                } else {
                    y = Double.parseDouble(number);
                    minY = (int) Math.min(minY, y);
                    maxY = (int) Math.max(maxY, y);
                }
                isX = !isX;
            }
        }
        dimensions[0] = maxX - minX;
        dimensions[1] = maxY - minY;
        return dimensions;
    }

}