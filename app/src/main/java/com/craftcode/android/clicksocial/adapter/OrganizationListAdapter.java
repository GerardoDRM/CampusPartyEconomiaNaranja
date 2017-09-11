package com.craftcode.android.clicksocial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.craftcode.android.clicksocial.R;
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
public class OrganizationListAdapter extends RecyclerView.Adapter<OrganizationListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Organization> mOrganizations;

    public OrganizationListAdapter(Context context, ArrayList<Organization> organizations) {
        this.mContext = context;
        this.mOrganizations = organizations;
    }

    @Override
    public OrganizationListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_organizations, parent, false);
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

//
//        holder.mView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent(mContext, ChallengeActivity.class);
////                Bundle mBundle = new Bundle();
////                mBundle.putString(GeneralConst.PROMO_KEY, mOrganizations.get(position).get_id());
////                i.putExtras(mBundle);
////                mContext.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mOrganizations.size();
    }

    public void refill(List<Organization> organizations) {
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
