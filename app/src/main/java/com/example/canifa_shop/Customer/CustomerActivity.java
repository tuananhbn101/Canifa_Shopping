package com.example.canifa_shop.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.canifa_shop.Customer.Object.Customer;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;
import com.example.canifa_shop.databinding.ActivityCustomerBinding;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {
    ActivityCustomerBinding binding;
    SQLHelper sqlHelper;
    List<Customer> customerList;
    ImageView btnAdd;
    String control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_customer);
        initialization();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this,CustomerDetailActivity.class);
                intent.putExtra("control","create");
                startActivity(intent);
            }
        });

    }
    public void getInten(){
        Intent intent = getIntent();
        control = intent.getStringExtra("control");
        if(control!=null){
            if(control.equals("getCustomer")){

            }
        }
    }
    public void initialization(){
        btnAdd = findViewById(R.id.btnAdd);
        sqlHelper = new SQLHelper(getApplicationContext());
        customerList = new ArrayList<>();
        customerList = sqlHelper.getAllCustomer();
    }
}