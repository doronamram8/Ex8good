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
import android.widget.Toolbar;

public class MainActivity extends Activity {
    EditText ed;
    EditText ed2;
    Button go;
    final int CHECK = 1;
    RadioButton cal;
    RadioButton ch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar tb=(Toolbar)findViewById(R.id.toolbar);
        tb.setLogo(R.mipmap.ic_launcher);
        setActionBar(tb);
        ed = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);
        cal = (RadioButton) findViewById(R.id.radioButton2);
        ch = (RadioButton) findViewById(R.id.radioButton);
        go = (Button) findViewById(R.id.button);
        go.setEnabled(false);
        ed.setEnabled(false);
        ed2.setEnabled(false);
        ed.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(cal != null && cal.isChecked() && ed.getText().toString().length() >0)
                {
                    ed2.setEnabled(false);
                    go.setEnabled(true);
                }
                else if(cal != null && cal.isChecked() && ed.getText().toString().length() == 0)
                {
                    ed2.setEnabled(true);
                    go.setEnabled(false);
                }
                else if(ch != null && ch.isChecked()  && ed.getText().toString().length() >0 && ed2.getText().toString().length() >0)
                {
                    go.setEnabled(true);
                }
                else if(ch != null && ch.isChecked()  && (ed.getText().toString().length() ==0 || ed2.getText().toString().length() ==0))
                {
                    go.setEnabled(false);
                }
                else
                {
                    go.setEnabled(false);
                }
            }
        });

        ed2.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(cal != null && cal.isChecked() && ed2.getText().toString().length() >0)
                {
                    ed.setEnabled(false);
                    go.setEnabled(true);
                }
                else if(cal != null && cal.isChecked() && ed.getText().toString().length() == 0)
                {
                    ed.setEnabled(true);
                    go.setEnabled(false);
                }
                else if(ch != null && ch.isChecked()  && ed.getText().toString().length() >0 && ed2.getText().toString().length() >0)
                {
                    go.setEnabled(true);
                }
                else if(ch != null && ch.isChecked()  && (ed.getText().toString().length() ==0 || ed2.getText().toString().length() ==0))
                {
                    go.setEnabled(false);
                }
                else
                {
                    go.setEnabled(false);
                }
            }
        });

    }

    public void onRadioButtonClicked(View view) {
         boolean checked = ((RadioButton) view).isChecked();
        if(checked) {
            ed.setEnabled(true);
            ed2.setEnabled(true);
        }
        else
        {
            ed.setEnabled(false);
            ed2.setEnabled(false);
        }
    }
    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, SeccondActivity.class);
        EditText editText1 = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        if(editText1.getText() != null) {
            String val1 = editText1.getText().toString();
            intent.putExtra("far", val1);
        }
        else
        {
            intent.putExtra("far", "");
        }
        if(editText1.getText() != null) {
            String val2 = editText2.getText().toString();
            intent.putExtra("cel", val2);
        }
        else
        {
            intent.putExtra("cel", "");
        }




        if(ch.isChecked())
        {
            intent.putExtra("check", "rch");
        }
        else
        {
            intent.putExtra("check", "rca");
        }
        startActivityForResult(intent, CHECK);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(!ch.isChecked()) {
            EditText editText;
            if (data.getStringExtra("empty").compareTo("cel") == 0) {
                editText = (EditText) findViewById(R.id.editText2);
            } else {
                editText = (EditText) findViewById(R.id.editText);
            }

            if (requestCode == CHECK) {
                editText.setText(data.getStringExtra("back"));
            }
        }

    }
}
