package com.example.canifa_shop.Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canifa_shop.Login.Object.Accounts;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;
import com.example.canifa_shop.databinding.ActivityAcountManagerBinding;

import java.util.ArrayList;
import java.util.List;

public class AcountManagerActivity extends AppCompatActivity {
    ActivityAcountManagerBinding binding;
    private ImageView btnBack, btnAdd;
    private TextView tvTitile, tvDelete;
    SQLHelper sqlHelper;
    List<Accounts> accountsList;
    SharedPreferences sharedPreferences;
    private int ID;
    private Accounts accountsChoose;
    private String control = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acount_manager);
        findByViewID();
        initialization();
        getInten();
        setData();
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (control.equals("create")) {
                    createAccount();
                } else {
                    updateAccout(accountsChoose);
                }
                finish();
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlHelper.deleteAccount(ID);
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
        tvDelete.setVisibility(View.INVISIBLE);
        tvTitile.setText("Tài khoản");

    }

    public void initialization() {
        sqlHelper = new SQLHelper(getApplicationContext());
        accountsList = new ArrayList<>();
        accountsList = sqlHelper.getAllAccounts();
    }

    public void getInten() {
        Intent intent = getIntent();
        control += intent.getStringExtra("control");
        if (control != null && !control.equals("")) {
            if (control.equals("create")) {
                binding.btnUpdate.setText("Thêm mới");
                binding.etUserName.setEnabled(true);
            } else if (control.equals("update")) {
                ID = intent.getIntExtra("ID", 0);
                tvDelete.setVisibility(View.VISIBLE);
            } else {
                sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
                ID = sharedPreferences.getInt("ID", 0);
            }
        }

    }

    public void setData() {
        for (Accounts accounts : accountsList) {
            if (accounts.getAccountID() == ID) {
                accountsChoose = accounts;
                binding.etDateOfBird.setText(accounts.getDateOfBirth());
                binding.etEmail.setText(accounts.getEmail());
                binding.etFullName.setText(accounts.getFullName());
                binding.etPassword.setText(accounts.getPassword());
                binding.etPhoneNumber.setText(accounts.getPhone());
                binding.etAddress.setText(accounts.getHomeTown());
                binding.etUserName.setText(accounts.getUserName());
            }
        }
    }

    public void updateAccout(Accounts accounts) {
        try {
            accounts.setDateOfBirth(binding.etDateOfBird.getText().toString());
            accounts.setEmail(binding.etEmail.getText().toString());
            accounts.setFullName(binding.etFullName.getText().toString());
            accounts.setHomeTown(binding.etAddress.getText().toString());
            accounts.setPassword(binding.etPassword.getText().toString());
            accounts.setPhone(binding.etPhoneNumber.getText().toString());
            sqlHelper.updateAccount(accounts);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Lỗi nhập liệu", Toast.LENGTH_SHORT).show();
        }

    }

    public void createAccount() {
        try {
            String userName = binding.etUserName.getText().toString();
            String password = binding.etPassword.getText().toString();
            String fullName = binding.etFullName.getText().toString();
            String dateOfBirth = binding.etDateOfBird.getText().toString();
            String phone = binding.etPhoneNumber.getText().toString();
            String email = binding.etEmail.getText().toString();
            String homeTow = binding.etAddress.getText().toString();
            String avatar = "";
            String permission = "employee";
            Accounts accounts = new Accounts(0, userName, password, fullName, dateOfBirth, phone, email, homeTow, avatar, permission);
            sqlHelper.insertAccount(accounts);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Lỗi nhập liệu", Toast.LENGTH_SHORT).show();
        }

    }
}