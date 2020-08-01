package com.jdots.wordcards;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class LevelActivity extends AppCompatActivity {
       public static final String TOKEN = "com.jdots.TOKEN";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_level);




        // Get the Main Linear layout of the entire activity
        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.MAINLAYOUT2);


        LinearLayout.LayoutParams bannerParameters =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        // Add to main Layout






        final InterstitialAd mInterstitialAd;
        MobileAds.initialize(this,getString(R.string.app_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_id_inter));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        final Button L5 = (Button) findViewById(R.id.Level5);
        final Button L6 = (Button) findViewById(R.id.Level6);
        final Button L7 = (Button) findViewById(R.id.Level7);
        final Button L8 = (Button) findViewById(R.id.Level8);
        final Button L9 = (Button) findViewById(R.id.Level9);

        AnimateButtons();

        Intent intent = getIntent();
        final String Ac= intent.getStringExtra(TOKEN);

        L5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonBlop();
                starter("5",Ac);
            }
        });
        L6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonBlop();
                starter("6",Ac);
            }
        });
        L7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonBlop();
                starter("7",Ac);
            }
        });
        L8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonBlop();
                starter("8",Ac);
            }
        });
        L9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButtonBlop();
                starter("9",Ac);
            }
        });
    }

    public void starter(String Letter,String ActivityCode) {

        Intent i;
        if(ActivityCode.equals("VOC")) i = new Intent(LevelActivity.this, MainActivity.class);
        else i = new Intent(LevelActivity.this, MemActivity.class);
        i.putExtra(TOKEN, Letter);
        startActivity(i);
    }

    public void AnimateButtons() {
            final Button L5 = (Button) findViewById(R.id.Level5);
            final Button L6 = (Button) findViewById(R.id.Level6);
            final Button L7 = (Button) findViewById(R.id.Level7);
            final Button L8 = (Button) findViewById(R.id.Level8);
            final Button L9 = (Button) findViewById(R.id.Level9);

            final Animation Anim1 = AnimationUtils.loadAnimation(this, R.anim.zoom);

                    L5.setAnimation(Anim1);
                    L6.setAnimation(Anim1);
                    L7.setAnimation(Anim1);
                    L8.setAnimation(Anim1);
                    L9.setAnimation(Anim1);
                }
    public void ButtonBlop() {

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.blop);
        mp.start();
        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(50);


        final InterstitialAd mInterstitialAd;
        MobileAds.initialize(this,getString(R.string.app_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_id_inter));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        if (mInterstitialAd.isLoaded())
        {
            mInterstitialAd.show();
        } else
        {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
    }


}

