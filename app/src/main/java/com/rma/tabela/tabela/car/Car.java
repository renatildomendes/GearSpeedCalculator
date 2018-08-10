package com.rma.tabela.tabela.car;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.rma.tabela.tabela.R;
import com.rma.tabela.tabela.car.data.Data;
import com.rma.tabela.tabela.car.parts.ReductionGears;
import com.rma.tabela.tabela.car.parts.Tire;
import com.rma.tabela.tabela.helper.Converter;
import com.rma.tabela.tabela.helper.FormatFractionDigits;

/**
 * Created by Renato on 08/08/2018.
 */

public class Car {
    private int revLimit;

    private Tire tire;
    private ReductionGears reductionGears;
    private Data data;
    private Context context;
    private boolean isMetricUnit;
    private String unit;

    public Car(Context context) {
        reductionGears = new ReductionGears();
        data = new Data();
        this.context = context;
        this.isMetricUnit = true;
    }

    public int getRevLimit() {
        return revLimit;
    }

    public void setRevLimit(int revLimit) {
        this.revLimit = revLimit;
    }

    public Tire getTire() {
        return tire;
    }

    public void setTire(Tire tire) {
        this.tire = tire;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setTable(TableLayout table) {
        this.data.setGearTable(table);
    }

    public ReductionGears getReductionGears() {
        return reductionGears;
    }

    public void setReductionGears(ReductionGears reductionGears) {
        this.reductionGears = reductionGears;
    }

    public boolean isMetricUnit() {
        return isMetricUnit;
    }

    public void setMetricUnit(boolean metricUnit) {
        isMetricUnit = metricUnit;
    }

    public void createGear(Context context, boolean singleDiff) {
        this.data.createTableRow(context,singleDiff);
    }

    public void deleteGear() {
        this.data.deleteTableRow();
    }

    public void calculateSpeedGears(boolean dualClutchCB_isChecked){
        setReductionGears();
        for(int i=0;i<data.getGearTable().getChildCount();i++){
            double vMax,v1000,diffValue;
            TableRow gearRow = (TableRow) data.getGearTable().getChildAt(i);
            EditText gearEditText = (EditText) gearRow.getVirtualChildAt(1);
            TextView v1000TextView = (TextView) gearRow.getVirtualChildAt(4);
            TextView vMaxTextView = (TextView) gearRow.getVirtualChildAt(5);
            if(gearEditText.getText().toString().isEmpty()) {
                v1000TextView.setText("");
                vMaxTextView.setText("");
                continue;
            }
            double gearValue = Converter.toDouble(gearEditText);
            if(!dualClutchCB_isChecked){
                //Log.i("CIRCUNFERENCE",gearValue+" "+diffGearRatio+" "+tire.getTireCircunference());
                v1000 = maxSpeedGear(1000,gearValue,reductionGears.getPrimaryDifferential(i),tire.getTireCircunference());
                vMax = maxSpeedGear(revLimit,gearValue,reductionGears.getPrimaryDifferential(i),tire.getTireCircunference());
            }else{
                EditText diffEditText = (EditText) gearRow.getVirtualChildAt(3);
                diffValue = Converter.toDouble(diffEditText);
                v1000 = maxSpeedGear(1000,gearValue,reductionGears.getPrimaryDifferential(i),tire.getTireCircunference());
                vMax = maxSpeedGear(revLimit,gearValue,reductionGears.getPrimaryDifferential(i),tire.getTireCircunference());
            }
            v1000TextView.setText("("+v1000+") ");
            vMaxTextView.setText(String.valueOf(vMax)+unit);
        }
    }

    private void setReductionGears(){
        for(int i=0;i<getData().getGearTable().getChildCount();i++){
            double diff = getData().getDiffInTableRow(i);
            getReductionGears().setPrimaryDifferential(diff,i);
        }
    }

    public void clearTable(){
        for(int i=0;i<data.getGearTable().getChildCount();i++){
            TableRow tableRow = (TableRow) data.getGearTable().getChildAt(i);
            EditText editText = (EditText) tableRow.getVirtualChildAt(1);
            editText.setText("");
        }
    }

    private double maxSpeedGear(int revLimit, double gearRatio, double diffRatio, double effectivecircunference){
        double speedGear;
        if(isMetricUnit){
            speedGear = (revLimit/gearRatio/diffRatio*effectivecircunference/1000*60);
            unit = " km/h";
        }else{
            speedGear = (revLimit/gearRatio/diffRatio*effectivecircunference/1000*60)/1.609344;
            unit = " mph";
        }
        speedGear = FormatFractionDigits.format1digit(speedGear);
        return speedGear;
    }

    public SharedPreferences.Editor getGears(SharedPreferences.Editor editor) {
        for(int i=0;i<data.getGearTable().getChildCount();i++){
            TableRow tableRow = (TableRow) data.getGearTable().getChildAt(i);
            EditText editText = (EditText) tableRow.getVirtualChildAt(1);
            editor.putString(""+i,editText.getText().toString());
            editText = (EditText) tableRow.getVirtualChildAt(3);
            editor.putString("D"+i,editText.getText().toString());
        }
        return editor;
    }

    public void addGearInRow(int i, String string) {
        TableRow tableRow = (TableRow) getData().getGearTable().getChildAt(i);
        EditText editText = (EditText) tableRow.getVirtualChildAt(1);
        editText.setText(string);
    }

    public void addDiffInRow(int i, String string) {
        TableRow tableRow = (TableRow) getData().getGearTable().getChildAt(i);
        EditText editText = (EditText) tableRow.getVirtualChildAt(3);
        editText.setText(string);
    }

}
