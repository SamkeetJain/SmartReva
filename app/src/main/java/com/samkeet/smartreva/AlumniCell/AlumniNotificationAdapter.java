package com.samkeet.smartreva.AlumniCell;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samkeet.smartreva.R;

/**
 * Created by Sam on 23-Jan-17.
 */

public class AlumniNotificationAdapter extends RecyclerView.Adapter<AlumniNotificationAdapter.ViewHolder> {

    private String[] mTitle, mMessage, mDdate;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitleView, mMessageView, mDdateView;

        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.title);
            mMessageView = (TextView) v.findViewById(R.id.message);
            mDdateView = (TextView) v.findViewById(R.id.ddate);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AlumniNotificationAdapter(String[] mTitle, String[] mMessage, String[] mDdate) {
        this.mTitle = mTitle;
        this.mMessage = mMessage;
        this.mDdate = mDdate;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AlumniNotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_alumni_notification, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTitleView.setText(mTitle[position]);
        holder.mMessageView.setText(mMessage[position]);
        holder.mDdateView.setText(mDdate[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTitle.length;
    }
}
