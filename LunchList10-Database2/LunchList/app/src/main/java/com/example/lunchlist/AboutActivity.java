package com.example.lunchlist;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    //media player variable for audio
    MediaPlayer mediaPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get rid of status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //set activity view to our new graphics class
        setContentView(new GraphicsView(this));

    }//end onCreate

    @Override
    protected void onResume(){
        super.onResume();

        //get preference manager
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        //set checkbox flag variable, use key from preferences.xml
        boolean playMusic = sharedPreferences.getBoolean("PlayMusic", false);

        //play music if setting is on
        if(playMusic){
            //tell media player what file to play
            mediaPlayer = MediaPlayer.create(AboutActivity.this, R.raw.ignition_switches2);
            //left and right volumes
            mediaPlayer.setVolume(1f, 1f);
            //set to loop
            mediaPlayer.setLooping(true);
            //start playing file
            mediaPlayer.start();
        }//end IF

    }//end onResume()

    @Override
    protected void onPause(){
        super.onPause();
        //stop music and clean up?
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
        }//end IF

    }//end onPause

    //new graphics view class with our drawings/shapes/text
    public static class GraphicsView extends View {
        //variables
        private final Path lowerBanner;
        private final Paint myBannerPaint;
        private final Paint textPaint;
        private final Rect rect;
        private final Rect rect2;
        private final Paint rectPaint;
        private final Paint rectPaint2;
        private final Paint circlePaint;
        private final Paint smilePaint;

        //required constructor
        public GraphicsView(Context context) {
            super(context);
            //colors
            int color = Color.rgb(109, 109, 109); //GRAY
            int color2 = Color.rgb(140, 255, 140); //LIGHT GREEN

            //line at bottom (Banner)
            lowerBanner = new Path();
            lowerBanner.setLastPoint(1200, 1800);
            lowerBanner.lineTo(0, 1800);

            //instance of Paint for the line
            myBannerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            myBannerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            myBannerPaint.setColor(color); //gray
            myBannerPaint.setStrokeWidth(300);
            //instance of Paint for text
            textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
            textPaint.setColor(color2); //green
            textPaint.setStrokeWidth(3f);
            textPaint.setTextSize(65);

            //draw left eye
            rect = new Rect(400, 500, 475, 575);
            //paint rectangle
            rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            rectPaint.setColor(Color.BLACK);

            //draw right eye
            rect2 = new Rect(620, 500, 695, 575);
            //paint rectangle
            rectPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
            rectPaint2.setColor(Color.BLACK);

            //paint circle
            circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
            circlePaint.setColor(Color.YELLOW);

            //paint smile
            smilePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            smilePaint.setColor(Color.BLACK);

            //get background color from XML
            setBackgroundResource(R.drawable.background);

        }//end Graphics view constructor

        //override the built in onDraw method
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onDraw (Canvas canvas){

            //draw lower banner with application name and version
            canvas.drawPath(lowerBanner, myBannerPaint);

            //draw text on banner
            canvas.drawText("Tucker's Lunch List", 15, 1750, textPaint);
            canvas.drawText("Version 10 - Database 2", 15, 1820, textPaint);

            //draw circle
            int startX= (getWidth())/2;//for horizontal position
            int startY=(getHeight())/3;//for vertical position
            int radius = 300;
            canvas.drawCircle(startX , startY, radius, circlePaint);

            //draw rectangles
            canvas.drawRect(rect, rectPaint);
            canvas.drawRect(rect2, rectPaint2);

            //draw smile
            canvas.drawArc(getWidth()/3, 550, getWidth()/2 + 175, 850,
                    0F, 180F, false, smilePaint);

        }//end onDraw method

    }//end Graphics view class

}//end About Activity class