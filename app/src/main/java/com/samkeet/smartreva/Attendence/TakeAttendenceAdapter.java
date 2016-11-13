package com.samkeet.smartreva.Attendence;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.samkeet.smartreva.R;

import java.util.ArrayList;


/**
 * Created by Sam on 12-Nov-16.
 */

public class TakeAttendenceAdapter extends RecyclerView.Adapter<TakeAttendenceAdapter.ViewHolder> {

    private String[] mTitle;
    private boolean[] checks;
    public ArrayList<String> fsds=new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitleView;
        public CheckBox mCheckBox;
        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.textview);
            mCheckBox= (CheckBox) v.findViewById(R.id.checkbox);
        }
    }
    public TakeAttendenceAdapter(String[] mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public TakeAttendenceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_take_attendence, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final int pos=position;
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTitleView.setText(mTitle[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTitle.length;
    }
}
