package com.splitclear.cschunsiu.splitclear.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

import com.splitclear.cschunsiu.splitclear.model.Bill;

import java.util.List;

@Dao
public interface BillDao {
    //TODO To be implemented
    @Query("Select * from `bill`")
    LiveData<List<Bill>> getBillList();

    @Insert
    long insertBill(Bill bill);

    @Delete
    void deleteBill(Bill bill);

    @Update (onConflict = OnConflictStrategy.REPLACE)
    void updateBill(Bill bill);
}
