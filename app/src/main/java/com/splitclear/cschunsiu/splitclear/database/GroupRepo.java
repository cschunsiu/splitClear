package com.splitclear.cschunsiu.splitclear.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.splitclear.cschunsiu.splitclear.database.dao.GroupDao;
import com.splitclear.cschunsiu.splitclear.model.Group;

public class GroupRepo {
    private final GroupDao groupDao;

    public GroupRepo (Context context){
        DatabaseConfig db = DatabaseConfig.getDatabase(context);
        groupDao = db.groupDao();
    }

    public GroupAllMembers getGroups(){
        return groupDao.getAllGroups();
    }

    public LiveData<Group> getGroups(int id){
        return groupDao.getGroup(id);
    }

    public void insertGroup(final Group group){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                groupDao.insertGroup(group);
                return null;
            }
        }.execute();
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
