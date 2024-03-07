package com.example.webmy3.managers;


public class Validator {
    public Double validateX(String x) {
        Double returningX = null;
        try {
            double numX = Double.parseDouble(x);
            if (numX >= -5 && numX <= 5) {
                returningX = numX;
            }
            returningX = numX;
        } catch (NumberFormatException ignored) {}
        return returningX;
    }

    public Double validateY(String y) {
        Double returningY = null;
        try {
            double numY = Double.parseDouble(y);
            if (numY >= -5 && numY <= 3) {
                returningY = numY;
            }
            returningY = numY;
        } catch (NumberFormatException ignored) {}
        return returningY;
    }

    public Double validateR(String r) {
        Double returningR = null;
        try {
            double numR = Double.parseDouble(r);
            if (numR >= 2 && numR <= 5) {
                returningR = numR;
            }
            returningR = numR;
        } catch (NumberFormatException ignored) {}
        return returningR;
    }
}
