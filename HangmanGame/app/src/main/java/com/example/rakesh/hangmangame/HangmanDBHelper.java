package com.example.rakesh.hangmangame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rakesh on 19/7/17.
 */

public class HangmanDBHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "HANGMAN.db";

    public HangmanDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

        final String CREATE_TABLE_ANIMALS = "CREATE TABLE "+
                HangmanContract.HangmanEntry.TABLE_ANIMALS + " (" +
                HangmanContract.HangmanEntry._ID + " INTEGER, " +
                HangmanContract.HangmanEntry.COLUMN_ANIMALS + " TEXT PRIMARY KEY" +
                ");";


        final String CREATE_TABLE_VEGETABLES = "CREATE TABLE "+
                HangmanContract.HangmanEntry.TABLE_VEGETABLES + " (" +
                HangmanContract.HangmanEntry._ID + " INTEGER, " +
                HangmanContract.HangmanEntry.COLUMN_VEGETABLES + " TEXT PRIMARY KEY" +
                ");";


        final String CREATE_TABLE_FRUITS = "CREATE TABLE "+
                HangmanContract.HangmanEntry.TABLE_FRUITS + " (" +
                HangmanContract.HangmanEntry._ID + " INTEGER, " +
                HangmanContract.HangmanEntry.COLUMN_FRUITS + " TEXT PRIMARY KEY" +
                ");";


        final String CREATE_TABLE_COUNTRIES = "CREATE TABLE "+
                HangmanContract.HangmanEntry.TABLE_COUNTRIES + " (" +
                HangmanContract.HangmanEntry._ID + " INTEGER, " +
                HangmanContract.HangmanEntry.COLUMN_COUNTRIES + " TEXT PRIMARY KEY" +
                ");";


        final String CREATE_TABLE_COLOURS = "CREATE TABLE "+
                HangmanContract.HangmanEntry.TABLE_COLOURS + " (" +
                HangmanContract.HangmanEntry._ID + " INTEGER, " +
                HangmanContract.HangmanEntry.COLUMN_COLOURS + " TEXT PRIMARY KEY" +
                ");";

        sqLiteDatabase.execSQL(CREATE_TABLE_ANIMALS);
        sqLiteDatabase.execSQL(CREATE_TABLE_COUNTRIES);
        sqLiteDatabase.execSQL(CREATE_TABLE_COLOURS);
        sqLiteDatabase.execSQL(CREATE_TABLE_FRUITS);
        sqLiteDatabase.execSQL(CREATE_TABLE_VEGETABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HangmanContract.HangmanEntry.TABLE_ANIMALS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HangmanContract.HangmanEntry.TABLE_COUNTRIES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HangmanContract.HangmanEntry.TABLE_COLOURS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HangmanContract.HangmanEntry.TABLE_FRUITS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HangmanContract.HangmanEntry.TABLE_VEGETABLES);

        onCreate(sqLiteDatabase);
    }
}
