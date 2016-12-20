package com.samkeet.smartreva.Attendence;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samkeet.smartreva.R;

/**
 * Created by Sam on 03-Dec-16.
 */

public class
AbsentDetailsAdapter extends RecyclerView.Adapter<AbsentDetailsAdapter.ViewHolder> {

    private String[] mTitle;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mSubView,mDateView,mPeriodView,mStatView;
        public ViewHolder(View v) {
            super(v);
            mSubView = (TextView) v.findViewById(R.id.sub);
            mDateView = (TextView) v.findViewById(R.id.date);
            mPeriodView = (TextView) v.findViewById(R.id.period);
            mStatView = (TextView) v.findViewById(R.id.stat);
        }
    }
    public AbsentDetailsAdapter(String[] mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public AbsentDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_absent_details, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String list[]=mTitle[position].split("\\|");
        String sub= list[0];
        String date= list[1];
        String per= list[2];
        String stat= list[3];

        holder.mSubView.setText(sub);
        holder.mDateView.setText(date);
        holder.mPeriodView.setText(per);
        holder.mStatView.setText(stat);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTitle.length;
    }
}
