package com.craftcode.android.clicksocial.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gerardo on 14/05/17.
 */

public class Convocation implements Parcelable{

    private String _id;
    private String title;
    private String description;
    private long creation_date;
    private String web;
    private String img;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(long creation_date) {
        this.creation_date = creation_date;
    }

    public String getChallenge() {
        return web;
    }

    public void setChallenge(String challenge) {
        this.web = challenge;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }



    protected Convocation(Parcel in) {
        _id = in.readString();
        title = in.readString();
        description = in.readString();
        web = in.readString();
        creation_date = in.readLong();
        img = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(web);
        dest.writeLong(creation_date);
        dest.writeString(img);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Convocation> CREATOR = new Creator<Convocation>() {
        @Override
        public Convocation createFromParcel(Parcel in) {
            return new Convocation(in);
        }

        @Override
        public Convocation[] newArray(int size) {
            return new Convocation[size];
        }
    };
}
