package com.anb.schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    Bucket_api_handler data_bucket;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewpager);

        final TabAdapter adapter = new TabAdapter(this,this.getSupportFragmentManager(), tabLayout.getTabCount());
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
        data_bucket = new Bucket_api_handler(getApplicationContext());
        tt=(TextView)findViewById(R.id.textView2);
        ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
        if (connected)
            new get_request("https://0yomwntoytmqbjsit8zrxq-on.drv.tw/www.immance.com/year1.json").execute();
        else
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_LONG).show();
        time_setter();
    }

    @Override
    public void onResume(){
        super.onResume();
        time_setter();
    }


    public void time_setter(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);
        int act= get_time_cords(hour,minutes);

        ((ImageView)findViewById(R.id.stamp8)).setImageResource(R.drawable.circle_not_passed);
        ((ImageView)findViewById(R.id.stamp7)).setImageResource(R.drawable.circle_not_passed);
        ((ImageView)findViewById(R.id.stamp6)).setImageResource(R.drawable.circle_not_passed);
        ((ImageView)findViewById(R.id.stamp5)).setImageResource(R.drawable.circle_not_passed);
        ((ImageView)findViewById(R.id.stamp4)).setImageResource(R.drawable.circle_not_passed);
        ((ImageView)findViewById(R.id.stamp3)).setImageResource(R.drawable.circle_not_passed);
        ((ImageView)findViewById(R.id.stamp2)).setImageResource(R.drawable.circle_not_passed);
        ((ImageView)findViewById(R.id.stamp1)).setImageResource(R.drawable.circle_not_passed);

        switch (act){
            case 8: ((ImageView)findViewById(R.id.stamp8)).setImageResource(R.drawable.circle_passed);
            case 7: ((ImageView)findViewById(R.id.stamp7)).setImageResource(R.drawable.circle_passed);
            case 6: ((ImageView)findViewById(R.id.stamp6)).setImageResource(R.drawable.circle_passed);
            case 5: ((ImageView)findViewById(R.id.stamp5)).setImageResource(R.drawable.circle_passed);
            case 4: ((ImageView)findViewById(R.id.stamp4)).setImageResource(R.drawable.circle_passed);
            case 3: ((ImageView)findViewById(R.id.stamp3)).setImageResource(R.drawable.circle_passed);
            case 2: ((ImageView)findViewById(R.id.stamp2)).setImageResource(R.drawable.circle_passed);
            case 1: ((ImageView)findViewById(R.id.stamp1)).setImageResource(R.drawable.circle_passed);
                break;
        }
    }

    public int get_time_cords(int _hour, int _min){
//        Toast.makeText(this,_hour+":"+_min,Toast.LENGTH_LONG).show();
        int cod = (_hour*60)+_min;
        if(cod>=925)
            return 8;
        if(cod>=875)
            return 7;
        if(cod>=825)
            return 6;
        if(cod>=775)
            return 5;
        if(cod>=720)
            return 4;
        if(cod>=665)
            return 3;
        if(cod>=610)
            return 2;
        if(cod>=555)
            return 1;
        return 0;
    }

    @SuppressLint("StaticFieldLeak")
    class get_request extends AsyncTask<Void, Void, Void>
    {

        OkHttpClient client = new OkHttpClient();
        Response response;
        String url;

        private get_request(String _url){
            url=_url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            try {
//                tt.setText(Objects.requireNonNull(response.body()).string());
                data_bucket.set_table(Objects.requireNonNull(response.body()).string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}