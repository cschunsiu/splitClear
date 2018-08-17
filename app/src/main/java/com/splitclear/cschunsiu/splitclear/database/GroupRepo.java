package com.splitclear.cschunsiu.splitclear.database;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;

import com.splitclear.cschunsiu.splitclear.database.dao.GroupDao;
import com.splitclear.cschunsiu.splitclear.model.Group;

import java.util.List;

public class GroupRepo {
    private final GroupDao groupDao;

    public GroupRepo (Context context){
        DatabaseConfig db = DatabaseConfig.getDatabase(context);
        groupDao = db.groupDao();
    }

//    public GroupAllMembers getGroups(){
//        return groupDao.getAllGroups();
//    }

    public List<Group> getNonLiveGroup() {
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
                returnValue(group);
            }
        }.execute();
        return null;
    }

    public LiveData<List<Group>> getGroup(){return groupDao.getGroupList();}

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

    private List<Group> returnValue(List<Group> groups){
        return groups;
    }
}
