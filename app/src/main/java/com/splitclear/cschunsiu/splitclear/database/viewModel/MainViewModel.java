package com.splitclear.cschunsiu.splitclear.database.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.splitclear.cschunsiu.splitclear.database.GroupAllMembers;
import com.splitclear.cschunsiu.splitclear.database.GroupRepo;
import com.splitclear.cschunsiu.splitclear.model.Group;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private GroupRepo repository;
    private LiveData<List<Group>> resultSample;

    public MainViewModel(Application application) {
        super(application);
        repository = new GroupRepo(this.getApplication());
        resultSample = repository.getGroup();
    }

    public LiveData<List<Group>> getGroupList(){
        return resultSample;
    }

    public void insertGroups(Group group){
        repository.insertGroup(group);
    }

    public List<Group> getNonLiveGroup(){
        return repository.getNonLiveGroup();
    }
}
