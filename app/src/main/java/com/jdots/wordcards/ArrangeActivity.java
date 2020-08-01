package com.jdots.wordcards;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.jdots.wordcards.MemActivity.TOKEN;
import static com.jdots.wordcards.MemActivity.TOKEN2;

public class ArrangeActivity extends AppCompatActivity implements View.OnClickListener {
    Button[] T = new Button[100];
    String[] Temp=new String[100];
    Button Check;
    TextView Info  ;
    TextView ReWords[] = new TextView[100];
    LinearLayout linLayout;
    ViewGroup.LayoutParams lpView;
    int WordLength;
    int wordi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String text = extras.getString(TOKEN2);
          WordLength = Integer.valueOf(text);
        }

        linLayout = new LinearLayout(this);
        ViewGroup.LayoutParams lpView;

        wordi=0;
        linLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams linLayoutParam =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        linLayoutParam.setMargins(10,10,10,10);
        linLayout.setGravity(Gravity.CENTER);
        linLayoutParam.setMargins(15, 15, 15, 15);
        linLayout.setLayoutParams(linLayoutParam);

        lpView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

       final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Random r = new Random();
        int j;
        String Tmp;

        Info=new TextView(this);
        Info.setText("Click the words in order");
        Info.setTextSize(24);
        Info.setGravity(Gravity.CENTER);
        Info.setTextColor(0xff0000ff);
        linLayout.addView(Info);

        for (int i = 0; i < prefs.getInt("MEMCOUNT", 2); i++) {
            T[i] = new Button(this);
            T[i].setText("0");
            Temp[i] = prefs.getString("MEMWORD" + i, "");
            T[i].setTextColor(0xffffff00);
            T[i].setLayoutParams(linLayoutParam);
            T[i].setOnClickListener(this);
           T[i].setBackground(getResources().getDrawable(R.drawable.roundshape2));
        }

        for (int i = 0; i < prefs.getInt("MEMCOUNT", 2); i++)
        {

            do {
                j = r.nextInt(prefs.getInt("MEMCOUNT", 2));
                Tmp = Temp[j];
                Temp[j]="0";

            }while(Tmp.equals("0"));

            if(T[i].getText().toString().equals("0"))T[i].setText(Tmp);

            linLayout.addView(T[i]);

        }

        Check=new Button(this);
        Check.setText("CHECK");
        Check.setLayoutParams(linLayoutParam);
        Check.setBackground(getResources().getDrawable(R.drawable.roundshape));
        linLayout.addView(Check);

        Check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ButtonBlop();
                if(WordsEqual(WordEdit(),ReWords,wordi)) {
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                    prefs.edit().putInt("MEMCOUNT", prefs.getInt("MEMCOUNT", 2)+1).apply();
                    prefs.edit().putInt("COINS", prefs.getInt("COINS", 0) + 1).apply();

                }else
                {
                    ButtonBeep();
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                    prefs.edit().putInt("MEMCOUNT", prefs.getInt("MEMCOUNT", 2)-1).apply();
                    if(prefs.getInt("MEMCOUNT", 2)<2)prefs.edit().putInt("MEMCOUNT", 2).apply();

                }
                wordi=0;
                Intent i = new Intent(ArrangeActivity.this, MemActivity.class);
                startActivity(i);

            }});

        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(lpView);
        scroll.setBackgroundResource(R.drawable.alphabet);
        scroll.addView(linLayout);
        setContentView(scroll, linLayoutParam);


    }

    public String[] WordEdit() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String A[] = new String[10000];

        for (int j = 0; j < prefs.getInt("MEMCOUNT", 2); j++) {

            A[j] = prefs.getString("MEMWORD" + j, null);

        }
        return A;
    }

    public int getLevel(int Num) {
        for (int i = 0; ; i++) {
            if (Num <= i * i) return i;
        }
    }


    @Override
    public void onClick(View view) {

        ButtonBlop();
        ReWords[wordi]=new TextView(this);
                ((Button)view).setVisibility(View.GONE);
                ReWords[wordi].setText(((Button)view).getText().toString());
                ReWords[wordi].setTextSize(24);
                ReWords[wordi].setTextColor(0xff0000ff);
                linLayout.addView(ReWords[wordi]);
                wordi++;

    }
    public boolean WordsEqual(String A[],TextView B[],int len)
    {
        Boolean Res=true;

       for(int i=0;i<len;i++)
       {
           if(!(A[len-i-1].equals(B[i].getText().toString()))) Res= false;
       }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ArrangeActivity.this);

        if(len<prefs.getInt("MEMCOUNT", 2))Res= false;
        return Res;
    }
    public void ButtonBlop() {

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.blop);
        mp.start();
        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(50);

    }
    public void ButtonBeep() {

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.beep1);
        mp.start();
        Vibrator vib = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(50);

    }
}
