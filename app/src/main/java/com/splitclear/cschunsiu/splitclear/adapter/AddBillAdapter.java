package com.splitclear.cschunsiu.splitclear.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.model.Member;
import com.splitclear.cschunsiu.splitclear.util.AddBillType;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class AddBillAdapter extends RecyclerView.Adapter<AddBillAdapter.AddBillViewHolder> {
    private AddBillType billType;
    private List<Member> memberList;

    public AddBillAdapter(AddBillType billType, List<Member> memberList){
        this.billType = billType;
        this.memberList = memberList;
    }

    public class AddBillViewHolder extends RecyclerView.ViewHolder{
        TextView memberName, memberAmount, percentageText;
        EditText memberCustomAmount;
        SeekBar memberPercentBar;

        public AddBillViewHolder(View itemView){
            super(itemView);

            memberName = itemView.findViewById(R.id.add_bill_memberName);
            memberAmount = itemView.findViewById(R.id.add_bill_amount);
            percentageText = itemView.findViewById(R.id.add_bill_percentage);
            memberPercentBar = itemView.findViewById(R.id.add_bill_percent_bar);
            memberCustomAmount = itemView.findViewById(R.id.add_bill_custom_amount);

            memberPercentBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    percentageText.setText(progress+"%");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
    }

    @NonNull
    @Override
    public AddBillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.add_bill_display_layout, parent, false);

        return new AddBillViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AddBillViewHolder holder, final int position) {
        if(billType == AddBillType.CUSTOM){
            holder.memberAmount.setVisibility(GONE);
            holder.memberCustomAmount.setVisibility(VISIBLE);
        }else if(billType == AddBillType.PERCENT){
            holder.memberAmount.setVisibility(GONE);
            holder.memberPercentBar.setVisibility(VISIBLE);
            holder.percentageText.setVisibility(VISIBLE);
        }

        holder.memberName.setText(memberList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

}