package com.jdots.wordtwist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;

public class MainActivity extends AppCompatActivity {

    String mainWord=null;
    int i,WordLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String text = extras.getString(LevelActivity.TOKEN);
            WordLength = Integer.valueOf(text);
        }

        final TextView Word = (TextView) findViewById(R.id.TwistedWord);
        Button B = (Button) findViewById(R.id.buttonChange);
        Button B2 = (Button) findViewById(R.id.Answer);


        final Button[] character = new Button[9];
        character[0] = (Button) findViewById(R.id.button3);
        character[1] = (Button) findViewById(R.id.button4);
        character[2] = (Button) findViewById(R.id.button5);
        character[3] = (Button) findViewById(R.id.button6);
        character[4] = (Button) findViewById(R.id.button7);
        character[5] = (Button) findViewById(R.id.button8);
        character[6] = (Button) findViewById(R.id.button9);
        character[7] = (Button) findViewById(R.id.button10);
        character[8] = (Button) findViewById(R.id.button11);

        for(i=0;i<9;i++) ButtonAnim(character[i]);


        Word.setText("");
        getWord(WordLength);

        B.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                getWord(WordLength);
                Word.setText("");
                for (i = 0; i < 8; i++) character[i].setEnabled(true);
                for(i=0;i<9;i++) ButtonAnim(character[i]);


            }
        });

        B2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            Word.setText(mainWord);
            for (i = 0; i < 8; i++) character[i].setEnabled(false);
            for(i=0;i<9;i++) ButtonAnim(character[i]);

            }
        });

        character[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buttonclick(character[0]);
            }
        }); character[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buttonclick(character[1]);
            }
        }); character[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buttonclick(character[2]);
            }
        }); character[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buttonclick(character[3]);
            }
        }); character[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buttonclick(character[8]);
            }
        }); character[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buttonclick(character[4]);
            }
        }); character[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buttonclick(character[5]);
            }
        }); character[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buttonclick(character[6]);
            }
        }); character[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buttonclick(character[7]);
            }
        }); character[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Buttonclick(character[8]);
            }
        });

    }




    public void getWord(int WordLen)
    {
        TextView Word=(TextView)findViewById(R.id.TwistedWord);
        Button [] character=new  Button [9];
        character[0]=(Button)findViewById(R.id.button3);
        character[1]=(Button)findViewById(R.id.button4);
        character[2]=(Button)findViewById(R.id.button5);
        character[3]=(Button)findViewById(R.id.button6);
        character[4]=(Button)findViewById(R.id.button7);
        character[5]=(Button)findViewById(R.id.button8);
        character[6]=(Button)findViewById(R.id.button9);
        character[7]=(Button)findViewById(R.id.button10);
        character[8]=(Button)findViewById(R.id.button11);

        for(int i=0 ; i< 9 ; i++){ character[i].setVisibility(View.GONE);}
        for(int i=0 ; i< WordLen ; i++){ character[i].setVisibility(View.VISIBLE);}
        for(int i=0 ; i< WordLen ; i++){ character[i].setEnabled(true);}


        String filename;

        switch (WordLen)
        {
            case 5:
                filename="five_letter";
                break;
            case 6:
                filename="six_letter";
                break;
            case 7:
                filename="seven_letter";
                break;
            case 8:
                filename="eight_letter";
                break;
            case 9:
                filename="nine_letter";
                break;

            default:
                filename="eight_letter";
        }


        BufferedReader reader;
        String line;
        int Count = 0;


        try {
            final InputStream f = getAssets().open(filename);
            reader = new BufferedReader(new InputStreamReader(f));

            do {

                line = reader.readLine();
                Count++;

            } while (line != null);
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }


        Random R=new Random();
        int val=R.nextInt(Count);
        Count=0;

        try{
            final InputStream file = getAssets().open(filename);
            reader = new BufferedReader(new InputStreamReader(file));

            do
            {
                line = reader.readLine();
                int len=line.length();

                mainWord=line.toUpperCase();

                char [] w=new char[WordLen];
                w=reArrange(line);

                for(int i=0;i<len;i++)
                {
                    character[i].setText(String.valueOf(w[i]).toUpperCase());
                }

                Count++;

            }while(line != null && Count<val);

        }

        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    public void Buttonclick(Button A)
    {
        final TextView Word = (TextView) findViewById(R.id.TwistedWord);
        final Button[] character = new Button[9];
        character[0] = (Button) findViewById(R.id.button3);
        character[1] = (Button) findViewById(R.id.button4);
        character[2] = (Button) findViewById(R.id.button5);
        character[3] = (Button) findViewById(R.id.button6);
        character[4] = (Button) findViewById(R.id.button7);
        character[5] = (Button) findViewById(R.id.button8);
        character[6] = (Button) findViewById(R.id.button9);
        character[7] = (Button) findViewById(R.id.button10);
        character[8] = (Button) findViewById(R.id.button11);


        Word.append(A.getText());
        A.setEnabled(false);
        ButtonBlop();
        ButtonAnim(A);

        if (mainWord.equals(Word.getText().toString()))
        {Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
            ButtonMirror();
            SettingsEdit(true,0,mainWord);
            for(i=0;i<9;i++)
            {
                character[i].setEnabled(false);
                ButtonAnim(character[i]);
            }
        }
        else if(Word.getText().toString().length()==mainWord.length())
        {
            Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
            for(i=0;i<9;i++)
            {
                character[i].setEnabled(true);
                ButtonAnim(character[i]);
            }
            Word.setText("");


        }

    }



    public void ButtonAnim(Button A)
    {
        if(A.isEnabled()) {
            final Animation Anim1 = AnimationUtils.loadAnimation(this, R.anim.shake);

                A.setAnimation(Anim1);
            }
        else
        {
            final Animation Anim2 = AnimationUtils.loadAnimation(this, R.anim.shakeoff);

                A.setAnimation(Anim2);

        }

    }
    public char[] reArrange(String A)
    {
        int len=A.length();
        Random R=new Random();
        char[] word=A.toCharArray();
        char[] word2=new char[len];
        for(int i=0;i<len;i++)
        {
            word2[i]='0';

        }
        for(int i=0,base=0;i<len;i++)
        {
            do {
                base = R.nextInt(len);

            }while ( word2[base]!='0');
            word2[base] = word[i];
        }
        return (word2);
    }
    public void ButtonBlop() {

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.blop);
        mp.start();
        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(50);

    }
    public void ButtonMirror() {

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.mirror);
        mp.start();
        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(50);

    }

    public void SettingsEdit(boolean EDIT,int KEY,String DATA) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);


        i=prefs.getInt("TOTAL",0);
        prefs.edit().putInt("TOTAL",i+1).apply();
        if (EDIT) {prefs.edit().putString("WORD"+i,DATA).apply(); }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.hint_menu:
                TextView Word = (TextView) findViewById(R.id.TwistedWord);
                if(Word.getText().length()>1)
                {
                    if(!(Word.getText().toString().substring(0, 2).equals(mainWord.substring(0, 2)))) {
                    Word.setText("");
                    Word.setText(mainWord.substring(0, 2));
                }
                }else Word.setText(mainWord.substring(0, 2));


                return true;

                        default:
                return super.onOptionsItemSelected(item);
        }
    }


}
