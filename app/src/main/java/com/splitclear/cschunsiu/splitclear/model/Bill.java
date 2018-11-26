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
    @ColumnInfo(name = "memberName")
    public String memberName;

    public Bill(String billName, long groupsId, long memberId, float amount, String memberName) {
        this.billName = billName;
        this.amount = amount;
        this.groupsId = groupsId;
        this.memberId = memberId;
        this.memberName = memberName;
    }
}
