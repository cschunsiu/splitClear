package com.splitclear.cschunsiu.splitclear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.adapter.EditBillAdapter;
import com.splitclear.cschunsiu.splitclear.database.DataRepo;
import com.splitclear.cschunsiu.splitclear.model.Bill;

import java.util.HashMap;
import java.util.List;

public class EditBillActivity extends FragmentActivity {
    private Bill selectedBill;
    private EditText billName;
    private EditText billAmount;
    private EditBillAdapter mAdapter;
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

        billList = repo.getBillsByName(selectedBill.groupsId, selectedBill.billName);

        for(Bill bill : billList){
            map.put(bill.memberId, bill.amount);
            System.out.println(bill.amount);
        }

        mAdapter = new EditBillAdapter(billList, map);
        rw.setLayoutManager(new LinearLayoutManager(this));
        rw.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rw.setAdapter(mAdapter);

        fillInData();
    }

    private void fillInData(){
        billName.setText(selectedBill.billName);
        billAmount.setText(selectedBill.amount.toString());
    }

    public void resetBill(View view){
        System.out.println("hi");
    }
}
