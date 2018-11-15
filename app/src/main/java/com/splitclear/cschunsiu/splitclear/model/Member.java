package com.splitclear.cschunsiu.splitclear.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class Member {
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

    public void setGroupsId(long groupsId){
        this.groupsId = groupsId;
    }

    public String getName(){
        return name;
    }
}
