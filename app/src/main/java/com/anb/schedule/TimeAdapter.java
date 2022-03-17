package com.anb.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.Viewholder> {

    JSONArray timeStamp;
    int time_stamp_passed;
    int time_stamp_percentage;

    TimeAdapter(Context context, JSONArray _timeStamp) throws JSONException {
        timeStamp=_timeStamp;
        Project_api_handler.Time time = new Project_api_handler.Time(timeStamp);
        time_stamp_passed = time.get_time_stamp_place(time.get_currentTime());
        time_stamp_percentage = time.get_time_progress();
    }

    @NonNull
    @Override
    public TimeAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_time_stamp, parent, false);
        return new TimeAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        try {
            holder.timeLabel.setText(timeStamp.getString(position));
            if(position<=time_stamp_passed){
                holder.timePassedIndicator.setImageResource(R.drawable.circle_passed);
                if(position<time_stamp_passed)
                    holder.timeProgressor.setProgress(100);
                else
                    holder.timeProgressor.setProgress(time_stamp_percentage);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return timeStamp.length();
    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        TextView timeLabel;
        ProgressBar timeProgressor;
        ImageView timePassedIndicator;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            timeLabel = itemView.findViewById(R.id.timeLabel);
            timeProgressor = itemView.findViewById(R.id.timeProgressor);
            timePassedIndicator = itemView.findViewById(R.id.timePassedIndicator);
        }
    }
}
