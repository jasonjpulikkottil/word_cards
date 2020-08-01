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
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class SelectActivity extends AppCompatActivity {

    public static final String TOKEN = "com.jdots.TOKEN";
    InterstitialAd mInterstitialAd;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        ButtonBlop();
        switch (item.getItemId()) {
            case R.id.action_words:
                Intent  i = new Intent(SelectActivity.this, WordsActivity.class);
                startActivity(i);
                return true;
            case R.id.action_about:
                ImageView image = new ImageView(this);
                image.setImageResource(R.drawable.jdotslab);

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(this)
                                .setView(image)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });

                builder.create().show();

                return true;
            case R.id.action_chlog:


                AlertDialog.Builder builder2 =new AlertDialog.Builder(this)
                        .setTitle("\t\t\t\t\t\tCHANGELOG")
                        .setMessage("\n\nVarious Bug fixes.\nAdded new words\nUpdated content.\n\n\n")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder2.create().show();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_select);


        AdView MobAdView;
        MobAdView = (AdView) findViewById(R.id.memadselect);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        MobAdView.loadAd(adRequest);



        // Get the Main Linear layout of the entire activity
        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.MAINLAYOUT1);


        LinearLayout.LayoutParams bannerParameters =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);



        MobileAds.initialize(this,getString(R.string.app_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_id_inter));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        Button Btn1 = (Button) findViewById(R.id.vocbut);
        Button Btn2 = (Button) findViewById(R.id.membut);
        Button Btn3 = (Button) findViewById(R.id.matbut);
        Button Btn4 = (Button) findViewById(R.id.colbut);

        AnimateButtons();
        Btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ButtonBlop();

                if (mInterstitialAd.isLoaded())
                {
                    mInterstitialAd.show();
                } else
                {

                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }

                Intent i = new Intent(SelectActivity.this, LevelActivity.class);
                i.putExtra(TOKEN,"VOC");
                startActivity(i);
            }
        });
        Btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ButtonBlop();
                if (mInterstitialAd.isLoaded())
                {
                    mInterstitialAd.show();
                } else
                {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }

                Intent i = new Intent(SelectActivity.this, LevelActivity.class);
                i.putExtra(TOKEN,"MEM");
                startActivity(i);
            }
        });
        Btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ButtonBlop();
                if (mInterstitialAd.isLoaded())
                {
                    mInterstitialAd.show();
                } else
                {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }

                Intent i = new Intent(SelectActivity.this,MathActivity.class);

                startActivity(i);
            }
        });
        Btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ButtonBlop();
                if (mInterstitialAd.isLoaded())
                {
                    mInterstitialAd.show();
                } else
                {
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                }

                Intent i = new Intent(SelectActivity.this,ColourActivity.class);

                startActivity(i);
            }
        });


    }

    public void AnimateButtons() {
        final Button puz1 = (Button) findViewById(R.id.membut);
        final Button puz2 = (Button) findViewById(R.id.vocbut);
        final Button puz3 = (Button) findViewById(R.id.matbut);
        final Button puz4 = (Button) findViewById(R.id.colbut);

        final Animation Anim1 = AnimationUtils.loadAnimation(this, R.anim.zoomslow);

        puz1.setAnimation(Anim1);
        puz2.setAnimation(Anim1);
        puz3.setAnimation(Anim1);
        puz4.setAnimation(Anim1);
    }
    public void ButtonBlop() {


        final MediaPlayer mp = MediaPlayer.create(this, R.raw.blop);
        mp.start();
        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(50);

    }



    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded())
        {
            mInterstitialAd.show();
        } else
        {
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
        super.onBackPressed();
    }
}

