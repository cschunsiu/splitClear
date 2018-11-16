package com.splitclear.cschunsiu.splitclear.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class Member implements Parcelable {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public Long id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "groupsId")
    public Long groupsId;

    @Ignore
    public Member(){
        this.name = "Default";
    }

    public Member(String name) {
        this.name = name;
    }

    public Member(Parcel parcel){
        id = parcel.readLong();
        name = parcel.readString();
        groupsId = parcel.readLong();
    }

    public void setGroupsId(long groupsId){
        this.groupsId = groupsId;
    }

    public String getName(){
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeLong(groupsId);
    }

    public static final Parcelable.Creator<Member> CREATOR = new Parcelable.Creator<Member>(){

        @Override
        public Member createFromParcel(Parcel parcel) {
            return new Member(parcel);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[0];
        }
    };
}
