package com.splitclear.cschunsiu.splitclear.database;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

public class GroupAllMembers{
    @Embedded
    public Group group;

    @Relation(parentColumn = "id", entityColumn = "groupsId", entity = Member.class)
    public List<Member> members;
}
