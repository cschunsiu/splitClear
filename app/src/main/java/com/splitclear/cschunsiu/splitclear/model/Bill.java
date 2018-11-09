package com.splitclear.cschunsiu.splitclear.model;

import android.arch.persistence.room.*;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "member",
        foreignKeys = @ForeignKey(entity = Group.class,
                parentColumns = "id",
                childColumns = "groupsId",
                onDelete = CASCADE))
public class Bill {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public long groupsId;

    public Bill(){
        this.name = "Default";
    }

    public Bill(String name) {
        this.name = name;
    }

    public void setGroupsId(long groupsId){
        this.groupsId = groupsId;
    }
}
