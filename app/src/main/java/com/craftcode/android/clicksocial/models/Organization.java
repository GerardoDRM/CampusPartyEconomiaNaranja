package com.craftcode.android.clicksocial.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.StringBuilderPrinter;

/**
 * Created by gerardo on 14/05/17.
 */

public class Organization implements Parcelable{

    private String _id;
    private String entity;
    private String web;
    private String model;
    private String federal_entity;
    private String email;
    private String type;
    private String social_group;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFederal_entity() {
        return federal_entity;
    }

    public void setFederal_entity(String federal_entity) {
        this.federal_entity = federal_entity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSocial_group() {
        return social_group;
    }

    public void setSocial_group(String social_group) {
        this.social_group = social_group;
    }

    protected Organization(Parcel in) {
        _id = in.readString();
        entity = in.readString();
        model = in.readString();
        web = in.readString();
        federal_entity = in.readString();
        type = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(entity);
        dest.writeString(model);
        dest.writeString(web);
        dest.writeString(federal_entity);
        dest.writeString(email);
        dest.writeString(type);
        dest.writeString(social_group);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Organization> CREATOR = new Creator<Organization>() {
        @Override
        public Organization createFromParcel(Parcel in) {
            return new Organization(in);
        }

        @Override
        public Organization[] newArray(int size) {
            return new Organization[size];
        }
    };
}
