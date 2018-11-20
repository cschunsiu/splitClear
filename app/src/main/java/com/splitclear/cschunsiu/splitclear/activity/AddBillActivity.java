package com.splitclear.cschunsiu.splitclear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.adapter.AddBillAdapter;
import com.splitclear.cschunsiu.splitclear.model.Bill;
import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;
import com.splitclear.cschunsiu.splitclear.util.AddBillType;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

public class AddBillActivity extends FragmentActivity {
    private EditText billName;
    private EditText billAmount;
    private Group selectedGroup;
    private Switch tipsSwitch;
    private List<Bill> billList;
    private RecyclerView rw;
    private AddBillAdapter abAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bill_view);
        Intent intent = getIntent();
        selectedGroup = intent.getParcelableExtra("Group with Members");

        billList = new ArrayList<>();
        billName = findViewById(R.id.add_bill_addBillName);
        billAmount = findViewById(R.id.add_bill_addBillAmount);
        tipsSwitch = findViewById(R.id.tipsSwitch);
        rw = findViewById(R.id.add_bill_distribution);
        abAdapter = new AddBillAdapter(selectedGroup.getMemberList());
        rw.setLayoutManager(new LinearLayoutManager(this));
        rw.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    public void splitEven(View view){
        resetBill(view);
        float amount = Float.parseFloat(billAmount.getText().toString());
        //add Recycler
        abAdapter.setAmountAndBillType(AddBillType.EVEN, amount);
        rw.setAdapter(abAdapter);
    }

    public void splitPercentage(View view){
        resetBill(view);
        //add Recycler
        abAdapter.setAmountAndBillType(AddBillType.PERCENT);
        rw.setAdapter(abAdapter);
    }

    public void splitCustom(View view){
        resetBill(view);
        //add Recycler
        abAdapter.setAmountAndBillType(AddBillType.CUSTOM);
        rw.setAdapter(abAdapter);
    }

    public void captureBill(View view){
        billCalculator(abAdapter.getBillType());
        finish();
    }

    public void resetBill(View view){
        rw.removeAllViews();
        tipsSwitch.setVisibility(GONE);
    }

    private void billCalculator(AddBillType billType){
        float amount = Float.parseFloat(billAmount.getText().toString());
        String billNameInput = billName.getText().toString();

        for(int i = 0; i < selectedGroup.getMemberList().size(); i++) {
            View ad = rw.getChildAt(i);

            if(billType == AddBillType.CUSTOM) {
                EditText result = ad.findViewById(R.id.add_bill_custom_amount);
                billList.add(new Bill(billNameInput, selectedGroup.id, selectedGroup.getMemberList().get(i).id, Float.parseFloat(result.getText().toString())));
            }else if(billType == AddBillType.PERCENT){
                SeekBar result = ad.findViewById(R.id.add_bill_percent_bar);
                float memberAmount = amount * ((float)result.getProgress()/100);
                billList.add(new Bill(billNameInput, selectedGroup.id, selectedGroup.getMemberList().get(i).id, memberAmount));
            }else{
                float memberAmount = amount/selectedGroup.getMemberList().size();
                billList.add(new Bill(billNameInput, selectedGroup.id, selectedGroup.getMemberList().get(i).id, memberAmount));
            }
        }
    }
}
