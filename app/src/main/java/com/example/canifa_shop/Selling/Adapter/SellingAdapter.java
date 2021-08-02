package com.example.canifa_shop.Selling.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.canifa_shop.Product.Object.Product;
import com.example.canifa_shop.Product.ProductDetailActivity;
import com.example.canifa_shop.R;
import com.example.canifa_shop.SQLHelper.SQLHelper;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URI;
import java.util.List;

public class SellingAdapter extends RecyclerView.Adapter<SellingAdapter.ViewHolder> {
    List<Product> productList;
    Context context;
    OnClickItem onClickItem;

    public SellingAdapter(List<Product> productList, Context context, OnClickItem onClickItem) {
        this.productList = productList;
        this.context = context;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SellingAdapter.ViewHolder holder, int position) {

            Product product = productList.get(position);
            String link = product.getImage();
            String name = product.getNameProduct();
            String price = product.getPrice()+"";
            String amount = product.getAmount()+"";
            Picasso.with(context).load("file://"+link).into(holder.ivProduct);
            holder.tvPriceProduct.setText(price);
            holder.tvNameProduct.setText(name);
            holder.tvAmountProduct.setText(amount);
            holder.llProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SQLHelper sqlHelper = new SQLHelper(context);
                    sqlHelper.insertOrderProduct(product);
                    onClickItem.onClickItem();
                }
            });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        TextView tvNameProduct,tvAmountProduct,tvPriceProduct;
        LinearLayout llProduct;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.ivProducts);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvAmountProduct = itemView.findViewById(R.id.tvAmountProduct);
            tvPriceProduct = itemView.findViewById(R.id.tvPriceProduct);
            llProduct = itemView.findViewById(R.id.llProduct);
        }
    }
}
