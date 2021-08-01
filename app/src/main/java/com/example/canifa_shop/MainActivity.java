package com.example.canifa_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.canifa_shop.Bill.BillFragment;
import com.example.canifa_shop.More.MoreFragment;
import com.example.canifa_shop.Report.ReportFragment;
import com.example.canifa_shop.Selling.SellingFragment;
import com.example.canifa_shop.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        getFragment(SellingFragment.newInstance());
        binding.bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.selling:{
                        getFragment(SellingFragment.newInstance());
                        return true;
                    }
                    case R.id.bill:{
                        getFragment(BillFragment.newInstance());
                        return true;
                    }
                    case R.id.report:{
                        getFragment(ReportFragment.newInstance());
                        return true;
                    }
                    case R.id.more:{
                        getFragment(MoreFragment.newInstance());
                        return true;
                    }
                }
                return false;
            }
        });
    }
    public void getFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.flMain,fragment).commit();
    }
}