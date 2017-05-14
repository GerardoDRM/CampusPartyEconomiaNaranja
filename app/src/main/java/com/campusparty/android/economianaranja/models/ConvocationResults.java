package com.campusparty.android.economianaranja.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerardo on 14/05/17.
 */

public class ConvocationResults implements Parcelable{

    private List<Convocation> convocations = new ArrayList<Convocation>();

    protected ConvocationResults(Parcel in) {
        convocations = in.createTypedArrayList(Convocation.CREATOR);
    }

    public List<Convocation> getResults() {
        return convocations;
    }


    public static final Creator<ConvocationResults> CREATOR = new Creator<ConvocationResults>() {
        @Override
        public ConvocationResults createFromParcel(Parcel in) {
            return new ConvocationResults(in);
        }

        @Override
        public ConvocationResults[] newArray(int size) {
            return new ConvocationResults[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(convocations);
    }
}
