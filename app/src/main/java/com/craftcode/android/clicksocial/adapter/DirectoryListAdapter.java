package com.craftcode.android.clicksocial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.craftcode.android.clicksocial.R;
import com.craftcode.android.clicksocial.models.Directory;
import com.craftcode.android.clicksocial.models.Organization;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Dummy adapter for Campus Party contest
 * This Adapter create instances on recyclerview
 */
public class DirectoryListAdapter extends RecyclerView.Adapter<DirectoryListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Directory> mOrganizations;

    public DirectoryListAdapter(Context context, ArrayList<Directory> organizations) {
        this.mContext = context;
        this.mOrganizations = organizations;
    }

    @Override
    public DirectoryListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_directory, parent, false);
        view.setFocusable(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitle.setText(mOrganizations.get(position).getTitle());
        holder.mWebsite.setText(mOrganizations.get(position).getWebsite());
        holder.mModel.setText(mOrganizations.get(position).getModel());
        holder.mPlace.setText(mOrganizations.get(position).getPlace());

        Picasso.with(mContext).load(mOrganizations.get(position).getImg()).fit().centerCrop().tag(mContext).into(holder.mThumbnail);

    }

    @Override
    public int getItemCount() {
        return mOrganizations.size();
    }

    public void refill(List<Directory> organizations) {
        mOrganizations.clear();
        mOrganizations.addAll(organizations);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title)
        TextView mTitle;
        @Bind(R.id.website) TextView mWebsite;
        @Bind(R.id.thumbnail)
        ImageView mThumbnail;
        @Bind(R.id.model)
        TextView mModel;
        @Bind(R.id.place)
        TextView mPlace;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mView = itemView;
        }
    }
}
