package com.rma.tabela.tabela.car.parts;

/**
 * Created by Renato on 08/08/2018.
 */

public class StreetTire extends Tire {


    public StreetTire(double width, double height, double wheelSize) {
        super.width = width;
        super.height = height;
        super.wheelSize = wheelSize;
    }

    @Override
    public boolean isStreetTire() {
        return true;
    }

    @Override
    public double getTireCircunference() {
        double circunference = ((((super.wheelSize*2.54)+ (double)(super.width*super.height)/1000 *2)* Math.PI)/(double)100) * 0.9702;
        return circunference;
    }
}
