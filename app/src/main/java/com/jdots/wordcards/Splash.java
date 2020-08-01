package com.jdots.wordcards;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.jdots.wordcards.SelectActivity;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);

        try {
            VideoView videoHolder = (VideoView) findViewById(R.id.videoView);

            Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash);
            videoHolder.setVideoURI(video);


            videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    jump();
                }
            });
            videoHolder.start();
        } catch (Exception ex) {
            jump();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        jump();
        return true;
    }

    private void jump() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, SelectActivity.class));
        finish();
    }}