package com.splitclear.cschunsiu.splitclear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.adapter.EditBillAdapter;
import com.splitclear.cschunsiu.splitclear.database.DataRepo;
import com.splitclear.cschunsiu.splitclear.model.Bill;
import com.splitclear.cschunsiu.splitclear.model.Member;
import com.splitclear.cschunsiu.splitclear.util.AddBillType;
import com.splitclear.cschunsiu.splitclear.util.BillAmountCalculator;

import java.util.HashMap;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class EditBillActivity extends FragmentActivity {
    private Bill selectedBill;
    private EditText billName;
    private EditText billAmount;
    private EditBillAdapter mAdapter;
    private EditText tipsInput;
    private RecyclerView rw;
    private HashMap<Long, Float> map = new HashMap<>();
    private List<Bill> billList;
    private DataRepo repo = new DataRepo(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bill_view);
        Intent intent = getIntent();
        selectedBill = intent.getParcelableExtra("Bill");
        billName = findViewById(R.id.add_bill_addBillName);
        billAmount = findViewById(R.id.add_bill_addBillAmount);
        rw = findViewById(R.id.add_bill_distribution);
        tipsInput = findViewById(R.id.tipsInput);

        billList = repo.getBillsByName(selectedBill.groupsId, selectedBill.billName);

        for(Bill bill : billList){
            map.put(bill.memberId, bill.amount);
        }

        mAdapter = new EditBillAdapter(billList, map);
        rw.setLayoutManager(new LinearLayoutManager(this));
        rw.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rw.setAdapter(mAdapter);

        fillInData();
    }

    private void fillInData(){
        billName.setText(selectedBill.billName);
        Float result = BillAmountCalculator.setDecimal(selectedBill.amount);
        billAmount.setText(result.toString());
    }

    public void splitEven(View view){
        resetBill(view);
        float amount = Float.parseFloat(billAmount.getText().toString());
        mAdapter.setAmountAndBillType(AddBillType.EVEN);
        BillAmountCalculator.calEachMemberBill(mAdapter.getBillType(), map, amount);
        rw.setAdapter(mAdapter);
    }

    public void splitPercentage(View view){
        resetBill(view);
        //add Recycler
        mAdapter.setAmountAndBillType(AddBillType.PERCENT);
        rw.setAdapter(mAdapter);
    }

    public void splitCustom(View view){
        //TODO implement tips switch
        resetBill(view);
        //add Recycler
        mAdapter.setAmountAndBillType(AddBillType.CUSTOM);
        rw.setAdapter(mAdapter);
        tipsInput.setVisibility(VISIBLE);
        Toast.makeText(this,"Tax will be calculated, please enter Tips if any", Toast.LENGTH_LONG).show();
    }

    public void captureBill(View view){
        createBillsForMembers(mAdapter.getBillType());
        repo.updateBills(billList);
        finish();
    }

    public void resetBill(View view){
        tipsInput.setText("0");
        tipsInput.setVisibility(GONE);
        rw.setAdapter(null);
    }

    private void createBillsForMembers(AddBillType billType){
        float amount = Float.parseFloat(billAmount.getText().toString());
        String billNameInput = billName.getText().toString();

        switch(billType){
            case CUSTOM:
                for (int i = 0; i < rw.getLayoutManager().getChildCount(); i++){
                    View view = rw.getChildAt(i);
                    EditText result = view.findViewById(R.id.add_bill_custom_amount);
                    map.put(billList.get(i).memberId, Float.parseFloat(result.getText().toString()));
                }
                BillAmountCalculator.calEachMemberBill(map,amount, Float.parseFloat(tipsInput.getText().toString()));
                break;
            case PERCENT:
                for (int i = 0; i < rw.getLayoutManager().getChildCount(); i++){
                    View view = rw.getChildAt(i);
                    SeekBar result = view.findViewById(R.id.add_bill_percent_bar);
                    map.put(billList.get(i).memberId, (float)result.getProgress());
                }
                BillAmountCalculator.calEachMemberBill(billType,map,amount);
                break;
        }

        for(Bill bill : billList){
            float adjustedAmount = map.get(bill.memberId);
            bill.billName = billNameInput;
            bill.amount = adjustedAmount;
        }
    }
}
