package com.splitclear.cschunsiu.splitclear.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.adapter.BillRecycleAdapter;
import com.splitclear.cschunsiu.splitclear.database.DataRepo;
import com.splitclear.cschunsiu.splitclear.database.viewModel.MainViewModel;
import com.splitclear.cschunsiu.splitclear.model.Bill;
import com.splitclear.cschunsiu.splitclear.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupViewActivity extends FragmentActivity{
    private MainViewModel mainViewModel;
    private BillRecycleAdapter mAdapter;
    private RecyclerView recyclerView;
    private Group selectedGroup;
    private List<Bill> bills = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_view);
        Intent intent = getIntent();
        selectedGroup = intent.getParcelableExtra("Group");

        recyclerView = findViewById(R.id.group_view_billList);
        setBillsRecyclerView(recyclerView);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getBillListSummary().observe(this, new Observer<List<Bill>>() {
            @Override
            public void onChanged(@Nullable List<Bill> bills) {
                liveDataFilterDisplay(bills);
            }
        });

        selectedGroup.setMemberList(new DataRepo(this).getMembers(selectedGroup));
    }

    public void setBillsRecyclerView(RecyclerView recyclerView){
        mAdapter = new BillRecycleAdapter(bills, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

    public void addBillListener(View view){
        Intent i = new Intent(this, AddBillActivity.class).putExtra("Group with Members", selectedGroup);
        startActivity(i);
    }

    public void showBalanceListener(View view) {
        Intent i = new Intent(this, ShowBalanceActivity.class).putExtra("Group with Members", selectedGroup);
        startActivity(i);
    }

    private void liveDataFilterDisplay(List<Bill> bills){
        List<Bill> filteredList = new ArrayList<>();
        for(Bill bill : bills){
            if(bill.groupsId == selectedGroup.id){
                filteredList.add(bill);
            }
        }

        mAdapter.setBillList(filteredList);
        mAdapter.notifyDataSetChanged();
    }

}
