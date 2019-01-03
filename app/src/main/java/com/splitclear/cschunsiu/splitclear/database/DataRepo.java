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

    public void insertBills(final List<Bill> billList) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for(Bill bill : billList){
                    billDao.insertBill(bill);
                }
                return null;
            }
        }.execute();
    }

    public LiveData<List<Bill>> getBillSummary(){return billDao.getBillListSummary();}

    public LiveData<List<Group>> getGroup(){return groupDao.getGroupList();}

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

    public List<Bill> getBillsByName(final Long groupId, String billName){
        try {
            return new AsyncTask<Void, Void, List<Bill>>() {
                @Override
                protected List<Bill> doInBackground(Void... voids) {
                    return billDao.getBillListByName(groupId, billName);
                }
            }.execute().get();
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<Bill> getBillsByMember(final Group group){
        try {
            return new AsyncTask<Void, Void, List<Bill>>() {
                @Override
                protected List<Bill> doInBackground(Void... voids) {
                    return billDao.getBillListByMember(group.id);
                }
            }.execute().get();
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void updateBills(final List<Bill> billList) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for(Bill bill : billList){
                    billDao.updateBill(bill);
                }
                return null;
            }
        }.execute();
    }

    public void deleteGroup(Group group){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                billDao.deleteBill(group.getId());
                memberDao.deleteMember(group.getId());
                groupDao.deleteGroup(group);
                return null;
            }
        }.execute();
    }

    public void updateGroupAndBill(Group group, List<Member> memberList){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                groupDao.updateGroup(group);
                for(Member member : memberList){
                    if(member.id == null){
                        member.setGroupsId(group.id);
                        memberDao.insertMember(member);
                    }else {
                        memberDao.updateMember(member);
                        billDao.updateMemberName(member.name, member.id, member.groupsId);
                    }
                }
                return null;
            }
        }.execute();
    }
}
