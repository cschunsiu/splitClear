package com.splitclear.cschunsiu.splitclear.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity
public class Member {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public long groupsId;

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
