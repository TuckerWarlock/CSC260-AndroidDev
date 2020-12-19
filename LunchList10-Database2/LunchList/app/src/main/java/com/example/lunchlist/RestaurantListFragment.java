package com.example.lunchlist;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.ListFragment;

public class RestaurantListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    Cursor model = null;
    RestaurantAdapter adapter = null;
    Database database;

    public final static String ID_EXTRA = "com.example.lunchlist._ID";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //set the view to the list_fragment XML
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        //return view to the onCreateView() method
        return view;
    }//end View() method

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        //get an instance of dBHelper, this is our database code wrapped into a class
        //because we are in a fragment now, where ever we used to pass this, need to call getActivity
        database = new Database(getActivity());

        //run the query to get all rows from the table restaurants
        model = database.getAll();

        //start the cursor watching, must call getActivity first since we are a fragment now
        getActivity().startManagingCursor(model);

        //Create an adapter
        adapter = new RestaurantAdapter(getActivity(), model);

        //let the list know what adapter we are using
        setListAdapter(adapter);

        //handle onItemClick
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO handle list item being clicked

        //get intent for DetailActivity
        Intent intent = new Intent(getActivity(), DetailActivity.class);

        //put extra passes parameters to the DetailActivity
        intent.putExtra(ID_EXTRA, String.valueOf(id));

        //start Detail Activity
        startActivity(intent);

    }//end onItemClick() method

    @Override
    public void onDestroy(){
        super.onDestroy();
        //close our database
        database.close();
    }

    static class RestaurantHolder{
        //variables
        private TextView title;
        private TextView address;
        private ImageView icon;

        //constructor
        RestaurantHolder(View row){
            title = row.findViewById(R.id.Name);
            address = row.findViewById(R.id.Address);
            icon = row.findViewById(R.id.icon);
        }//end constructor

        void populateFrom(Cursor cursor, Database database){
            title.setText(database.getRestaurantName(cursor));
            address.setText(database.getRestaurantAddress(cursor));

            if(database.getRestaurantType(cursor).equals("Sit-Down")){
                icon.setImageResource(R.drawable.fork);
            }
            if(database.getRestaurantType(cursor).equals("Take-Out")){
                icon.setImageResource(R.drawable.bag);
            }
            if(database.getRestaurantType(cursor).equals("Delivery")){
                icon.setImageResource(R.drawable.car);
            }
        }//end populateForm method

    }//end RestaurantHolder class

    //new Adapter to use the Cursor Adapter
    class RestaurantAdapter extends CursorAdapter {
        //new Restaurant Adapter object and super constructor
        public RestaurantAdapter(Context context, Cursor cursor){
            super(context, cursor, FLAG_REGISTER_CONTENT_OBSERVER);
        }//end constructor

        @Override
        public void bindView(View row, Context context, Cursor cursor){
            RestaurantHolder holder = (RestaurantHolder)row.getTag();
            holder.populateFrom(cursor, database);
        }//end bindView() method

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent){
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View row = inflater.inflate(R.layout.listview_row, parent, false);
            RestaurantHolder holder = new RestaurantHolder(row);
            row.setTag(holder);

            return row;
        }//end View

    }//end RestaurantAdapter

}//end RestaurantListFragment class
