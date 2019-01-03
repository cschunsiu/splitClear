package com.splitclear.cschunsiu.splitclear.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.splitclear.cschunsiu.splitclear.R;


public class EditGroupViewHolder extends RecyclerView.ViewHolder{
    TextView showingTextView, nameTextview;
    ImageView icon;
    SwipeRevealLayout sw;
    ConstraintLayout viewForeground;

    public EditGroupViewHolder(View itemView){
        super(itemView);

        showingTextView = itemView.findViewById(R.id.group_children_edittext);
        nameTextview = itemView.findViewById(R.id.group_children_postMemberName);
        icon = itemView.findViewById(R.id.group_children_image);
        viewForeground = itemView.findViewById(R.id.view_foreground);
        sw = itemView.findViewById(R.id.sw);
        sw.setLockDrag(true);
    }

}
