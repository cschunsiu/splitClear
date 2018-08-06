package com.splitclear.cschunsiu.splitclear.database.dao;

import android.arch.persistence.room.*;

import com.splitclear.cschunsiu.splitclear.model.Groups;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

@Dao
public interface MemberDao {

    @Insert
    void insert(Member member);

//    @Delete
//    void delete(Member member);
//
//    @Query("SELECT * FROM member")
//    List<Member> getAllRepos();
//
//    @Query("SELECT * FROM member WHERE groupsId=:groupsId")
//    List<Member> findMemberById(int groupsId);
}
