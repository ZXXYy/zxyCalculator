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
    ArrayList<TextView> preTextViews = new ArrayList<TextView>();
    Button buttondot, buttonC,buttonperc, buttonback, buttonadd, buttonsub, buttonmul,buttondiv,buttoneq;
    String exp = "";
    boolean equalPushed = false;
    Calculate cal = new Calculate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preTextViews.add((TextView)findViewById(R.id.preTextView1));
        preTextViews.add((TextView)findViewById(R.id.preTextView2));
        preTextViews.add((TextView)findViewById(R.id.preTextView3));
        preTextViews.add((TextView)findViewById(R.id.preTextView4));
        preTextViews.add((TextView)findViewById(R.id.preTextView5));
        preTextViews.add((TextView)findViewById(R.id.preTextView6));

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
                    if(equalPushed){
                        exp = "";
                        textView.setText("");
                        equalPushed = false;
                    }
                    if(exp.length()<21)
                    {
                        exp += Integer.toString(finalI);
                        textView.setText(textView.getText()+Integer.toString(finalI));
                    }
                }
            });
        }

        // set the auxiliary button onClick method
        buttonback.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                if (exp.length() > 1)
                    exp = exp.substring(0, (exp.length() - 1));
                else
                    exp = "";
                textView.setText(exp);
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp = "";
                textView.setText(exp);
            }
        });

        buttonperc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exp += "%";
                textView.setText(textView.getText() + "%");
            }
        });


        // set calculator's operations onClick method
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(equalPushed) equalPushed = false;
                exp += "+";
                textView.setText(textView.getText() + "+");
            }
        });

        buttonsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(equalPushed) equalPushed = false;
                exp += "-";
                textView.setText(textView.getText() + "-");
            }
        });

        buttonmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(equalPushed) equalPushed = false;
                exp += "*";
                textView.setText(textView.getText() + "×");
            }
        });

        buttondiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(equalPushed) equalPushed = false;
                exp += "/";
                textView.setText(textView.getText() + "÷");
            }
        });

        // set get result operation
        buttoneq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String preExp = exp;
                try{
                    exp = cal.calExp(exp);
                }catch(Exception e){
                    e.printStackTrace();
                    exp = "Error";
                }
                setPreTextView(preExp);
                textView.setText(exp);
                equalPushed = true;
            }
        });

        buttondot.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(textView.getText().length()==0){
                    textView.setText("0.");
                    exp = "0." + exp;
                }
                else{
                    textView.setText(textView.getText()+".");
                    exp += ".";
                }
            }
        });


    }

    public void setPreTextView(String preExp){
        for(int i = preTextViews.size()-1;i>=1;i--){
            preTextViews.get(i).setText(preTextViews.get(i-1).getText());
        }
        preTextViews.get(0).setText(preExp);
    }
}