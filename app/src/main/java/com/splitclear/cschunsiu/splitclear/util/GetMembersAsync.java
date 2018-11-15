package com.splitclear.cschunsiu.splitclear.util;

import android.os.AsyncTask;

import com.splitclear.cschunsiu.splitclear.database.dao.MemberDao;
import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

public class GetMembersAsync extends AsyncTask<Void, Void, List<Member>>{
    private OnTaskComplete onComplete =null;
    private Group group;
    private MemberDao memberDao;

    public GetMembersAsync(OnTaskComplete onComplete, Group group, MemberDao memberDao){
        this.onComplete = onComplete;
        this.group = group;
        this.memberDao = memberDao;
    }

    @Override
    protected List<Member> doInBackground(Void... voids) {
        List<Member> result = memberDao.getMembers(group.id);
        return result;
    }

    @Override
    protected void onPostExecute(List<Member> result){
        onComplete.onOutput(result);
    }
}
