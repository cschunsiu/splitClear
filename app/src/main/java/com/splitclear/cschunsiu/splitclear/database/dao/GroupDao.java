package com.splitclear.cschunsiu.splitclear.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.splitclear.cschunsiu.splitclear.database.GroupAllMembers;
import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

@Dao
public interface GroupDao {
//    @Query("Select * from `Group`")
//    GroupAllMembers getAllGroups();

    @Query("Select * from `Group`")
    LiveData<List<Group>> getGroupList();

    @Query("Select * from `Group` where id=:groupsId")
    LiveData<Group> getGroup(int groupsId);

    @Query("Select * from `Group`")
    List<Group> getGroup();

    @Query("Select * from `Group` where name=:name")
    Group getGroupMethod(String name);

    @Query("Select * from `Member` where groupsId=:groupsId")
    List<Member> getMemberMethod(int groupsId);

    @Insert
    int insertGroup(Group group);

    @Insert
    void insertMemberList(List<Member> members);

    @Delete
    void deleteGroup(Group group);

    @Update (onConflict = OnConflictStrategy.REPLACE)
    void updateGroup(Group group);
}
