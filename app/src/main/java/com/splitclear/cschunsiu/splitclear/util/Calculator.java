package com.splitclear.cschunsiu.splitclear.util;

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

        float taxRate = setDecimal((amount - tips - customTotal) / setDecimal(customTotal) * 100);

        taxRate /= 100;
        for (Map.Entry<Long,Float> entry: map.entrySet()) {
            float ff = setDecimal(entry.getValue() * (1 + taxRate));
            entry.setValue(ff);
        }

        float offset = getOffset(tips, map.size(),2);
        tips -= offset;
        for (Map.Entry<Long,Float> entry: map.entrySet()) {
            float evenTips = setDecimal(tips/map.size());
            float result = entry.getValue();
            entry.setValue(setDecimal(result + evenTips));
        }

        List prepList = new ArrayList(map.keySet());
        for (int i = (int) (offset*100); i != 0; i--) {
            int randomIndex = rand.nextInt(prepList.size());
            long memberId = (long) prepList.get(randomIndex);

            Float result = map.get(memberId);
            map.replace(memberId,setDecimal(result+(float)0.01));
            prepList.remove(randomIndex);
        }


        float finalTotal = 0;
        for (Map.Entry<Long,Float> entry: map.entrySet()) {
            finalTotal += entry.getValue();
        }
        finalTotal = setDecimal(finalTotal);

        Long minMap= Long.valueOf(0);
        Long maxMap= Long.valueOf(0);
        Float min = null;
        Float max = null;
        for (Map.Entry<Long,Float> entry: map.entrySet()) {
            if(min == null){
                min = entry.getValue();
                minMap = entry.getKey();
                max = entry.getValue();
                maxMap = entry.getKey();
            }
            if(entry.getValue() < min){
                min = entry.getValue();
                minMap = entry.getKey();
            }else if (entry.getValue() > max){
                max = entry.getValue();
                maxMap = entry.getKey();
            }
        }

        if(finalTotal > amount){
            map.replace(maxMap, setDecimal(map.get(maxMap) - (float)0.01));
        }else if (finalTotal < amount){
            map.replace(minMap, setDecimal(map.get(minMap) + (float)0.01));
        }

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

            map.replace(memberId,setDecimal(result+(float)0.01));
            prepList.remove(randomIndex);
        }

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

    public static float setDecimal(float num){
        BigDecimal bd = new BigDecimal(Float.toString(num));
        float newNum = bd.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();

        return newNum;
    }
}
