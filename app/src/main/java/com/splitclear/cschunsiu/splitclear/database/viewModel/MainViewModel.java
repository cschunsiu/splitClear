package com.splitclear.cschunsiu.splitclear.database.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.splitclear.cschunsiu.splitclear.database.DataRepo;
import com.splitclear.cschunsiu.splitclear.model.Bill;
import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private DataRepo dataRepo;
    private LiveData<List<Group>> groupLists;
    private LiveData<List<Bill>> billList;

    public MainViewModel(Application application) {
        super(application);
        dataRepo = new DataRepo(this.getApplication());
        groupLists = dataRepo.getGroup();
        billList = dataRepo.getBill();
    }

    public LiveData<List<Group>> getGroupList(){
        return groupLists;
    }

    public LiveData<List<Bill>> getBillList(){
        return billList;
    }

    public void insertGroupAndMember (Group group){
        dataRepo.insertGroupAndMember(group);
    }

    public void insertBills (Group group){
        dataRepo.insertGroupAndMember(group);
    }

    public List<Member> getMemberList(Group group){
        return dataRepo.getMembers(group);
    }
}
