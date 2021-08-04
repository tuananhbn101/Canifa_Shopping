package com.example.canifa_shop.Bill.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.canifa_shop.Bill.Object.Bill;
import com.example.canifa_shop.Customer.Adapter.CustomerAdapter;
import com.example.canifa_shop.Customer.Object.Customer;
import com.example.canifa_shop.R;

import java.util.List;

public class BillListAdapter extends ArrayAdapter<Bill> {
    List<Bill> billList;
    Context context;
    int id;
    public BillListAdapter(@NonNull Context context, int resource, List<Bill> billList) {
        super(context, resource,billList);
        this.billList=billList;
        this.id=resource;
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Viewholer view;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(context).inflate(id, parent, false);
          view=new Viewholer();
            view.tvName=convertView.findViewById(R.id.tvBillCustomer);
            view.tvID=convertView.findViewById(R.id.tvIDBill);
            view.tvDate=convertView.findViewById(R.id.tvDateTime);
            view.tvTotalPrice=convertView.findViewById(R.id.tvTotalPrice);
            convertView.setTag(view);
        }else{
            view=(Viewholer) convertView.getTag();
        }
        Bill b=billList.get(position);
        view.tvID.setText(b.getIDBill()+"");
        view.tvName.setText(b.getNames());
        view.tvTotalPrice.setText(b.getPrice());
        view.tvDate.setText(b.getDate());
        return convertView;
    }
    class Viewholer{
        TextView tvID, tvName, tvDate, tvTotalPrice;
    }
}
