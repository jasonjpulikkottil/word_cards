package com.jdots.wordtwist;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class WordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams linLayoutParam =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);


        ViewGroup.LayoutParams lpView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        TextView[] T  = new TextView[100];
        String Word[]=SettingsEdit();
        for(int i=0 ; i < prefs.getInt("TOTAL",0) ; i++)
        {
            T[i]=new TextView(this);
            T[i].setText(Word[i]);

            T[i].setLayoutParams(lpView);
            linLayout.addView(T[i]);
        }

        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(lpView);
        scroll.addView(linLayout);
        setContentView(scroll, linLayoutParam);

    }
    public String[] SettingsEdit() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
       String A[]=new String[10000];
        int i=0 ;
        i=prefs.getInt("TOTAL",0);

        Toast.makeText(getApplicationContext(),"Total "+i+" words unlocked" , Toast.LENGTH_SHORT).show();
        for (int j=0;j<=i;j++) {

          A[j]=  prefs.getString("WORD" + j,null);

        } return A;
    }

}
