package com.splitclear.cschunsiu.splitclear.database.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.splitclear.cschunsiu.splitclear.database.GroupRepo;
import com.splitclear.cschunsiu.splitclear.model.Group;

public class MainViewModel extends AndroidViewModel {

    private LiveData<Group> resultSample;

    public MainViewModel(@NonNull Application application) {
        super(application);
        GroupRepo repository = new GroupRepo(this.getApplication());
        //TODO change id to getGroup
        resultSample = repository.getGroups(1);
    }

    public LiveData<Group> getGroups(){
        return resultSample;
    }
}
