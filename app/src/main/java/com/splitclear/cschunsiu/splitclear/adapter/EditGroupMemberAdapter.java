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

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;

import java.util.List;

import static android.content.ContentValues.TAG;

public class EditGroupMemberAdapter extends RecyclerView.Adapter<EditGroupViewHolder> {
    private List<Member> memberList;
    private Context context;

    public EditGroupMemberAdapter(List<Member> memberList, Context context){
        this.memberList = memberList;
        this.context = context;
    }

    @NonNull
    @Override
    public EditGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_item_list_layout, parent, false);

        return new EditGroupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EditGroupViewHolder holder, final int position) {
        String member = memberList.get(position).name;

        if(!(memberList.get(position).name).equals("Default")){
            holder.showingTextView.setVisibility(View.GONE);
            holder.icon.setVisibility(View.GONE);
            holder.nameTextview.setText(member);
        }
        holder.viewForeground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClicked " + position);
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
                        memberList.get(position).name = input.getText().toString();
                        notifyDataSetChanged();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }
}
