package com.example.canifa_shop.Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.canifa_shop.R;
import com.example.canifa_shop.databinding.ActivityProductBinding;

public class ProductActivity extends AppCompatActivity {
    ActivityProductBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_product);
    }
}