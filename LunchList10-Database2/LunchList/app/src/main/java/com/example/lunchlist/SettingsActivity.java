package com.example.lunchlist;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //call parent class onCreate
        super.onCreate(savedInstanceState);
        //Ties the settings layout to this java class
        setContentView(R.layout.settings);

        //read Settings file back in and set value
        boolean playMusicSetting = false;

        //use Settings class we created with methods
        Settings settings = new Settings(this);
        playMusicSetting = settings.GetPlayMusic();

        CheckBox playMusic = findViewById(R.id.playMusic);
        if(playMusicSetting){
            playMusic.setChecked(true);
        }//end IF

    }
    public void SaveSettingsClicked(View view){

        //get a reference to the checkbox for play music
        CheckBox playMusic = findViewById(R.id.playMusic);

        //local variable
        Boolean playMusicSetting = false;

        //check if music in Settings is checked
        if(playMusic.isChecked()){
            playMusicSetting = true;
        }

        //instance of Settings class
        Settings settings = new Settings(this);
        settings.SetPlayMusic(playMusicSetting);

    }//end SaveSettingsClicked() method

}//end SettingsActivity class
