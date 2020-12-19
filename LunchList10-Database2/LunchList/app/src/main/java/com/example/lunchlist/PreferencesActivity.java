package com.example.lunchlist;

import android.os.Bundle;
import android.preference.PreferenceFragment;

//class to control the preferences screen
public class PreferencesActivity extends android.preference.PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set fragment for preferences screen
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MyPreferenceFragment()).commit();

    }//end onCreate() method

    public static class MyPreferenceFragment extends PreferenceFragment {

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //add xml to fragment
            addPreferencesFromResource(R.xml.preferences);
        }//end onCreate() method

    }//end MyPreferenceFragment class

}//end PreferencesActivity class