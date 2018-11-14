package com.splitclear.cschunsiu.splitclear.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.splitclear.cschunsiu.splitclear.adapter.MemberRecycleAdapter;
import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.database.viewModel.MainViewModel;
import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.ArrayList;
import java.util.List;

public class AddGroupActivity extends FragmentActivity {
    private EditText groupNameView;
    private RecyclerView recyclerView;
    private MemberRecycleAdapter mAdapter;
    public List<Member> memberList  = new ArrayList();
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_group_member_view);
        groupNameView = findViewById(R.id.editGroupNameField);
        initMemberList();

        recyclerView = findViewById(R.id.addGroupMemberList);
        setRecyclerViewListener(recyclerView);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    //complete button
    public void captureGroupData(View view){
        String text = groupNameView.getText().toString();

        Group group = new Group(text);
        memberList.remove(memberList.size()-1);
        group.setMemberList(new ArrayList<>(memberList));

        mainViewModel.insertGroupAndMember(group);
        memberList.clear();
        finish();
    }

    private void initMemberList(){
        memberList.add(new Member());
    }

    private void setRecyclerViewListener(RecyclerView recyclerView){
        mAdapter = new MemberRecycleAdapter(memberList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }
}
