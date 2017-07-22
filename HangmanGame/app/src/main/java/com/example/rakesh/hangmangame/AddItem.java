package com.example.rakesh.hangmangame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddItem extends AppCompatActivity {

    EditText E;
    TextView T;
    String wordOnTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        this.setFinishOnTouchOutside(false);

        T = (TextView) findViewById(R.id.ewtv);
        E = (EditText) findViewById(R.id.edittxt);

        Intent intent = getIntent();
        char c = intent.getCharExtra("CHAR",'c');
        textOnTextView(c);

    }

    public void textOnTextView(char c)
    {
        switch (c)
        {
            case 'c': wordOnTextView = "COUNTRY";break;
            case 'C': wordOnTextView = "COLOUR";break;
            case 'a': wordOnTextView = "ANIMAL";break;
            case 'f': wordOnTextView = "FRUIT";break;
            case 'v': wordOnTextView = "VEGETABLE";break;
        }

        T.setText("ENTER NAME OF " + wordOnTextView);
    }

    public void sendResult(View view)
    {
        String res = E.getText().toString();

        if(res.length() == 0)
            Toast.makeText(getApplicationContext(),"ENTER A WORD",Toast.LENGTH_LONG).show();

        else
        {
            Intent i = new Intent();
            i.putExtra("word",res);
            setResult(RESULT_OK,i);
            finish();
        }
    }
}


