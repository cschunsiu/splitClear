package com.splitclear.cschunsiu.splitclear.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import com.splitclear.cschunsiu.splitclear.model.Bill;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

@Dao
public interface BillDao {
    @Query("Select *, SUM(amount) as amount from `bill` group by billName")
    LiveData<List<Bill>> getBillListSummary();

    @Query("Select * from `bill` where groupsId = :groupsId and billName = :billName")
    List<Bill> getBillListByName(long groupsId, String billName);

    @Query("Select *, SUM(amount) as amount from `bill` where groupsId = :groupsId group by memberId, memberName")
    List<Bill> getBillListByMember(long groupsId);

    @Query("Update `bill` set memberName = :memberName where memberId = :memberId and groupsId = :groupsId")
    void updateMemberName(String memberName, long memberId, long groupsId);

    @Insert
    long insertBill(Bill bill);

    @Query("Delete from `bill` where groupsId= :groupsId")
    void deleteBill(Long groupsId);

    @Update (onConflict = OnConflictStrategy.REPLACE)
    void updateBill(Bill bill);
}
