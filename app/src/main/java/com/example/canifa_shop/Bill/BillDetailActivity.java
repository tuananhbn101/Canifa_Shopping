package com.example.canifa_shop.Bill;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.canifa_shop.Bill.Adapter.BillAdapter;
import com.example.canifa_shop.Bill.Object.Bill;
import com.example.canifa_shop.Customer.CustomerActivity;
import com.example.canifa_shop.Product.Object.Product;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;
import com.example.canifa_shop.databinding.ActivityBillDetailBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BillDetailActivity extends AppCompatActivity {
    ActivityBillDetailBinding binding;
    SQLHelper sqlHelper;
    BillAdapter adapter;
    List<Product> productOrderList;
    List<Product> productList;
    int IDCustomer;
    final static int REQUEST_CODE = 1;
    ImageView btnBack, btnAdd;
    TextView tvTitile, tvDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bill_detail);
        findByViewID();
        initialization();
        setAdapter();
        binding.btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewBill();
                updateProduct();
                sqlHelper.deleteOrderProduct();
                finish();
            }
        });
        binding.tvCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillDetailActivity.this, CustomerActivity.class);
                intent.putExtra("control", "getCustomer");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlHelper.deleteOrderProduct();
                finish();
            }
        });
    }

    public void findByViewID() {
        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);
        tvTitile = findViewById(R.id.tvTitle);
        tvDelete = findViewById(R.id.tvDelete);
        btnAdd.setVisibility(View.INVISIBLE);
        tvTitile.setText("Hóa đơn chi tiết");
    }

    public void initialization() {
        productList = new ArrayList<>();
        sqlHelper = new SQLHelper(getApplicationContext());
        productOrderList = new ArrayList<>();
        productList = sqlHelper.getAllPrduct();
        productOrderList = sqlHelper.getAllOrderPrduct();
    }

    public void addNewBill() {
        String IDProduct = "";
        String amount = "";
        long total = 0;
        for (Product product : productOrderList) {
            IDProduct += product.getID() + "|";
            amount += product.getAmount() + "|";
            total += (product.getPrice() * product.getAmount());
        }
        Bill bill = new Bill(0, new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()), IDProduct, amount, "0", total, IDCustomer, 0);
        sqlHelper.insertBill(bill);
    }

    public void setAdapter() {
        adapter = new BillAdapter(productOrderList, getApplicationContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1, RecyclerView.VERTICAL, false);
        binding.rvOrderProduct.setLayoutManager(layoutManager);
        binding.rvOrderProduct.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REQUEST_CODE) {
            IDCustomer = data.getIntExtra("ID", 0);
        }
    }

    public void updateProduct() {
        for(Product product : productList){
            updateAmountProduct(product);
        }
    }

    public void updateAmountProduct(Product product) {
        for (Product productItem : productOrderList) {
            if (productItem.getBardCode().equals(product.getBardCode())) {
                int hienCo = product.getAmount();
                int order = productItem.getAmount();
                product.setAmount(hienCo-order);
                sqlHelper.updateProduct(product);
            }
        }
    }
}