package com.example.madaim.ex8good;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

public class MainActivity extends Activity {
        Button go;
        EditText fer;
        EditText cel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go=(Button)findViewById(R.id.button);
        fer=(EditText)findViewById(R.id.editText2);
        cel=(EditText)findViewById(R.id.editText);
        go.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(MainActivity.this,SeccondActivity.class);
                startActivity(i);
             }
        });



        fer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                        if(s.toString().equals("")) {
                            go.setEnabled(false);
                        }
                            else{
                                go.setEnabled(true);
                            }

            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        cel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence d, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence d, int i, int i1, int i2) {
                if(d.toString().equals("")) {
                    go.setEnabled(false);
                }
                else{
                    go.setEnabled(true);
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {}});


     }
        public void RadioButtonclicled(View d){
        RadioButton chk=(RadioButton)findViewById(R.id.radioButton);
        RadioButton calc=(RadioButton)findViewById(R.id.radioButton2);


        }
}
