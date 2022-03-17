package com.anb.schedule;

import android.content.Context;
import android.content.SharedPreferences;

public class Bucket_api_handler {

    public static final String MY_PREFS_NAME = "Buckets";
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Bucket_api_handler(Context context){
        pref = context.getSharedPreferences(MY_PREFS_NAME, 0);
        editor = pref.edit();
    }

    public String get_table()
    {
        return pref.getString("table", "0");
    }

    public int get_year()
    {
        return pref.getInt("year", 0);
    }


    public String get_section()
    {
        return pref.getString("section", "0");
    }


    public boolean has_cred()
    {
        return !pref.getString("section", "121121").equals("121121") && !pref.getString("table", "456456").equals("456456");
    }



    public void set_section(String section)
    {
        editor.putString("section", section);
        editor.apply();
    }

    public void set_year(int year) {
        editor.putInt("year", year);
        editor.apply();
    }

    public void set_table(String json) {
        editor.putString("table", json);
        editor.apply();
    }

    public boolean clear_all(boolean sure){
        if(sure){
            editor.clear();
            editor.apply();
            return true;
        }
        return false;
    }



}
