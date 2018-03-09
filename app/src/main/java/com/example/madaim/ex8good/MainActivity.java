package com.example.madaim.ex8good;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends Activity implements MyDialog.ResultListener,MyDialog.PercisionListener {
    private int index = 0;
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
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.setLogo(R.mipmap.ic_launcher);
        setActionBar(tb);
        ed = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        cal = (RadioButton) findViewById(R.id.radioButton2);
        ch = (RadioButton) findViewById(R.id.radioButton);
        go = (Button) findViewById(R.id.button);
        go.setEnabled(false);
        ed.setEnabled(false);
        ed2.setEnabled(false);
        ed.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (cal != null && cal.isChecked() && ed.getText().toString().length() > 0) {
                    ed2.setEnabled(false);
                    go.setEnabled(true);
                } else if (cal != null && cal.isChecked() && ed.getText().toString().length() == 0) {
                    ed2.setEnabled(true);
                    go.setEnabled(false);
                } else if (ch != null && ch.isChecked() && ed.getText().toString().length() > 0 && ed2.getText().toString().length() > 0) {
                    go.setEnabled(true);
                } else if (ch != null && ch.isChecked() && (ed.getText().toString().length() == 0 || ed2.getText().toString().length() == 0)) {
                    go.setEnabled(false);
                } else {
                    go.setEnabled(false);
                }
            }
        });

        ed2.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (cal != null && cal.isChecked() && ed2.getText().toString().length() > 0) {
                    ed.setEnabled(false);
                    go.setEnabled(true);
                } else if (cal != null && cal.isChecked() && ed.getText().toString().length() == 0) {
                    ed.setEnabled(true);
                    go.setEnabled(false);
                } else if (ch != null && ch.isChecked() && ed.getText().toString().length() > 0 && ed2.getText().toString().length() > 0) {
                    go.setEnabled(true);
                } else if (ch != null && ch.isChecked() && (ed.getText().toString().length() == 0 || ed2.getText().toString().length() == 0)) {
                    go.setEnabled(false);
                } else {
                    go.setEnabled(false);
                }
            }
        });

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        if (checked) {
            ed.setEnabled(true);
            ed2.setEnabled(true);
        } else {
            ed.setEnabled(false);
            ed2.setEnabled(false);
        }
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, SeccondActivity.class);
        EditText editText1 = (EditText) findViewById(R.id.editText);
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        if (editText1.getText() != null) {
            String val1 = editText1.getText().toString();
            intent.putExtra("far", val1);
        } else {
            intent.putExtra("far", "");
        }
        if (editText1.getText() != null) {
            String val2 = editText2.getText().toString();
            intent.putExtra("cel", val2);
        } else {
            intent.putExtra("cel", "");
        }


        if (ch.isChecked()) {
            intent.putExtra("check", "rch");
        } else {
            intent.putExtra("check", "rca");
        }
        intent.putExtra("percision", Integer.toString(index));
        startActivityForResult(intent, CHECK);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!ch.isChecked()) {
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
        if (ed.getText().length() > 0) {
            ed.setText(String.format("%." + index + "f", Double.parseDouble(ed.getText().toString())));
        }
        if (ed2.getText().length() > 0) {
            ed2.setText(String.format("%." + index + "f", Double.parseDouble(ed2.getText().toString())));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.set:
                Context context = getApplicationContext();
                String text = "setting push";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                MyDialog.newInstance(MyDialog.PRECISION_DIALOG).show(getFragmentManager(), "precisin");
                return true;
            case R.id.help:
                Intent i = new Intent(Intent.ACTION_VIEW);
                String url = "https://en.wikipedia.org/wiki/Conversion_of_units_of_temperature";
                i.setData(Uri.parse(url));
                startActivity(i);

                return true;
            case R.id.exit:
                MyDialog.newInstance(MyDialog.EXIT_DIALOG).show(getFragmentManager(), "Exit Dialog");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putInt("preindex",index);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null) {
             index=savedInstanceState.getInt("preindex");
        }
    }
    @Override
    public void onFinishedDialog(int requestCode, Object results) {
        this.index= (int) results;
        switch (requestCode) {
            case MyDialog.EXIT_DIALOG:
                Toast.makeText(this, "Bye Bye", Toast.LENGTH_LONG).show();
                finish();
                System.exit(0);
            case MyDialog.PRECISION_DIALOG:
                setNewpercision((Integer) results);



        }
    }

    public void setNewpercision(int b) {
        if (ed.getText().length() > 0) {
            double num = Double.parseDouble(ed.getText().toString());
            ed.setText(String.format("%." + b + "f", Double.parseDouble(ed.getText().toString())));
        }
        if (ed2.getText().length() > 0) {
            double num2 = Double.parseDouble(ed2.getText().toString());
            ed2.setText(String.format("%." + b + "f", Double.parseDouble(ed2.getText().toString())));

        }
    }

    @Override
    public int getCurrentPercision() {
        return index;
    }
}