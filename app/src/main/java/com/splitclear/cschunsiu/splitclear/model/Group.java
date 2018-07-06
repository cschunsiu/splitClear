package com.splitclear.cschunsiu.splitclear.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Group implements Serializable{
    private String name;
    private ArrayList<String> members = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getMembers() {
        return members;
    }

    public void addGroupMembers(){
        members.add("default");
        System.out.println("default");
    }

    public void addGroupMembers(String groupMemberName){
        members.set(members.size()-1, groupMemberName);
        System.out.println("member");
        if (members.size() < 10){
            members.add("default");
            System.out.println("default");
        }
    }
}
