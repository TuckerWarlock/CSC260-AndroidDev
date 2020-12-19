package com.example.lunchlist;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener{

    //global variable declaration
    EditText name = null;
    EditText address = null;
    RadioGroup types = null;
    EditText note = null;
    //get a reference to currently selected restaurant
    Restaurants currentRestaurant = null;
    Database database;
    String restaurantId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //find the save button
        View saveButton = findViewById(R.id.SaveButton);
        //tell the save button to list for onClick
        saveButton.setOnClickListener(this);

        //find the cancel button
        View cancelButton = findViewById(R.id.CancelButton);
        //tell the save button to list for onClick
        cancelButton.setOnClickListener(this);

        //get the parameter for what restaurant id to edit
        restaurantId = getIntent().getStringExtra(RestaurantListFragment.ID_EXTRA);

        //get instance of database class
        database = new Database(this);

        //reference to UI controls
        name = findViewById(R.id.Name);
        address = findViewById(R.id.Address);
        types = findViewById(R.id.Type);
        note = findViewById(R.id.Note);

        //data from database and put into the UI controls
        if (restaurantId != null){
            load();
        }//end IF

    }//end onCreate() method


    @Override
    public void onClick(View view) {
        //find the name edit text field
        if (view.getId() == R.id.SaveButton) {

            //find the name edit text field
            name = findViewById(R.id.Name);
            //get restaurant name, set toString
            String Name = name.getText().toString();

            //find the address edit text field
            address = findViewById(R.id.Address);
            //get address, set toString
            String Address = address.getText().toString();

            //find the address edit text field
            note = findViewById(R.id.Note);
            //get address, set toString
            String Note = note.getText().toString();

            //find radio button group
            types = findViewById(R.id.Type);
            String type;

            //restaurantId = currentRestaurant.getId();

            //determine radio button selection
            switch (types.getCheckedRadioButtonId()) {
                case R.id.delivery:
                    type = "Delivery";
                    break;
                case R.id.sitdown:
                    type = "Sit-Down";
                    break;
                case R.id.takeout:
                    type = "Take-Out";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + types.getCheckedRadioButtonId());
            }//end SWITCH

            //create instance of Restaurant class
            Restaurants myRestaurant = new Restaurants();
            myRestaurant.setRestaurantName(Name);
            myRestaurant.setRestaurantAddress(Address);
            myRestaurant.setRestaurantNote(Note);
            myRestaurant.setRestaurantType(type);
            myRestaurant.setId(restaurantId);

            if(myRestaurant == null){
                //only create instance for new restaurants
                myRestaurant = new Restaurants();
            }

            //add mode, do an insert
            if(restaurantId == null){
                database.insert(myRestaurant);
            }else{
                database.update(myRestaurant);
            }

            //reset all fields for next add
            name.getText().clear();
            address.getText().clear();
            note.getText().clear();
            types.clearCheck();
            //move focus back to name field
            name.requestFocus();

            //close the form when done
            finish();
        }//end IF

        if (view.getId() == R.id.CancelButton){
            //find the name edit text field
            name = findViewById(R.id.Name);
            //find the address edit text field
            address = findViewById(R.id.Address);
            //find the type radio button
            types = findViewById(R.id.Type);
            //find the address edit text field
            note = findViewById(R.id.Note);

            //reset all fields for next add
            name.getText().clear();
            address.getText().clear();
            note.getText().clear();
            types.clearCheck();
            //move focus back to name field
            name.requestFocus();

            //close the form when done
            finish();
        }//end IF

    }//end onClick() method

    @Override
    public void onDestroy(){
        super.onDestroy();
        //close our database
        database.close();
    }

    private void load(){
        //the restaurant for the associate id
        currentRestaurant = database.getRestaurant(restaurantId);

        name.setText(currentRestaurant.getRestaurantName());
        address.setText(currentRestaurant.getRestaurantAddress());

        //determine radio button selection
        if(currentRestaurant.getRestaurantType().equals("Delivery")){
            types.check(R.id.delivery);
        }else if(currentRestaurant.getRestaurantType().equals("Sit-Down")) {
            types.check(R.id.sitdown);
        }else if(currentRestaurant.getRestaurantType().equals("Take-Out")) {
            types.check(R.id.takeout);
        }

        note.setText(currentRestaurant.getRestaurantNote());
    }

}//end DetailActivity class