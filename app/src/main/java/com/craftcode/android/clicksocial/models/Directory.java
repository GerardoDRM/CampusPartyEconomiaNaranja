package com.craftcode.android.clicksocial.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gerardo on 14/05/17.
 */

public class Directory implements Parcelable{


    private String _id;
    private String social_reason;
    private String figure;
    private String emails;
    private String federal_entity;
    private String place;
    private String network;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSocial_reason() {
        return social_reason;
    }

    public void setSocial_reason(String social_reason) {
        this.social_reason = social_reason;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getFederal_entity() {
        return federal_entity;
    }

    public void setFederal_entity(String federal_entity) {
        this.federal_entity = federal_entity;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    protected Directory(Parcel in) {
        _id = in.readString();
        social_reason = in.readString();
        figure = in.readString();
        emails = in.readString();
        place = in.readString();
        federal_entity = in.readString();
        network = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(social_reason);
        dest.writeString(figure);
        dest.writeString(emails);
        dest.writeString(place);
        dest.writeString(place);
        dest.writeString(federal_entity);
        dest.writeString(network);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Directory> CREATOR = new Creator<Directory>() {
        @Override
        public Directory createFromParcel(Parcel in) {
            return new Directory(in);
        }

        @Override
        public Directory[] newArray(int size) {
            return new Directory[size];
        }
    };
}
