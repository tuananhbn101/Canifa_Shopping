package com.example.canifa_shop.Bill;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.canifa_shop.Bill.Adapter.BillListAdapter;
import com.example.canifa_shop.Bill.Object.Bill;
import com.example.canifa_shop.R;
import com.example.canifa_shop.databinding.FragmentBillBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BillFragment extends Fragment {
    FragmentBillBinding binding;
    List<Bill> billList=null;
    BillListAdapter adapter;

    public static BillFragment newInstance() {

        Bundle args = new Bundle();

        BillFragment fragment = new BillFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bill,container,false);
        Bill b1=new Bill(1, "4/8/2021", "Tuan Anh", "10","1000000 VND", 30, 1, 1 );
        Bill b2=new Bill(2, "4/8/2021", "Ms. Thao", "9","1500000 VND", 30, 1, 1 );
        Bill b3=new Bill(3, "1/8/2021", "Tuan Anh", "10","1000000 VND", 30, 1, 1 );

        billList=new ArrayList<>();
        billList.add(b1);
        billList.add(b2);
        billList.add(b3);
        adapter=new BillListAdapter(getContext(), R.layout.item_list_bill, billList);
        binding.rvBill.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return binding.getRoot();
    }
}
