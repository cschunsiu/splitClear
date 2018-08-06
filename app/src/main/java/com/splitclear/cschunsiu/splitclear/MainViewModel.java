package com.splitclear.cschunsiu.splitclear;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.splitclear.cschunsiu.splitclear.database.GroupRepo;
import com.splitclear.cschunsiu.splitclear.model.Groups;

public class MainViewModel extends AndroidViewModel {

    private LiveData<Groups> resultSample;

    public MainViewModel(@NonNull Application application) {
        super(application);
        GroupRepo repository = new GroupRepo(this.getApplication());
        resultSample = repository.getGroups(1);
    }

    public LiveData<Groups> getGroups(){
        return resultSample;
    }
}
