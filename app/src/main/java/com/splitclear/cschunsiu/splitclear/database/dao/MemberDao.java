package com.splitclear.cschunsiu.splitclear.database.dao;

import android.arch.persistence.room.*;

import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

@Dao
public interface MemberDao {
    @Query("Select * from `Member` where id=:groupsId")
    List<Member> getMembers(int groupsId);

    @Insert
    long insertMember(Member member);

    @Delete
    void deleteMember(Member member);

    @Update (onConflict = OnConflictStrategy.REPLACE)
    void updateMember(Member member);
}
