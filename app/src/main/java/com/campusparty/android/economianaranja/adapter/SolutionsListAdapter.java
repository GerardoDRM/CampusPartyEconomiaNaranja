package com.campusparty.android.economianaranja.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.campusparty.android.economianaranja.R;
import com.campusparty.android.economianaranja.SolutionsActivity;
import com.campusparty.android.economianaranja.models.Solutions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Dummy adapter for Campus Party contest
 * This Adapter create instances on recyclerview
 */
public class SolutionsListAdapter extends RecyclerView.Adapter<SolutionsListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Solutions> mSolutions;

    public SolutionsListAdapter(Context context, ArrayList<Solutions> solution) {
        this.mContext = context;
        this.mSolutions = solution;
    }

    @Override
    public SolutionsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solutions, parent, false);
        view.setFocusable(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTitle.setText(mSolutions.get(position).getName());
        holder.mDescription.setText(mSolutions.get(position).getDescription());
        Picasso.with(mContext).load(mSolutions.get(position).getImage()).fit().centerCrop().tag(mContext).into(holder.mThumbnail);

        holder.mMembers.setText(String.valueOf(mSolutions.get(position).getMembers()));

        holder.mGoTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, SolutionsActivity.class);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mSolutions.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.event_title)
        TextView mTitle;
        @Bind(R.id.event_description) TextView mDescription;
        @Bind(R.id.thumbnail)
        ImageView mThumbnail;
        @Bind(R.id.about_event)
        Button mGoTo;
        @Bind(R.id.event_members)
        TextView mMembers;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mView = itemView;
        }
    }
}
