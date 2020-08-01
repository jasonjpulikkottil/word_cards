package com.jdots.wordcards;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class WordsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams linLayoutParam =
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        linLayout.setGravity(Gravity.CENTER );
        linLayoutParam.setMargins(10, 10, 10, 10);
        linLayout.setLayoutParams( linLayoutParam);

        ViewGroup.LayoutParams lpView = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);


        final BadgeDrawable drawable =
                new BadgeDrawable.Builder()
                        .type(BadgeDrawable.TYPE_WITH_TWO_TEXT_COMPLEMENTARY)
                        .badgeColor(0xff991111)
                        .text1("LEVEL")
                        .text2(" " + String.valueOf(getLevel(prefs.getInt("TOTAL",0))) + " ")
                        .textSize(100)
                        .build();
        SpannableString spannableString =
                new SpannableString(TextUtils.concat(drawable.toSpannable(),"\n"));

        TextView Badge=new TextView(this);
        Badge.setText(spannableString);
        Badge.setGravity(Gravity.CENTER );
        linLayout.addView(Badge);



        TextView WordCount=new TextView(this);
        WordCount.setText(TextUtils.concat("Number of words unlocked : " ,
                String.valueOf(prefs.getInt("TOTAL",0)),"\n\nCOINS : ",
                String.valueOf(prefs.getInt("COINS", 0)),
                "\n\nUnlocked Words :\n\n"));
        WordCount.setTextColor(0xff0000ff);
        WordCount.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
        linLayout.addView(WordCount);

        TextView[] T  = new TextView[10000];
        String Word[]=SettingsEdit();
        for(int i=0 ; i < prefs.getInt("TOTAL",0) ; i++)
        {
            T[i]=new TextView(this);
            T[i].setText(Word[i]);
            T[i].setTextColor(0xff3333ff);
            T[i].setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
            T[i].setLayoutParams(lpView);
            linLayout.addView(T[i]);
        }

        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(lpView);
        scroll.setBackgroundResource(R.drawable.alphabet);
        scroll.addView(linLayout);
        setContentView(scroll, linLayoutParam);



    }
    public String[] SettingsEdit() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
       String A[]=new String[10000];
        int i=0 ;
        i=prefs.getInt("TOTAL",0);

        for (int j=0;j<=i;j++) {

          A[j]=  prefs.getString("WORD" + j,null);

        } return A;
    }
    public int getLevel(int Num)
    {
       for(int i=0;;i++)
       {
           if (Num<=i*i)  return i;
       }
    }

}
