package com.samkeet.smartreva.Placement;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samkeet.smartreva.R;

/**
 * Created by FROST on 12/11/2016.
 */

public class TraningCertificationAdapter extends RecyclerView.Adapter<TraningCertificationAdapter.ViewHolder> {

    private String[] mTitle, mDuration, mFP, mEP, mOrg, mSubject;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitleView, mDurationView, mPeriodView, mOrgView, mSubjectView;

        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.title);
            mDurationView = (TextView) v.findViewById(R.id.duration);
            mPeriodView = (TextView) v.findViewById(R.id.period);
            mOrgView = (TextView) v.findViewById(R.id.org);
            mSubjectView = (TextView) v.findViewById(R.id.subject);
        }
    }

    public TraningCertificationAdapter(String[] mTitle, String[] mDuration, String[] mFP, String[] mEP, String[] mOrg, String[] mSubject) {
        this.mTitle = mTitle;
        this.mDuration = mDuration;
        this.mFP = mFP;
        this.mEP = mEP;
        this.mOrg = mOrg;
        this.mSubject = mSubject;
    }

    @Override
    public TraningCertificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_placement_tandc, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTitleView.setText(mTitle[position]);
        holder.mDurationView.setText(mDuration[position]);
        holder.mPeriodView.setText(mFP[position] + " - " + mEP[position]);
        holder.mOrgView.setText(mOrg[position]);
        holder.mSubjectView.setText(mSubject[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTitle.length;
    }
}
