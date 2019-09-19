package com.ats.dairydistributorapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ats.dairydistributorapp.R;
import com.ats.dairydistributorapp.activity.HomeActivity;
import com.ats.dairydistributorapp.model.GetOrderDetail;
import com.ats.dairydistributorapp.util.CustomSharedPreference;

import java.util.ArrayList;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.MyViewHolder> {

    private ArrayList<GetOrderDetail> itemList;
    private Context context;

    public OrderItemAdapter(ArrayList<GetOrderDetail> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvRate, tvQty, tvTotal;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvRate = view.findViewById(R.id.tvRate);
            tvQty = view.findViewById(R.id.tvQty);
            tvTotal = view.findViewById(R.id.tvTotal);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_order_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final GetOrderDetail model = itemList.get(position);
        Log.e("Adapter : ", " model : " + model);

        HomeActivity activity = (HomeActivity) context;
        String lang = CustomSharedPreference.getString(activity, CustomSharedPreference.KEY_LANGUAGE);
        int langType = 0;
        if (lang.equalsIgnoreCase("1")) {
            langType = 1;
        } else if (lang.equalsIgnoreCase("2")) {
            langType = 2;
        }

        if (langType <= 1) {
            holder.tvName.setText(model.getItemEngName());
        } else if (langType == 2) {
            holder.tvName.setText(model.getItemMarName());
        }

        holder.tvRate.setText("" + model.getItemRate());
        holder.tvQty.setText("" + model.getOrderQty());
        holder.tvTotal.setText("" + (model.getItemRate() * model.getOrderQty()));


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}
