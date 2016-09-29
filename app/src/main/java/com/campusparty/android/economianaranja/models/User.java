package com.campusparty.android.economianaranja.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gerardo on 15/09/16.
 * This class has the user parcelable model
 */
public class User implements Parcelable{

    String name;
    String model;
    String career;
    String image;

    // Constructor
    public User(String name, String model, String career, String image) {
        this.name = name;
        this.model = model;
        this.career = career;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    protected User(Parcel in) {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
