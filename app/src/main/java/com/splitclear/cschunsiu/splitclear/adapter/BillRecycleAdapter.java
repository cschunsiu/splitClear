package com.splitclear.cschunsiu.splitclear.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.model.Bill;

import java.util.List;

import static android.content.ContentValues.TAG;

public class BillRecycleAdapter extends RecyclerView.Adapter<BillRecycleAdapter.BillViewHolder> {

    private List<Bill> billList;
    private Context context;

    public BillRecycleAdapter(List<Bill> billList, Context context){
        this.billList = billList;
        this.context = context;
    }

    public class BillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView showingTextView, nameTextview;
        ImageView icon;

        public BillViewHolder(View itemView){
            super(itemView);

            showingTextView = (TextView)itemView.findViewById(R.id.group_children_edittext);
            nameTextview = (TextView)itemView.findViewById(R.id.group_children_postMemberName);
            icon = (ImageView) itemView.findViewById(R.id.group_children_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClicked " + getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_item_list_layout, parent, false);

        return new BillViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BillViewHolder holder, final int position) {
        String member = billList.get(position).name;

        if(!(billList.get(position).name).equals("Default")){
            holder.showingTextView.setVisibility(View.GONE);
            holder.icon.setVisibility(View.GONE);
            holder.nameTextview.setText(member);
        }
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

}