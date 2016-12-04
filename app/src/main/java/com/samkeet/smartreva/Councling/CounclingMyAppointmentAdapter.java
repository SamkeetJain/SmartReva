package com.samkeet.smartreva.Councling;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samkeet.smartreva.R;

/**
 * Created by Sam on 20-Oct-16.
 */

public class CounclingMyAppointmentAdapter extends RecyclerView.Adapter<CounclingMyAppointmentAdapter.ViewHolder> {

    private String[] mTitle,mDesc,mDates;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitleView,mDescView,mDateView;
        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.title);
            mDescView= (TextView) v.findViewById(R.id.desc);
            mDateView= (TextView) v.findViewById(R.id.datetext);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CounclingMyAppointmentAdapter(String[] mTitle, String[] mDesc,String[] mDates ){
        this.mTitle = mTitle;
        this.mDesc = mDesc;
        this.mDates=mDates;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CounclingMyAppointmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_councling_my_appointment, parent, false);
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
        holder.mDescView.setText(mDesc[position]);
        String datetime = mDates[position];
//        String datetime=""+mDates[position].substring(0,8)+"  @  " +mDates[position].substring(9,11)+":00";
        holder.mDateView.setText(datetime);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTitle.length;
    }
}