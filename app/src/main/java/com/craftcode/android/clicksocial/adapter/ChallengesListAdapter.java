package com.craftcode.android.clicksocial.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.craftcode.android.clicksocial.ChallengeActivity;
import com.craftcode.android.clicksocial.R;
import com.craftcode.android.clicksocial.models.Challenge;
import com.craftcode.android.clicksocial.utils.GeneralConst;
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
public class ChallengesListAdapter extends RecyclerView.Adapter<ChallengesListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Challenge> mChallenges;

    public ChallengesListAdapter(Context context, ArrayList<Challenge> challenges) {
        this.mContext = context;
        this.mChallenges = challenges;
    }

    @Override
    public ChallengesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_challenges, parent, false);
        view.setFocusable(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitle.setText(mChallenges.get(position).getTitle());
        holder.mDescription.setText(mChallenges.get(position).getDescription());
        Picasso.with(mContext).load(mChallenges.get(position).getImg()).fit().centerCrop().tag(mContext).into(holder.mThumbnail);

//        SimpleDateFormat sds = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//
//        sds.setTimeZone(TimeZone.getDefault());
//        long event_date = mChallenges.get(position).getCreation_date();
//        final Date date_s = new Date(event_date * 1000L);
//        holder.mCreation.setText(sds.format(date_s));

        holder.mGoTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ChallengeActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString(GeneralConst.PROMO_KEY, mChallenges.get(position).get_id());
                i.putExtras(mBundle);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mChallenges.size();
    }

    public void refill(List<Challenge> challenges) {
        mChallenges.clear();
        mChallenges.addAll(challenges);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title)
        TextView mTitle;
        @Bind(R.id.description) TextView mDescription;
        @Bind(R.id.thumbnail)
        ImageView mThumbnail;
        @Bind(R.id.btn_challenges)
        Button mGoTo;
//        @Bind(R.id.creation)
//        TextView mCreation;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mView = itemView;
        }
    }
}
