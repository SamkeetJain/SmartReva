package com.samkeet.smartreva.Mentor;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samkeet.smartreva.R;

/**
 * Created by Blazzing Frost on 1/15/2017.
 */

public class MentorStudentAcademicDetailAdapter extends RecyclerView.Adapter<MentorStudentAcademicDetailAdapter.ViewHolder> {

    private String[] mName, mSrn;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mNameView, mSrnView;

        public ViewHolder(View v) {
            super(v);
            mNameView = (TextView) v.findViewById(R.id.name);
            mSrnView = (TextView) v.findViewById(R.id.srn);
        }
    }

    public MentorStudentAcademicDetailAdapter(String[] mSrn, String[] mName) {
        this.mName = mName;
        this.mSrn = mSrn;

    }

    @Override
    public MentorStudentAcademicDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cards_mentor_student_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        holder.mNameView.setText(mName[position]);
        holder.mSrnView.setText(mSrn[position]);


    }

    @Override
    public int getItemCount() {
        return mName.length;
    }
}
