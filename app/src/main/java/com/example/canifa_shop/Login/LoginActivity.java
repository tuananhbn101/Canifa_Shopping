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
        initialization(); // hàm này để lấy danh sách các tài khoản trong bảng Account của SQLHelper ,
        // nếu danh sách bằng null thì tiến hành thực thi hàm createAdmin()
        binding.btnLogin.setOnClickListener(v -> {
            // khi click vào button Đăng nhập, thì sẽ thực hiện hàm checkAccount(), nếu kết quả là true thì điều hướng về màn hình trang chủ MainActivity.java
            //Nếu kết quả là false, thì thông báo "Tài khoản hoặc mật khẩu không đúng"
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
        // kiểm tra thông tin đăng nhập, nếu đúng thì trả về true, sai thì trả về false
        boolean checkHas = false;
        for (Accounts accounts : accountsList) {
            if (accounts.getUserName().equals(binding.etUserName.getText().toString().trim())
                    && accounts.getPassword().equals(binding.etPassword.getText().toString().trim())) {
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
        // tạo tài khoản đăng nhập cho admin, nếu danh sách tài khoản ban đầu bằng rỗng thì tiến hành tạo tài khoản cho admin
        if (accountsList.size()==0||accountsList==null){
            Accounts accounts = new Accounts(0,"thanhthao","thao123","Nguyễn Tuấn Anh","10/01/2000",
                    "0395501405","anh@gmail.com","Bắc Ninh","Hi", Function.permissionAdmin);
            sqlHelper.insertAccount(accounts);
            accountsList.clear();
            accountsList = sqlHelper.getAllAccounts();
        }
    }
}