package com.rma.tabela.tabela.car.data;

import android.content.Context;
import android.graphics.Color;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.rma.tabela.tabela.helper.Converter;
import com.rma.tabela.tabela.helper.Mask;
import com.rma.tabela.tabela.helper.Settings;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.view.Gravity.CENTER;
import static android.view.Gravity.END;

/**
 * Created by Renato on 08/08/2018.
 */

public class Data {
    private TableLayout gearTable;

    public Data() {
    }

    public TableLayout getGearTable() {
        return gearTable;
    }

    public void setGearTable(TableLayout gearTable) {
        this.gearTable = gearTable;
    }

    public void createTableRow(Context context, boolean singleDiff) {
        TableRow row = new TableRow(context);
        row.setGravity(CENTER);

        Mask mask = Mask.getInstance();

        TextView tv1 = new TextView(context);//shows which gear is setting
        tv1.setTextColor(Color.BLACK);
        if(gearTable.getChildCount()==0) tv1.setText("R:");
        else tv1.setText(gearTable.getChildCount()+":");
        tv1.setTextSize(Settings.fontSize);
        tv1.setWidth(55);

        EditText et1 = new EditText(context);
        et1.setInputType(TYPE_CLASS_NUMBER);
        et1.setTextColor(Color.BLACK);
        et1.setTextSize(Settings.fontSize);
        et1.setWidth(120);
        mask.setMaskForDifferential(et1);

        TextView tv2 = new TextView(context);//shows which gear is setting
        tv2.setTextColor(Color.BLACK);
        tv2.setText("dif:");
        tv2.setTextSize(Settings.fontSize);
        tv2.setWidth(55);

        EditText et2 = new EditText(context);
        et2.setInputType(TYPE_CLASS_NUMBER);
        et2.setTextColor(Color.BLACK);
        et2.setTextSize(Settings.fontSize);
        et2.setWidth(120);
        et2.setFocusable(!singleDiff);
        et2.setFocusableInTouchMode(!singleDiff);
        mask.setMaskForDifferential(et2);

        TextView tv3 = new TextView(context);//shows which gear is setting
        tv3.setTextColor(Color.BLACK);
        tv3.setHint("(000,0)");
        tv3.setGravity(END);
        tv3.setTextSize(Settings.fontSize);
        tv3.setWidth(120);

        TextView tv4 = new TextView(context);//shows which gear is setting
        tv4.setTextColor(Color.BLACK);
        tv4.setHint("000,0 ZZZ");
        tv4.setGravity(END);
        tv4.setTextSize(Settings.fontSize);
        tv4.setWidth(200);

        row.addView(tv1);
        row.addView(et1);
        row.addView(tv2);
        row.addView(et2);
        row.addView(tv3);
        row.addView(tv4);

        gearTable.addView(row);
    }

    public void deleteTableRow() {
        if(gearTable.getChildCount()<=0){
            return;
        }
        gearTable.removeViewAt(gearTable.getChildCount()-1);
    }

    public double getDiffInTableRow(int i){
        TableRow tableRow = (TableRow) gearTable.getChildAt(i);
        EditText editText = (EditText) tableRow.getVirtualChildAt(3);
        return Converter.toDouble(editText);
    }
}
