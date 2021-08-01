package com.example.canifa_shop.Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.example.canifa_shop.Product.Object.Product;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;

import com.example.canifa_shop.Selling.Adapter.SellingAdapter;
import com.example.canifa_shop.Selling.Adapter.SellingAdapterGrid;
import com.example.canifa_shop.databinding.ActivityProductDetailBinding;

import java.util.ArrayList;
import java.util.List;


public class ProductDetailActivity extends AppCompatActivity {
    ActivityProductDetailBinding binding;
    SQLiteOpenHelper sqLiteOpenHelper;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        initialization();
        getIntents();

    }

    public void getIntents() {
        Intent intent = getIntent();
        int ID = intent.getIntExtra("ID", 0);

        for (Product product : productList
        ) {
            if(product.getID()==ID){
                binding.etNameProduct.setText(product.getNameProduct());
                binding.etAmountProduct.setText(product.getAmount()+"");
                binding.etBardCodeProduct.setText(product.getBardCode());
                binding.etCodeProduct.setText(product.getID()+"");
                binding.etNote.setText(product.getDescribe());
                binding.etPriceImport.setText(product.getImportprice()+"");
                binding.etPriceSell.setText(product.getPrice()+"");
            }
        }
    }

    public void initialization() {
        sqLiteOpenHelper = new SQLHelper(this);
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
    }
}