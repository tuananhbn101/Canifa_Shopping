package com.example.canifa_shop.Category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.canifa_shop.Category.Adapter.CategoryAdapter;
import com.example.canifa_shop.Category.Object.Category;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;
import com.example.canifa_shop.databinding.ActivityCategoryBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    ActivityCategoryBinding binding;
    ImageView btnBack,btnAdd;
    TextView tvTitile, tvDelete;
    List<Category> categoryList;
    SQLHelper sqlHelper;
    CategoryAdapter categoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_category);
        findByViewID();
        btnAdd.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),CategoryDetail.class));
        });
    }
    public void initialization(){
        categoryList = new ArrayList<>();
        sqlHelper = new SQLHelper(getApplicationContext());
        sqlHelper.insertCategory(new Category(0,"Quan",1,"Dep"));
        sqlHelper.insertCategory(new Category(0,"Ao",1,"Dep"));
        categoryList = sqlHelper.getAllCategory();
    }
    public void findByViewID() {
        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);
        tvTitile = findViewById(R.id.tvTitle);
        tvDelete = findViewById(R.id.tvDelete);
        tvDelete.setVisibility(View.INVISIBLE);
        btnAdd = findViewById(R.id.btnAdd);
        tvTitile.setText("Danh mục");
    }
    public void setAdapter(){
        categoryAdapter = new CategoryAdapter(categoryList);
        binding.lvCategory.setAdapter(categoryAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initialization();
        setAdapter();
    }
}