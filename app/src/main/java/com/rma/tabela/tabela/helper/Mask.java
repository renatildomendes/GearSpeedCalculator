package com.rma.tabela.tabela.helper;

import android.provider.MediaStore;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

/**
 * Created by Renato on 05/08/2018.
 */

public class Mask {

    private  static Mask instance;

    private Mask() {
    }

    public static synchronized Mask getInstance(){
        if(instance==null){
            instance = new Mask();
        }
        return instance;
    }

    public void setMaskForDifferential(EditText view){
        SimpleMaskFormatter simpleMaskRatio = new SimpleMaskFormatter("N,NNN");
        MaskTextWatcher maskDiffRatio = new MaskTextWatcher(view,simpleMaskRatio);
        view.addTextChangedListener(maskDiffRatio);
    }

    public void setMaskForTireHeight(EditText view){
        SimpleMaskFormatter simpleMaskRatio = new SimpleMaskFormatter("NNN");
        MaskTextWatcher maskDiffRatio = new MaskTextWatcher(view,simpleMaskRatio);
        view.addTextChangedListener(maskDiffRatio);
    }

    public void setMaskForRevLimit(EditText view){
        SimpleMaskFormatter simpleMaskRatio = new SimpleMaskFormatter("NNNNN");
        MaskTextWatcher maskDiffRatio = new MaskTextWatcher(view,simpleMaskRatio);
        view.addTextChangedListener(maskDiffRatio);
    }

    public void setMaskForRimSize(EditText view){
        SimpleMaskFormatter simpleMaskRatio = new SimpleMaskFormatter("NN");
        MaskTextWatcher maskDiffRatio = new MaskTextWatcher(view,simpleMaskRatio);
        view.addTextChangedListener(maskDiffRatio);
    }

}
