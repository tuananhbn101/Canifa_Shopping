package com.example.canifa_shop.More;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.canifa_shop.Category.CategoryActivity;
import com.example.canifa_shop.Customer.CustomerActivity;
import com.example.canifa_shop.Manager.AcountManagerActivity;
import com.example.canifa_shop.Manager.EmployeeManagerActivity;
import com.example.canifa_shop.Product.ProductActivity;
import com.example.canifa_shop.R;
import com.example.canifa_shop.databinding.FragmentMoreBinding;

public class MoreFragment extends Fragment {
    FragmentMoreBinding binding;
    public static MoreFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MoreFragment fragment = new MoreFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_more,container,false);
        binding.btnAccountManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AcountManagerActivity.class));
            }
        });
        binding.btnCategoryManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CategoryActivity.class));
            }
        });
        binding.btnCustomerManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CustomerActivity.class));
            }
        });
        binding.btnEmployeeManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), EmployeeManagerActivity.class));
            }
        });
        binding.btnProdutManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProductActivity.class));
            }
        });
        return binding.getRoot();
    }
}
