package com.splitclear.cschunsiu.splitclear.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.adapter.MemberRecycleAdapter;
import com.splitclear.cschunsiu.splitclear.adapter.ShowBalanceAdapter;
import com.splitclear.cschunsiu.splitclear.database.DataRepo;
import com.splitclear.cschunsiu.splitclear.database.viewModel.MainViewModel;
import com.splitclear.cschunsiu.splitclear.model.Bill;
import com.splitclear.cschunsiu.splitclear.model.Group;

import java.util.HashMap;
import java.util.List;

public class ShowBalanceActivity extends FragmentActivity {
    private Group selectedGroup;
    private MainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private ShowBalanceAdapter mAdapter;
    private List<Bill> billList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_balance_view);
        Intent intent = getIntent();
        selectedGroup = intent.getParcelableExtra("Group with Members");
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        recyclerView = findViewById(R.id.show_balance_list);
        fetchData();
        displayData();
    }

    private void fetchData(){
        billList = new DataRepo(this).getBillsByMember(selectedGroup);
    }

    private void displayData(){
        mAdapter = new ShowBalanceAdapter(billList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }
}
