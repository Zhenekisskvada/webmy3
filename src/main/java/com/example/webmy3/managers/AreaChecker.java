package com.example.webmy3.managers;

import java.io.Serializable;

public class AreaChecker implements Serializable {
    public boolean check(double x, double y, double r) {
        return hitRectangle(x, y ,r) || hitTriangle(x, y, r) || hitSector(x, y, r);
    }

    private boolean hitRectangle(double x, double y, double r) {
        return (x <= r/2 && x >= 0) && (-y <= r && y <= 0);
    }

    private boolean hitTriangle(double x, double y, double r) {
        return x <= 0 && y <= 0 && (-y <= (x + r)/2);
    }

    private boolean hitSector(double x, double y, double r) {
        return x <= 0 && y >= 0 && (x * x + y * y <= r * r);
    }
}
