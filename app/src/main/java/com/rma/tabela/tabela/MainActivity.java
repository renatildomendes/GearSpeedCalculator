package com.rma.tabela.tabela;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.rma.tabela.tabela.car.Car;
import com.rma.tabela.tabela.car.parts.Tire;
import com.rma.tabela.tabela.helper.Converter;
import com.rma.tabela.tabela.helper.Mask;
import com.rma.tabela.tabela.helper.Watcher;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {

    private EditText revLimitET;
    private TableRow tireTR;
    private EditText tireWidthET;
    private EditText tireHeightET;
    private EditText rimSizeET;
    private Button tireTypeButton;
    private CheckBox dualClutchCB;
    private EditText diffGearET;
    private EditText reductionHubET;
    private EditText lowRangeET;
    private Button addGearButton;
    private Button calculateButton;
    private Button removeGearButton;
    private Button speedUnitButton;
    private Button clearButton;

    private Tire tire;
    private Car car;

    private boolean streetTire = true;
    private static final String PREFERENCE_FILE = "PreferenceFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout gearTable = findViewById(R.id.gearTable);
        car = new Car(this);
        car.setTable(gearTable);

        init();
        createMask();
        getPreferences();

        tireTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    streetTire = !streetTire;
                    setTireTypeButton();
                    tire = Converter.convertTire(tireTR,streetTire);
                    car.setTire(tire);
                    setPreferences();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        dualClutchCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Watcher.setSingleDiffAndTextWatcher(car.getData().getGearTable(), diffGearET, dualClutchCB.isChecked());
                setPreferences();
            }
        });
        addGearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addGear();
                setPreferences();
            }
        });
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    calculate();
                    setPreferences();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        removeGearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                car.deleteGear();
                setPreferences();
            }
        });
        speedUnitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                car.setMetricUnit(!car.isMetricUnit());
                if(car.isMetricUnit()){
                    speedUnitButton.setText("KM/H");
                }else{
                    speedUnitButton.setText("MPH");
                }
                calculate();
                setPreferences();
            }
        });
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
                setPreferences();
            }
        });
    }

    public void addGear(){
        car.createGear(MainActivity.this,dualClutchCB.isChecked());
        Watcher.setSingleDiffAndTextWatcher(car.getData().getGearTable(), diffGearET, dualClutchCB.isChecked());
    }

    public void setPreferences(){
        SharedPreferences sp = getSharedPreferences(PREFERENCE_FILE,0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("revLimit",revLimitET.getText().toString());
        editor.putString("tireWidth",tireWidthET.getText().toString());
        editor.putString("tireHeight",tireHeightET.getText().toString());
        editor.putString("rimSize",rimSizeET.getText().toString());
        editor.putString("primaryDiff",diffGearET.getText().toString());
        editor.putString("reductionHub",reductionHubET.getText().toString());
        editor.putString("lowRange",lowRangeET.getText().toString());
        editor.putBoolean("streetTire",streetTire);
        editor = car.getGears(editor);
        editor.commit();
    }

    public void getPreferences(){
        int i=0;
        SharedPreferences sp = getSharedPreferences(PREFERENCE_FILE,0);
        revLimitET.setText(sp.getString("revLimit",""));
        tireWidthET.setText(sp.getString("tireWidth",""));
        tireHeightET.setText(sp.getString("tireHeight",""));
        rimSizeET.setText(sp.getString("rimSize",""));
        diffGearET.setText(sp.getString("primaryDiff",""));
        reductionHubET.setText(sp.getString("reductionHub",""));
        lowRangeET.setText(sp.getString("lowRange",""));
        streetTire = sp.getBoolean("streetTire",true);
        setTireTypeButton();
        while(sp.contains(""+i)){
            addGear();
            car.addGearInRow(i,sp.getString(""+i,""));
            car.addDiffInRow(i,sp.getString("D"+i,""));
            i++;
        }
    }

    public void setTireTypeButton(){
        if(streetTire){
            tireTypeButton.setHint("Street");
        }else{
            tireTypeButton.setHint("Racing");
        }
    }

    public void clear(){
        revLimitET.setText("");
        tireWidthET.setText("");
        tireHeightET.setText("");
        rimSizeET.setText("");
        diffGearET.setText("");
        reductionHubET.setText("");
        lowRangeET.setText("");
        car.clearTable();
    }

    public void calculate(){
        tire = Converter.getTire(tireTR,streetTire);
        car.setTire(tire);
        getReductionGears();
        car.calculateSpeedGears(dualClutchCB.isChecked());
    }

    public void init(){
        revLimitET = findViewById(R.id.revLimitEditText);
        tireTR = findViewById(R.id.tireTableRow);
        tireWidthET  = findViewById(R.id.tireWidthEditText);
        tireHeightET = findViewById(R.id.tireHeightEditText);
        rimSizeET  = findViewById(R.id.rimSizeEditText);
        tireTypeButton = findViewById(R.id.tireTypeButton);
        dualClutchCB = findViewById(R.id.singleDiffCheckBox);
        diffGearET = findViewById(R.id.primaryDiffEditText);
        reductionHubET = findViewById(R.id.reductionHubGearEditText);
        lowRangeET = findViewById(R.id.lowRangeEditText);
        addGearButton = findViewById(R.id.addGearButton);
        calculateButton = findViewById(R.id.calculateButton);
        removeGearButton = findViewById(R.id.removeGearButton);
        speedUnitButton = findViewById(R.id.speedUnitButton);
        clearButton = findViewById(R.id.clearButton);
    }

    public void getReductionGears(){
        int revLimit = Converter.toInt(revLimitET);
        car.setRevLimit(revLimit);

        double reductionHub;
        if(reductionHubET.getText().toString().isEmpty()){
            reductionHub = 1;}
        else{
            reductionHub = Converter.toDouble(reductionHubET);}
        car.getReductionGears().setReductionHub(reductionHub);

        double lowRange;
        if(lowRangeET.getText().toString().isEmpty()){
            lowRange = 1;}
        else{
            lowRange = Converter.toDouble(lowRangeET);}
        car.getReductionGears().setLowRange(lowRange);
    }

    public void createMask(){
        Mask mask = Mask.getInstance();
        mask.setMaskForRevLimit(revLimitET);
        mask.setMaskForTireHeight(tireHeightET);
        mask.setMaskForTireHeight(tireWidthET);
        mask.setMaskForRimSize(rimSizeET);
        mask.setMaskForDifferential(diffGearET);
        mask.setMaskForDifferential(reductionHubET);
        mask.setMaskForDifferential(lowRangeET);
    }

}
