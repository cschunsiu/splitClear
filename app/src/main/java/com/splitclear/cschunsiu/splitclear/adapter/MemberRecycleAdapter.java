package com.splitclear.cschunsiu.splitclear.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

public class MemberRecycleAdapter extends RecyclerView.Adapter<MemberRecycleAdapter.NumberViewHolder> {

    private List<String> memberList;

    public class NumberViewHolder extends RecyclerView.ViewHolder{
        TextView showingTextView;

        public NumberViewHolder(View itemView){
            super(itemView);

            showingTextView = (TextView)itemView.findViewById(R.id.group_children_edittext);
        }
    }

    public MemberRecycleAdapter(List<String> memberList){
        this.memberList = memberList;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_member_layout, parent, false);

        return new NumberViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {
        String member = memberList.get(position);
        holder.showingTextView.setText("HI");
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }
}
