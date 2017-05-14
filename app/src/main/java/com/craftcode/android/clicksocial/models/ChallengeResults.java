package com.craftcode.android.clicksocial.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerardo on 14/05/17.
 */

public class ChallengeResults implements Parcelable{

    private List<Challenge> challenges = new ArrayList<Challenge>();

    protected ChallengeResults(Parcel in) {
        challenges = in.createTypedArrayList(Challenge.CREATOR);
    }

    public List<Challenge> getResults() {
        return challenges;
    }


    public static final Creator<ChallengeResults> CREATOR = new Creator<ChallengeResults>() {
        @Override
        public ChallengeResults createFromParcel(Parcel in) {
            return new ChallengeResults(in);
        }

        @Override
        public ChallengeResults[] newArray(int size) {
            return new ChallengeResults[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(challenges);
    }
}
