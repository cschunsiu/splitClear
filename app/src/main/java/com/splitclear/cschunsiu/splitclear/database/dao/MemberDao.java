package com.splitclear.cschunsiu.splitclear.database.dao;

import android.arch.persistence.room.*;

import com.splitclear.cschunsiu.splitclear.model.Member;

@Dao
public interface MemberDao {
//    @Query("Select * from `Group` where id=:groupsId")
//    LiveData<Group> getGroup(int groupsId);

    @Insert
    void insertMemeber(Member... member);

    @Delete
    void deleteMember(Member member);

    @Update (onConflict = OnConflictStrategy.REPLACE)
    void updateMember(Member member);
}
