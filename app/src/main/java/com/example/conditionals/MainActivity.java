package com.example.conditionals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editTextCond0 = (EditText) findViewById(R.id.edit_cond0);
        EditText editTextCond1 = (EditText) findViewById(R.id.edit_cond1);
        EditText editTextCond2 = (EditText) findViewById(R.id.edit_cond2);
        editTextCond0.setOnEditorActionListener( new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if( event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                    // обработка нажатия Enter
                    return true;
                }
                return false;
            }
        });

    }
}