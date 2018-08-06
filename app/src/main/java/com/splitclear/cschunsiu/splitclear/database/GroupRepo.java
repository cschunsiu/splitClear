package com.splitclear.cschunsiu.splitclear.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.splitclear.cschunsiu.splitclear.database.dao.GroupDao;
import com.splitclear.cschunsiu.splitclear.model.Groups;

public class GroupRepo {
    private final GroupDao groupDao;

    public GroupRepo (Context context){
        DatabaseConfig db = DatabaseConfig.getDatabase(context);
        groupDao = db.groupDao();
    }

    public GroupAllMembers getGroups(){
        return groupDao.getAllGroups();
    }

    public LiveData<Groups> getGroups(int id){
        return groupDao.getGroup(id);
    }

    public void insertGroup(final Groups group){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                groupDao.insertGroup(group);
                return null;
            }
        }.execute();
    }

    public void updateGroup(final Groups group){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                groupDao.updateGroup(group);
                return null;
            }
        }.execute();
    }

    public void deleteGroup(final int id) {
        final LiveData<Groups> group = getGroups(id);
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
