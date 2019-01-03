package com.splitclear.cschunsiu.splitclear.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.model.Bill;
import java.util.List;

public class ShowBalanceAdapter  extends RecyclerView.Adapter<ShowBalanceAdapter.ShowBalanceViewHolder>  {
    private List<Bill> billList;

    public ShowBalanceAdapter(List<Bill> billList){
        this.billList = billList;
    }

    @NonNull
    @Override
    public ShowBalanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_item_list_layout, parent, false);

        return new ShowBalanceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowBalanceViewHolder holder, int position) {
            holder.nameTextview.setText(billList.get(position).amount.toString());
            holder.icon.setVisibility(View.GONE);
            holder.showingTextView.setText(billList.get(position).memberName);
    }

    @Override
    public int getItemCount() {
        return billList.size();
    }

    public class ShowBalanceViewHolder extends RecyclerView.ViewHolder{
        TextView showingTextView, nameTextview;
        ImageView icon;
        SwipeRevealLayout sw;

        public ShowBalanceViewHolder(View itemView){
            super(itemView);

            showingTextView = itemView.findViewById(R.id.group_children_edittext);
            nameTextview = itemView.findViewById(R.id.group_children_postMemberName);
            icon = itemView.findViewById(R.id.group_children_image);
            sw = itemView.findViewById(R.id.sw);
            sw.setLockDrag(true);
        }
    }
}
