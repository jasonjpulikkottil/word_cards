package com.jdots.wordcards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class MemActivity extends AppCompatActivity
{
    public static final String TOKEN = "com.jdots.TOKEN";
    public static final String TOKEN2 = "com.jdots.TOKEN2";


  public  String mainWord=null;
    public RewardedVideoAd mRewardedVideoAd;
    int i,WordLength,Act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
             Act= Integer.valueOf(extras.getString(TOKEN));
        }

        AdView MobAdView;
        MobAdView = (AdView) findViewById(R.id.memad);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        MobAdView.loadAd(adRequest);

        MobileAds.initialize(this, getString(R.string.app_id));

        TimerStarter(Act,getLevel(),true);

        Button Btn = (Button) findViewById(R.id.buttonStart);
        final TextView Word=(TextView)findViewById(R.id.WordText);
        final   TextView NumT=(TextView)findViewById(R.id.num);

        Btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i=new Intent(MemActivity.this, ArrangeActivity.class);

                startActivity(i);
                ButtonBlop();

            }
        });
        NumT.setVisibility(View.GONE);

    }
    public int getLevel()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        return prefs.getInt("MEMCOUNT",2);

    }
    public void getWord(int WordLen)
    {
        TextView Word=(TextView)findViewById(R.id.WordText);

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
            { line = reader.readLine();
                mainWord=line.toUpperCase();
                Word.setText(mainWord);
                Count++;
            }while(line != null && Count<val);
        } catch(IOException ioe) {ioe.printStackTrace();



        }
    }
public void TimerStarter(final int WordLen, final int RecurCount,boolean INITIALIZE)
{


    final   TextView WordT=(TextView)findViewById(R.id.WordText);
    final   TextView NumT=(TextView)findViewById(R.id.num);

    final  Button Btn = (Button) findViewById(R.id.buttonStart);
    final  ImageView Im=(ImageView)findViewById(R.id.CountImg);
    final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

    if(INITIALIZE) NumT.setText("0");
    NumT.setVisibility(View.INVISIBLE);
    if(!(NumT.getText().toString().equals("0")))
   {
        NumT.setVisibility(View.VISIBLE);
        WordT.setVisibility(View.VISIBLE);
    }

    Im.setVisibility(View.VISIBLE);


    new CountDownTimer(5000, 1000) {

        public void onTick(long millisUntilFinished) {



            if (millisUntilFinished>=4000)
            {
                Im.setImageResource(R.drawable.three);
            }else if (millisUntilFinished>=3000)
            {
                Im.setImageResource(R.drawable.two);
            }else  if (millisUntilFinished>=2000)
            {
                Im.setImageResource(R.drawable.one);
            }
            else if (millisUntilFinished>=1000)
            {
                Im.setImageResource(R.drawable.zero);
            }

        }
        public void onFinish() {


            Im.setImageResource(R.drawable.zero);
            getWord(WordLen);
            prefs.edit().putString("MEMWORD"+String.valueOf(Integer.valueOf(RecurCount-1)),mainWord).apply();

            NumT.setText(String.valueOf(Integer.valueOf(NumT.getText().toString()) + 1));
            if (RecurCount>0) TimerStarter(WordLen,RecurCount-1,false);
            if (RecurCount==0)
            {

                WordT.setVisibility(View.GONE);
                NumT.setVisibility(View.GONE);
                Btn.setVisibility(View.VISIBLE);
            }

        }
    }.start();

}
    public void ButtonBlop() {

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.blop);
        mp.start();
        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(50);

    }

    void AddCoins()
    {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putInt("COINS",prefs.getInt("COINS" ,0)+20).apply();
        Toast.makeText(getApplicationContext(), "You got 20 Coins", Toast.LENGTH_SHORT).show();
    }
}