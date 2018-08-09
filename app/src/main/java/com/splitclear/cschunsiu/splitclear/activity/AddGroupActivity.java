package com.splitclear.cschunsiu.splitclear.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.splitclear.cschunsiu.splitclear.adapter.MemberRecycleAdapter;
import com.splitclear.cschunsiu.splitclear.database.viewModel.MainViewModel;
import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.adapter.MemberAdapter;
import com.splitclear.cschunsiu.splitclear.database.GroupRepo;
import com.splitclear.cschunsiu.splitclear.model.Group;

import java.util.ArrayList;

public class AddGroupActivity extends FragmentActivity {
    private EditText groupNameView;
    private ListView groupMemberList;
    GroupRepo repository;
    private RecyclerView recyclerView;
    private MemberRecycleAdapter mAdapter;
    private ArrayList ii = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_group_view);
        groupNameView = findViewById(R.id.editGroupNameField);

        ii.add("default");
        ii.add("default");
        ii.add("default");
        ii.add("default");
        ii.add("default");
        ii.add("default");
        ii.add("default");
        ii.add("default");
        ii.add("default");
        ii.add("default");

//        MemberAdapter custom = new MemberAdapter(this, ii);
//        groupMemberList.setAdapter(custom);

        recyclerView = (RecyclerView) findViewById(R.id.addGroupMemberList);

        mAdapter = new MemberRecycleAdapter(ii);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

    public void captureGroupData(View view){
        String text = groupNameView.getText().toString();

        Group group = new Group(text);
        group.setName(text);
    }
}
