package com.example.canifa_shop.Manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.canifa_shop.Bill.Adapter.BillAdapter;
import com.example.canifa_shop.Manager.Adapter.ProductImportListAdapter;
import com.example.canifa_shop.Manager.Adapter.ProductListAdapter;
import com.example.canifa_shop.Manager.Object.Receipt;
import com.example.canifa_shop.Product.Adapter.ProductAdapter;
import com.example.canifa_shop.Product.Object.Product;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;
import com.example.canifa_shop.databinding.ActivityImprodProductBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ImportProductActivity extends AppCompatActivity {
    ActivityImprodProductBinding binding;
    private List<Product> productList;
    SQLHelper sqlHelper;
    private List<Product> productListSearch;
    ProductListAdapter productListAdapter;
    List<Product> productImportList;
    ProductImportListAdapter productImportListAdapter;
    SharedPreferences sharedPreferences;
    private int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_improd_product);
        initialization();
        getIntents();
        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                productListSearch.clear();
                binding.btnDelete.setVisibility(View.VISIBLE);
                for (Product product : productList) {
                    if (product.getBardCode().contains(binding.edtSearch.getText().toString()) || product.getNameProduct().contains(binding.edtSearch.getText().toString())) {
                        productListSearch.add(product);
                    }
                }
                setAdapter(productListSearch);
                if (binding.edtSearch.getText().toString().equals("")) {
                    binding.btnDelete.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtSearch.setText("");
                setAdapter(productList);
                binding.btnDelete.setVisibility(View.INVISIBLE);
            }
        });
        binding.lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                showDialogAmount(productListSearch.get(position));

            }
        });
        binding.btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProduct();
                addReceipt(productImportList);
                finish();
                Toast.makeText(getApplicationContext(), "Nhập hàng thành công", Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showDialogAmount(Product product) {
        Dialog dialog = new Dialog(ImportProductActivity.this);
        dialog.setContentView(R.layout.dialog_update_amount);
        EditText etAmount = dialog.findViewById(R.id.etAmountt);
        Button btnUpdate = dialog.findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setAmount(Integer.valueOf(etAmount.getText().toString()));
                productImportList.add(product);
                productImportListAdapter.notifyDataSetChanged();
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void getIntents() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            if (intent.getStringExtra("control").equals("show")) {
                int ID = intent.getIntExtra("ID", 0);
                addProduct(ID);
                binding.llChooseProduct.setVisibility(View.GONE);
            }
        } else setAdapter(productListSearch);

    }

    public void initialization() {
        sqlHelper = new SQLHelper(getApplicationContext());
        productList = new ArrayList<>();
        productListSearch = new ArrayList<>();
        productImportList = new ArrayList<>();
        productList = sqlHelper.getAllPrduct();
        productListSearch = productList;
        sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        ID = sharedPreferences.getInt("ID", 0);
    }

    public void addProduct(int ID) {
        List<Product> products = sqlHelper.getAllPrduct();
        productListSearch.clear();
        Receipt receipt = sqlHelper.getReceipt(ID);
        String[] IDProduct = receipt.getIDProduct().split(";");
        for (int i = 0; i < IDProduct.length; i++) {
            if (IDProduct[i].equals("")) break;
            else
                for (Product product : products) {
                    if (String.valueOf(product.getID()).equals(IDProduct[i])) {
                        productList.add(product);
                    }
                }
        }
        productListSearch = productList;
        setAdapter(productListSearch);
    }

    public void setAdapter(List<Product> productList) {
        productListAdapter = new ProductListAdapter(productListSearch, getApplicationContext());
        binding.lvProduct.setAdapter(productListAdapter);
        productImportListAdapter = new ProductImportListAdapter(productImportList, getApplicationContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 1, RecyclerView.VERTICAL, false);
        binding.rvProductImport.setLayoutManager(layoutManager);
        binding.rvProductImport.setAdapter(productImportListAdapter);
    }

    public void updateProduct() {
        for (Product productImport : productImportList) {
            for (Product product : productList) {
                if (product.getBardCode().equals(productImport.getBardCode())) {
                    product.setAmount(product.getAmount() + productImport.getAmount());
                    sqlHelper.updateProduct(product);
                }
            }
        }
    }

    public void addReceipt(List<Product> productList) {
        if (productList.size()!=0||productList != null){
            String IDProduct = "";
            int totalProduct = 0;
            for (Product product : productList) {
                IDProduct += product.getID() + ";";
                totalProduct += product.getAmount();
            }
            Receipt receipt = new Receipt(0, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()), IDProduct, totalProduct, ID);
            sqlHelper.insertReceipt(receipt);
        }

    }
}