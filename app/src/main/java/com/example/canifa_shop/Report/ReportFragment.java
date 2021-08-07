package com.example.canifa_shop.Report;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.canifa_shop.Bill.Object.Bill;
import com.example.canifa_shop.R;
import com.example.canifa_shop.Report.Objcet.Report;
import com.example.canifa_shop.SQLHelper.SQLHelper;
import com.example.canifa_shop.databinding.FragmentReportBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReportFragment extends Fragment {
    FragmentReportBinding binding;
    SQLHelper sqlHelper;
    List<Report> reportList;
    List<Bill> billList;
    private long totalPrice = 0;
    private long totalImportPrice = 0;
    private long totalSaleMoney = 0;
    private long totalProfit = 0;

    public static ReportFragment newInstance() {

        Bundle args = new Bundle();

        ReportFragment fragment = new ReportFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_report, container, false);
        return binding.getRoot();
    }

    public void initialization() {
        sqlHelper = new SQLHelper(getContext());
        reportList = new ArrayList<>();
        billList = new ArrayList<>();
        billList = sqlHelper.getAllBill();
        reportList = sqlHelper.getAllReport();
        for (Report report : reportList) {
            totalPrice += report.getTotalSale();
            totalImportPrice +=report.getTotalImport();
            totalSaleMoney +=report.getSaleMoney();
        }
        totalProfit = totalPrice-totalImportPrice;
    }
    public void setData(){
        binding.tvProfit.setText(totalProfit+"");
        binding.tvSales.setText(totalPrice+"");
        binding.tvDiscount.setText(totalSaleMoney+"");
        binding.tvInvoiceValue.setText(totalPrice+"");
        binding.tvInvoiceNumber.setText(billList.size()+"");
        binding.tvSellAmount.setText(totalPrice+"");
        binding.tvFundAmount.setText(totalImportPrice+"");
        binding.tvBankAmount.setText("0");
        binding.tvBluntAmount.setText(totalPrice+"");
        binding.tvDebtAmount.setText("0");
        binding.tvTaxAmount.setText("0");
    }

    @Override
    public void onStart() {
        super.onStart();
        initialization();
        setData();
    }
}
