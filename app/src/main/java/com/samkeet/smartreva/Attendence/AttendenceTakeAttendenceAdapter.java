package com.samkeet.smartreva.Attendence;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.samkeet.smartreva.R;


/**
 * Created by Sam on 12-Nov-16.
 */

public class AttendenceTakeAttendenceAdapter extends RecyclerView.Adapter<AttendenceTakeAttendenceAdapter.ViewHolder> {

    private String[] mTitle;
    private boolean[] checks;
    private String[] mName;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitleView,mNameView;
        public CheckBox mCheckBox;
        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.textview);
            mNameView = (TextView) v.findViewById(R.id.text);
            mCheckBox= (CheckBox) v.findViewById(R.id.checkbox);
        }
    }
    public AttendenceTakeAttendenceAdapter(String[] mTitle, String[] mName) {
        this.mTitle = mTitle;
        this.mName = mName;
        checks=new boolean[this.mTitle.length];
        for(int i=0;i<checks.length;i++){
            checks[i]=true;
        }
    }

    @Override
    public AttendenceTakeAttendenceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        holder.mTitleView.setText(mTitle[pos].toUpperCase());
        holder.mNameView.setText(mName[pos].toUpperCase());
        holder.mCheckBox.setChecked(checks[pos]);
        holder.mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checks[pos]==true){
                    checks[pos]=false;
                }
                else {
                    checks[pos]=true;
                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTitle.length;
    }

    public boolean[] getCheckBoxes(){

        return checks;
    }
    public String[] getTitles(){

        return mTitle;
    }
}
