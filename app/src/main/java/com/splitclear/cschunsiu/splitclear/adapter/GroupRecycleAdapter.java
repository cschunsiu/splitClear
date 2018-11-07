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
import android.widget.ImageView;
import android.widget.TextView;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.model.Group;

import java.util.List;

import static android.content.ContentValues.TAG;

public class GroupRecycleAdapter extends RecyclerView.Adapter<GroupRecycleAdapter.GroupViewHolder>{
    private List<Group> groupList;
    private Context context;

    public class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView showingTextView, nameTextview;
        ImageView icon;

        public GroupViewHolder(View itemView){
            super(itemView);

            showingTextView = itemView.findViewById(R.id.group_children_edittext);
            nameTextview = itemView.findViewById(R.id.postMemberName);
            icon = itemView.findViewById(R.id.group_children_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClicked " + getAdapterPosition());
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(R.layout.member_input_popup);
            builder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("CONFIRM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public GroupRecycleAdapter(List<Group> groupList, Context context){
        this.groupList = groupList;
        this.context = context;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_input_name_layout,parent,false);

        return new GroupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        String member = groupList.get(position).name;
        holder.showingTextView.setVisibility(View.GONE);
        holder.icon.setVisibility(View.GONE);
        holder.nameTextview.setText(member);
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public void setGroup(List<Group> group) {
        groupList = group;
        notifyDataSetChanged();
    }
}
