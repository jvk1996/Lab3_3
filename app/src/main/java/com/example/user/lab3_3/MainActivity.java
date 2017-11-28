package com.example.user.lab3_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerAge;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale;
    private CheckBox checkBoxSmoker;
    private TextView textViewPremium;
    private Button buttonCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerAge = (Spinner)findViewById(R.id.spinnerAge);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.age_group, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAge.setOnItemSelectedListener(this);
        spinnerAge.setAdapter(adapter);

        radioGroupGender = (RadioGroup)findViewById(R.id.radioGroupGender);
        radioButtonFemale = (RadioButton)findViewById(R.id.radioButtonFemale);
        radioButtonMale = (RadioButton)findViewById(R.id.radioButtonMale);
        checkBoxSmoker = (CheckBox)findViewById(R.id.checkBoxSmoker);
        textViewPremium = (TextView)findViewById(R.id.textViewPremium);
        buttonCalculate = (Button)findViewById(R.id.buttonCalculate);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"Position " + position, Toast.LENGTH_SHORT).show();
        buttonCalculate.performClick();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calculatePremium(View view){
        int pos;
        int premium=0;
        pos = spinnerAge.getSelectedItemPosition();
        switch (pos){
            case 0:premium=50;
                break;
            case 1:premium = 55;
                break;
            case 2:premium = 60;
                break;
            case 3:premium = 70;
                break;
            case 4:premium = 120;
                break;
            case 5:premium = 160;
                break;
            case 6:premium = 200;
                break;
            case 7:premium = 250;
                break;
        }

        int idGender;
        idGender = radioGroupGender.getCheckedRadioButtonId();

        if(idGender == R.id.radioButtonMale){
            //calc for male
            switch(pos){
                case 2:
                case 5: premium +=50;
                    break;
                case 3:
                case 4: premium += 100;
                    break;
                default: break;
            }
        }
        else if(idGender==R.id.radioButtonFemale){

        }

        if(checkBoxSmoker.isChecked()){
            switch(pos){
                case 3:premium +=100;
                    break;
                case 4:
                case 5: premium +=150;
                    break;
                case 6:
                case 7:premium += 250;
                    break;
                default: break;
            }
        }

        textViewPremium.setText(getString(R.string.premium) + premium);

        //calc insurance premium
    }

    public void onReset(View view){
        textViewPremium.setText(getString(R.string.premium));
        spinnerAge.setSelection(0);
        checkBoxSmoker.setChecked(false);
        radioButtonMale.setChecked(true);
    }
}
