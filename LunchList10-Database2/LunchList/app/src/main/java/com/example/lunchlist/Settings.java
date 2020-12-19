package com.example.lunchlist;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Settings {
    //Context is a class Android injects we need for openFileOutput and openFileInput
    private Context fileContext;
    //Constants
    private final String SettingsFile = "Settings";
    private final  String MusicOn = "ON";
    private final  String MusicOff = "OFF";

    //constructor, calling activity should pass this
    public Settings(Context fileContext){
        this.fileContext = fileContext;
    }

    public void SetPlayMusic(boolean playMusic){

        String playMusicValue;

        if(playMusic){
            playMusicValue = MusicOn;
        }//end IF
        else{
            playMusicValue = MusicOff;
        }//end ELSE

        FileOutputStream outputStream;

        //openFileOutput opens the file in your applications directory
        try {
            outputStream = fileContext.getApplicationContext()
                    .openFileOutput(SettingsFile, Context.MODE_PRIVATE);
            //write the whole file by turning the string value into bytes
            outputStream.write(playMusicValue.getBytes());
            //close the stream
            outputStream.close();
        }//end TRY
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }//end CATCH
        catch (IOException e) {
            e.printStackTrace();
        }//end CATCH
    }//end SetPlayMusic method

    //all wrapped up, calling class needs no knowledge of
    //file IO or where or how the file is stored
    public boolean GetPlayMusic(){

        //read the file where settings values are stored
        FileInputStream fileInputStream;

        try {
            //get a FileInputStream by calling openFileInput
            //and pointing to the Settings file
            fileInputStream = fileContext.getApplicationContext()
                    .openFileInput(SettingsFile);

            //get the number of bytes in the file so we know
            //how big to make the byte array
            long numberBytes = fileInputStream.getChannel().size();

            //array of byte that is passed to read method
            byte[] fileBytes = new byte[(int) numberBytes];

            //returns an int with how many bytes were read or -1
            int result = fileInputStream.read(fileBytes);

            //turn the array of bytes into a string
            String fileContents = new String(fileBytes);

            if(fileContents.equals(MusicOn)){
                return true;
            }//end IF
        }//end TRY
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }//end CATCH
        catch (IOException e) {
            e.printStackTrace();
        }//end CATCH
        return false;

    }//end GetPlayMusic method

}//end Settings class
