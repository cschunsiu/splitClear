package com.splitclear.cschunsiu.splitclear.database.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.splitclear.cschunsiu.splitclear.database.DataRepo;
import com.splitclear.cschunsiu.splitclear.database.GroupAllMembers;
import com.splitclear.cschunsiu.splitclear.model.Group;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private DataRepo dataRepo;
    private LiveData<List<Group>> GroupLists;
    ////
    private LiveData<List<GroupAllMembers>> testObject;

    public MainViewModel(Application application) {
        super(application);
        dataRepo = new DataRepo(this.getApplication());
        GroupLists = dataRepo.getGroup();
        testObject = dataRepo.testGetGroup();
    }

    public LiveData<List<Group>> getGroupList(){
        return GroupLists;
    }

    public LiveData<List<GroupAllMembers>> TestgetGroupList(){
        return testObject;
    }

    public void insertGroupAndMember (Group group){
        dataRepo.insertGroupAndMember(group);
    }
}
