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
import android.widget.Toast;

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
import static android.view.View.VISIBLE;

public class AddBillActivity extends FragmentActivity {
    private EditText billName;
    private EditText billAmount;
    private Group selectedGroup;
    private List<Bill> billList = new ArrayList<>();
    private RecyclerView rw;
    private EditText tipsInput;
    private DataRepo dataRepo;
    private AddBillAdapter abAdapter;
    private HashMap<Long, Float> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bill_view);
        Intent intent = getIntent();
        selectedGroup = intent.getParcelableExtra("Group with Members");
        for(Member m : selectedGroup.getMemberList()){
            map.put(m.id, (float) 0);
        }

        billName = findViewById(R.id.add_bill_addBillName);
        billAmount = findViewById(R.id.add_bill_addBillAmount);
        rw = findViewById(R.id.add_bill_distribution);
        tipsInput = findViewById(R.id.tipsInput);
        tipsInput.setVisibility(GONE);
        tipsInput.getText().clear();
        dataRepo = new DataRepo(this);
        abAdapter = new AddBillAdapter(selectedGroup.getMemberList(), map);
        rw.setLayoutManager(new LinearLayoutManager(this));
        rw.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    public void splitEven(View view){
        resetBill(view);
        float amount = Float.parseFloat(billAmount.getText().toString());
        abAdapter.setAmountAndBillType(AddBillType.EVEN);
        BillAmountCalculator.calEachMemberBill(abAdapter.getBillType(), map, amount);
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
        tipsInput.setVisibility(VISIBLE);
        Toast.makeText(this,"Tax will be calculated, please enter Tips if any", Toast.LENGTH_LONG).show();
    }

    public void captureBill(View view){
        createBillsForMembers(abAdapter.getBillType());
        dataRepo.insertBills(billList);
        finish();
    }

    public void resetBill(View view){
        tipsInput.getText().clear();
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
                    map.put(selectedGroup.getMemberList().get(i).id, Float.parseFloat(result.getText().toString()));
                }
                BillAmountCalculator.calEachMemberBill(map,amount, Float.parseFloat(tipsInput.getText().toString()));
                break;
            case PERCENT:
                for (int i = 0; i < rw.getLayoutManager().getChildCount(); i++){
                    View view = rw.getChildAt(i);
                    SeekBar result = view.findViewById(R.id.add_bill_percent_bar);
                    map.put(selectedGroup.getMemberList().get(i).id, (float)result.getProgress());
                }
                BillAmountCalculator.calEachMemberBill(billType,map,amount);
                break;
        }

        for(Member member : selectedGroup.getMemberList()){
            billList.add(new Bill(billNameInput,selectedGroup.getId(), member.id, map.get(member.id), member.name));
        }
    }
}
