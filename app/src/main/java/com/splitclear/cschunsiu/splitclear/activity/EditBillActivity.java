package com.splitclear.cschunsiu.splitclear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;

import com.splitclear.cschunsiu.splitclear.R;
import com.splitclear.cschunsiu.splitclear.model.Bill;

public class EditBillActivity extends FragmentActivity {
    private Bill selectedBill;
    private EditText billName;
    private EditText billAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_bill_view);
        Intent intent = getIntent();
        selectedBill = intent.getParcelableExtra("Bill");
        billName = findViewById(R.id.add_bill_addBillName);
        billAmount = findViewById(R.id.add_bill_addBillAmount);

        fillInData();
    }

    private void fillInData(){
        billName.setText(selectedBill.billName);
        billAmount.setText(selectedBill.amount.toString());
    }

    public void resetBill(View view){
        System.out.println("hi");
    }
}
