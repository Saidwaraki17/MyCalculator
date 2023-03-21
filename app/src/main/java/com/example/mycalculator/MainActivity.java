package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result_tv, solution_tv;
    MaterialButton buttonC,buttondiv,buttonopbracket,buttonclbracket;
    MaterialButton button7,button8,button9,buttonmul;
    MaterialButton button4,button5,button6,buttonsub;
    MaterialButton button1, button2,button3,buttonadd;
    MaterialButton buttonAc,button0,buttonfulstop,buttonequal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result_tv = findViewById(R.id.result_id);
        solution_tv = findViewById(R.id.solution_id);

        assignId(buttonC,R.id.button_c);
        assignId(button1,R.id.button_1);
        assignId(button0,R.id.button_0);
        assignId(button3,R.id.button_3);
        assignId(button2,R.id.button_2);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7 ,R.id.button_7);
        assignId(button8 ,R.id.button_8);
        assignId(button9 ,R.id.button_9);
        assignId(buttonsub ,R.id.button_subtract);
        assignId(buttonadd ,R.id.button_add);
        assignId(buttonmul,R.id.button_multi);
        assignId(buttondiv ,R.id.button_divide);
        assignId(buttonopbracket ,R.id.button_open_bracket);
        assignId(buttonclbracket,R.id.button_closed_bracket);
        assignId(buttonequal,R.id.button_equals);
        assignId(buttonfulstop ,R.id.button_full_stop);
        assignId(buttonAc ,R.id.button_ac);

    }
    public void assignId(MaterialButton mb,int id){
        mb = findViewById(id);
        mb.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String ButtonText = button.getText().toString();
        String data = solution_tv.getText().toString();

        if (ButtonText.equals("AC")){
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }

        if (ButtonText.equals("=")){
            solution_tv.setText(result_tv.getText());
            return;
        }

        if (ButtonText.equals("C")){
            data = data.substring(0,data.length()-1);
        }
        else{
            data = data + ButtonText;
        }

        String finalResult = getResult(data);

        if(!finalResult.equals("Err")){
            result_tv.setText(finalResult);
        }
    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String FinalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (FinalResult.endsWith(".0")){
                FinalResult.replace(".0","");
            }
            return FinalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}