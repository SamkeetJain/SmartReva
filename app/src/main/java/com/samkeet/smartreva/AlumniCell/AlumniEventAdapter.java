package com.samkeet.smartreva.AlumniCell;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samkeet.smartreva.R;

/**
 * Created by Sam on 11-Jan-17.
 */

public class AlumniEventAdapter extends RecyclerView.Adapter<AlumniEventAdapter.ViewHolder> {

    private String[] mType, mDesc, mDates, mNames;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTypeView, mDescView, mDateView, mNameView;

        public ViewHolder(View v) {
            super(v);
            mTypeView = (TextView) v.findViewById(R.id.type);
            mDescView = (TextView) v.findViewById(R.id.desc);
            mDateView = (TextView) v.findViewById(R.id.datetext);
            mNameView = (TextView) v.findViewById(R.id.name);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AlumniEventAdapter(String[] mType, String[] mDesc, String[] mDates, String[] mNames) {
        this.mType = mType;
        this.mDesc = mDesc;
        this.mDates = mDates;
        this.mNames = mNames;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AlumniEventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_events_list, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTypeView.setText(mType[position]);
        holder.mDescView.setText(mDesc[position]);
        holder.mDateView.setText(mDates[position]);
        holder.mNameView.setText(mNames[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mType.length;
    }
}
