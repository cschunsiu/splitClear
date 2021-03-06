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
import com.splitclear.cschunsiu.splitclear.adapter.GroupRecycleAdapter;
import com.splitclear.cschunsiu.splitclear.database.DataRepo;
import com.splitclear.cschunsiu.splitclear.database.viewModel.MainViewModel;
import com.splitclear.cschunsiu.splitclear.model.Group;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements GroupRecycleAdapter.GroupViewRecyclerItemClick {
    private MainViewModel mainViewModel;
    private List<Group> groupList = new ArrayList<>();
    private DataRepo dataRepo;
    private GroupRecycleAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataRepo = new DataRepo(this);
        recyclerView = findViewById(R.id.main_view_GroupList);
        setGroupRecyclerView(recyclerView);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getGroupList().observe(MainActivity.this, new Observer<List<Group>>() {
            @Override
            public void onChanged(@Nullable List<Group> groups) {
                groupList = groups;
                mAdapter.setGroup(groups);
            }
        });
     }

     //Add Group Button Function
    public void addGroupListener(View view){
        Intent i = new Intent(this, AddGroupActivity.class);
        startActivity(i);
    }

    public void setGroupRecyclerView(RecyclerView recyclerView){
        mAdapter = new GroupRecycleAdapter(groupList, this);
        mAdapter.setListener(this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(int position, String action) {
        if(action.equals("delete")){
            dataRepo.deleteGroup(groupList.get(position));
        }else{
            Intent i = new Intent(this, EditGroupActivity.class).putExtra("Group", groupList.get(position));
            startActivity(i);
        }
    }
}