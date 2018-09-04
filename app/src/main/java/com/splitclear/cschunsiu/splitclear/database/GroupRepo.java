package com.splitclear.cschunsiu.splitclear.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.splitclear.cschunsiu.splitclear.database.dao.GroupDao;
import com.splitclear.cschunsiu.splitclear.database.dao.MemberDao;
import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;
import com.splitclear.cschunsiu.splitclear.util.DatabaseAsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class GroupRepo {
    private final GroupDao groupDao;
    private final MemberDao memberDao;

    public GroupRepo (Context context){
        DatabaseConfig db = DatabaseConfig.getDatabase(context);
        groupDao = db.groupDao();
        memberDao = db.memberDao();
    }

//    public GroupAllMembers getGroups(){
//        return groupDao.getAllGroups();
//    }

    public List<Group> getNonLiveGroup() {
        final List<Group> resultGroup = null;
        new AsyncTask<Void, Void, List<Group>>() {
            @Override
            protected List<Group> doInBackground(Void... voids) {
                List<Group> m = null;
                try {
                    m = groupDao.getGroup();
                } catch (Exception e) {
                    System.out.println(e);
                }
                return m;
            }
            @Override
            protected void onPostExecute(List<Group> group){
            }
        }.execute();
        return null;
    }

    public LiveData<List<Group>> getGroup(){return groupDao.getGroupList();}

    public LiveData<Group> getGroups(int id){
        return groupDao.getGroup(id);
    }

    public Long insertGroup(final Group group){
        Long rowID = null;
        try {
            rowID = new AsyncTask<Void, Void, Long>() {
                Long result;
                @Override
                protected Long doInBackground(Void... voids) {
                    Long rowID = groupDao.insertGroup(group);
                    return rowID;
                }
                @Override
                protected void onPostExecute(Long rowID){

                }

            }.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return rowID;

//        new DatabaseAsyncTask(new DatabaseAsyncTask.AsyncResponse() {
//            @Override
//            public void processFinish(Object output) {
//            }
//        }){
//            @Override
//            public void processFinish(Object output) {
//
//            }
//
//            @Override
//            public Long doInBackground(Void... voids) {
//                Long rowID = groupDao.insertGroup(group);
//                return rowID;
//            }
//        }.execute();
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

//    private <T> T returnValue(T value){
//        return value;
//    }

    public void insertMember(final List<Member> members){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                for(Member member : members) {
                    memberDao.insertMemeber(member);
                }
                return null;
            }
        }.execute();
    }
}
