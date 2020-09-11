package com.example.conditionals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase myDB;
    final int num = 0;
    EditText editTextCond0;
    EditText editTextCond1;
    EditText editTextCond2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextCond0 = (EditText) findViewById(R.id.edit_cond0);
        editTextCond1 = (EditText) findViewById(R.id.edit_cond1);
        editTextCond2 = (EditText) findViewById(R.id.edit_cond2);
        myDB = openOrCreateDatabase("sentences.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS cond0 (cond0 VARCHAR(300))");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS cond1 (cond1 VARCHAR(300))");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS cond2 (cond2 VARCHAR(300))");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS cond3 (cond3 VARCHAR(300))");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS mix1 (mix1 VARCHAR(300))");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS mix2 (mix2 VARCHAR(300))");
        refreshSentences();
//        int num = myDB.rawQuery("SELECT * FROM cond0", null).getCount();
//        editTextCond0.setText(String.valueOf(num));
//        editTextCond0.setOnEditorActionListener( new TextView.OnEditorActionListener() {
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if( event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER){
//                    // обработка нажатия Enter
////                    int num = myDB.rawQuery("SELECT * FROM cond0", null).getCount();
////                    num++;
////                    editTextCond0.setText(String.valueOf(num), TextView.BufferType.SPANNABLE);
//                    return true;
//                }
//                return false;
//            }
//        });
//        editTextCond0.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
////                int num = myDB.rawQuery("SELECT * FROM cond0", null).getCount();
////                editTextCond0.setText(editTextCond0.getText() + String.valueOf(num));
//        }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

    }
    public void refreshSentences(){
        Cursor myCursor = myDB.rawQuery("select * from cond0", null);
        editTextCond0.setText("");
        editTextCond1.setText("");
        editTextCond2.setText("");
        while(myCursor.moveToNext()){
            editTextCond0.append(myCursor.getString(0) + '\n');
        }
        myCursor = myDB.rawQuery("select * from cond1", null);
        while(myCursor.moveToNext()){
            editTextCond1.append(myCursor.getString(0) + '\n');
        }
        myCursor = myDB.rawQuery("select * from cond2", null);
        while(myCursor.moveToNext()){
            editTextCond2.append(myCursor.getString(0) + '\n');
        }
        myCursor.close();
    }
    public void saveSentences(View view){
        myDB.execSQL("DELETE FROM cond0");
        myDB.execSQL("DELETE FROM cond1");
        myDB.execSQL("DELETE FROM cond2");
        ContentValues row = new ContentValues();
        String condText = String.valueOf(editTextCond0.getText());
        row.put("cond0", condText);
        myDB.insert("cond0", null, row);

        ContentValues row1 = new ContentValues();
        condText = String.valueOf(editTextCond1.getText());
        row1.put("cond1", condText);
        myDB.insert("cond1", null, row1);

        ContentValues row2 = new ContentValues();
        condText = String.valueOf(editTextCond2.getText());
        row2.put("cond2", condText);
        myDB.insert("cond2", null, row2);
    }
    public void deleteSentences(View view){
        myDB.execSQL("DELETE FROM cond0");
        myDB.execSQL("DELETE FROM cond1");
        myDB.execSQL("DELETE FROM cond2");
        refreshSentences();
    }
}