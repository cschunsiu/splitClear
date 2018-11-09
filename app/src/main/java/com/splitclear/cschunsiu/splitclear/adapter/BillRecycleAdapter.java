package com.splitclear.cschunsiu.splitclear.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.activity.AddGroupActivity;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

import static android.content.ContentValues.TAG;

public class BillRecycleAdapter extends RecyclerView.Adapter<BillRecycleAdapter.BillViewHolder> {

    private List<Member> memberList;
    private Context context;

    public class BillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView showingTextView, nameTextview;
        ImageView icon;

        public BillViewHolder(View itemView){
            super(itemView);

            showingTextView = (TextView)itemView.findViewById(R.id.group_children_edittext);
            nameTextview = (TextView)itemView.findViewById(R.id.postMemberName);
            icon = (ImageView) itemView.findViewById(R.id.group_children_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClicked " + getAdapterPosition());
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            final View view = inflater.inflate(R.layout.member_input_popup,null);
            builder.setView(view);
            builder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("CONFIRM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText input = view.findViewById(R.id.userInputMemberName);
                    addMember(input.getText().toString());
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public BillRecycleAdapter(List<Member> memberList, Context context){
        this.memberList = memberList;
        this.context = context;
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.member_input_name_layout, parent, false);

        return new BillViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, final int position) {
        String member = memberList.get(position).name;

        if(!(memberList.get(position).name).equals("Default")){
            holder.showingTextView.setVisibility(View.GONE);
            holder.icon.setVisibility(View.GONE);
            holder.nameTextview.setText(member);
        }

    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public void addMember(String name) {
        memberList.remove(memberList.size()-1);
        memberList.add(new Member(name));
        if(memberList.size() < 10){
            memberList.add(new Member());
        }
        AddGroupActivity.memberList = memberList;
        notifyDataSetChanged();
    }
}