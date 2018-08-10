package com.rma.tabela.tabela.helper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Created by Renato on 08/08/2018.
 */

public class Watcher {

    public static void setSingleDiffAndTextWatcher(TableLayout gearTable,EditText diffGearET,boolean dualClutchCB_isChecked){
        for(int i=0;i<gearTable.getChildCount();i++){
            TableRow gearRow = (TableRow) gearTable.getChildAt(i);
            final EditText diffEditText = (EditText) gearRow.getVirtualChildAt(3);
            diffEditText.setFocusable(dualClutchCB_isChecked);
            diffEditText.setFocusableInTouchMode(dualClutchCB_isChecked);

            TextWatcher inputTextWatcher = new TextWatcher() {
                public void afterTextChanged(Editable s) {
                    diffEditText.setText(s.toString());
                }
                public void beforeTextChanged(CharSequence s, int start, int count, int after){
                }
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }
            };
            if(!dualClutchCB_isChecked){
                diffGearET.addTextChangedListener(inputTextWatcher);
                diffEditText.setText(diffGearET.getText().toString());
            }else{
                diffGearET.removeTextChangedListener(inputTextWatcher);
            }
        }
    }

}
