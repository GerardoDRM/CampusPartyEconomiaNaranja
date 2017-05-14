package com.campusparty.android.economianaranja.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gerardo on 14/05/17.
 */

public class SuccessCase implements Parcelable{

    private String _id;
    private String title;
    private String description;
    private long creation_date;
    private String img;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public long getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(long creation_date) {
        this.creation_date = creation_date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    protected SuccessCase(Parcel in) {
        _id = in.readString();
        title = in.readString();
        description = in.readString();
        creation_date = in.readLong();
        img = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(_id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeLong(creation_date);
        dest.writeString(img);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SuccessCase> CREATOR = new Creator<SuccessCase>() {
        @Override
        public SuccessCase createFromParcel(Parcel in) {
            return new SuccessCase(in);
        }

        @Override
        public SuccessCase[] newArray(int size) {
            return new SuccessCase[size];
        }
    };
}
