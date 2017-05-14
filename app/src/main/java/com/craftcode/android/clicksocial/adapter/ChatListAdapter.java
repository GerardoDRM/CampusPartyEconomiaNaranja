package com.craftcode.android.clicksocial.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.craftcode.android.clicksocial.R;
import com.craftcode.android.clicksocial.models.User;
import com.craftcode.android.clicksocial.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Dummy adapter for Campus Party contest
 * This Adapter create instances on recyclerview
 */
public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<User> mUsers;

    public ChatListAdapter(Context context, ArrayList<User> users) {
        this.mContext = context;
        this.mUsers = users;
    }

    @Override
    public ChatListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        view.setFocusable(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mName.setText(mUsers.get(position).getName());
        holder.mLastChat.setText("Lorem Ipsum Dolorem....");
        Picasso.with(mContext).load(mUsers.get(position).getImage()).fit().centerCrop().tag(mContext).transform(new CircleTransform()).into(holder.mThumbnail);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ChatRoom.class);
                mContext.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.user_name)
        TextView mName;
        @Bind(R.id.user_lastchat) TextView mLastChat;
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
