package com.samkeet.smartreva.Councling;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.samkeet.smartreva.R;

/**
 * Created by Sam on 09-Nov-16.
 */

public class CounclingTimePickAdapter extends RecyclerView.Adapter<CounclingTimePickAdapter.ViewHolder> {

    private String[] mResId;
    private String[] mAvalibility;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public Button ResId;
        public ViewHolder(View v) {
            super(v);
            ResId = (Button) v.findViewById(R.id.button);
        }
    }

    public CounclingTimePickAdapter(String[] mResId, String[] mAvalibility) {
        this.mResId = mResId;
        this.mAvalibility=mAvalibility;
    }


    @Override
    public CounclingTimePickAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_councling_time_picker, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CounclingTimePickAdapter.ViewHolder holder, int position) {

        String slot="" + mResId[position].charAt(9) + mResId[position].charAt(10) + ":00";
        holder.ResId.setText(slot);

        if (mAvalibility[position].equals("0")){
            holder.ResId.setBackgroundColor(Color.parseColor("#00ff00"));
        }else {
            holder.ResId.setBackgroundColor(Color.parseColor("#ff0000"));
        }

    }

    @Override
    public int getItemCount() {
        return mResId.length;
    }


}
