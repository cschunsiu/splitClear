package com.splitclear.cschunsiu.splitclear.util;

import android.content.Context;
import android.view.View;

import com.splitclear.cschunsiu.splitclear.model.Group;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BillAmountCalculator {
    private static Random rand = new Random();
    public static void calEachMemberBill(AddBillType billType, HashMap<Long,Float> map, float amount){
        switch(billType){
            case EVEN:
                calEvenDistribution(map,amount);
                break;
            case PERCENT:
                for (Map.Entry<Long,Float> entry: map.entrySet()) {
                    float result = entry.getValue();
                    System.out.println(setDecimal(amount*(result/100)));
                    entry.setValue(setDecimal(amount*(result/100)));
                }
                break;
        }
    }

    public static void calEachMemberBill(HashMap<Long,Float> map, float amount, float tips){
        float customTotal = 0;
        for (Map.Entry<Long,Float> entry: map.entrySet()) {
            customTotal += entry.getValue();
        }

        calCustomDistributionWIthTipsAndTax(tips, amount, customTotal);
    }

    private static void calEvenDistribution(HashMap<Long,Float> map, float amount){
        List prepList = new ArrayList(map.keySet());
        float offset = getOffset(amount, map.size(),2);
        amount -= offset;

        for (Map.Entry<Long,Float> entry: map.entrySet()) {
            float result = amount/map.size();
            entry.setValue(setDecimal(result));
        }

        for (int i = (int) (offset*100); i != 0; i--) {
            int randomIndex = rand.nextInt(prepList.size());
            long memberId = (long) prepList.get(randomIndex);

            Float result = map.get(memberId);

            System.out.println(setDecimal(result+(float)0.01));
            map.replace(memberId,setDecimal(result+(float)0.01));
            prepList.remove(randomIndex);
        }

    }

    private static void calCustomDistributionWIthTipsAndTax(float tips, float amount, float customTotal){

    }

    private static float getOffset(float amount, int memberNumber, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(amount));
        amount = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();

        float offset = 0;

        amount *= 100;

        while(amount % memberNumber != 0){
            offset += 0.01;
            amount -=1;
        }

        return offset;
    }

    private static float setDecimal(float num){
        BigDecimal bd = new BigDecimal(Float.toString(num));
        float newNum = bd.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();

        return newNum;
    }
}
