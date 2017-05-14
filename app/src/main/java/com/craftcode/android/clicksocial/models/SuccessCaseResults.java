package com.craftcode.android.clicksocial.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerardo on 14/05/17.
 */

public class SuccessCaseResults implements Parcelable{

    private List<SuccessCase> cases = new ArrayList<SuccessCase>();

    protected SuccessCaseResults(Parcel in) {
        cases = in.createTypedArrayList(SuccessCase.CREATOR);
    }

    public List<SuccessCase> getResults() {
        return cases;
    }


    public static final Creator<SuccessCaseResults> CREATOR = new Creator<SuccessCaseResults>() {
        @Override
        public SuccessCaseResults createFromParcel(Parcel in) {
            return new SuccessCaseResults(in);
        }

        @Override
        public SuccessCaseResults[] newArray(int size) {
            return new SuccessCaseResults[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(cases);
    }
}
