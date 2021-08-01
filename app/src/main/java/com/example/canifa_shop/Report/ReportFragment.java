package com.example.canifa_shop.Report;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.canifa_shop.R;
import com.example.canifa_shop.databinding.FragmentReportBinding;

import org.jetbrains.annotations.NotNull;

public class ReportFragment extends Fragment {
    FragmentReportBinding binding;
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_report,container,false);
        return binding.getRoot();
    }
}
