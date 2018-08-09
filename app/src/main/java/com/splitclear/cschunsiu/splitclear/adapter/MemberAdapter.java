package com.splitclear.cschunsiu.splitclear.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.splitclear.cschunsiu.splitclear.R;

import java.util.ArrayList;

public class MemberAdapter extends BaseAdapter{
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<String> groups;

    public MemberAdapter(Context context,ArrayList groups){
        super();
        this.groups = groups;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.add_member_layout, null);

        ImageView ig = convertView.findViewById(R.id.group_children_image);
        TextView tv = convertView.findViewById(R.id.group_children_edittext);
        TextView tv2 = convertView.findViewById(R.id.postMemberName);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View mView = layoutInflater.inflate(R.layout.group_children_popup,null);
                final EditText memberName = mView.findViewById(R.id.userInputMemberName);
                builder.setView(mView);
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        if(groups.get(position).equals("default")) {
                            //AddGroupActivity.addGroupMemberButton(memberName.getText().toString());
                        }else{
                            groups.set(position,memberName.getText().toString());
                        }
                        MemberAdapter.super.notifyDataSetChanged();
                    }
                })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }});
                builder.show();
            }
        });

        if(groups.get(position) != "default") {
            ig.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);
            tv2.setText(groups.get(position));
        }

        return convertView;
    }
}
