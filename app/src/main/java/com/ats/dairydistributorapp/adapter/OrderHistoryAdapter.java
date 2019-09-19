package com.ats.dairydistributorapp.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ats.dairydistributorapp.R;
import com.ats.dairydistributorapp.activity.HomeActivity;
import com.ats.dairydistributorapp.model.GetOrder;
import com.ats.dairydistributorapp.model.GetOrderDetail;
import com.ats.dairydistributorapp.util.CustomSharedPreference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.MyViewHolder> {

    private ArrayList<GetOrder> headerList;
    private Context context;

    public OrderHistoryAdapter(ArrayList<GetOrder> headerList, Context context) {
        this.headerList = headerList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public EditText edCratesPendingBal, edCratesIssued, edCratesReceived, edAmountPending, edAmountReceived, edOrderTotal;
        public TextView tvType, tvDate;
        public RecyclerView recyclerView;

        public MyViewHolder(View view) {
            super(view);
            edCratesPendingBal = view.findViewById(R.id.edCratesPendingBal);
            edCratesIssued = view.findViewById(R.id.edCratesIssued);
            edCratesReceived = view.findViewById(R.id.edCratesReceived);
            edAmountPending = view.findViewById(R.id.edAmountPending);
            edAmountReceived = view.findViewById(R.id.edAmountReceived);
            tvType = view.findViewById(R.id.tvType);
            tvDate = view.findViewById(R.id.tvDate);
            edOrderTotal = view.findViewById(R.id.edOrderTotal);
            recyclerView = view.findViewById(R.id.recyclerView);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_order_history, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final GetOrder model = headerList.get(position);
        Log.e("Adapter : ", " model : " + model);

        String type = "";
        if (model.getOrderType() == 0) {
            type = context.getResources().getString(R.string.str_regular_order);
        } else if (model.getOrderType() == 1) {
            type = context.getResources().getString(R.string.str_special_order);
        }

        HomeActivity activity = (HomeActivity) context;
        String lang = CustomSharedPreference.getString(activity, CustomSharedPreference.KEY_LANGUAGE);
        int langType = 0;
        if (lang.equalsIgnoreCase("1")) {
            langType = 1;
        } else if (lang.equalsIgnoreCase("2")) {
            langType = 2;
        }

        if (langType <= 1) {

        } else if (langType == 2) {

        }

        holder.tvType.setText("" + type);
        holder.edCratesPendingBal.setText("" + model.getPrevPendingCrateBal());
        holder.edCratesIssued.setText("" + model.getCratesIssued());
        holder.edCratesReceived.setText("" + model.getCratesReceived());
        holder.edAmountPending.setText("" + model.getPrevPendingAmt());
        holder.edAmountReceived.setText("" + model.getAmtReceived());
        holder.edOrderTotal.setText("" + model.getOrderTotal());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date date = sdf.parse(model.getOrderDate());
            holder.tvDate.setText("" + sdf1.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (model.getGetOrderDetailList().size() > 0) {

            ArrayList<GetOrderDetail> orderDetailList = new ArrayList<>();
            for (int i = 0; i < model.getGetOrderDetailList().size(); i++) {
                orderDetailList.add(model.getGetOrderDetailList().get(i));
            }

            OrderItemAdapter adapter = new OrderItemAdapter(orderDetailList, context);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            holder.recyclerView.setLayoutManager(mLayoutManager);
            holder.recyclerView.setItemAnimator(new DefaultItemAnimator());
            holder.recyclerView.setAdapter(adapter);


        }


    }

    @Override
    public int getItemCount() {
        return headerList.size();
    }


}
