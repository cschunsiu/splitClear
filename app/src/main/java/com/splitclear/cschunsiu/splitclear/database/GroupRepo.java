package com.splitclear.cschunsiu.splitclear.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.splitclear.cschunsiu.splitclear.database.dao.GroupDao;
import com.splitclear.cschunsiu.splitclear.database.dao.MemberDao;
import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

public class GroupRepo{
    private final GroupDao groupDao;
    private final MemberDao memberDao;

    public GroupRepo (Context context){
        DatabaseConfig db = DatabaseConfig.getDatabase(context);
        groupDao = db.groupDao();
        memberDao = db.memberDao();
    }

    public void insertGroupAndMember(final Group group) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                long groupID = groupDao.insertGroup(group);
                for(Member member : group.getMemberList()){
                    member.setGroupsId(groupID);
                    memberDao.insertMemeber(member);
                }
                return null;
            }
        }.execute();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    public LiveData<List<Group>> getGroup(){return groupDao.getGroupList();}

    public LiveData<Group> getGroups(int id){
        return groupDao.getGroup(id);
    }

    public void updateGroup(final Group group){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                groupDao.updateGroup(group);
                return null;
            }
        }.execute();
    }

    public void deleteGroup(final int id) {
        final LiveData<Group> group = getGroups(id);
        if(group != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    groupDao.deleteGroup(group.getValue());
                    return null;
                }
            }.execute();
        }
    }

}
