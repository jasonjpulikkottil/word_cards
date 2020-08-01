package com.jdots.wordtwist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

public class LevelActivity extends AppCompatActivity {
    public static final String TOKEN = "com.jdots.TOKEN";



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.action_words:
                Intent  i = new Intent(LevelActivity.this, WordsActivity.class);
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
                        .setMessage("\n\n\t\t\t\t\t\tCHANGELOG\n\nVarious Bug fixes.\nAdded new words\nUpdated content.\n\n\n")
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
        setContentView(R.layout.activity_level);

        final Button L5 = (Button) findViewById(R.id.Level5);
        final Button L6 = (Button) findViewById(R.id.Level6);
        final Button L7 = (Button) findViewById(R.id.Level7);
        final Button L8 = (Button) findViewById(R.id.Level8);
        final Button L9 = (Button) findViewById(R.id.Level9);

        AnimateButtons();

        L5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vib.vibrate(50);
                starter("5");
            }
        });
        L6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vib.vibrate(50);
                starter("6");
            }
        });
        L7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vib.vibrate(50);
                starter("7");
            }
        });
        L8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vib.vibrate(50);
                starter("8");
            }
        });
        L9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vib.vibrate(50);
                starter("9");
            }
        });
    }

    public void starter(String Letter) {
        Intent i = new Intent(LevelActivity.this, MainActivity.class);
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
}

