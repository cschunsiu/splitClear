package com.splitclear.cschunsiu.splitclear.model;

import android.arch.persistence.room.*;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class Bill {
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

    public Bill(String billName, long groupsId, long memberId, float amount) {
        this.billName = billName;
        this.amount = amount;
        this.groupsId = groupsId;
        this.memberId = memberId;
    }

//    public void setAmount(float amount){
//        this.amount = amount;
//    }
//
//    public void setGroupsId(long groupsId){
//        this.groupsId = groupsId;
//    }
//
//    public void setMemberId(long memberId){
//        this.memberId = memberId;
//    }
//
//    public void setMemberName(String memberName){
//        this.memberName = memberName;
//    }
}
