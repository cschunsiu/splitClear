package com.splitclear.cschunsiu.splitclear.adapter;


import android.app.AlertDialog;
import android.app.Application;
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
import com.splitclear.cschunsiu.splitclear.model.Member;

import org.w3c.dom.Text;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MemberRecycleAdapter extends RecyclerView.Adapter<MemberRecycleAdapter.NumberViewHolder> {

    private List<String> memberList;
    private Context context;


    public class NumberViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView showingTextView, nameTextview;
        ImageView icon;

        public NumberViewHolder(View itemView){
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
            //View mView = layoutInflater.inflate(R.layout.group_children_popup,null);
            builder.setView(R.layout.group_children_popup);
            builder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setNegativeButton("CONFIRM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // DO SOMETHING HERE

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public MemberRecycleAdapter(List<String> memberList, Context context){
        this.memberList = memberList;
        this.context = context;
    }

    @NonNull
    @Override
    public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_member_layout, parent, false);

        return new NumberViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NumberViewHolder holder, final int position) {
        String member = memberList.get(position);
        holder.showingTextView.setText(member);
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }
}
