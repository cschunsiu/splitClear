package com.splitclear.cschunsiu.splitclear.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.splitclear.cschunsiu.splitclear.database.GroupAllMembers;
import com.splitclear.cschunsiu.splitclear.model.Groups;

import java.util.List;

@Dao
public interface GroupDao {
//    @Query("SELECT * from groups")
//    LiveData<List<Groups>> getAllGroups();

    @Query("Select * from groups")
    GroupAllMembers getAllGroups();

    @Query("Select * from groups where id=:groupsId")
    LiveData<Groups> getGroup(int groupsId);

    @Insert
    void insertGroup(Groups groups);

    @Delete
    void deleteGroup(Groups groups);

    @Update (onConflict = OnConflictStrategy.REPLACE)
    void updateGroup(Groups groups);
}
