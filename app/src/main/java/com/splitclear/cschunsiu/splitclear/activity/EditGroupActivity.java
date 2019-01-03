package com.splitclear.cschunsiu.splitclear.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.adapter.EditGroupMemberAdapter;
import com.splitclear.cschunsiu.splitclear.adapter.MemberRecycleAdapter;
import com.splitclear.cschunsiu.splitclear.database.DataRepo;
import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.ArrayList;
import java.util.List;

public class EditGroupActivity extends FragmentActivity {
    private Group selectedGroup;
    private EditGroupMemberAdapter mAdapter;
    private EditText groupName;
    private RecyclerView recyclerView;
    List<Member> memberList  = new ArrayList();
    private DataRepo dataRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_group_member_view);
        Intent intent = getIntent();
        selectedGroup = intent.getParcelableExtra("Group");
        groupName = findViewById(R.id.add_member_groupName);
        dataRepo = new DataRepo(this);

        groupName.setText(selectedGroup.name);
        memberList = dataRepo.getMembers(selectedGroup);
        memberList.add(new Member());

        recyclerView = findViewById(R.id.add_member_memberList);
        mAdapter = new EditGroupMemberAdapter(memberList,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

    public void captureGroupData(View view){
        selectedGroup.setName(groupName.getText().toString());
        dataRepo.updateGroupAndBill(selectedGroup, memberList);
    }
}
