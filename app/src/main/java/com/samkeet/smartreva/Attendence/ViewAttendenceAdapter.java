package com.samkeet.smartreva.Attendence;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samkeet.smartreva.Constants;
import com.samkeet.smartreva.R;

import java.text.DecimalFormat;

/**
 * Created by Sam on 15-Nov-16.
 */

public class ViewAttendenceAdapter extends RecyclerView.Adapter<ViewAttendenceAdapter.ViewHolder> {

    private String[] mTitle;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mSubView,mFracView,mPerView;
        public ViewHolder(View v) {
            super(v);
            mSubView = (TextView) v.findViewById(R.id.sub);
            mFracView=(TextView) v.findViewById(R.id.frac);
            mPerView=(TextView) v.findViewById(R.id.per);
        }
    }
    public ViewAttendenceAdapter(String[] mTitle) {
        this.mTitle = mTitle;

    }

    @Override
    public ViewAttendenceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_view_attendence, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String sub,frac,per;
        String list[]=mTitle[position].split("\\|");
        sub=list[0];
        frac=list[2]+"/"+list[3];
        double d=((Double.parseDouble(list[2]))/(Double.parseDouble(list[3])))*100;
        DecimalFormat df = new DecimalFormat("#.##");
        per = String.valueOf(Double.valueOf(df.format(d)));

        holder.mSubView.setText(sub);
        holder.mFracView.setText(frac);
        holder.mPerView.setText(per);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTitle.length;
    }
}
