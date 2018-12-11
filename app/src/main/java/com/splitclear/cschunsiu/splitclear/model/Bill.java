package com.splitclear.cschunsiu.splitclear.model;

import android.arch.persistence.room.*;
import android.os.Parcel;
import android.os.Parcelable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class Bill implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "billName")
    public String billName;
    @ColumnInfo(name = "amount")
    public Float amount;
    @ColumnInfo(name = "groupsId")
    public long groupsId;
    @ColumnInfo(name = "memberId")
    public long memberId;
    @ColumnInfo(name = "memberName")
    public String memberName;

    public Bill(String billName, long groupsId, long memberId, float amount, String memberName) {
        this.billName = billName;
        this.amount = amount;
        this.groupsId = groupsId;
        this.memberId = memberId;
        this.memberName = memberName;
    }

    public Bill(Parcel parcel){
        id = parcel.readInt();
        billName = parcel.readString();
        amount = parcel.readFloat();
        groupsId = parcel.readLong();
        memberId = parcel.readLong();
        memberName = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(billName);
        dest.writeFloat(amount);
        dest.writeLong(groupsId);
        dest.writeLong(memberId);
        dest.writeString(memberName);
    }

    public static final Parcelable.Creator<Bill> CREATOR = new Parcelable.Creator<Bill>(){

        @Override
        public Bill createFromParcel(Parcel parcel) {
            return new Bill(parcel);
        }

        @Override
        public Bill[] newArray(int size) {
            return new Bill[0];
        }
    };
}
