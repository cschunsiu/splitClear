package com.splitclear.cschunsiu.splitclear.util;

import android.os.Parcel;
import android.os.Parcelable;

import com.splitclear.cschunsiu.splitclear.model.Group;

public class ParcelableGroup implements Parcelable {

    private final Group group;

    private ParcelableGroup(Parcel parcel) {
        this.group = new Group(parcel.readString());
    }

    public ParcelableGroup(Group group){
        this.group = group;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParcelableGroup> CREATOR = new Creator<ParcelableGroup>() {
        @Override
        public ParcelableGroup createFromParcel(Parcel in) {
            return new ParcelableGroup(in);
        }

        @Override
        public ParcelableGroup[] newArray(int size) {
            return new ParcelableGroup[size];
        }
    };
}
