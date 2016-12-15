package com.samkeet.smartreva.Library;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samkeet.smartreva.Notes.ViewNotesAdapter;
import com.samkeet.smartreva.R;

/**
 * Created by FROST on 12/15/2016.
 */

public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> {

    private String[] mNotesID, mTitle, mMessage, mFilename;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitleView, mNotesView, mMessageView, mFileView;

        public ViewHolder(View v) {
            super(v);
            mTitleView = (TextView) v.findViewById(R.id.title);
            mMessageView = (TextView) v.findViewById(R.id.message);
            mFileView = (TextView) v.findViewById(R.id.filename);
        }
    }

    public LibraryAdapter(String[] mNotesID,String[] mTitle,String[] mMessage,String[] mFilename) {
        this.mTitle = mTitle;
        this.mFilename= mFilename;
        this.mMessage = mMessage;
        this.mNotesID = mNotesID;
    }

    @Override
    public LibraryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_view_notes, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final int pos = position;
        holder.mTitleView.setText(mTitle[pos]);
        holder.mMessageView.setText(mMessage[pos]);
        holder.mFileView.setText(mFilename[pos]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mTitle.length;
    }

}