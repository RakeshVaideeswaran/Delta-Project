package com.example.rakesh.hangmangame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.widget.TextView;

public class DeleteItem extends AppCompatActivity {

    TextView textView;
    String tableName,columnName;

    SQLiteDatabase db;
    HangmanAdapter adapter;
    HangmanDBHelper dbHelper;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);

        Intent i = getIntent();
        columnName = i.getStringExtra("columnName");

        textView = (TextView) findViewById(R.id.headingtv);
        textView.setText(columnName.toUpperCase());

        tableName = i.getStringExtra("tableName");


        rv = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        dbHelper = new HangmanDBHelper(this);
        db = dbHelper.getWritableDatabase();

        Cursor cursor = getAllItems();
        adapter = new HangmanAdapter(this,cursor,columnName);
        rv.setAdapter(adapter);


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT){


            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                String txt = (String) viewHolder.itemView.getTag();
                deleteItem(txt);

            }
        }).attachToRecyclerView(rv);

    }

    public void deleteItem(String s)
    {
        db.delete(tableName, columnName + "=" + "'" + s + "'",null);
        Cursor cursor = getAllItems();
        adapter= new HangmanAdapter(this,cursor,columnName);
        rv.setAdapter(adapter);
        //finish();
        //startActivity(getIntent());
    }

    public Cursor getAllItems()
    {
        return db.query(
                tableName,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
