package com.campusparty.android.economianaranja.adapter;

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

import com.campusparty.android.economianaranja.ProfileActivity;
import com.campusparty.android.economianaranja.R;
import com.campusparty.android.economianaranja.models.User;
import com.campusparty.android.economianaranja.utils.DynamicHeightTransformation;
import com.campusparty.android.economianaranja.utils.PaletteTransformation;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Dummy adapter for Campus Party contest
 * This Adapter create instances on recyclerview
 */
public class SearchNetAdapter extends RecyclerView.Adapter<SearchNetAdapter.ViewHolder> {

    private Context mContext;
    private Activity mActivity;
    private ArrayList<User> mUser;


    public SearchNetAdapter(Context context, ArrayList<User> users, Activity activity) {
        this.mContext = context;
        this.mUser = users;
        this.mActivity = activity;
    }

    @Override
    public SearchNetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (parent instanceof RecyclerView) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_users, parent, false);
            view.setFocusable(true);
            return new ViewHolder(view);
        } else {
            throw new RuntimeException("Not bound to RecyclerView");
        }
    }

    @Override
    public void onBindViewHolder(final SearchNetAdapter.ViewHolder holder, final int position) {
        holder.mName.setText(mUser.get(position).getName());
        holder.mCareer.setText(mUser.get(position).getCareer());
        holder.mModel.setText(mUser.get(position).getModel());
        List<Transformation> transformations = new ArrayList<>();
        String URL = mUser.get(position).getImage();

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
                                                Intent intent = new Intent(mContext, ProfileActivity.class);

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


    @Override
    public int getItemCount() {
        return mUser.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.user_name)
        TextView mName;
        @Bind(R.id.user_career)
        TextView mCareer;
        @Bind(R.id.user_model)
        TextView mModel;
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