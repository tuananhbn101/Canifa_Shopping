package com.example.canifa_shop.Product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.canifa_shop.Product.Adapter.ProductAdapter;
import com.example.canifa_shop.Product.Object.Product;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;
import com.example.canifa_shop.Selling.Adapter.SellingAdapter;
import com.example.canifa_shop.Selling.Adapter.SellingAdapterGrid;
import com.example.canifa_shop.databinding.ActivityProductBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    ActivityProductBinding binding;
    ImageView btnBack, btnAdd;
    TextView tvTitile,tvDelete;
    SQLHelper sqlHelper;
    ProductAdapter productAdapter;
    List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_product);
        findByViewID();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this,ProductDetailActivity.class);
                intent.putExtra("control","create");
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    public void findByViewID(){
        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);
        tvTitile = findViewById(R.id.tvTitle);
        tvDelete = findViewById(R.id.tvDelete);
        tvDelete.setVisibility(View.INVISIBLE);
        tvTitile.setText("Sản phẩm");
    }
    public void initialization(){
        sqlHelper = new SQLHelper(getApplicationContext());
        productList = new ArrayList<>();
        productList=sqlHelper.getAllPrduct();


    }
    public void setAdapter(){
            productAdapter=new ProductAdapter(productList,getApplicationContext());
            GridLayoutManager gridLayoutManager =new GridLayoutManager(getApplicationContext(),1, RecyclerView.VERTICAL,false);
            binding.rvProduct.setLayoutManager(gridLayoutManager);
            binding.rvProduct.setAdapter(productAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initialization();
        setAdapter();
    }
}