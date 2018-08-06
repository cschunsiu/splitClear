package com.splitclear.cschunsiu.splitclear.activity;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.splitclear.cschunsiu.splitclear.MainViewModel;
import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.adapter.MemberAdapter;
import com.splitclear.cschunsiu.splitclear.database.GroupAllMembers;
import com.splitclear.cschunsiu.splitclear.database.GroupRepo;
import com.splitclear.cschunsiu.splitclear.model.Groups;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class AddGroupActivity extends FragmentActivity {
    private EditText groupNameView;
    private ListView groupMemberList;
    private Groups groups;
    GroupRepo repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_group_view);
        groupNameView = findViewById(R.id.editGroupNameField);
        groupMemberList = findViewById(R.id.groupMemberList);

        ArrayList ii = new ArrayList();
        ii.add("default");

        MemberAdapter custom = new MemberAdapter(this, ii);
        groupMemberList.setAdapter(custom);

        groups = new Groups("HI");

        repository = new GroupRepo(this);

        repository.insertGroup(groups);
        retrieveGroup();
    }

    private void retrieveGroup(){
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getGroups().observe(this, new Observer<Groups>() {
            @Override
            public void onChanged(@Nullable Groups groups) {
                System.out.println("changed");
                System.out.println(groups.name);
            }
        });
    }

//    public void captureGroupData(View view){
//        String text = groupNameView.getText().toString();
//        groups.setName(text);
//
//        if(groups.getMembers().size() < 10)
//            groups.getMembers().remove(groups.getMembers().size()-1);
//
//        System.out.println(groups.getMembers());
//        System.out.println(groups.getName());
//
//        DatabaseConfig db = DatabaseConfig.getDatabase(this);
//        GroupDao gd = db.groupDao();
//        gd.insert(groups);
//        List<Groups> allGroup = gd.GET_ALL_GROUPS();
//        System.out.println(allGroup);
//    }
//
//    public void addGroupMembers(String groupMemberName){
//        members.set(members.size()-1, groupMemberName);
//        System.out.println("member");
//        if (members.size() < 10){
//            members.add("default");
//            System.out.println("default");
//        }
//    }

}
