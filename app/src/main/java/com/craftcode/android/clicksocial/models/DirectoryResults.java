package com.craftcode.android.clicksocial.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerardo on 14/05/17.
 */

public class DirectoryResults implements Parcelable{

    private List<Directory> directory = new ArrayList<Directory>();

    protected DirectoryResults(Parcel in) {
        directory = in.createTypedArrayList(Directory.CREATOR);
    }

    public List<Directory> getResults() {
        return directory;
    }


    public static final Creator<DirectoryResults> CREATOR = new Creator<DirectoryResults>() {
        @Override
        public DirectoryResults createFromParcel(Parcel in) {
            return new DirectoryResults(in);
        }

        @Override
        public DirectoryResults[] newArray(int size) {
            return new DirectoryResults[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(directory);
    }
}
