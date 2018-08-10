package com.rma.tabela.tabela.car.parts;

import java.util.ArrayList;

/**
 * Created by Renato on 08/08/2018.
 */

public class ReductionGears {
    private ArrayList<Double> primaryDifferential;
    private double reductionHub;
    private double lowRange;

    public ReductionGears() {
        primaryDifferential = new ArrayList<>();
        this.reductionHub = 1;
        this.lowRange = 1;
    }

    public double getPrimaryDifferential(int i) {
        return primaryDifferential.get(i)*reductionHub;
    }

    public void setPrimaryDifferential(double primaryDifferential,int i) {
        this.primaryDifferential.add(i,primaryDifferential);
    }

    public double getReductionHub() {
        return reductionHub;
    }

    public void setReductionHub(double reductionHub) {
        this.reductionHub = reductionHub;
    }

    public double getLowRange(int i) {
        return primaryDifferential.get(i)*reductionHub*lowRange;
    }

    public void setLowRange(double lowRange) {
        this.lowRange = lowRange;
    }

}
