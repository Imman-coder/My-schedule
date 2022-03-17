package com.anb.schedule;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    Project_api_handler data_bucket;
    TabLayout tabLayout;
    ViewPager viewPager;
    RecyclerView timeRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        timeRV = findViewById(R.id.timeRV);

        final TabAdapter adapter = new TabAdapter(this,this.getSupportFragmentManager(), tabLayout.getTabCount());
        data_bucket = new Project_api_handler(getApplicationContext());

        try {
            JSONObject array = new JSONObject(data_bucket.get_table());
            JSONArray times = array.getJSONArray("Time_Stamps");
            TimeAdapter timeAdapter = new TimeAdapter(this,times);
            timeRV.setAdapter(timeAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        viewPager.setCurrentItem(day-2);
        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        };

    }

    @Override
    public void onResume(){
        super.onResume();
    }


}