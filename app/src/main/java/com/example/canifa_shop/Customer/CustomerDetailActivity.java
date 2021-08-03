package com.example.canifa_shop.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.canifa_shop.Customer.Object.Customer;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;
import com.example.canifa_shop.databinding.ActivityCustomerDetailBinding;

import java.util.ArrayList;

public class CustomerDetailActivity extends AppCompatActivity {
    ActivityCustomerDetailBinding binding;
    SQLHelper sqlHelper;
    Intent intent;
    String control;
    Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_customer_detail);
        getItent();
        initialization();
        binding.btnControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(control.equals("create")){
                    addCustomer();
                }
                else {
                    updateCustomer(customer);
                }
            }
        });

    }
    public void addCustomer(){
        String name = binding.etFulName.getText().toString().trim();
        String phone = binding.etPhoneNumber.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String address = binding.etAddress.getText().toString().trim();
        String points= binding.etPoint.getText().toString().trim();
        String type = binding.etType.getText().toString().trim();
        String voucher = binding.etVoucher.getText().toString().trim();
        Customer customer = new Customer(0,name,phone,email,address,points,type,voucher);
        sqlHelper.insertCustomer(customer);
    }
    public void updateCustomer(Customer customer){
            String name = binding.etFulName.getText().toString().trim();
            String phone = binding.etPhoneNumber.getText().toString().trim();
            String email = binding.etEmail.getText().toString().trim();
            String address = binding.etAddress.getText().toString().trim();
            String points= binding.etPoint.getText().toString().trim();
            String type = binding.etType.getText().toString().trim();
            String voucher = binding.etVoucher.getText().toString().trim();
            customer.setCustomerName(name);
            customer.setCustomerPhone(phone);
            customer.setCustomerEmail(email);
            customer.setCustomerAddress(address);
            customer.setCustomerPoints(points);
            customer.setCustomerType(type);
            customer.setCustomerVoucher(voucher);
            sqlHelper.updateCustomer(customer);
    }
    public void getItent(){
        intent = getIntent();
        control = intent.getStringExtra("control");
        if(control!=null || control.isEmpty()){
            return;
        }else {
            if(control.equals("create")){
                binding.btnControl.setText("Thêm mới");
            }
            else {
                binding.btnControl.setText("Cập nhật");
                customer = (Customer) intent.getSerializableExtra("customer");
            }
        }
    }
    public void initialization(){
        sqlHelper = new SQLHelper(getApplicationContext());
    }
}