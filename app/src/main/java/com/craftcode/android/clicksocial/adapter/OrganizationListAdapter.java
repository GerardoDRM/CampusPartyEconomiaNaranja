package com.craftcode.android.clicksocial.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.craftcode.android.clicksocial.R;
import com.craftcode.android.clicksocial.models.Organization;
import com.craftcode.android.clicksocial.utils.CircleTransform;
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
        holder.mEntity.setText(mOrganizations.get(position).getEntity());
        holder.mWebsite.setText(mOrganizations.get(position).getWeb());
        holder.mFederal.setText(mOrganizations.get(position).getFederal_entity());
        holder.mEmail.setText(mOrganizations.get(position).getEmail());

        // Photo depends from social group
        String type = mOrganizations.get(position).getType();
        int resource = 0;
        if(type.equals("Desarrollo Integral")) {
            resource = R.drawable.integral;
            holder.mView.setBackgroundColor(mContext.getResources().getColor(R.color.integral));
        } else if(type.equals("Sociedad Incluyente")) {
            resource = R.drawable.incluyente;
            holder.mView.setBackgroundColor(mContext.getResources().getColor(R.color.include));
        } else if(type.equals("Alimentación")) {
            resource = R.drawable.food;
            holder.mView.setBackgroundColor(mContext.getResources().getColor(R.color.food));
        } else if(type.equals("Equidad de Género")) {
            resource = R.drawable.gender;
            holder.mView.setBackgroundColor(mContext.getResources().getColor(R.color.gender));
        } else if(type.equals("Profesionalización")) {
            resource = R.drawable.teamwork;
            holder.mView.setBackgroundColor(mContext.getResources().getColor(R.color.prof));
        } else if(type.equals("Investigación")) {
            resource = R.drawable.research;
            holder.mView.setBackgroundColor(mContext.getResources().getColor(R.color.investigation));
        }

        Picasso.with(mContext).load(resource).fit().centerCrop().transform(new CircleTransform()).tag(mContext).into(holder.mThumbnail);

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

        @Bind(R.id.entity)
        TextView mEntity;
        @Bind(R.id.web) TextView mWebsite;
        @Bind(R.id.thumbnail)
        ImageView mThumbnail;
        @Bind(R.id.federal_entity)
        TextView mFederal;
        @Bind(R.id.email)
        TextView mEmail;
        public View mView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mView = itemView;
        }
    }
}
