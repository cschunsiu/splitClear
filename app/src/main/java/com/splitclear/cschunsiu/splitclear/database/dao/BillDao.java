package com.splitclear.cschunsiu.splitclear.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import com.splitclear.cschunsiu.splitclear.model.Bill;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

@Dao
public interface BillDao {
    @Query("Select * from `bill` where groupsId =:groupsId group by billName")
    List<Bill> getBillList(long groupsId);

    @Insert
    long insertBill(Bill bill);

    @Delete
    void deleteBill(Bill bill);

    @Update (onConflict = OnConflictStrategy.REPLACE)
    void updateBill(Bill bill);
}
