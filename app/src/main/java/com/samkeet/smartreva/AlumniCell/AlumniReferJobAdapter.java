package com.samkeet.smartreva.AlumniCell;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samkeet.smartreva.R;

/**
 * Created by Sam on 12-Jan-17.
 */

public class AlumniReferJobAdapter extends RecyclerView.Adapter<AlumniReferJobAdapter.ViewHolder> {

    private String[] mCompany, mRole, mJobType, mDates;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mCompanyView, mRoleView, mJobTypeView, mDatesView;

        public ViewHolder(View v) {
            super(v);
            mCompanyView = (TextView) v.findViewById(R.id.company);
            mRoleView = (TextView) v.findViewById(R.id.role);
            mJobTypeView = (TextView) v.findViewById(R.id.type);
            mDatesView = (TextView) v.findViewById(R.id.ddate);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AlumniReferJobAdapter(String[] mCompany, String[] mRole, String[] mJobType, String[] mDates) {
        this.mCompany = mCompany;
        this.mRole = mRole;
        this.mJobType = mJobType;
        this.mDates = mDates;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AlumniReferJobAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_alumni_refer_jobs, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mCompanyView.setText(mCompany[position]);
        holder.mRoleView.setText(mRole[position]);
        holder.mJobTypeView.setText(mJobType[position]);
        holder.mDatesView.setText(mDates[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCompany.length;
    }
}
