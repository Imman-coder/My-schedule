package com.anb.schedule;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Calendar;

public class Project_api_handler {

    public static final String MY_PREFS_NAME = "Buckets";
    public static final String BRANCH = "Branch";
    public static final String YEAR = "Year";
    public static final String SECTION = "Section";
    public static final String TABLE = "Table";

    public static final String[] BRANCH_LIST = {"CSe","EE","EEE","Mech"};
    public static final String[] YEAR_LIST = {"1st Year","2nd Year","3rd Year","4th Year",};
    public static final String[] SECTION_LIST = {"A","B","C","D"};

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Project_api_handler(Context context) {
        pref = context.getSharedPreferences(MY_PREFS_NAME, 0);
        editor = pref.edit();
    }

    public String get_table() {
        return pref.getString(TABLE, "0");
    }

    public String get_branch() {
        return pref.getString(BRANCH, "0");
    }

    public int get_year() {
        return pref.getInt(YEAR, 0);
    }


    public String get_section() {
        return pref.getString(SECTION, "0");
    }


    public boolean has_cred() {
        return !pref.getString(SECTION, "121121").equals("121121") && !pref.getString(BRANCH, "456456").equals("456456") && !(pref.getInt(YEAR,0)==0) ;
    }


    public void set_section(String section) {
        editor.putString(SECTION, section);
        editor.apply();
    }
    public void set_branch(String branch) {
        editor.putString(BRANCH, branch);
        editor.apply();
    }

    public void set_year(int year) {
        editor.putInt(YEAR, year);
        editor.apply();
    }

    public void set_table(String json) {
        editor.putString(TABLE, json);
        editor.apply();
    }

    public boolean clear_all(boolean sure) {
        if (sure) {
            editor.clear();
            editor.apply();
            return true;
        }
        return false;
    }


    public static class Time{
        JSONArray times;
        Time(JSONArray _times){
            times=_times;
        }
        public int get_currentTime()
        {
            Calendar calendar = Calendar.getInstance();
            return (calendar.get(Calendar.HOUR_OF_DAY)*60)+calendar.get(Calendar.MINUTE);
        }
        public String time_to_string(int time){
            return time/60+":"+time%60;
        }
        public int get_time_stamp_place(int time) throws JSONException {
            for(int num = times.length()-1 ; num >= 0  ; num--){
                if(times.getInt(num)<time)
                    return num;
            }
            return -1;
        }

        public int get_time_progress() throws JSONException {
            int stamp_pos = get_time_stamp_place(get_currentTime());
            double time_passed = get_currentTime()-times.getInt(stamp_pos);
            double time_span = times.getInt(stamp_pos+1)-times.getInt(stamp_pos);
            double peers = (time_passed/time_span)*100;
            return (int)peers;
        }
    }


}
