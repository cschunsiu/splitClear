package com.splitclear.cschunsiu.splitclear.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.splitclear.cschunsiu.splitclear.model.Group;

import java.util.List;

@Dao
public interface GroupDao {
    @Query("Select * from `Group`")
    LiveData<List<Group>> getGroupList();

    @Insert
    long insertGroup(Group group);

    //TODO implete delete and update
    @Delete
    void deleteGroup(Group group);

    @Update (onConflict = OnConflictStrategy.REPLACE)
    void updateGroup(Group group);
}
