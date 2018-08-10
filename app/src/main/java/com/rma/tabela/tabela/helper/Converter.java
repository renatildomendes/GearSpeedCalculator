package com.rma.tabela.tabela.helper;

import android.util.Log;
import android.widget.EditText;
import android.widget.TableRow;

import com.rma.tabela.tabela.car.parts.RacingTire;
import com.rma.tabela.tabela.car.parts.StreetTire;
import com.rma.tabela.tabela.car.parts.Tire;

/**
 * Created by Renato on 09/08/2018.
 */

public class Converter {

    private static EditText tireWidthET;
    private static EditText tireHeightET;
    private static EditText rimSizeET;

    private static double tireWidth;
    private static double tireHeight;
    private static double rimSize;
    private static Tire tire;

    public static int toInt(EditText view){
        return Integer.parseInt(view.getText().toString());
    }

    public static double toDouble(EditText view){
        return Double.parseDouble(view.getText().toString().replace(",","."));
    }

    public static Tire convertTire(TableRow tireRow, boolean streetTire){

        tireWidthET = (EditText) tireRow.getVirtualChildAt(1);
        tireHeightET = (EditText) tireRow.getVirtualChildAt(3);
        rimSizeET = (EditText) tireRow.getVirtualChildAt(5);

        if(     !tireWidthET.getText().toString().isEmpty() ||
                !tireHeightET.getText().toString().isEmpty() ||
                !rimSizeET.getText().toString().isEmpty()) {

            tireWidth = toDouble(tireWidthET);
            tireHeight = toDouble(tireHeightET);
            rimSize = toDouble(rimSizeET);

            if (streetTire) {
                tireHeight = (tireHeight - (rimSize * 25.4)) / 2 / tireWidth * 100;
                tireHeight = FormatFractionDigits.format0digit(tireHeight);
                tire = new StreetTire(tireWidth, tireHeight, rimSize);
                tireHeightET.setText(String.valueOf(((int) tireHeight)));
            } else {
                tireHeight = rimSize * 25.4 + (tireWidth * tireHeight / 100 * 2);
                Log.i("TESTE", "" + tireHeight);
                tireHeight = FormatFractionDigits.format0digit(tireHeight);
                tire = new RacingTire(tireWidth, tireHeight, rimSize);
                tireHeightET.setText(String.valueOf(tireHeight));
            }
            return tire;
        }
        return null;
    }

    public static Tire getTire(TableRow tireRow, boolean streetTire){

        tireWidthET = (EditText) tireRow.getVirtualChildAt(1);
        tireHeightET = (EditText) tireRow.getVirtualChildAt(3);
        rimSizeET = (EditText) tireRow.getVirtualChildAt(5);

        if(     !tireWidthET.getText().toString().isEmpty() ||
                !tireHeightET.getText().toString().isEmpty() ||
                !rimSizeET.getText().toString().isEmpty()){

            tireWidth = toDouble(tireWidthET);
            tireHeight = toDouble(tireHeightET);
            rimSize = toDouble(rimSizeET);

            if(streetTire){
                tire = new StreetTire(tireWidth,tireHeight,rimSize);
            }else{
                tire = new RacingTire(tireWidth,tireHeight,rimSize);
            }
            return tire;
        }
        return null;
    }

}
