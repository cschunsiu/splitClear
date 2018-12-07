package com.splitclear.cschunsiu.splitclear;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.splitclear.cschunsiu.splitclear.activity.AddBillActivity;
import com.splitclear.cschunsiu.splitclear.util.AddBillType;
import com.splitclear.cschunsiu.splitclear.util.BillAmountCalculator;

import java.lang.reflect.Method;
import java.util.HashMap;

import static com.splitclear.cschunsiu.splitclear.util.AddBillType.CUSTOM;
import static com.splitclear.cschunsiu.splitclear.util.AddBillType.EVEN;
import static com.splitclear.cschunsiu.splitclear.util.AddBillType.PERCENT;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CalculatorTest {
    private HashMap<Long, Float> testMap;
    private float testNum;
    private float testTips;
    private AddBillType testType;
    @Mock
    private BillAmountCalculator billCal;

    @Before
    public void setUp(){
        testMap = new HashMap<>();
        testMap.put((long) 1, (float) 2);
        testMap.put((long) 2, (float) 4);
        testMap.put((long) 3, (float) 6);
    }

    @After
    public void cleanUp(){
        testMap = null;
    }

    @Test
    public void customCalculationTest() {
        testNum = (float) 19.4;
        testTips = 5;
        billCal.calEachMemberBill(testMap, testNum, testTips);
        assertEquals(2.4, testMap.get((long)1), 2);
        assertEquals(4.8, testMap.get((long)2), 2);
        assertEquals(7.2, testMap.get((long)3), 2);
    }

    @Test
    public void evenCalculationTest() {
        testNum = (float) 14.4;
        testType = EVEN;
        billCal.calEachMemberBill(testType,testMap,testNum);
        assertEquals(4.8, testMap.get((long)1), 2);
        assertEquals(4.8, testMap.get((long)2), 2);
        assertEquals(4.8, testMap.get((long)3), 2);
    }

    @Test
    public void percentageCalculationTest() {
        testNum = (float) 14.4;
        testType = PERCENT;

        testMap.put((long) 1, (float) 10);
        testMap.put((long) 2, (float) 20);
        testMap.put((long) 3, (float) 70);

        billCal.calEachMemberBill(testType,testMap,testNum);
        assertEquals(1.44, testMap.get((long)1), 2);
        assertEquals(2.88, testMap.get((long)2), 2);
        assertEquals(10.08, testMap.get((long)3), 2);
    }


}