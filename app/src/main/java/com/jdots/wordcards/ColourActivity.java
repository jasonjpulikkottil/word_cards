package com.jdots.wordcards;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Random;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.CYAN;

public class ColourActivity extends AppCompatActivity {
    AdView MobAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour);


        MobAdView = (AdView) findViewById(R.id.AdColour);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        MobAdView.loadAd(adRequest);

        final   Button Yes=(Button)findViewById(R.id.buttonYes);

        final  Button No=(Button)findViewById(R.id.ButtonNo);
        final TextView TC=(TextView) findViewById(R.id.wordColour);
        final ProgressBar PB=(ProgressBar) findViewById(R.id.progressBarC);
        final Random R=new Random();

        Generate();


        new CountDownTimer(60000, 1000) {

            @Override
            public void onTick(long l) {
                if(PB.getProgress()<PB.getMax())PB.setProgress(PB.getProgress()+1);


            }

            @Override
            public void onFinish() {


                Toast.makeText(getApplicationContext(), "Time Over\nScore " + ColourScore(), Toast.LENGTH_SHORT).show();
                ColourScoreReset();
                PB.setProgress(0);
                finish();

            }
        }.start();


        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ButtonBlop();

                Drawable a=TC.getBackground();
                int c=((ColorDrawable)a).getColor();


               if(ColCheck(c,TC.getText().toString())) {
                   Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                   ColourScore();

               }

               else {
                   Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_LONG).show();
                ButtonBeep();
            }


                Generate();

            }
            });
       No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ButtonBlop();

                Drawable a=TC.getBackground();
                int c=((ColorDrawable)a).getColor();


                if(!(ColCheck(c,TC.getText().toString()))){

                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                    ColourScore();

                }
                else {
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_LONG).show();
                    ButtonBeep();
                }


                Generate();

            }
        });


    }

    void Generate()
    {
        final TextView TC=(TextView) findViewById(R.id.wordColour);
        final Random R=new Random();
        int Ran=R.nextInt(8);

        TC.setBackgroundColor(GenCol(Ran));

        if(R.nextBoolean())
            TC.setText(NameCol(GenCol(R.nextInt(8))));
        else TC.setText(NameCol(GenCol(Ran)));

        TC.setTextColor(0xff6666ff);

    }

   int ColourScore()
    {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putInt("COINS",prefs.getInt("COINS" ,0)+1).apply();
        prefs.edit().putInt("COLOUR", prefs.getInt("COLOUR", 0) + 1).apply();
        return prefs.getInt("COLOUR", 0);

    }
    void ColourScoreReset()
    {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putInt("COLOUR", 0).apply();
    }
    public void ButtonBeep() {

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.beep1);
        mp.start();
        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(50);

    }



    public void ButtonBlop() {

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.blop);
        mp.start();
        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(50);

    }

    int GenCol(int i)
    {
        switch (i)
        {
            case 0:
                return 0xff0000ff;
            case 1:
                return 0xffff0000;
            case 2:
                return 0xffffff00;
            case 3:
                return 0xffff6600;
            case 4:
                return 0xff00ff00;
            case 5:
                return 0xff6600ff;
            case 6:
                return 0xff000000;
            default:
                return 0xffffffff;


        }
    }
    String NameCol(int i)
    {
        switch (i)
        {
            case 0xff0000ff: return "BLUE";
            case 0xffff0000: return "RED";
            case 0xffffff00: return "YELLOW";
            case 0xffff6600: return "ORANGE";
            case 0xff00ff00: return "GREEN";
            case 0xff6600ff: return "PURPLE";
            case 0xff000000: return "BLACK";
            case 0xffffffff: return "WHITE";

            case 0x00f: return "BLUE";
            case 0xf00: return "RED";
            case 0xfff0: return "YELLOW";
            case 0x0f0: return "GREEN";
            case 0x000: return "BLACK";
            case 0xfff: return "WHITE";

            case 0xff6600: return "ORANGE";
            case 0x6600ff: return "PURPLE";
            case 0xfffff: return "WHITE";
        }
        return "ERROR";
    }
    boolean ColCheck(int C,String Name)
    {
        switch (C)
        {
            case 0xff0000ff: return Name.equals("BLUE");
            case 0xffff0000: return  Name.equals("RED");
            case 0xffffff00: return  Name.equals("YELLOW");
            case 0xffff6600: return  Name.equals("ORANGE");
            case 0xff00ff00: return  Name.equals("GREEN");
            case 0xff6600ff: return  Name.equals("PURPLE");
            case 0xff000000: return  Name.equals("BLACK");
            case 0xffffffff: return  Name.equals("WHITE");
            default:return false;
        }
    }

}
