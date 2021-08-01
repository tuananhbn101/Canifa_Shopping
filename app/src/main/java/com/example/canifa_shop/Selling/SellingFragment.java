package com.example.canifa_shop.Selling;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.canifa_shop.Product.Object.Product;
import com.example.canifa_shop.Product.ProductDetailActivity;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;

import com.example.canifa_shop.Selling.Adapter.SellingAdapter;
import com.example.canifa_shop.Selling.Adapter.SellingAdapterGrid;
import com.example.canifa_shop.databinding.FragmentSellingBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SellingFragment extends Fragment {
    FragmentSellingBinding binding;
    SQLiteOpenHelper sqLiteOpenHelper;
    List<Product> productList;
    SellingAdapter sellingAdapter;
    SellingAdapterGrid sellingAdapterGrid;
    public static SellingFragment newInstance() {

        Bundle args = new Bundle();

        SellingFragment fragment = new SellingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_selling,container,false);
        initialization();
        setAdapter();
        binding.imvList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imvGrid.setVisibility(View.VISIBLE);
                binding.imvList.setVisibility(View.INVISIBLE);
                setAdapter();
            }
        });
        binding.imvGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.imvGrid.setVisibility(View.INVISIBLE);
                binding.imvList.setVisibility(View.VISIBLE);
                setAdapter();
            }
        });
        return binding.getRoot();
    }
    public void initialization(){
        sqLiteOpenHelper = new SQLHelper(getContext());
        productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());
        sellingAdapter=new SellingAdapter(productList,getContext());
        sellingAdapterGrid = new SellingAdapterGrid(productList,getContext());
    }
    public void setAdapter(){
        if(binding.imvGrid.getVisibility()==View.VISIBLE){
            GridLayoutManager gridLayoutManager =new GridLayoutManager(getContext(),3, RecyclerView.VERTICAL,false);
            binding.rvProduct.setLayoutManager(gridLayoutManager);
            binding.rvProduct.setAdapter(sellingAdapterGrid);
        }
        else {
            GridLayoutManager gridLayoutManager =new GridLayoutManager(getContext(),1, RecyclerView.VERTICAL,false);
            binding.rvProduct.setLayoutManager(gridLayoutManager);
            binding.rvProduct.setAdapter(sellingAdapter);
        }
    }
}
