package com.craftcode.android.clicksocial.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerardo on 14/05/17.
 */

public class OrganizationsResults implements Parcelable{

    private List<Organization> organizations = new ArrayList<Organization>();

    protected OrganizationsResults(Parcel in) {
        organizations = in.createTypedArrayList(Organization.CREATOR);
    }

    public List<Organization> getResults() {
        return organizations;
    }


    public static final Creator<OrganizationsResults> CREATOR = new Creator<OrganizationsResults>() {
        @Override
        public OrganizationsResults createFromParcel(Parcel in) {
            return new OrganizationsResults(in);
        }

        @Override
        public OrganizationsResults[] newArray(int size) {
            return new OrganizationsResults[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(organizations);
    }
}
