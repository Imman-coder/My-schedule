package com.anb.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class table_populator extends Fragment  {

    private RecyclerView courseRV;
//    private String json = "{\n    \"last_updated\":\"pata nii\",\n    \"table\": {\n      \"A\":[\n            [\n                {\n                    \"name\":\"BME\",\n                    \"teacher\":\"NP\",\n                    \"time\":\"9:15 - 10:10\"\n                },\n                {\n                    \"name\":\"CE GR-1 / PHY GR-II\",\n                    \"teacher\":\"LP / SB,BS\",\n                    \"time\":\"10:10 - 12:55\"\n                },\n                {\n                    \"name\":\"LUNCH BREAK\",\n                    \"teacher\":\"\",\n                    \"time\":\"12:55 - 1:45\"\n                },\n                {\n                    \"name\":\"BEE\",\n                    \"teacher\":\"SKS\",\n                    \"time\":\"1:45 - 2:35\"\n                },\n                {\n                    \"name\":\"MATH\",\n                    \"teacher\":\"NCS\",\n                    \"time\":\"2:35 - 3:25\"\n                },\n                {\n                    \"name\":\"SE\",\n                    \"teacher\":\"SD\",\n                    \"time\":\"3:25 - 4:15\"\n                }\n            ],\n            [\n                {\n                    \"name\":\"CE\",\n                    \"teacher\":\"SD\",\n                    \"time\":\"9:15 - 10:10\"\n                },\n                {\n                    \"name\":\"BME\",\n                    \"teacher\":\"NP\",\n                    \"time\":\"10:10 - 11:05\"\n                },\n                {\n                    \"name\":\"PHY\",\n                    \"teacher\":\"SB\",\n                    \"time\":\"11:05 - 12:00\"\n                },\n                {\n                    \"name\":\"MATH\",\n                    \"teacher\":\"NCS\",\n                    \"time\":\"12:00 - 12:55\"\n                },\n                {\n                    \"name\":\"LUNCH BREAK\",\n                    \"teacher\":\"\",\n                    \"time\":\"12:55 - 1:45\"\n                },\n                {\n                    \"name\":\"CE GP-II / EWS GP-I\",\n                    \"teacher\":\"LP / NP\",\n                    \"time\":\"1:45 - 4:15\"\n                }\n            ],\n            [\n                {\n                    \"name\":\"PHY\",\n                    \"teacher\":\"BS\",\n                    \"time\":\"9:15 - 10:10\"\n                },\n                {\n                    \"name\":\"CE\",\n                    \"teacher\":\"SD\",\n                    \"time\":\"10:10 - 11:05\"\n                },\n                {\n                    \"name\":\"BEE\",\n                    \"teacher\":\"SKS\",\n                    \"time\":\"11:05 - 12:00\"\n                },\n                {\n                    \"name\":\"MATH\",\n                    \"teacher\":\"RKJ\",\n                    \"time\":\"12:00 - 12:55\"\n                },\n                {\n                    \"name\":\"LUNCH BREAK\",\n                    \"teacher\":\"\",\n                    \"time\":\"12:55 - 1:45\"\n                },\n                {\n                    \"name\":\"BEE GR-I / EWS GP-II\",\n                    \"teacher\":\"PCP,MRM / NP\",\n                    \"time\":\"1:45 - 4:15\"\n                }\n            ],\n            [\n                {\n                    \"name\":\"CE\",\n                    \"teacher\":\"SD\",\n                    \"time\":\"9:15 - 10:10\"\n                },\n                {\n                    \"name\":\"BME\",\n                    \"teacher\":\"NP\",\n                    \"time\":\"10:10 - 11:05\"\n                },\n                {\n                    \"name\":\"PHY\",\n                    \"teacher\":\"BS\",\n                    \"time\":\"11:05 - 12:00\"\n                },\n                {\n                    \"name\":\"MATH\",\n                    \"teacher\":\"NCS\",\n                    \"time\":\"12:00 - 12:55\"\n                },\n                {\n                    \"name\":\"LUNCH BREAK\",\n                    \"teacher\":\"\",\n                    \"time\":\"12:55 - 1:45\"\n                },\n                {\n                    \"name\":\"BEE GP-II / PHY GR-I\",\n                    \"teacher\":\"AN,GM / BS,SB\",\n                    \"time\":\"1:45 - 4:15\"\n                }\n            ],\n            [\n                {\n                    \"name\":\"BEE\",\n                    \"teacher\":\"SKS\",\n                    \"time\":\"9:15 - 10:10\"\n                },\n                {\n                    \"name\":\"PHY\",\n                    \"teacher\":\"BS\",\n                    \"time\":\"10:10 - 11:05\"\n                },\n                {\n                    \"name\":\"MATH\",\n                    \"teacher\":\"RKJ\",\n                    \"time\":\"11:05 - 12:00\"\n                },\n                {\n                    \"name\":\"EVS\",\n                    \"teacher\":\"SSB\",\n                    \"time\":\"12:00 - 12:55\"\n                },\n                {\n                    \"name\":\"LUNCH BREAK\",\n                    \"teacher\":\"\",\n                    \"time\":\"12:55 - 1:45\"\n                },\n                {\n                    \"name\":\"CE\",\n                    \"teacher\":\"SD\",\n                    \"time\":\"1:45 - 2:35\"\n                },\n                {\n                    \"name\":\"BME\",\n                    \"teacher\":\"NP\",\n                    \"time\":\"2:35 - 3:25\"\n                },\n                {\n                    \"name\":\"LIBRARY/TUT\",\n                    \"teacher\":\"\",\n                    \"time\":\"3:25 - 4:15\"\n                }\n            ],\n            [\n                {\n                    \"name\":\"CE\",\n                    \"teacher\":\"SD\",\n                    \"time\":\"9:15 - 10:10\"\n                },\n                {\n                    \"name\":\"BEE\",\n                    \"teacher\":\"SKS\",\n                    \"time\":\"10:10 - 11:05\"\n                },\n                {\n                    \"name\":\"PHY\",\n                    \"teacher\":\"SB\",\n                    \"time\":\"11:05 - 12:00\"\n                },\n                {\n                    \"name\":\"EVS\",\n                    \"teacher\":\"SSB\",\n                    \"time\":\"12:00 - 12:55\"\n                },\n                {\n                    \"name\":\"LUNCH BREAK\",\n                    \"teacher\":\"\",\n                    \"time\":\"12:55 - 1:45\"\n                },\n                {\n                    \"name\":\"BEE\",\n                    \"teacher\":\"SKS\",\n                    \"time\":\"1:45 - 2:35\"\n                },\n                {\n                    \"name\":\"BME\",\n                    \"teacher\":\"NP\",\n                    \"time\":\"2:35 - 3:25\"\n                },\n                {\n                    \"name\":\"PROCT/TUT\",\n                    \"teacher\":\"\",\n                    \"time\":\"3:25 - 4:15\"\n                }\n            ]\n        ]\n    }\n}";
    private String json;
    int page_no;
    Bucket_api_handler data_bucket;
    JSONArray day;
        public table_populator(int no) {
            // Required empty public constructor
            page_no = no;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View root_view = inflater.inflate(R.layout.table_content, container, false);
            // Inflate the layout for this fragment
            data_bucket= new Bucket_api_handler(root_view.getContext());
            json=data_bucket.get_table();

            courseRV = root_view.findViewById(R.id.topic_list);
//            TextView tv = root_view.findViewById(R.id.pageno);
//            tv.setText(Integer.toString(page_no+1));

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            courseRV.setLayoutManager(linearLayoutManager);

            try {
                JSONObject data = new JSONObject(json);
                JSONObject table = data.getJSONObject("table");
                JSONArray section = table.getJSONArray("A");
                day = section.getJSONArray(page_no);
//                Toast.makeText(root_view.getContext(),day.toString(),Toast.LENGTH_LONG).show();
                SubjectAdapter subjectAdapter = new SubjectAdapter(root_view.getContext(),day);
                courseRV.setAdapter(subjectAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(root_view.getContext(),"error",Toast.LENGTH_LONG).show();
            }

            return root_view;
        }

    }
