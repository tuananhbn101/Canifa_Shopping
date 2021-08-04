package com.example.canifa_shop.Customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canifa_shop.Customer.Adapter.CustomerAdapter;
import com.example.canifa_shop.Customer.Object.Customer;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;
import com.example.canifa_shop.databinding.ActivityCustomerBinding;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {
    ActivityCustomerBinding binding;
    SQLHelper sqlHelper;
    List<Customer> customerList;
    ImageView btnAdd;
    String control;
    CustomerAdapter adapter = null;
    ImageView btnBack;
    TextView tvTitile, tvDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_customer);
        initialization();
        findByViewID();
        getInten();
        setAdapter();
        binding.rvCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //    Toast.makeText(getBaseContext(), customerList.get(position).getIDCustomer()+"", Toast.LENGTH_SHORT).show();
                int ID = customerList.get(position).getIDCustomer();
                showDialog(ID, customerList);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this, CustomerDetailActivity.class);
                intent.putExtra("control", "create");
                startActivity(intent);
            }
        });

    }

    public void findByViewID() {
        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);
        tvTitile = findViewById(R.id.tvTitle);
        tvDelete = findViewById(R.id.tvDelete);
        tvDelete.setVisibility(View.INVISIBLE);
        btnAdd = findViewById(R.id.btnAdd);
        tvTitile.setText("Khách hàng");
    }

    private void showDialog(int id, List<Customer> customerList) {
        id = id - 1;
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_customer_detail);
        ImageView img = dialog.findViewById(R.id.ivCustomer);
        img.setImageResource(R.drawable.ic_baseline_account_circle_24);
        TextView tvVoucher, tvPoints, tvType, button;
        EditText etName, etAddress, etPhone, etEmail;
        tvVoucher = dialog.findViewById(R.id.etVoucher);
        tvVoucher.setText(customerList.get(id).getCustomerVoucher());
        tvType = dialog.findViewById(R.id.etType);
        tvType.setText(customerList.get(id).getCustomerType());
        tvPoints = dialog.findViewById(R.id.etPoint);
        tvPoints.setText(customerList.get(id).getCustomerPoints());
        etName = dialog.findViewById(R.id.etFulName);
        etName.setText(customerList.get(id).getCustomerName());
        etAddress = dialog.findViewById(R.id.etAddress);
        etAddress.setText(customerList.get(id).getCustomerAddress());
        etEmail = dialog.findViewById(R.id.etEmail);
        etEmail.setText(customerList.get(id).getCustomerEmail());
        etPhone = dialog.findViewById(R.id.etPhoneNumber);
        etPhone.setText(customerList.get(id).getCustomerPhone());
        button = dialog.findViewById(R.id.btnControl);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);


    }

    public void getInten() {
        Intent intent = getIntent();
        control = intent.getStringExtra("control");
        if (control != null) {
            if (control.equals("getCustomer")) {

            } else
                tvDelete.setVisibility(View.INVISIBLE);

        }
    }

    public void initialization() {
        sqlHelper = new SQLHelper(getApplicationContext());
        Customer customer = new Customer(1, "Nguyen Tuan Anh", "123456789", "tuananh@gmail.com", "Bac Ninh", "120 point", "Khach hang tiem nang", "Giam 10%");
        sqlHelper.insertCustomer(customer);
        customerList = new ArrayList<>();
        customerList = sqlHelper.getAllCustomer();
    }
    public void setAdapter(){
        adapter = new CustomerAdapter(this, R.layout.layout_item_customer, customerList);
        binding.rvCustomer.setAdapter(adapter);
    }

}