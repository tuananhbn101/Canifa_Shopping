package com.example.canifa_shop.Emplyee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.canifa_shop.Account.AcountManagerActivity;
import com.example.canifa_shop.Customer.Object.Customer;
import com.example.canifa_shop.Emplyee.Adater.EmployeeAdapter;
import com.example.canifa_shop.Login.Object.Accounts;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;
import com.example.canifa_shop.databinding.ActivityEmployeeManagerBinding;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManagerActivity extends AppCompatActivity {
    ActivityEmployeeManagerBinding binding;
    List<Accounts> accountsList = null;
    List<Accounts> accountsListSearch;
    EmployeeAdapter adapter;
    SQLHelper sqlHelper;
    ImageView btnBack, btnAdd;
    TextView tvTitile, tvDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_manager);
        initialization();
        findByViewID();
        setAdapter(accountsList);
        btnBack.setOnClickListener(v -> {
            finish();
        });
        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                addListSearch(binding.edtSearch.getText().toString());
                if (binding.edtSearch.getText().toString().equals("")) {
                    binding.btnDelete.setVisibility(View.INVISIBLE);
                    setAdapter(accountsList);
                } else binding.btnDelete.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AcountManagerActivity.class);
                intent.putExtra("control", "create");
                startActivity(intent);
            }
        });

        binding.rvEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), AcountManagerActivity.class);
                intent.putExtra("control", "update");
                intent.putExtra("ID", accountsList.get(position).getAccountID());
                startActivity(intent);
            }
        });

    }

    public void initialization() {
        sqlHelper = new SQLHelper(getApplicationContext());
        accountsList = new ArrayList<>();
        accountsListSearch = new ArrayList<>();
        accountsList = sqlHelper.getAllAccounts();
    }

    public void setAdapter(List<Accounts> accountsList) {
        adapter = new EmployeeAdapter(this, R.layout.item_employee, accountsList);
        binding.rvEmployee.setAdapter(adapter);
    }

    public void addListSearch(String text) {
        accountsListSearch.clear();
        for (Accounts accounts : accountsList) {
            if (String.valueOf(accounts.getAccountID()).contains(text) || accounts.getFullName().contains(text)) {
                accountsListSearch.add(accounts);
            }
        }
        setAdapter(accountsListSearch);
    }

    public void findByViewID() {
        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);
        tvTitile = findViewById(R.id.tvTitle);
        tvDelete = findViewById(R.id.tvDelete);
        tvDelete.setVisibility(View.INVISIBLE);
        btnAdd = findViewById(R.id.btnAdd);
        tvTitile.setText("Nhân viên");
    }

    @Override
    protected void onStart() {
        super.onStart();
        initialization();
        setAdapter(accountsList);
    }
}