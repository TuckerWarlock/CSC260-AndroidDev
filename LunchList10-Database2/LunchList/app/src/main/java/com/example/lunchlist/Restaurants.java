package com.example.lunchlist;

/*
Class to set name, address, and restaurant type
 */

public class Restaurants {
    //variables
    private String restaurantName = "";
    private String restaurantAddress = "";
    private String restaurantType = "";
    private String restaurantNote = "";
    private String Id = "";

    //get name from user input
    public String getRestaurantName(){
        return this.restaurantName;
    }
    //set name of contact
    public void setRestaurantName(String name){
        this.restaurantName = name;
    }

    //get phone number from user
    public String getRestaurantAddress(){
        return this.restaurantAddress;
    }
    //set phone number
    public void setRestaurantAddress(String address){
        this.restaurantAddress = address;
    }

    //get phone number type
    public String getRestaurantType(){
        return this.restaurantType;
    }
    //set type of phone number
    public void setRestaurantType(String type){
        this.restaurantType = type;
    }

    //get restaurant note
    public String getRestaurantNote(){
        return this.restaurantNote;
    }
    //set restaurant note
    public void setRestaurantNote(String note){
        this.restaurantNote = note;
    }

    //get restaurant id
    public String getId(){
        return this.Id;
    }
    //set restaurant id
    public void setId(String id){
        this.Id = id;
    }

    @Override
    public String toString(){
        return "Current Contents of this Restaurant: \nName:" +getRestaurantName() + "\nAddress: "
                + getRestaurantAddress() + "\nType: " + getRestaurantType() + "\nNote:" + getRestaurantNote();
    }
}
