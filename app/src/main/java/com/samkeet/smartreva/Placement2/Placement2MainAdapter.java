package com.samkeet.smartreva.Placement2;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samkeet.smartreva.R;

/**
 * Created by Frost on 12-02-2017.
 */

public class Placement2MainAdapter extends RecyclerView.Adapter<Placement2MainAdapter.ViewHolder> {

    private String[] mTitle, mDate;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitleView, mDateView;

        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.company_title);
            mDateView = (TextView) v.findViewById(R.id.datetext);
        }
    }

    public Placement2MainAdapter(String[] mTitle, String[] mDate) {
        this.mTitle = mTitle;
        this.mDate = mDate;
    }

    @Override
    public Placement2MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_placement_drives, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTitleView.setText(mTitle[position]);
        String ddate = "On " + mDate[position];
        holder.mDateView.setText(ddate);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTitle.length;
    }
}

