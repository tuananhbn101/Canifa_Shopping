package com.example.canifa_shop.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.canifa_shop.R;
import com.example.canifa_shop.databinding.ActivityCustomerBinding;

public class CustomerActivity extends AppCompatActivity {
    ActivityCustomerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_customer);
    }
}