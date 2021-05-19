package com.example.zxycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ArrayList<Button> ButtonNumbs = new ArrayList<Button>();
    Button buttondot, buttonC,buttonperc, buttonback, buttonadd, buttonsub, buttonmul,buttondiv,buttoneq;
    String exp = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        ButtonNumbs.add((Button) findViewById(R.id.button0));
        ButtonNumbs.add((Button) findViewById(R.id.button1));
        ButtonNumbs.add((Button) findViewById(R.id.button2));
        ButtonNumbs.add((Button) findViewById(R.id.button3));
        ButtonNumbs.add((Button) findViewById(R.id.button4));
        ButtonNumbs.add((Button) findViewById(R.id.button5));
        ButtonNumbs.add((Button) findViewById(R.id.button6));
        ButtonNumbs.add((Button) findViewById(R.id.button7));
        ButtonNumbs.add((Button) findViewById(R.id.button8));
        ButtonNumbs.add((Button) findViewById(R.id.button9));

        buttondot = (Button) findViewById(R.id.buttondot);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonperc = (Button) findViewById(R.id.buttonperc);
        buttonback = (Button) findViewById(R.id.buttonback);
        buttonadd = (Button) findViewById(R.id.buttonadd);
        buttonsub = (Button) findViewById(R.id.buttonsub);
        buttonmul = (Button) findViewById(R.id.buttonmul);
        buttondiv = (Button) findViewById(R.id.buttondiv);
        buttoneq = (Button) findViewById(R.id.buttoneq);

        // set the numbers onClick method
        for(int i = 0;i<ButtonNumbs.size();i++){
            int finalI = i;
            ButtonNumbs.get(i).setOnClickListener(new View.OnClickListener(){
                public void onClick(View view)
                {
                    if(exp.length()<10)
                    {
                        exp += Integer.toString(finalI);
                        textView.setText(textView.getText() + Integer.toString(finalI));
                    }
                }
            });
        }


    }
}