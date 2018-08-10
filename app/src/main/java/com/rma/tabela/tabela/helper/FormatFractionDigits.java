package com.rma.tabela.tabela.helper;

import java.text.NumberFormat;

/**
 * Created by Renato on 04/08/2018.
 */

public class FormatFractionDigits {

    private static FormatFractionDigits instance = new FormatFractionDigits();

    //private FormatFractionDigits() {
    //}

    /*public static FormatFractionDigits getInstance(){
        return instance;
    }*/

    public static double format0digit(double num){
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(0);
        return Double.valueOf(format.format(num));
    }

    public static double format1digit(double num){
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(1);
        return Double.valueOf(format.format(num));
    }

    public static double format2digits(double num){
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);
        return Double.valueOf(format.format(num));
    }

}
