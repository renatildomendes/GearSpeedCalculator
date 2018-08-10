package com.rma.tabela.tabela.car.parts;

/**
 * Created by Renato on 04/08/2018.
 */

public abstract class Tire {
    double width;
    double height;
    double wheelSize;

    Tire() {
    }

    protected Tire(int width, int height, int wheelSize) {
        this.width = width;
        this.height = height;
        this.wheelSize = wheelSize;
    }

    protected double getWidth() {
        return width;
    }

    protected void setWidth(double width) {
        this.width = width;
    }

    protected double getHeight() {
        return height;
    }

    protected void setHeight(double height) {
        this.height = height;
    }

    protected double getWheelSize() {
        return wheelSize;
    }

    protected void setWheelSize(double wheelSize) {
        this.wheelSize = wheelSize;
    }

    public abstract boolean isStreetTire();

    public abstract double getTireCircunference();


}
