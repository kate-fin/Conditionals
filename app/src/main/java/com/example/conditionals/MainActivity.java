package com.example.conditionals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase myDB;
    final int num = 0;
    EditText editTextCond0, editTextCond1, editTextCond2;
    EditText editTextCond3, editTextMix1, editTextMix2;
    EditText editNotes;
    private final static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        editTextCond0 = (EditText) findViewById(R.id.edit_cond0);
        editTextCond1 = (EditText) findViewById(R.id.edit_cond1);
        editTextCond2 = (EditText) findViewById(R.id.edit_cond2);
        editTextCond3 = (EditText) findViewById(R.id.edit_cond3);
        editTextMix1 = (EditText) findViewById(R.id.edit_mix1);
        editTextMix2 = (EditText) findViewById(R.id.edit_mix2);
        editNotes = findViewById(R.id.edit_notes);
        myDB = openOrCreateDatabase("sentences.db", MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS cond0 (cond0 VARCHAR(300))");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS cond1 (cond1 VARCHAR(300))");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS cond2 (cond2 VARCHAR(300))");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS cond3 (cond3 VARCHAR(300))");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS mix1 (mix1 VARCHAR(300))");
        myDB.execSQL("CREATE TABLE IF NOT EXISTS mix2 (mix2 VARCHAR(300))");
        refreshSentences();
        Bundle arguments = getIntent().getExtras();
        if(arguments != null) {
            editNotes.append(arguments.get("edit_temp_text").toString());
        }

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "onStop");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
    }
    @Override
    protected void onPause(){
        saveSentences();
        super.onPause();
        Log.d(TAG, "onPause");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected  void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected  void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState");
    }

    public void refreshSentences(){
        editTextCond0.setText("");
        editTextCond1.setText("");
        editTextCond2.setText("");
        editTextCond3.setText("");
        editTextMix1.setText("");
        editTextMix2.setText("");
        Cursor myCursor = myDB.rawQuery("select * from cond0", null);
        while(myCursor.moveToNext()){
            editTextCond0.append(myCursor.getString(0));
        }
        myCursor = myDB.rawQuery("select * from cond1", null);
        while(myCursor.moveToNext()){
            editTextCond1.append(myCursor.getString(0));
        }
        myCursor = myDB.rawQuery("select * from cond2", null);
        while(myCursor.moveToNext()){
            editTextCond2.append(myCursor.getString(0));
        }
        myCursor = myDB.rawQuery("select * from cond3", null);
        while(myCursor.moveToNext()){
            editTextCond3.append(myCursor.getString(0));
        }
        myCursor = myDB.rawQuery("select * from mix1", null);
        while(myCursor.moveToNext()){
            editTextMix1.append(myCursor.getString(0));
        }
        myCursor = myDB.rawQuery("select * from mix2", null);
        while(myCursor.moveToNext()){
            editTextMix2.append(myCursor.getString(0));
        }
        myCursor.close();

        // открытие файла
        FileInputStream fin = null;
        try {
            fin = openFileInput("notes.txt");
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String (bytes);
            if(text != null) {
                editNotes.setText(text);
            }
            //Toast.makeText(this, "файл прочитан успешно", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(this,"не удалось прочитать файл", Toast.LENGTH_LONG).show();
        }finally {
            try {
                if(fin != null)
                    fin.close();
            }catch (IOException ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void saveSentences(){//(View view){
        myDB.execSQL("DELETE FROM cond0");
        myDB.execSQL("DELETE FROM cond1");
        myDB.execSQL("DELETE FROM cond2");
        myDB.execSQL("DELETE FROM cond3");
        myDB.execSQL("DELETE FROM mix1");
        myDB.execSQL("DELETE FROM mix2");
        ContentValues col = new ContentValues();
        String condText = String.valueOf(editTextCond0.getText());
        col.put("cond0", condText);
        myDB.insert("cond0", null, col);

        ContentValues col1 = new ContentValues();
        condText = String.valueOf(editTextCond1.getText());
        col1.put("cond1", condText);
        myDB.insert("cond1", null, col1);

        ContentValues col2 = new ContentValues();
        condText = String.valueOf(editTextCond2.getText());
        col2.put("cond2", condText);
        myDB.insert("cond2", null, col2);

        ContentValues col3 = new ContentValues();
        condText = String.valueOf(editTextCond3.getText());
        col3.put("cond3", condText);
        myDB.insert("cond3", null, col3);

        ContentValues col4 = new ContentValues();
        condText = String.valueOf(editTextMix1.getText());
        col4.put("mix1", condText);
        myDB.insert("mix1", null, col4);

        ContentValues col5 = new ContentValues();
        condText = String.valueOf(editTextMix2.getText());
        col5.put("mix2", condText);
        myDB.insert("mix2", null, col5);

        // сохранение файла
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("notes.txt", MODE_PRIVATE);
            fos.write(editNotes.getText().toString().getBytes());
            //Toast.makeText(this, "файл запсиан успешно", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            //Toast.makeText(this, "ошибка записи файла", Toast.LENGTH_LONG).show();
        }finally {
            try {
                if (fos != null)
                    fos.close();
            }
            catch (IOException ex){
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void deleteSentences(View view){
        myDB.execSQL("DELETE FROM cond0");
        myDB.execSQL("DELETE FROM cond1");
        myDB.execSQL("DELETE FROM cond2");
        myDB.execSQL("DELETE FROM cond3");
        myDB.execSQL("DELETE FROM mix1");
        myDB.execSQL("DELETE FROM mix2");
        refreshSentences();
    }

    public void nextActivity(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}