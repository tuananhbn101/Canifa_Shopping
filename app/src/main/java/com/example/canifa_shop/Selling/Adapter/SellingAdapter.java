package com.example.canifa_shop.Selling.Adapter;

import android.content.Context;
import android.content.Intent;
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
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SellingAdapter extends RecyclerView.Adapter<SellingAdapter.ViewHolder> {
    List<Product> productList;
    Context context;

    public SellingAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
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
        try {
            Product product = productList.get(position);
          //  Picasso.get().load(product.getProducer()).into(holder.ivProduct);
//            holder.tvPriceProduct.setText(product.getPrice()+"");
//            holder.tvNameProduct.setText(product.getNameProduct());
//            holder.tvAmountProduct.setText(product.getAmount());
            holder.llProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        catch (Exception e){
            Log.e("Lỗi",e.getMessage());
        }

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
            ivProduct = itemView.findViewById(R.id.ivProduct);
            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvAmountProduct = itemView.findViewById(R.id.tvNameProduct);
            tvPriceProduct = itemView.findViewById(R.id.tvNameProduct);
            llProduct = itemView.findViewById(R.id.llProduct);
        }
    }
}
