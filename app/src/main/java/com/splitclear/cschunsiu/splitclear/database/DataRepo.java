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
import com.splitclear.cschunsiu.splitclear.util.OnTaskComplete;

import java.util.ArrayList;
import java.util.List;

public class DataRepo implements OnTaskComplete{
    private final GroupDao groupDao;
    private final MemberDao memberDao;
    private final BillDao billDao;

    private List<Group> jii;

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

    public LiveData<List<Bill>> getBill(){return billDao.getBillList();}

    public List<Member> getMembers(final Group group){
        LoadData d = new LoadData();
        d.onComplete = this;
        d.execute(group);

        return null;
    }

    @Override
    public void onOutput(List<Group> result) {
        jii = result;
        System.out.println(jii);
    }

    public class LoadData extends AsyncTask<Group, Void, List<Group>> {
        private OnTaskComplete onComplete =null;

        @Override
        protected List<Group> doInBackground(Group... groups) {
            return null;
        }

        @Override
        protected void onPostExecute(List<Group> result){
            onComplete.onOutput(result);

        }
    }
}
