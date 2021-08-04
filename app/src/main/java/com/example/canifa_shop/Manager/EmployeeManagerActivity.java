package com.example.canifa_shop.Manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.canifa_shop.Manager.Adapter.EmployeeAdapter;
import com.example.canifa_shop.Manager.Object.Accounts;
import com.example.canifa_shop.R;
import com.example.canifa_shop.databinding.ActivityEmployeeManagerBinding;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManagerActivity extends AppCompatActivity {
    ActivityEmployeeManagerBinding binding;
    List<Accounts> accountsList=null;
    EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_employee_manager);
        //String userName, String password, String fullname, String birth, String sex, String phonenumber, String email
        Accounts a1=new Accounts(1,"thanhthao00", "12345","An Thị Thanh Thảo", "15/11/2000", "Nữ", "0982173912", "thanhthao@gmail.com");
        accountsList=new ArrayList<>();
        accountsList.add(a1);
        adapter=new EmployeeAdapter(this, R.layout.item_employee, accountsList);
        binding.rvEmployee.setAdapter(adapter);

    }
}