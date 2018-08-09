package com.splitclear.cschunsiu.splitclear.database;

import android.content.Context;

import com.splitclear.cschunsiu.splitclear.database.dao.MemberDao;

public class MemberRepo {
//    private final MemberDao memberDao;
//
//    public MemberRepo (Context context){
//        DatabaseConfig db = DatabaseConfig.getDatabase(context);
//        memberDao = db.memberDao();
//    }
//
//    public GroupAllMembers getGroups(){
//        return memberDao.getAllGroups();
//    }
//
//    public LiveData<Group> getGroups(int id){
//        return memberDao.getGroup(id);
//    }
//
//    public void insertGroup(final Group group){
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                memberDao.insertGroup(group);
//                return null;
//            }
//        }.execute();
//    }
//
//    public void updateGroup(final Group group){
//        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                memberDao.updateGroup(group);
//                return null;
//            }
//        }.execute();
//    }
//
//    public void deleteGroup(final int id) {
//        final LiveData<Group> group = getGroups(id);
//        if(group != null) {
//            new AsyncTask<Void, Void, Void>() {
//                @Override
//                protected Void doInBackground(Void... voids) {
//                    memberDao.deleteGroup(group.getValue());
//                    return null;
//                }
//            }.execute();
//        }
//    }

}
