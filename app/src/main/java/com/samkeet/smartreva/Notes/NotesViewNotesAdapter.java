package com.samkeet.smartreva.Notes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samkeet.smartreva.R;

/**
 * Created by FROST on 11/17/2016.
 */

public class NotesViewNotesAdapter extends RecyclerView.Adapter<NotesViewNotesAdapter.ViewHolder> {

    private String[] mTitle, mMessage;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitleView, mMessageView;

        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.title);
            mMessageView = (TextView) v.findViewById(R.id.message);
        }
    }

    public NotesViewNotesAdapter(String[] mTitle, String[] mMessage) {
        this.mTitle = mTitle;
        this.mMessage = mMessage;
    }

    @Override
    public NotesViewNotesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_view_notes, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final int pos = position;
        holder.mTitleView.setText(mTitle[pos]);
        holder.mMessageView.setText(mMessage[pos]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTitle.length;
    }

}