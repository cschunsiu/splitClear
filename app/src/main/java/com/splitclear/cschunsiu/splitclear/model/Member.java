package com.splitclear.cschunsiu.splitclear.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "member",
        foreignKeys = @ForeignKey(entity = Groups.class,
        parentColumns = "id",
        childColumns = "groupsId",
        onDelete = CASCADE))
public class Member {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public int groupsId;

    public Member(String name, int groupsId) {
        this.name = name;
        this.groupsId = groupsId;
    }
}
