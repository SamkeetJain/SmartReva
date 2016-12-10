package com.samkeet.smartreva.Placement;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samkeet.smartreva.R;

/**
 * Created by FROST on 12/9/2016.
 */

public class PlacementMainAdapter extends RecyclerView.Adapter<PlacementMainAdapter.ViewHolder> {

    private String[] mTitle,mDate,mDept,mRole;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitleView, mDateView, mDeptView, mRoleView;

        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.company_title);
            mDateView = (TextView) v.findViewById(R.id.datetext);
            mDeptView = (TextView) v.findViewById(R.id.dept);
            mRoleView = (TextView) v.findViewById(R.id.role);
        }
    }

    public PlacementMainAdapter(String[] mTitle,String[] mDate,String[] mDept,String[] mRole) {
        this.mTitle = mTitle;
        this.mDate = mDate;
        this.mDept = mDept;
        this.mRole = mRole;
    }

    @Override
    public PlacementMainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        holder.mDateView.setText(mDate[position]);
        holder.mDeptView.setText(mDept[position]);
        holder.mRoleView.setText(mRole[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTitle.length;
    }
}
