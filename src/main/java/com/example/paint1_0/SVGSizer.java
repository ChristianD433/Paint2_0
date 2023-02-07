package com.example.paint1_0;

import java.text.DecimalFormat;

public class SVGSizer {
    public static String resizeSVG(String svg, double multiplierX, double multiplierY) {
        StringBuilder newSVG = new StringBuilder();
        StringBuilder number = new StringBuilder();
        boolean isX = true;
        for (char c : svg.toCharArray())
            if (Character.isDigit(c) || c == '.') {
                number.append(c);
                isX = true;
            } else {
                if (number.length() > 0) {
                    newSVG.append(Float.parseFloat(number.toString()) * (isX ? multiplierX : multiplierY));
                    isX = !isX;
                    number = new StringBuilder();
                }
                newSVG.append(c);
            }
        return newSVG.toString();
    }
}
