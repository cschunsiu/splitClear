package com.splitclear.cschunsiu.splitclear.activity;

import android.arch.lifecycle.LiveData;
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
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.adapter.GroupRecycleAdapter;
import com.splitclear.cschunsiu.splitclear.adapter.MemberRecycleAdapter;
import com.splitclear.cschunsiu.splitclear.database.GroupAllMembers;
import com.splitclear.cschunsiu.splitclear.database.viewModel.MainViewModel;
import com.splitclear.cschunsiu.splitclear.model.Group;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private MainViewModel mainViewModel;
    private GroupRecycleAdapter mAdapter;
    private List<Group> initGroupList = new ArrayList();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.mainGroupList);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getGroupList().observe(MainActivity.this, new Observer<List<Group>>() {
            @Override
            public void onChanged(@Nullable List<Group> groups) {
                mAdapter.setGroup(groups);
            }
        });

        setGroupRecyclerView(recyclerView);
     }

     //Add Group Button Function
    public void addGroupListener(View view){
        Intent i = new Intent(this, AddGroupActivity.class);
        startActivity(i);
    }

    public void setGroupRecyclerView(RecyclerView recyclerView){
        mAdapter = new GroupRecycleAdapter(initGroupList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

}