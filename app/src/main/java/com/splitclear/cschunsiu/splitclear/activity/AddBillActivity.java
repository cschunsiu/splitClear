package com.splitclear.cschunsiu.splitclear.activity;

import android.arch.lifecycle.ViewModelProviders;
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
import com.splitclear.cschunsiu.splitclear.database.DataRepo;
import com.splitclear.cschunsiu.splitclear.database.viewModel.MainViewModel;
import com.splitclear.cschunsiu.splitclear.model.Bill;
import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;
import com.splitclear.cschunsiu.splitclear.util.AddBillType;
import com.splitclear.cschunsiu.splitclear.util.BillAmountCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.view.View.GONE;

public class AddBillActivity extends FragmentActivity {
    private EditText billName;
    private EditText billAmount;
    private Group selectedGroup;
    private Switch tipsSwitch;
    private List<Bill> billList = new ArrayList<>();
    private RecyclerView rw;
    private DataRepo dataRepo;
    private AddBillAdapter abAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bill_view);
        Intent intent = getIntent();
        selectedGroup = intent.getParcelableExtra("Group with Members");

        billName = findViewById(R.id.add_bill_addBillName);
        billAmount = findViewById(R.id.add_bill_addBillAmount);
        tipsSwitch = findViewById(R.id.tipsSwitch);
        rw = findViewById(R.id.add_bill_distribution);
        dataRepo = new DataRepo(this);
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
        //TODO implement tips switch
        resetBill(view);
        //add Recycler
        abAdapter.setAmountAndBillType(AddBillType.CUSTOM);
        rw.setAdapter(abAdapter);
    }

    public void captureBill(View view){
        //TODO change behavior or calculator
        createBillsForMembers(abAdapter.getBillType());
        dataRepo.insertBills(billList);
        finish();
    }

    public void resetBill(View view){
        rw.removeAllViews();
        tipsSwitch.setVisibility(GONE);
    }

    private void createBillsForMembers(AddBillType billType){
        //TODO implement accurate calculator
        float amount = Float.parseFloat(billAmount.getText().toString());
        String billNameInput = billName.getText().toString();
        HashMap<Long, Float> map = new HashMap<>();

        switch(billType){
            case CUSTOM:
                for (int i = 0; i < rw.getLayoutManager().getChildCount(); i++){
                    View view = rw.getChildAt(i);
                    EditText result = view.findViewById(R.id.add_bill_custom_amount);
                    map.put(selectedGroup.getMemberList().get(i).id, Float.parseFloat(result.getText().toString()));
                }
                break;
            case PERCENT:
                for (int i = 0; i < rw.getLayoutManager().getChildCount(); i++){
                    View view = rw.getChildAt(i);
                    SeekBar result = view.findViewById(R.id.add_bill_percent_bar);
                    map.put(selectedGroup.getMemberList().get(i).id, (float)result.getProgress());
                }
                break;
            case EVEN:
                for (int i = 0; i < rw.getLayoutManager().getChildCount(); i++) {
                    map.put(selectedGroup.getMemberList().get(i).id, amount);
                }
                break;
        }

        BillAmountCalculator.calEachMemberBill(billType,map,amount, selectedGroup);

        for(Member member : selectedGroup.getMemberList()){
            billList.add(new Bill(billNameInput,selectedGroup.getId(), member.id, map.get(member.id), member.name));
        }
    }
}
