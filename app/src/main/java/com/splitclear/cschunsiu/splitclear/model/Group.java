package com.splitclear.cschunsiu.splitclear.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.arch.persistence.room.*;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

@Entity
public class Group {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public String name;
    @Ignore
    private List<Member> memberList;

    public Group(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }
}
