package com.jdots.wordcards;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;


import java.util.Random;

public class MathActivity extends AppCompatActivity {



    public RewardedVideoAd mRewardedVideoAd;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.math, menu);
        return true;
    }

    public void onRewarded(RewardItem reward) {
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putInt("COINS",prefs.getInt("COINS" ,0)+100).apply();
// Reward the user.
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ButtonBlop();

        mRewardedVideoAd.loadAd(getString(R.string.ad_id_hint),
                new AdRequest.Builder().build());
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
        final  RadioGroup RG=(RadioGroup) findViewById(R.id.RGroup);
        final   RadioButton R1 = (RadioButton) findViewById(R.id.radioButton1);
        final    RadioButton R2 = (RadioButton) findViewById(R.id.radioButton2);
        final    RadioButton R3 = (RadioButton) findViewById(R.id.radioButton3);
        final   RadioButton R4 = (RadioButton) findViewById(R.id.radioButton4);

        switch (item.getItemId()) {
            case R.id.math_menu:




                final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                if(prefs.getInt("COINS" ,0)>9) {
                    prefs.edit().putInt("COINS", prefs.getInt("COINS", 0) - 10).apply();

                    String Ans=prefs.getString("ANS", null);
                    if (R1.getText().toString().equals(Ans.toUpperCase()) ) R1.setChecked(true);
                    if (R2.getText().toString().equals(Ans.toUpperCase()) ) R2.setChecked(true);
                    if (R3.getText().toString().equals(Ans.toUpperCase()) ) R3.setChecked(true);
                    if (R4.getText().toString().equals(Ans.toUpperCase()) ) R4.setChecked(true);
                }
                else Toast.makeText(getApplicationContext(), "No Enough Coins", Toast.LENGTH_SHORT).show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
         final   SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        final    Button B = (Button) findViewById(R.id.ButtonShow);
        final  RadioGroup RG=(RadioGroup) findViewById(R.id.RGroup);
        final   RadioButton R1 = (RadioButton) findViewById(R.id.radioButton1);
        final    RadioButton R2 = (RadioButton) findViewById(R.id.radioButton2);
        final    RadioButton R3 = (RadioButton) findViewById(R.id.radioButton3);
        final   RadioButton R4 = (RadioButton) findViewById(R.id.radioButton4);
        final  TextView T1=(TextView) findViewById(R.id.WordN1);
        final TextView T2=(TextView) findViewById(R.id.WordN2);
        final TextView T3=(TextView) findViewById(R.id.WordN3);
        GetWord();

        AdView MobAdView;
        MobAdView = (AdView) findViewById(R.id.mathad);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        MobAdView.loadAd(adRequest);

        MobileAds.initialize(this, getString(R.string.app_id));

        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.loadAd(getString(R.string.ad_id_hint),
                new AdRequest.Builder().build());

        B.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                ButtonBlop();
                String T="";
                if(R1.isChecked())T=R1.getText().toString();
                if(R2.isChecked())T=R2.getText().toString();
                if(R3.isChecked())T=R3.getText().toString();
                if(R4.isChecked())T=R4.getText().toString();

                if(prefs.getString("ANS", null).toUpperCase().equals(T)) {
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();

                    prefs.edit().putInt("COINS", prefs.getInt("COINS", 0) + 1).apply();

                }else {
                    ButtonBeep();

                    Toast.makeText(getApplicationContext(), "Wrong\nAnswer : " + prefs.getString("ANS", null), Toast.LENGTH_SHORT).show();
                }
                RG.setVisibility(View.INVISIBLE);
                RG.setVisibility(View.INVISIBLE);
                RG.setVisibility(View.INVISIBLE);
                RG.setVisibility(View.INVISIBLE);
                T1.setVisibility(View.INVISIBLE);
                T2.setVisibility(View.INVISIBLE);
                T3.setVisibility(View.INVISIBLE);


                GetWord();
            }

        });
    }
        void GetWord(){
    final    Button B = (Button) findViewById(R.id.ButtonShow);
    final  RadioGroup RG=(RadioGroup) findViewById(R.id.RGroup);
    final   RadioButton R1 = (RadioButton) findViewById(R.id.radioButton1);
    final    RadioButton R2 = (RadioButton) findViewById(R.id.radioButton2);
    final    RadioButton R3 = (RadioButton) findViewById(R.id.radioButton3);
    final   RadioButton R4 = (RadioButton) findViewById(R.id.radioButton4);
    final ImageView Im=( ImageView) findViewById(R.id.imageView);
    final  TextView T1=(TextView) findViewById(R.id.WordN1);
    final TextView T2=(TextView) findViewById(R.id.WordN2);
    final TextView T3=(TextView) findViewById(R.id.WordN3);
    final Random r = new Random();

    final int Num1=r.nextInt(999);
    final int Num2=r.nextInt(499);
    final int Num3=r.nextInt(99);
    final int NumSum=Num1+Num2+Num3;
            final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            prefs.edit().putString("ANS", NumToWord(NumSum)).apply();

    new CountDownTimer(4000, 1000) {

        public void onTick(long millisUntilFinished) {


            if (millisUntilFinished>=3000)
            {
                Im.setImageResource(R.drawable.one);
                T1.setText(NumToWord(Num1).toUpperCase());
                T1.setVisibility(View.VISIBLE);
                T1.setTextColor(0xffffff00);
            }else if (millisUntilFinished>=2000)
            {
                Im.setImageResource(R.drawable.two);
                T2.setText(NumToWord(Num2).toUpperCase());
                T2.setVisibility(View.VISIBLE);
                T2.setTextColor(0xffffff00);
            }else
            {
                Im.setImageResource(R.drawable.three);
                T3.setText(NumToWord(Num3).toUpperCase());
                T3.setVisibility(View.VISIBLE);
                T3.setTextColor(0xffffff00);
            }

        }
        public void onFinish() {
            RG.setVisibility(View.VISIBLE);
            R1.setChecked(true);
            do {
                R1.setText(NumToWord(NumSum + r.nextInt(10)).toUpperCase());
                R2.setText(NumToWord(NumSum + r.nextInt(50)).toUpperCase());
                R3.setText(NumToWord(NumSum + r.nextInt(100)).toUpperCase());
                R4.setText(NumToWord(NumSum + r.nextInt(10)).toUpperCase());
            }while(R1.getText().toString().equals(R2.getText().toString())||
                    R1.getText().toString().equals(R2.getText().toString())||
                    R1.getText().toString().equals(R3.getText().toString())||
                    R1.getText().toString().equals(R4.getText().toString())||
                    R2.getText().toString().equals(R3.getText().toString())||
                    R2.getText().toString().equals(R4.getText().toString())||
                    R3.getText().toString().equals(R4.getText().toString())||
                    R1.getText().toString().equals(NumToWord(NumSum ))||
                    R2.getText().toString().equals(NumToWord(NumSum ))||
                    R3.getText().toString().equals(NumToWord(NumSum ))||
                    R4.getText().toString().equals(NumToWord(NumSum ))
                    );

            switch(r.nextInt(4))
            {
                case 0: R1.setText(NumToWord(NumSum ).toUpperCase());break;
                case 1: R2.setText(NumToWord(NumSum ).toUpperCase());break;
                case 2: R3.setText(NumToWord(NumSum ).toUpperCase());break;
                case 3: R4.setText(NumToWord(NumSum ).toUpperCase());break;

            }

        }
    }.start();

}
        String NumToWord(int num) {

        if(num<0)return ("minus " + NumToWord(-num));

        if (num < 20 ) {

            String T="";
            switch (num) {
                case (0):

                    T = "zero";
                    break;

                case (1):
                    T =  "one";
                    break;

                case (2):
                    T = "two";
                    break;

                case (3):
                    T =  "three";
                    break;

                case (4):
                    T =  "four";
                    break;

                case (5):
                    T =  "five";
                    break;

                case (6):
                    T =  "six";
                    break;

                case (7):
                    T =  "seven";
                    break;

                case (8):
                    T =  "eight";
                    break;

                case (9):
                    T =  "nine";
                    break;

                case (10):
                    T =  "ten";
                    break;

                case (11):
                    T =  "eleven";
                    break;

                case (12):
                    T =  "twelve";
                    break;

                case (13):
                    T =  "thirteen";
                    break;

                case (14):
                    T =  "fourteen";
                    break;

                case (15):
                    T =  "fifteen";
                    break;

                case (16):
                    T = "sixteen";

                    break;
                case (17):
                    T =  "seventeen";

                    break;
                case (18):
                    T = "eighteen";
                    break;

                case (19):
                    T =  "nineteen";
                    break;

            }return T;

        } else if (num < 100) {
            String T = "";
            switch (num / 10) {
                case (2):
                    T = "twenty";
                    break;

                case (3):
                    T = "thirty";
                    break;

                case (4):
                    T = "forty";
                    break;

                case (5):
                    T = "fifty";
                    break;

                case (6):
                    T = "sixty";
                    break;

                case (7):
                    T = "seventy";
                    break;

                case (8):
                    T = "eighty";
                    break;

                case (9):
                    T = "ninety";
                    break;

            }
            if((num - (num / 10)*10)==0)return T;
            return T + " " + NumToWord(num - (num / 10)*10);

        } else if (num > 99 && num < 1000) {
            if((num - (num / 100) * 100)==0)return (NumToWord(num / 100) + " hundred");
            return (NumToWord(num / 100) + " hundred and " + NumToWord(num - (num / 100) * 100));

        } else if (num > 999 && num < 10000) {
            if((num - (num / 1000) * 1000)==0)return (NumToWord(num / 100) + " thousand");
            return (NumToWord(num / 1000) + " thousand " + NumToWord(num - (num / 1000) * 1000));

        }
        if((num - (num / 10000) * 10000)==0)return (NumToWord(num / 100) + " million");
        return (NumToWord(num / 10000) + " million " + NumToWord(num - (num / 10000) * 10000));

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
    public void ButtonBeep() {

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.beep1);
        mp.start();
        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(50);

    }
}
