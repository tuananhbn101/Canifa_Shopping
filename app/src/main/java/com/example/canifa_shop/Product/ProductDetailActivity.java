package com.example.canifa_shop.Product;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.canifa_shop.Category.Object.Category;
import com.example.canifa_shop.MainActivity;
import com.example.canifa_shop.Product.Object.Product;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;

import com.example.canifa_shop.Selling.Adapter.SellingAdapter;
import com.example.canifa_shop.Selling.Adapter.SellingAdapterGrid;
import com.example.canifa_shop.databinding.ActivityProductBinding;
import com.example.canifa_shop.databinding.ActivityProductDetailBinding;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;



public class ProductDetailActivity extends AppCompatActivity {
    public static final int PICK_IMAGE = 1;
    ActivityProductDetailBinding binding;
    ArrayList<Category> categories;
    ArrayAdapter<Category> categoryArrayAdapter;
    SQLHelper  sqlHelper;
    List<Product> productList;
    ImageView btnBack, btnAdd;
    TextView tvTitile,tvDelete;
    String control;
    Product productUpdate;
    int ID;
    String linkImage = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        findByViewID();
        initialization();
        getIntents();
        setAdapterSpiner();
        binding.btnControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(control.equals("create")){
                    addProduct();
                   finish();
                }else {
                    updateProduct();
                    finish();
                }
            }
        });
        binding.ivProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog builder = new AlertDialog.Builder(ProductDetailActivity.this)
                        .setTitle("Bạn có muốn xóa")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sqlHelper.deleteItemProduct(ID);
                                finish();
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                builder.show();
            }
        });

    }

    public void getIntents() {
        Intent intent = getIntent();
        control = intent.getStringExtra("control");
        if(control.equals("create")){
            binding.btnControl.setText("Thêm mới");
            tvDelete.setVisibility(View.INVISIBLE);
        }
        else {
            binding.btnControl.setText("Cập nhật");
            tvDelete.setVisibility(View.VISIBLE);
            ID = intent.getIntExtra("ID", 0);
            for (Product product : productList
            ) {
                if(product.getID()==ID){
                    productUpdate = product;
                    binding.etNameProduct.setText(product.getNameProduct());
                    binding.etAmountProduct.setText(product.getAmount()+"");
                    binding.etBardCodeProduct.setText(product.getBardCode());
                    binding.etNote.setText(product.getDescribe());
                    Picasso.with(getApplicationContext()).load("file://"+product.getImage()).into(binding.ivProduct);
                    binding.etPriceImport.setText(product.getImportprice()+"");
                    binding.etPriceSell.setText(product.getPrice()+"");
                }
            }
        }

    }
    public void addProduct(){
        String name = binding.etNameProduct.getText().toString().trim();
        long priceImport = Long.valueOf(binding.etPriceImport.getText().toString().trim());
        long priceSell= Long.valueOf(binding.etPriceSell.getText().toString().trim());
        String code = binding.etBardCodeProduct.getText().toString().trim();
        String note = binding.etNote.getText().toString().trim();
        int amount = Integer.valueOf(binding.etAmountProduct.getText().toString().trim());
        String type = binding.spType.getSelectedItem().toString();
        Product product = new Product(0,name,priceImport,priceSell,amount,type,note,linkImage,code);
        sqlHelper.insertProduct(product);
    }
    public void findByViewID(){
        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);
        tvTitile = findViewById(R.id.tvTitle);
        tvDelete = findViewById(R.id.tvDelete);
        btnAdd.setVisibility(View.INVISIBLE);
        tvTitile.setText("Sản phẩm");

    }
    public void initialization() {

        sqlHelper = new SQLHelper(this);
        productList = new ArrayList<>();
        categories = new ArrayList<>();
        productList = sqlHelper.getAllPrduct();
        categories.add(new Category("QC","Quần","20"));
        categories.add(new Category("QC","Áo","20"));
        categories.add(new Category("QC","Váy","20"));
    }
    public void setAdapterSpiner(){
        categoryArrayAdapter = new ArrayAdapter<Category>(this, android.R.layout.simple_list_item_1,categories);
        categoryArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        binding.spType.setAdapter(categoryArrayAdapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            Uri selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                // update the preview image in the layout
                binding.ivProduct.setImageURI(selectedImageUri);
                linkImage += convertMediaUriToPath(selectedImageUri);
            }
        }
    }
    public String convertMediaUriToPath(Uri uri) {
        String [] proj={MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, proj,  null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }
    public void updateProduct(){
        String name = binding.etNameProduct.getText().toString().trim();
        long priceImport = Long.valueOf(binding.etPriceImport.getText().toString().trim());
        long priceSell= Long.valueOf(binding.etPriceSell.getText().toString().trim());
        String code = binding.etBardCodeProduct.getText().toString().trim();
        String note = binding.etNote.getText().toString().trim();
        int amount = Integer.valueOf(binding.etAmountProduct.getText().toString().trim());
        String type = binding.spType.getSelectedItem().toString();
        productUpdate.setNameProduct(name);
        productUpdate.setPrice(priceSell);
        productUpdate.setImportprice(priceImport);
        productUpdate.setAmount(amount);
        productUpdate.setBardCode(code);
        productUpdate.setDescribe(note);
        productUpdate.setType(type);
        sqlHelper.updateProduct(productUpdate);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}