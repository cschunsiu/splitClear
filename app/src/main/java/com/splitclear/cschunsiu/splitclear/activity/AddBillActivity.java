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
import android.widget.Switch;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.adapter.AddBillAdapter;
import com.splitclear.cschunsiu.splitclear.model.Bill;
import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.util.AddBillType;

import java.util.List;

import static android.view.View.GONE;

public class AddBillActivity extends FragmentActivity {
    private EditText billName;
    private EditText billAmount;
    private Group selectedGroup;
    private Switch tipsSwitch;
    private ConstraintLayout billDistribution;
    private List<Bill> billList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bill_view);
        Intent intent = getIntent();
        selectedGroup = intent.getParcelableExtra("Group with Members");

        billName = findViewById(R.id.add_bill_addBillName);
        billAmount = findViewById(R.id.add_bill_addBillAmount);
        billDistribution = findViewById(R.id.add_bill_distributionLayout);
        tipsSwitch = findViewById(R.id.tipsSwitch);
    }

    public void splitEven(View view){
        resetBill(view);
        Float amount = Float.parseFloat(billAmount.getText().toString());
        //add Recycler
        RecyclerView rw = new RecyclerView(this);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
        rw.setLayoutParams(lp);
        rw.setHasFixedSize(true);
        rw.setLayoutManager(new LinearLayoutManager(this));
        rw.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rw.setAdapter(new AddBillAdapter(AddBillType.EVEN, selectedGroup.getMemberList()));
        billDistribution.addView(rw);
    }

    public void splitPercentage(View view){
        resetBill(view);
        Float amount = Float.parseFloat(billAmount.getText().toString());
        //add Recycler
        RecyclerView rw = new RecyclerView(this);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
        rw.setLayoutParams(lp);
        rw.setHasFixedSize(true);
        rw.setLayoutManager(new LinearLayoutManager(this));
        rw.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rw.setAdapter(new AddBillAdapter(AddBillType.PERCENT, selectedGroup.getMemberList()));
        billDistribution.addView(rw);
    }

    public void splitCustom(View view){
        resetBill(view);
        Float amount = Float.parseFloat(billAmount.getText().toString());
        //add Recycler
        RecyclerView rw = new RecyclerView(this);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
        rw.setLayoutParams(lp);
        rw.setHasFixedSize(true);
        rw.setLayoutManager(new LinearLayoutManager(this));
        rw.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rw.setAdapter(new AddBillAdapter(AddBillType.CUSTOM, selectedGroup.getMemberList()));
        billDistribution.addView(rw);
    }

    public void captureBill(View view){}

    public void resetBill(View view){
        billDistribution.removeAllViews();
        tipsSwitch.setVisibility(GONE);
    }
}
