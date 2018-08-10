package com.rma.tabela.tabela.car.parts;

/**
 * Created by Renato on 08/08/2018.
 */

public class RacingTire extends Tire{

    public RacingTire(double width, double height, double wheelSize) {
        super.width = width;
        super.height = height;
        super.wheelSize = wheelSize;
    }

    @Override
    public boolean isStreetTire() {
        return false;
    }

    @Override
    public double getTireCircunference() {
        return 2*Math.PI*(double)(super.height/2)/(double)1000 * 0.9702;
    }
}
