package com.splitclear.cschunsiu.splitclear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.model.Group;
import com.splitclear.cschunsiu.splitclear.model.Member;

public class AddBillActivity extends FragmentActivity {
    private EditText billName;
    private EditText billAmount;
    private Group selectedGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bill_view);
        Intent intent = getIntent();
        selectedGroup = intent.getParcelableExtra("Group with Members");

        billName = findViewById(R.id.add_bill_addBillName);
        billAmount = findViewById(R.id.add_bill_addBillAmount);
    }

    public void splitEven(View view){
        Float amount = Float.parseFloat(billAmount.getText().toString());
        System.out.println(amount);
    }

    public void splitPercentage(){

    }

    public void splitCustom(){

    }

    public void captureBill(){}

    public void resetBill(){}
}
