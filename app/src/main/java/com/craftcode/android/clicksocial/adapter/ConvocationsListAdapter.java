package com.craftcode.android.clicksocial.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.craftcode.android.clicksocial.ConvocationActivity;
import com.craftcode.android.clicksocial.R;
import com.craftcode.android.clicksocial.models.Convocation;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Dummy adapter for Campus Party contest
 * This Adapter create instances on recyclerview
 */
public class ConvocationsListAdapter extends RecyclerView.Adapter<ConvocationsListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Convocation> mConvocations;

    public ConvocationsListAdapter(Context context, ArrayList<Convocation> events) {
        this.mContext = context;
        this.mConvocations = events;
    }

    @Override
    public ConvocationsListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_convocation, parent, false);
        view.setFocusable(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTitle.setText(mConvocations.get(position).getTitle());
        holder.mDescription.setText(mConvocations.get(position).getDescription());
        Picasso.with(mContext).load(mConvocations.get(position).getImg()).fit().centerCrop().tag(mContext).into(holder.mThumbnail);

        SimpleDateFormat sds = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        sds.setTimeZone(TimeZone.getDefault());
        long event_date = mConvocations.get(position).getCreation_date();
        final Date date_s = new Date(event_date * 1000L);
        holder.mCreation.setText(sds.format(date_s));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ConvocationActivity.class);
                mContext.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mConvocations.size();
    }

    public void refill(List<Convocation> convocations) {
        mConvocations.clear();
        mConvocations.addAll(convocations);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title)
        TextView mTitle;
        @Bind(R.id.description) TextView mDescription;
        @Bind(R.id.thumbnail)
        ImageView mThumbnail;
        @Bind(R.id.creation)
        TextView mCreation;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mView = itemView;
        }
    }
}
