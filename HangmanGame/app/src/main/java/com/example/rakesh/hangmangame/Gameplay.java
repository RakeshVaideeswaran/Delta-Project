package com.example.rakesh.hangmangame;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Gameplay extends AppCompatActivity {

    ArrayList<String> words;
    String WORD=null;
    char c;
    char[] MOD_WORD=null;
    TextView T,A;
    ImageView I;
    Cursor cursor;
    LinearLayout BL;
    RelativeLayout AL,SL;
    int n,count,imgcount;
    String TABLENAME,COLUMNNAME;

    SQLiteDatabase mDb;
    HangmanDBHelper dbHelper;

    Button a,b,c1,d,e,f,g,h,i,j,k,l,m,n1,o,p,q,r,s,t,u,v,w,x,y,z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        count=0;
        imgcount=0;
        MOD_WORD = null;
        AL = (RelativeLayout) findViewById(R.id.anslayout);
        SL = (RelativeLayout) findViewById(R.id.statuslayout);
        BL = (LinearLayout) findViewById(R.id.buttonlayout);
        A = (TextView) findViewById(R.id.anstxtview);
        I = (ImageView) findViewById(R.id.imgview);
        T = (TextView) findViewById(R.id.txtview);

        initbuttons();
        dbHelper = new HangmanDBHelper(this);
        mDb = dbHelper.getWritableDatabase();

        playingList();
        startGame();
    }

    public void initbuttons()
    {
        a = (Button) findViewById(R.id.A);
        b = (Button) findViewById(R.id.B);
        c1 = (Button) findViewById(R.id.C);
        d = (Button) findViewById(R.id.D);
        e = (Button) findViewById(R.id.E);
        f = (Button) findViewById(R.id.F);
        g = (Button) findViewById(R.id.G);
        h = (Button) findViewById(R.id.H);
        i = (Button) findViewById(R.id.I);
        j = (Button) findViewById(R.id.J);
        k = (Button) findViewById(R.id.K);
        l = (Button) findViewById(R.id.L);
        m = (Button) findViewById(R.id.M);
        n1 = (Button) findViewById(R.id.N);
        o = (Button) findViewById(R.id.O);
        p = (Button) findViewById(R.id.P);
        q = (Button) findViewById(R.id.Q);
        r = (Button) findViewById(R.id.R);
        s = (Button) findViewById(R.id.S);
        t = (Button) findViewById(R.id.T);
        u = (Button) findViewById(R.id.U);
        v = (Button) findViewById(R.id.V);
        w = (Button) findViewById(R.id.W);
        x = (Button) findViewById(R.id.X);
        y = (Button) findViewById(R.id.Y);
        z = (Button) findViewById(R.id.Z);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(getApplicationContext());
        inflater.inflate(R.menu.categories,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(Gameplay.this,CategoriesActivity.class);
        startActivity(i);
        finish();
        return true;
    }

    public void playingList()
    {
        Resources res = getApplicationContext().getResources();
        Intent i = getIntent();
        char c = i.getCharExtra("playinglist",'c');

        switch (c)
        {
            case 'c': words = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.countries))); break;
            case 'C': words = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.colours)));break;
            case 'v': words = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.vegetables)));break;
            case 'f': words = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.fruits)));break;
            case 'a': words = new ArrayList<>(Arrays.asList(res.getStringArray(R.array.animals)));break;
        }

        cursor = getAllItemsFromDatabase(c);

        addWordstoPlayingList();

        // T.setText(words[0]);
    }

    public void addWordstoPlayingList()
    {
        Log.e("afada",cursor.getCount() + "");

        if(cursor.getCount() == 0)
            return;

        while(cursor.moveToNext())
        {
            words.add(cursor.getString(cursor.getColumnIndex(COLUMNNAME)));
        }

        Log.e("total",words.size() + "");
        Log.e("last word", words.get(words.size()-1));
    }


    public Cursor getAllItemsFromDatabase(char c)
    {
        switch(c)
        {
            case 'c': TABLENAME = HangmanContract.HangmanEntry.TABLE_COUNTRIES;
                COLUMNNAME = HangmanContract.HangmanEntry.COLUMN_COUNTRIES;
                break;

            case 'C': TABLENAME = HangmanContract.HangmanEntry.TABLE_COLOURS;
                COLUMNNAME = HangmanContract.HangmanEntry.COLUMN_COLOURS;
                break;

            case 'v': TABLENAME = HangmanContract.HangmanEntry.TABLE_VEGETABLES;
                COLUMNNAME = HangmanContract.HangmanEntry.COLUMN_VEGETABLES;
                break;

            case 'f': TABLENAME = HangmanContract.HangmanEntry.TABLE_FRUITS;
                COLUMNNAME = HangmanContract.HangmanEntry.COLUMN_FRUITS;
                break;

            case 'a': TABLENAME = HangmanContract.HangmanEntry.TABLE_ANIMALS;
                COLUMNNAME = HangmanContract.HangmanEntry.COLUMN_ANIMALS;
                break;
        }

        return mDb.query(
                TABLENAME,
                null,
                null,
                null,
                null,
                null,
                null
        );



    }


    public void startGame()
    {
        Random random = new Random();
        n = random.nextInt(words.size());

        WORD = new String(words.get(n));
        MOD_WORD = new char[WORD.length()];

        for(int i = 0;i < WORD.length(); i++)
        {
            if(WORD.charAt(i) == ' ')
            {
                MOD_WORD[i] = ' ';
            }

            else
            {
                MOD_WORD[i] = '-';
            }
        }



        T.setText(String.valueOf(MOD_WORD));

    }

    public void gamePlay(View view)
    {
        int buttonclicked = view.getId();
        Button B = (Button) view;

        switch(buttonclicked)
        {
            case R.id.A: c = 'a'; break;
            case R.id.B: c = 'b'; break;
            case R.id.C: c = 'c'; break;
            case R.id.D: c = 'd'; break;
            case R.id.E: c = 'e'; break;
            case R.id.F: c = 'f'; break;
            case R.id.G: c = 'g'; break;
            case R.id.H: c = 'h'; break;
            case R.id.I: c = 'i'; break;
            case R.id.J: c = 'j'; break;
            case R.id.K: c = 'k'; break;
            case R.id.L: c = 'l'; break;
            case R.id.M: c = 'm'; break;
            case R.id.N: c = 'n'; break;
            case R.id.O: c = 'o'; break;
            case R.id.P: c = 'p'; break;
            case R.id.Q: c = 'q'; break;
            case R.id.R: c = 'r'; break;
            case R.id.S: c = 's'; break;
            case R.id.T: c = 't'; break;
            case R.id.U: c = 'u'; break;
            case R.id.V: c = 'v'; break;
            case R.id.W: c = 'w'; break;
            case R.id.X: c = 'x'; break;
            case R.id.Y: c = 'y'; break;
            case R.id.Z: c = 'z'; break;
        }

        B.setVisibility(View.INVISIBLE);
        Log.d("gameplay","c = "+c);
        searchChar(c);
    }


    public void searchChar(char c)
    {
        count=0;

        for(int i=0;i<WORD.length();i++)
            if(c == WORD.toLowerCase().charAt(i))
            {
                count=1;
                MOD_WORD[i] = Character.toUpperCase(c);
            }

        Log.d("Searchchar","count = " + count);
        if(count==1)
            UIupdate();

        else
            setImage();

    }


    public void setImage()
    {
        imgcount++;

        switch(imgcount)
        {
            case 1: I.setImageResource(R.drawable.pic2);break;
            case 2: I.setImageResource(R.drawable.pic3);break;
            case 3: I.setImageResource(R.drawable.pic4);break;
            case 4: I.setImageResource(R.drawable.pic5);break;
            case 5: I.setImageResource(R.drawable.pic6);break;
            case 6: I.setImageResource(R.drawable.pic7);break;
            case 7: I.setImageResource(R.drawable.pic8);break;
        }

        if(imgcount == 7)
        {
            Toast.makeText(getApplicationContext(),"YOU LOSE",Toast.LENGTH_LONG).show();
            showAnswer();
            imgcount = 0;
        }
    }

    public void showAnswer()
    {
        int i;
        BL.setVisibility(View.GONE);
        AL.setVisibility(View.VISIBLE);



        for(i = 0; i<MOD_WORD.length;i++)
            if(MOD_WORD[i] == '-')
            {
                MOD_WORD[i] = WORD.toLowerCase().charAt(i);
            }

        SpannableStringBuilder RESULT = new SpannableStringBuilder();

        String s;
        SpannableString S;
        for(i=0;i<MOD_WORD.length;i++)
        {
            if(Character.isLowerCase(MOD_WORD[i]))
            {
                s = Character.toUpperCase(MOD_WORD[i]) + "";
                S = new SpannableString(s);
                S.setSpan(new ForegroundColorSpan(getApplicationContext().getResources().getColor(R.color.nguesslettercolor)),0,s.length(),0);
                RESULT.append(S);
            }

            else
            {
                s = MOD_WORD[i] + "";
                S = new SpannableString(s);
                S.setSpan(new ForegroundColorSpan(getApplicationContext().getResources().getColor(R.color.deflettercolor)),0,s.length(),0);
                RESULT.append(S);
            }
        }


        A.setText(RESULT, TextView.BufferType.SPANNABLE);
    }

    public void UIupdate()
    {
        Log.d("UIupdate",String.valueOf(MOD_WORD));
        T.setText(String.valueOf(MOD_WORD));
        checkResult();
    }

    public void checkResult()
    {
        if(String.valueOf(MOD_WORD).equals(WORD))
        {
            Toast.makeText(getApplicationContext(), "YOU WIN", Toast.LENGTH_LONG).show();
            showStatus();
        }
    }

    public void restartGame(View view)
    {
        BL.setVisibility(View.VISIBLE);
        AL.setVisibility(View.GONE);
        SL.setVisibility(View.GONE);
        imgcount = 0;
        I.setImageResource(R.drawable.pic1);
        resetVisibility();
        startGame();
    }

    public void showStatus()
    {
        BL.setVisibility(View.GONE);
        SL.setVisibility(View.VISIBLE);
        imgcount = 0;
        I.setImageResource(R.drawable.pic1);
    }

    public void resetVisibility()
    {
        a.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);
        c1.setVisibility(View.VISIBLE);
        d.setVisibility(View.VISIBLE);
        e.setVisibility(View.VISIBLE);
        f.setVisibility(View.VISIBLE);
        g.setVisibility(View.VISIBLE);
        h.setVisibility(View.VISIBLE);
        i.setVisibility(View.VISIBLE);
        j.setVisibility(View.VISIBLE);
        k.setVisibility(View.VISIBLE);
        l.setVisibility(View.VISIBLE);
        m.setVisibility(View.VISIBLE);
        n1.setVisibility(View.VISIBLE);
        o.setVisibility(View.VISIBLE);
        p.setVisibility(View.VISIBLE);
        q.setVisibility(View.VISIBLE);
        r.setVisibility(View.VISIBLE);
        s.setVisibility(View.VISIBLE);
        t.setVisibility(View.VISIBLE);
        u.setVisibility(View.VISIBLE);
        v.setVisibility(View.VISIBLE);
        w.setVisibility(View.VISIBLE);
        x.setVisibility(View.VISIBLE);
        y.setVisibility(View.VISIBLE);
        z.setVisibility(View.VISIBLE);
    }
}