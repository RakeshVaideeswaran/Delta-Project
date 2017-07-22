package com.example.rakesh.hangmangame;

import android.content.ContentValues;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CategoriesActivity extends AppCompatActivity {

    RadioGroup RG;
    int selID;
    char c;
    String wordToBeAdded;
    public final int ADDITEMREQ = 999;
    SQLiteDatabase db;
    String table,columnname;
    HangmanDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        helper = new HangmanDBHelper(this);
        db = helper.getWritableDatabase();
    }


    public void initchar()
    {
        RG = (RadioGroup) findViewById(R.id.RG);
        selID = RG.getCheckedRadioButtonId();

        switch(selID)
        {
            case R.id.COUNTRIES : c = 'c';table = HangmanContract.HangmanEntry.TABLE_COUNTRIES;
                columnname = HangmanContract.HangmanEntry.COLUMN_COUNTRIES;break;

            case R.id.ANIMALS : c = 'a'; table = HangmanContract.HangmanEntry.TABLE_ANIMALS;
                columnname = HangmanContract.HangmanEntry.COLUMN_ANIMALS;break;

            case R.id.COLOURS : c = 'C'; table = HangmanContract.HangmanEntry.TABLE_COLOURS;
                columnname = HangmanContract.HangmanEntry.COLUMN_COLOURS;break;

            case R.id.VEGETABLES : c = 'v';table = HangmanContract.HangmanEntry.TABLE_VEGETABLES;
                columnname = HangmanContract.HangmanEntry.COLUMN_VEGETABLES;break;

            case R.id.FRUITS : c = 'f'; table = HangmanContract.HangmanEntry.TABLE_FRUITS;
                columnname = HangmanContract.HangmanEntry.COLUMN_FRUITS;break;
        }

        Toast.makeText(getApplicationContext(),columnname.toUpperCase(),Toast.LENGTH_LONG).show();
    }

    public void chooseCorrectCategory(View view)
    {
        initchar();

        Intent intent = new Intent(CategoriesActivity.this,Gameplay.class);
        intent.putExtra("playinglist",c);
        startActivity(intent);
    }

    public void intentGenToAddWord(View view)
    {
        initchar();

        Intent i = new Intent(CategoriesActivity.this,AddItem.class);
        i.putExtra("CHAR",c);
        startActivityForResult(i,ADDITEMREQ);
    }

    public void intentGenToDeleteWord(View view)
    {
        initchar();

        Intent i = new Intent(CategoriesActivity.this,DeleteItem.class);
        i.putExtra("columnName",columnname);
        i.putExtra("tableName",table);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADDITEMREQ)
        {
            if(resultCode == RESULT_OK)
            {
                if(data!=null)
                {
                    wordToBeAdded = data.getStringExtra("word");
                    addItemToDatabase();
                }
            }
        }
    }


    public void addItemToDatabase()
    {
        ContentValues cv = new ContentValues();
        cv.put(columnname,wordToBeAdded);
        long res = db.insert(table,null,cv);

        if(res != -1)
            Toast.makeText(getApplicationContext(),"WORD SUCCESSFULLY ADDED",Toast.LENGTH_LONG).show();

        else
            Toast.makeText(getApplicationContext(),"WORD ALREADY EXISTS",Toast.LENGTH_LONG).show();

    }

}









