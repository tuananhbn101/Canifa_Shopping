package com.example.canifa_shop.Customer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.canifa_shop.Customer.Object.Customer;
import com.example.canifa_shop.Product.Object.Product;
import com.example.canifa_shop.Product.ProductDetailActivity;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;
import com.example.canifa_shop.databinding.ActivityCustomerDetailBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomerDetailActivity extends AppCompatActivity {
    ActivityCustomerDetailBinding binding;
    SQLHelper sqlHelper;
    List<Customer> customerArrayList;
    private String control;
    private Customer customerChoose;
    int ID;
    ImageView btnBack, btnAdd;
    TextView tvTitile, tvDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_customer_detail);
        initialization();
        findByViewID();
        getIntents();
        binding.btnControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (control.equals("create")) {
                    addCustomer();
                } else {
                    updateCustomer(customerChoose);
                }
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog builder = new AlertDialog.Builder(CustomerDetailActivity.this)
                        .setTitle("Bạn có muốn xóa")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sqlHelper.deleteCustomer(ID);
                                finish();
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                builder.show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void addCustomer() {
        String name = binding.etFulName.getText().toString().trim();
        String phone = binding.etPhoneNumber.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String address = binding.etAddress.getText().toString().trim();
        String points = binding.etPoint.getText().toString().trim();
        String type = binding.etType.getText().toString().trim();
        String voucher = binding.etVoucher.getText().toString().trim();
        Customer customer = new Customer(0, name, phone, email, address, points, type, voucher);
        sqlHelper.insertCustomer(customer);
        finish();
    }

    public void updateCustomer(Customer customer) {
        String name = binding.etFulName.getText().toString().trim();
        String phone = binding.etPhoneNumber.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();
        String address = binding.etAddress.getText().toString().trim();
        String points = binding.etPoint.getText().toString().trim();
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
        finish();
    }

    public void findByViewID() {
        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);
        tvTitile = findViewById(R.id.tvTitle);
        tvDelete = findViewById(R.id.tvDelete);
        btnAdd.setVisibility(View.INVISIBLE);
        tvTitile.setText("Khách hàng");

    }

    public void getIntents() {
        Intent intent = getIntent();
        control = intent.getStringExtra("control");
        if (control.equals("create")) {
            binding.btnControl.setText("Thêm mới");
            tvDelete.setVisibility(View.INVISIBLE);
        } else {
            binding.btnControl.setText("Cập nhật");
            tvDelete.setVisibility(View.VISIBLE);
            ID = intent.getIntExtra("ID", 0);
            for (Customer customer : customerArrayList) {
                if (customer.getIDCustomer() == ID) {
                    customerChoose = customer;
                    binding.etVoucher.setText(customer.getCustomerVoucher());
                    binding.etType.setText(customer.getCustomerType());
                    binding.etPoint.setText(customer.getCustomerPoints());
                    binding.etAddress.setText(customer.getCustomerAddress());
                    binding.etPhoneNumber.setText(customer.getCustomerPhone());
                    binding.etEmail.setText(customer.getCustomerEmail());
                    binding.etFulName.setText(customer.getCustomerName());
                }
            }
        }

    }

    public void initialization() {
        sqlHelper = new SQLHelper(getApplicationContext());
        customerArrayList = new ArrayList<>();
        customerArrayList = sqlHelper.getAllCustomer();
    }
}