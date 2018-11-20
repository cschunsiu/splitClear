package com.splitclear.cschunsiu.splitclear.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.splitclear.cschunsiu.splitclear.database.dao.BillDao;
import com.splitclear.cschunsiu.splitclear.database.dao.GroupDao;
import com.splitclear.cschunsiu.splitclear.database.dao.MemberDao;
import com.splitclear.cschunsiu.splitclear.model.Bill;
import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

public class DataRepo{
    private final GroupDao groupDao;
    private final MemberDao memberDao;
    private final BillDao billDao;

    public DataRepo(Context context){
        DatabaseConfig db = DatabaseConfig.getDatabase(context);
        groupDao = db.groupDao();
        memberDao = db.memberDao();
        billDao = db.billDao();
    }

    public void insertGroupAndMember(final Group group) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                long groupID = groupDao.insertGroup(group);
                for(Member member : group.getMemberList()){
                    member.setGroupsId(groupID);
                    memberDao.insertMember(member);
                }
                return null;
            }
        }.execute();
    }

    public void insertBills(final Group group) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }
        }.execute();
    }

    public LiveData<List<Group>> getGroup(){return groupDao.getGroupList();}

    public List<Bill> getBill(Group group){return billDao.getBillList(group.id);}

    public List<Member> getMembers(final Group group){
        try {
            return new AsyncTask<Void, Void, List<Member>>() {
                @Override
                protected List<Member> doInBackground(Void... voids) {
                    return memberDao.getMembers(group.id);
                }
            }.execute().get();
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
}
