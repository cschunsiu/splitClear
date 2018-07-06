package com.splitclear.cschunsiu.splitclear.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.adapter.MemberAdapter;
import com.splitclear.cschunsiu.splitclear.model.Group;

public class AddGroupActivity extends Activity{
    private EditText groupNameView;
    private ListView groupMemberList;
    private static Group group = new Group();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_group_view);
        groupNameView = findViewById(R.id.editGroupNameField);
        groupMemberList = findViewById(R.id.groupMemberList);
        group.addGroupMembers();

        MemberAdapter custom = new MemberAdapter(this, group.getMembers());
        groupMemberList.setAdapter(custom);
    }

    public void captureGroupData(View view){
        String text = groupNameView.getText().toString();
        group.setName(text);

        if(group.getMembers().size() < 10)
            group.getMembers().remove(group.getMembers().size()-1);

        System.out.println(group.getMembers());
        System.out.println(group.getName());
    }

    public static void addGroupMemberButton(String memberName){
        group.addGroupMembers(memberName);
    }

}
