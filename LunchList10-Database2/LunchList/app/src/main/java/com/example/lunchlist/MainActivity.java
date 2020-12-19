package com.example.lunchlist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText note = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //call parent class onCreate
        super.onCreate(savedInstanceState);
        //Ties the layout to this java class
        setContentView(R.layout.activity_main);

        note = findViewById(R.id.Note);

    }//end onCreate

    //creates our options menu (three dots/hamburger)
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //inflate the menu - adds items to action
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }//onCreateOptionsMenu

    //handle menu items being selected
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //actions with ID settings
        switch(item.getItemId()){

            //show settings was selected
            case R.id.settings:
                //show new preferences activity
                startActivity(new Intent(this, PreferencesActivity.class));
                break;
            //exit button
            case R.id.exit:
                finish();
                break;
            case R.id.note:
                //set the note to something if no contact is selected
                if(note == null || note.getText().toString().equals("")){
                    Toast.makeText(this, "No Restaurant Selected", Toast.LENGTH_LONG).show();
                }else{
                    //get text from Note string and show in toast
                    String Note = note.getText().toString();
                    //Toast android widget
                    Toast.makeText(this, Note,
                            Toast.LENGTH_SHORT).show();
                }
                break;
            //show the About Activity
            case R.id.about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            //show the Detail Activity to add a new restaurant
            case R.id.add:
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }//end switch
        return true;
    }//end onOptionItemSelected

    //add view of DetailActivity
    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener(){
                public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    startActivity(intent);
                }//end onItemClick() method
            };//end AdapterView constructor


}//end MainActivity class