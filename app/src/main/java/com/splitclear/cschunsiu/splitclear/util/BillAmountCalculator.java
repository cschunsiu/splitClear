package com.splitclear.cschunsiu.splitclear.util;

import android.content.Context;
import android.view.View;

import com.splitclear.cschunsiu.splitclear.model.Group;

import java.util.HashMap;
import java.util.Map;

public class BillAmountCalculator {
    public static void calEachMemberBill(AddBillType billType, HashMap<Long,Float> map, float amount, Group selectedGroup){
        for (Map.Entry<Long,Float> entry: map.entrySet()) {
            entry.setValue((float)50);
        }
    }
}
