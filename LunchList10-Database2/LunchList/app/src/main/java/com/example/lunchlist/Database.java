package com.example.lunchlist;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    //class level variables
    private static final String DATABASE_NAME = "restaurants.db";
    private static final int SCHEMA_VERSION = 1;

    //constructor for super
    public Database(Context context){
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }//end Database constructor

    @Override
    public void onCreate(SQLiteDatabase database){
        //create table
        String sql = "CREATE TABLE restaurants (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, type TEXT, note TEXT);";
        //execute SQL statement
        database.execSQL(sql);
    }//end onCreate() method

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        //method to modify existing database entries
    }//end onUpgrade() method

    //insert row into restaurants table
    public void insert(Restaurants restaurants){
        //create sql insert statement with helper class
        ContentValues contentValues = new ContentValues();

        //use content value to insert value into column
        contentValues.put("name", restaurants.getRestaurantName());
        contentValues.put("address", restaurants.getRestaurantAddress());
        contentValues.put("type", restaurants.getRestaurantType());
        contentValues.put("note", restaurants.getRestaurantNote());

        //write values to database
        getWritableDatabase().insert("restaurants", "abc", contentValues);

    }//end insert() method

    //method to get the cursor by the SQL id
    public Cursor getById(String id){
        String[] args = {id};
        String sql = "SELECT * FROM restaurants WHERE _ID=?";

        Cursor cursor = getReadableDatabase().rawQuery(sql, args);

        return cursor;
    }//end Cursor getById constructor method


    //get cursor that contains all rows
    public Cursor getAll(){
        String sql = "SELECT _id, name, address, type, note FROM restaurants ORDER BY name";
        return getReadableDatabase().rawQuery(sql, null);
    }//end Cursor constructor

    //Use Getters and Setters from Restaurant class
    //helper method for name value
    public String getRestaurantName(Cursor c){
        return c.getString(c.getColumnIndex("name"));
    }//end getName

    //helper method for address value
    public String getRestaurantAddress(Cursor c){
        return c.getString(c.getColumnIndex("address"));
    }//end getAddress

    //helper method for type value
    public String getRestaurantType(Cursor c){
        return c.getString(c.getColumnIndex("type"));
    }//end getType

    //helper method for note value
    public String getRestaurantNote(Cursor c){
        return c.getString(c.getColumnIndex("note"));
    }//end getNote

    public String getId(Cursor c){

        int id = c.getInt(c.getColumnIndex("_id"));
        return Integer.toString(id);
    }

    //get instance of Restaurant from current row
    public Restaurants getRestaurant(Cursor c){
        //create instance of Restaurant class
        Restaurants restaurants = new Restaurants();

        //fill each value with cursor column
        restaurants.setRestaurantName(getRestaurantName(c));
        restaurants.setRestaurantAddress(getRestaurantAddress(c));
        restaurants.setRestaurantType(getRestaurantType(c));
        restaurants.setRestaurantNote(getRestaurantNote(c));
        restaurants.setId(getId(c));

        //return the restaurant
        return restaurants;
    }//end of getRestaurant method

    public Restaurants getRestaurant(String id){
        //create an array of parameters to pass to query
        String[] args = {id};

        //? is a place holder that get replaced with data in args array
        String sql = "SELECT * FROM restaurants WHERE _id=?";

        //get a cursor from the database
        Cursor cursor = getReadableDatabase().rawQuery(sql, args);

        //go to first row in the result set
        cursor.moveToFirst();

        //pointing to the correct position
        Restaurants restaurant = getRestaurant(cursor);

        return restaurant;
    }

    public void update(Restaurants restaurant){
        //create sql insert statement with helper class
        ContentValues contentValues = new ContentValues();

        //use content value to insert value into column
        contentValues.put("name", restaurant.getRestaurantName());
        contentValues.put("address", restaurant.getRestaurantAddress());
        contentValues.put("type", restaurant.getRestaurantType());
        contentValues.put("note", restaurant.getRestaurantNote());
        contentValues.put("_id", restaurant.getId());

        //array of string
        String[] args = {restaurant.getId()};

        //update value in database
        getWritableDatabase().update("restaurants", contentValues, "_id=?", args);
    }

}//end Database class
