package com.craftcode.android.clicksocial.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.craftcode.android.clicksocial.R;
import com.craftcode.android.clicksocial.SuccessCaseActivity;
import com.craftcode.android.clicksocial.models.SuccessCase;
import com.craftcode.android.clicksocial.utils.DynamicHeightTransformation;
import com.craftcode.android.clicksocial.utils.PaletteTransformation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * This Adapter create instances on recyclerview
 */
public class SuccessCasesAdapter extends RecyclerView.Adapter<SuccessCasesAdapter.ViewHolder> {

    private Context mContext;
    private Activity mActivity;
    private ArrayList<SuccessCase> mCases;


    public SuccessCasesAdapter(Context context, ArrayList<SuccessCase> mcase, Activity activity) {
        this.mContext = context;
        this.mCases = mcase;
        this.mActivity = activity;
    }

    @Override
    public SuccessCasesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (parent instanceof RecyclerView) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cases, parent, false);
            view.setFocusable(true);
            return new ViewHolder(view);
        } else {
            throw new RuntimeException("Not bound to RecyclerView");
        }
    }

    @Override
    public void onBindViewHolder(final SuccessCasesAdapter.ViewHolder holder, final int position) {
        holder.mTitle.setText(mCases.get(position).getTitle());
        holder.mDrescription.setText(mCases.get(position).getDescription());

        SimpleDateFormat sds = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        sds.setTimeZone(TimeZone.getDefault());
        long event_date = mCases.get(position).getCreation_date();
        final Date date_s = new Date(event_date * 1000L);

        holder.mCreation.setText(sds.format(date_s));
        List<Transformation> transformations = new ArrayList<>();
        String URL = mCases.get(position).getImg();

        // Get image with Picasso
        transformations.add(new DynamicHeightTransformation(300, false));
        transformations.add(PaletteTransformation.instance());
        Picasso.with(mContext).load(URL).transform(transformations)
                .transform(PaletteTransformation.instance()).tag(mContext).into(holder.mThumbnail, new Callback.EmptyCallback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) holder.mThumbnail.getDrawable()).getBitmap();
                Palette palette = PaletteTransformation.getPalette(bitmap);
                CardView cd = (CardView) holder.mView;
                cd.setCardBackgroundColor(palette.getMutedColor(0xFF333333));

            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener()

                                        {
                                            @Override
                                            public void onClick(View v) {
                                                // create the shared transition animation
                                                Intent intent = new Intent(mContext, SuccessCaseActivity.class);

                                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                                                    mActivity.getWindow().setExitTransition(new Explode());
                                                    Bundle bundle = ActivityOptions
                                                            .makeSceneTransitionAnimation(mActivity).toBundle();

                                                    mContext.startActivity(intent, bundle);
                                                } else {
                                                    mContext.startActivity(intent);
                                                }
                                            }
                                        }

        );
    }



    public void refill(List<SuccessCase> mcases) {
        mCases.clear();
        mCases.addAll(mcases);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mCases.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title)
        TextView mTitle;
        @Bind(R.id.creation)
        TextView mCreation;
        @Bind(R.id.description)
        TextView mDrescription;
        @Bind(R.id.thumbnail)
        ImageView mThumbnail;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mView = itemView;
        }
    }
}