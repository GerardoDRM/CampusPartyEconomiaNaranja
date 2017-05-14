package com.craftcode.android.clicksocial.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.craftcode.android.clicksocial.R;
import com.craftcode.android.clicksocial.ConvocationActivity;
import com.craftcode.android.clicksocial.models.Support;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Dummy adapter for Campus Party contest
 * This Adapter create instances on recyclerview
 */
public class SupportListAdapter extends RecyclerView.Adapter<SupportListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Support> mSupport;

    public SupportListAdapter(Context context, ArrayList<Support> events) {
        this.mContext = context;
        this.mSupport = events;
    }

    @Override
    public SupportListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_support, parent, false);
        view.setFocusable(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTitle.setText(mSupport.get(position).getTitle());
        holder.mDescription.setText(mSupport.get(position).getDescription());
        Picasso.with(mContext).load(mSupport.get(position).getImage()).fit().centerCrop().tag(mContext).into(holder.mThumbnail);
        holder.mModel.setText(mSupport.get(position).getModel());

        holder.mStart.setText(mSupport.get(position).getStart_date());
        holder.mEnd.setText(mSupport.get(position).getEnd_date());

        holder.mGoTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ConvocationActivity.class);
                mContext.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mSupport.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.event_title)
        TextView mTitle;
        @Bind(R.id.event_description) TextView mDescription;
        @Bind(R.id.thumbnail)
        ImageView mThumbnail;
        @Bind(R.id.about_event)
        Button mGoTo;
        @Bind(R.id.event_date_start)
        TextView mStart;
        @Bind(R.id.event_model)
        TextView mModel;
        @Bind(R.id.event_date_end)
        TextView mEnd;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mView = itemView;
        }
    }
}
