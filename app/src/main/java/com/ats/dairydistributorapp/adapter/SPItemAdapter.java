package com.ats.dairydistributorapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ats.dairydistributorapp.R;
import com.ats.dairydistributorapp.activity.HomeActivity;
import com.ats.dairydistributorapp.fragment.SpecialOrderFragment;
import com.ats.dairydistributorapp.model.GetAllItemsForRegOrder;
import com.ats.dairydistributorapp.util.CustomSharedPreference;

import java.util.ArrayList;

public class SPItemAdapter extends RecyclerView.Adapter<SPItemAdapter.MyViewHolder> {

    private ArrayList<GetAllItemsForRegOrder> itemList;
    private Context context;
    private int lang;
    float total = 0;

    public SPItemAdapter(ArrayList<GetAllItemsForRegOrder> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public SPItemAdapter(ArrayList<GetAllItemsForRegOrder> itemList, Context context, int lang) {
        this.itemList = itemList;
        this.context = context;
        this.lang = lang;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvMrp, tvRate;
        public EditText edQty;

        public MyViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvMrp = view.findViewById(R.id.tvMrp);
            tvRate = view.findViewById(R.id.tvRate);
            edQty = view.findViewById(R.id.edQty);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final GetAllItemsForRegOrder model = itemList.get(position);
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
            holder.tvName.setText(model.getItemEngName() + " " + model.getItemWt() + " " + model.getUomName());
            holder.tvMrp.setText(context.getResources().getString(R.string.str_mrp) + " : " + model.getItemMrp());
            holder.tvRate.setText(context.getResources().getString(R.string.str_rate) + " : " + model.getItemRate());
        } else if (langType == 2) {
            holder.tvName.setText(model.getItemMarName() + " " + model.getItemWt() + " " + model.getUomName());
            holder.tvMrp.setText(context.getResources().getString(R.string.str_mrp) + " : " + model.getItemMrp());
            holder.tvRate.setText(context.getResources().getString(R.string.str_rate) + " : " + model.getItemRate());
        }

        // holder.edQty.setText("" + model.getOrderQty());

        if (model.getIsQtyChanged() == 1 && (model.getNewQty() != model.getOrderQty())) {
            if (model.getNewQty() == 0) {
                holder.edQty.setHint("" + model.getOrderQty());
            } else {
                holder.edQty.setText("" + model.getNewQty());
            }
        } else {
            holder.edQty.setHint("" + model.getOrderQty());
        }

        holder.edQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int qty = model.getOrderQty();
                try {
                    qty = Integer.parseInt(s.toString());

                    model.setNewQty(qty);
                    model.setIsQtyChanged(1);

                    SpecialOrderFragment homeFragment = new SpecialOrderFragment();
                    homeFragment.displayTotal();

                } catch (Exception e) {
                    Log.e("ERROR : ", "----------------- QTY : 0");
                    model.setNewQty(0);
                    model.setIsQtyChanged(0);
                    SpecialOrderFragment homeFragment = new SpecialOrderFragment();
                    homeFragment.displayTotal();
                    holder.edQty.setHint("" + model.getOrderQty());

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


}


