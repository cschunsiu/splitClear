package com.splitclear.cschunsiu.splitclear.database.dao;

import android.arch.persistence.room.*;
import android.database.Cursor;

import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

@Dao
public interface MemberDao {
    @Query("Select * from `Member` where groupsId = :groupsId")
    List<Member> getMembers(Long groupsId);

    @Query("Select * from `Member`")
    Cursor TESgetMembers();

    @Insert
    long insertMember(Member member);

    @Delete
    void deleteMember(Member member);

    @Update (onConflict = OnConflictStrategy.REPLACE)
    void updateMember(Member member);
}
