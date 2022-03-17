package com.anb.schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FirstActivity extends AppCompatActivity {
    Spinner yearSP,branchSP,sectionSP;
    Project_api_handler data = new Project_api_handler(this);
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        yearSP = findViewById(R.id.yearSpinner);
        branchSP = findViewById(R.id.branchSpinner);
        sectionSP = findViewById(R.id.sectionSpinner);
        start = findViewById(R.id.Start);

        List<String> Year = new ArrayList<String>();
        Collections.addAll(Year, Project_api_handler.YEAR_LIST);

        List<String> Branch = new ArrayList<String>();
        Collections.addAll(Branch, Project_api_handler.BRANCH_LIST);

        List<String> Section = new ArrayList<String>();
        Collections.addAll(Section, Project_api_handler.SECTION_LIST);


        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Year);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> sectionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Section);
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> branchAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Branch);
        branchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        branchSP.setAdapter(branchAdapter);
        yearSP.setAdapter(yearAdapter);
        sectionSP.setAdapter(sectionAdapter);



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.set_section(Section.get(sectionSP.getSelectedItemPosition()));
                data.set_year(yearSP.getSelectedItemPosition()+1);
                data.set_branch(Branch.get(branchSP.getSelectedItemPosition()));
                if(data.has_cred() && (data.get_table().contains("{")))
                {
                    Intent move = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(move);
                }
                else{

                    ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo nInfo = cm.getActiveNetworkInfo();
                    boolean connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
                    if(connected)
                        new get_request("https://0yomwntoytmqbjsit8zrxq-on.drv.tw/www.immance.com/year"+data.get_year()+".json").execute();
                    else
                        Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
                }
            }
        });



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
                data.set_table(Objects.requireNonNull(response.body()).string());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent move = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(move);
        }
    }

}