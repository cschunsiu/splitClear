package com.splitclear.cschunsiu.splitclear.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.activity.GroupViewActivity;
import com.splitclear.cschunsiu.splitclear.model.Group;

import java.util.List;

import static android.content.ContentValues.TAG;

public class GroupRecycleAdapter extends RecyclerView.Adapter<GroupRecycleAdapter.GroupViewHolder>{
    private List<Group> groupList;
    private Context context;
    private GroupViewRecyclerItemClick listener;

    public GroupRecycleAdapter(List<Group> groupList, Context context){
        this.groupList = groupList;
        this.context = context;
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder{
        TextView showingTextView, nameTextview;
        ImageView icon;
        ConstraintLayout viewForeground;
        RelativeLayout groupEditButton, groupDeleteButton;

        public GroupViewHolder(View itemView){
            super(itemView);

            showingTextView = itemView.findViewById(R.id.group_children_edittext);
            nameTextview = itemView.findViewById(R.id.group_children_postMemberName);
            icon = itemView.findViewById(R.id.group_children_image);
            viewForeground = itemView.findViewById(R.id.view_foreground);
            groupEditButton = itemView.findViewById(R.id.group_edit_button);
            groupDeleteButton = itemView.findViewById(R.id.group_delete_button);
        }
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_item_list_layout,parent,false);

        return new GroupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        String member = groupList.get(position).name;
        holder.showingTextView.setVisibility(View.GONE);
        holder.icon.setVisibility(View.GONE);
        holder.nameTextview.setText(member);
        holder.viewForeground.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, GroupViewActivity.class).putExtra("Group", groupList.get(position));
                context.startActivity(i);
            }
        });
        holder.groupDeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println(position + " deletr");
            }
        });
        holder.groupEditButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                System.out.println(position + " Edit");
            }
        });

    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public void setGroup(List<Group> group) {
        groupList = group;
        notifyDataSetChanged();
    }

    public interface GroupViewRecyclerItemClick{
        void onItemClick(int position, String action);
    }

    public void setListener(GroupViewRecyclerItemClick listener) {
        this.listener = listener;
    }


}