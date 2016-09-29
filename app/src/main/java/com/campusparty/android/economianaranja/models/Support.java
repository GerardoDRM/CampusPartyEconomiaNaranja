package com.campusparty.android.economianaranja.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gerardo on 15/09/16.
 * Dummy class to generate a list of government support
 */
public class Support implements Parcelable {

    String title;
    String description;
    String start_date;
    String end_date;
    String image;
    String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    // Constructor for dummy data
    public Support (String title, String model, String desc, String sD, String eD, String image) {
        this.title = title;
        this.model = model;
        this.description = desc;
        this.start_date = sD;
        this.end_date = eD;
        this.image = image;
    }



    protected Support(Parcel in) {
        title = in.readString();
        description = in.readString();
        start_date = in.readString();
        end_date = in.readString();
        image = in.readString();
    }

    public static final Creator<Support> CREATOR = new Creator<Support>() {
        @Override
        public Support createFromParcel(Parcel in) {
            return new Support(in);
        }

        @Override
        public Support[] newArray(int size) {
            return new Support[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(start_date);
        parcel.writeString(end_date);
        parcel.writeString(image);
    }
}
