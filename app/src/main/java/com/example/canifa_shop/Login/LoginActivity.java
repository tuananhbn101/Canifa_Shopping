package com.example.canifa_shop.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.canifa_shop.Helper.Function;
import com.example.canifa_shop.Login.Object.Accounts;
import com.example.canifa_shop.MainActivity;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;
import com.example.canifa_shop.databinding.ActivityLoginBinding;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    SQLHelper sqlHelper;
    List<Accounts> accountsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        initialization();
        binding.btnLogin.setOnClickListener(v -> {
          if(checkAccount()){
              Intent intent = new Intent(LoginActivity.this, MainActivity.class);
              startActivity(intent);
              finish();
          }
          else {
              Toast.makeText(getApplicationContext(),"Tài khoản hoặc mật khẩu không đúng",Toast.LENGTH_LONG).show();
          }

        });
    }

    public void initialization() {
        sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        accountsList = new ArrayList<>();
        sqlHelper = new SQLHelper(getApplicationContext());
        accountsList = sqlHelper.getAllAccounts();
        createAccountAdmin();
    }

    public boolean checkAccount() {
        boolean checkHas = false;
        for (Accounts accounts : accountsList) {
            if (accounts.getUserName().equals(binding.etUserName.getText().toString().trim()) && accounts.getPassword().equals(binding.etPassword.getText().toString().trim())) {
                editor = sharedPreferences.edit();
                editor.putInt("ID", accounts.getAccountID());
                editor.commit();
                checkHas = true;
                break;
            }
        }
        return checkHas;
    }
    public void createAccountAdmin(){
        if (accountsList.size()==0||accountsList==null){
            Accounts accounts = new Accounts(0,"tuananh12","tuananh","Nguyễn Tuấn Anh","10/01/2000","0395501405","anh@gmail.com","Bắc Ninh","Hi", Function.permissionAdmin);
            sqlHelper.insertAccount(accounts);
            accountsList.clear();
            accountsList = sqlHelper.getAllAccounts();
        }
    }
}