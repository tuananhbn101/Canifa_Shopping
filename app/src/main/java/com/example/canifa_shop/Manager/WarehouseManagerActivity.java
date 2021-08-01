package com.example.canifa_shop.Manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.canifa_shop.R;
import com.example.canifa_shop.databinding.ActivityWarehouseManagerBinding;

public class WarehouseManagerActivity extends AppCompatActivity {
    ActivityWarehouseManagerBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_warehouse_manager);
    }
}