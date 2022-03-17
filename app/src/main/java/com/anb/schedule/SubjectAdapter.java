package com.anb.schedule;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.Viewholder> {

    private final Context context;
    JSONArray day;

    // Constructor
    public SubjectAdapter(Context context, JSONArray _day) {
        day=_day;
        this.context = context;
    }

    @NonNull
    @Override
    public SubjectAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_subject, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.Viewholder holder, int position) {
        try {
            JSONObject current_job= day.getJSONObject(position);
            holder.subjectTV.setText(current_job.getString("name"));
            holder.facultynameTV.setText(current_job.getString("teacher"));
            if(current_job.getString("teacher").contains("/")) {
                holder.longi.getLayoutParams().height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 270, context.getResources().getDisplayMetrics());
            }
//            holder.timeTV.setText(current_job.getString("time"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // to set data to textview and imageview of each card layout
    }

    @Override
    public int getItemCount() {
        return day.length();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public static class Viewholder extends RecyclerView.ViewHolder {
        private final TextView subjectTV;
        private final TextView facultynameTV;
        private TextView timeTV;
        private final TextView longi;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            subjectTV = itemView.findViewById(R.id.subject_tv);
            facultynameTV = itemView.findViewById(R.id.faculty_name_tv);
            longi = itemView.findViewById(R.id.longi);
//            timeTV = itemView.findViewById(R.id.time_tv);
        }
    }
}
