package com.samkeet.smartreva.AlumniCell;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.samkeet.smartreva.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sam on 22-Jan-17.
 */

public class AlumniMainAdapter extends RecyclerView.Adapter<AlumniMainAdapter.ViewHolder> {

    private JSONObject[] disscussionObjects;
    private String[] name, loc, title, desc, time, stars, replies;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout mStarClicker;
        public TextView mName, mloc, mTitle, mDesc, mTime, mStars, mReplies;

        public ViewHolder(View v) {
            super(v);
            mName = (TextView) v.findViewById(R.id.name);
            mloc = (TextView) v.findViewById(R.id.loc);
            mTitle = (TextView) v.findViewById(R.id.title);
            mDesc = (TextView) v.findViewById(R.id.desc);
            mTime = (TextView) v.findViewById(R.id.time);
            mStars = (TextView) v.findViewById(R.id.starscount);
            mReplies = (TextView) v.findViewById(R.id.repiescount);

            mStarClicker = (LinearLayout) v.findViewById(R.id.starclicker);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AlumniMainAdapter(JSONObject[] disscussionObjects) {
        this.disscussionObjects = disscussionObjects;

        name=new String[disscussionObjects.length];
        loc=new String[disscussionObjects.length];
        title=new String[disscussionObjects.length];
        desc=new String[disscussionObjects.length];
        time=new String[disscussionObjects.length];
        stars=new String[disscussionObjects.length];
        replies=new String[disscussionObjects.length];

        for(int i=0;i<disscussionObjects.length;i++){
            try {
                name[i]=disscussionObjects[i].getString("name");
                loc[i]=disscussionObjects[i].getString("loc");
                title[i]=disscussionObjects[i].getString("title");
                desc[i]=disscussionObjects[i].getString("message");
                time[i]=disscussionObjects[i].getString("ddate");
                stars[i]=disscussionObjects[i].getString("starscount");
                replies[i]=disscussionObjects[i].getString("repliescount");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    // Create new views (invoked by the layout manager)
    @Override
    public AlumniMainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_alumni_disscussion, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mName.setText(name[position]);
        holder.mloc.setText(loc[position]);
        holder.mTitle.setText(title[position]);
        holder.mDesc.setText(desc[position]);
        holder.mTime.setText(time[position]);
        holder.mStars.setText(stars[position]);
        holder.mReplies.setText(replies[position]);

        holder.mStarClicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("OnCLick","Star clicker "+ position);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return name.length;
    }
}
