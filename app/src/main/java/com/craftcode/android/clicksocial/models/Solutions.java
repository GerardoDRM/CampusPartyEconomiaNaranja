package com.craftcode.android.clicksocial.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gerardo on 15/09/16.
 */
public class Solutions implements Parcelable {

    String name;
    String description;
    int members;
    String image;

    // Constructor for dummy data
    public Solutions (String name, String description, int members, String image) {
        this.name = name;
        this.description = description;
        this.members = members;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Solutions(Parcel in) {
        name = in.readString();
        description = in.readString();
        members = in.readInt();
        image = in.readString();
    }

    public static final Creator<Solutions> CREATOR = new Creator<Solutions>() {
        @Override
        public Solutions createFromParcel(Parcel in) {
            return new Solutions(in);
        }

        @Override
        public Solutions[] newArray(int size) {
            return new Solutions[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeInt(members);
        parcel.writeString(image);
    }
}
