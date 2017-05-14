package com.campusparty.android.economianaranja.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gerardo on 14/05/17.
 */

public class Challenge implements Parcelable{

    private String _id;
    private String title;
    private String description;
    private long creation_date;
    private String challenge;
    private String img;
    private int likes;
    private Address address;

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
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    protected Challenge(Parcel in) {
        _id = in.readString();
        title = in.readString();
        description = in.readString();
        challenge = in.readString();
        creation_date = in.readLong();
        img = in.readString();
        likes = in.readInt();
        address = in.readParcelable(Address.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(challenge);
        dest.writeLong(creation_date);
        dest.writeString(img);
        dest.writeInt(likes);
        dest.writeParcelable(address, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Challenge> CREATOR = new Creator<Challenge>() {
        @Override
        public Challenge createFromParcel(Parcel in) {
            return new Challenge(in);
        }

        @Override
        public Challenge[] newArray(int size) {
            return new Challenge[size];
        }
    };
}
