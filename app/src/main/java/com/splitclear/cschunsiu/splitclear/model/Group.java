package com.splitclear.cschunsiu.splitclear.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.arch.persistence.room.*;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

@Entity
public class Group implements Parcelable{

    @PrimaryKey(autoGenerate = true)
    public long id;
    @NonNull
    public String name;
    @Ignore
    private List<Member> memberList;

    public Group(String name){
        this.name = name;
    }

    public Group(Parcel parcel){
        id = parcel.readLong();
        name = parcel.readString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId(){
        return id;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
    }

    public static final Parcelable.Creator<Group> CREATOR = new Parcelable.Creator<Group>(){

        @Override
        public Group createFromParcel(Parcel parcel) {
            return new Group(parcel);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[0];
        }
    };
}
