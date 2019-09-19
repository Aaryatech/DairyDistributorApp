package com.ats.dairydistributorapp.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.dairydistributorapp.Constant.Constants;
import com.ats.dairydistributorapp.R;
import com.ats.dairydistributorapp.activity.HomeActivity;
import com.ats.dairydistributorapp.activity.LoginActivity;
import com.ats.dairydistributorapp.adapter.ItemAdapter;
import com.ats.dairydistributorapp.adapter.VerifyOrderAdapter;
import com.ats.dairydistributorapp.model.Category;
import com.ats.dairydistributorapp.model.CategoryAndItems;
import com.ats.dairydistributorapp.model.CategoryItems;
import com.ats.dairydistributorapp.model.Distributor;
import com.ats.dairydistributorapp.model.ErrorMessage;
import com.ats.dairydistributorapp.model.GetAllItemsForRegOrder;
import com.ats.dairydistributorapp.model.GetCatItemList;
import com.ats.dairydistributorapp.model.GetItem;
import com.ats.dairydistributorapp.model.GetOrderHeader;
import com.ats.dairydistributorapp.model.Order;
import com.ats.dairydistributorapp.model.OrderDetail;
import com.ats.dairydistributorapp.model.Route;
import com.ats.dairydistributorapp.util.CommonDialog;
import com.ats.dairydistributorapp.util.CustomSharedPreference;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ats.dairydistributorapp.activity.HomeActivity.tvTitle;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private TextView tvCrates, tvPending, tvFromToDate, tvDate;
    public static TextView tvTotalAmount;
    private Button btnVerify;
    private RecyclerView recyclerView;
    private GridView gridView;

    CategoryAdapter categoryAdapter;
    ItemAdapter itemAdapter;

    private ArrayList<GetAllItemsForRegOrder> itemList = new ArrayList<>();

    public static ArrayList<GetCatItemList> staticCategoryItemList = new ArrayList<>();

    private static GetOrderHeader orderHeader;

    int distributorId, hubId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tvCrates = view.findViewById(R.id.tvCrates);
        tvPending = view.findViewById(R.id.tvPending);
        tvTotalAmount = view.findViewById(R.id.tvTotalAmount);
        btnVerify = view.findViewById(R.id.btnVerify);
        recyclerView = view.findViewById(R.id.recyclerView);
        gridView = view.findViewById(R.id.gridView);
        tvFromToDate = view.findViewById(R.id.tvFromToDate);
        tvDate = view.findViewById(R.id.tvDate);

        tvTitle.setText(getContext().getText(R.string.fragment_regular_order));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        tvDate.setText("" + sdf.format(cal.getTimeInMillis()));

        btnVerify.setOnClickListener(this);

        Gson gson = new Gson();
        String jsonDist = CustomSharedPreference.getString(getContext(), CustomSharedPreference.KEY_DISTRIBUTOR);
        final Distributor distributor = gson.fromJson(jsonDist, Distributor.class);

        Log.e("Distributor : ", "---------------------------" + distributor);

        if (distributor != null) {
            distributorId = distributor.getDistId();
            hubId = distributor.getHubId();
            //getItemsByDistributor(distributorId);
            getAllItems(distributorId, hubId);
        }


        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnVerify) {

            if (staticCategoryItemList.size() > 0) {

                ArrayList<GetAllItemsForRegOrder> allItems = new ArrayList<>();

                for (int i = 0; i < staticCategoryItemList.size(); i++) {
                    allItems.addAll(staticCategoryItemList.get(i).getAllItemList());
                }

                if (allItems.size() > 0) {

                    ArrayList<GetAllItemsForRegOrder> itemsList = new ArrayList<>();
                    for (int i = 0; i < allItems.size(); i++) {
                        if (allItems.get(i).getIsQtyChanged() == 1) {
                            itemsList.add(allItems.get(i));
                        }
                    }

                    if (itemsList.size() > 0) {
                        showVerifyDialog(itemsList);
                    } else {
                        Toast.makeText(getContext(), "please enter quantity", Toast.LENGTH_SHORT).show();
                    }

                }

               /* if (allItems.size() > 0) {

                    ArrayList<OrderDetail> orderDetailList = new ArrayList<>();

                    for (int i = 0; i < allItems.size(); i++) {
                        if (allItems.get(i).getIsQtyChanged() == 1 && allItems.get(i).getNewQty() > 0) {

                            GetAllItemsForRegOrder item = allItems.get(i);

                            float basicVal = ((item.getItemRate() * 100) / (100 + (item.getItemCgst() + item.getItemSgst())));

                            int qty = item.getNewQty();

                            OrderDetail detail = new OrderDetail(item.getOrderDetailId(), orderHeader.getOrderHeaderId(), item.getItemId(), qty, qty, qty, qty, (qty * item.getItemRate()), qty, item.getItemCgst(), item.getItemSgst(), item.getItemIgst(), basicVal);
                            orderDetailList.add(detail);
                            *//* orderItemList.add(allItems.get(i));*//*
                        }
                    }


                    if (orderDetailList.size() > 0) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar calendar = Calendar.getInstance();

                        //Order order = new Order(0, 1, distributorId, sdf.format(calendar.getTimeInMillis()), 0f, 1, orderDetailList);

                        Order order = new Order(orderHeader.getOrderHeaderId(), 0, distributorId, sdf.format(calendar.getTimeInMillis()), 0, sdf.format(calendar.getTimeInMillis()), 0, orderDetailList);
                        saveOrder(order);

                    } else {
                        Toast.makeText(getContext(), "please enter quantity", Toast.LENGTH_SHORT).show();
                    }

                }*/

            }


        }
    }

    public class CategoryAdapter extends BaseAdapter {

        private ArrayList<GetCatItemList> categoryModelList;
        private Context context;
        private LayoutInflater inflater = null;

        public CategoryAdapter(ArrayList<GetCatItemList> categoryModelList, Context context) {
            this.categoryModelList = categoryModelList;
            this.context = context;
            this.inflater = (LayoutInflater) context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return categoryModelList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public class Holder {
            TextView tvName;
            ImageView imageView;
            LinearLayout linearLayout;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Holder holder;
            View rowView = convertView;

            if (rowView == null) {
                holder = new Holder();
                LayoutInflater inflater = LayoutInflater.from(context);
                rowView = inflater.inflate(R.layout.adapter_category, null);

                holder.tvName = rowView.findViewById(R.id.tvName);
                holder.linearLayout = rowView.findViewById(R.id.linearLayout);
                holder.imageView = rowView.findViewById(R.id.imageView);

                rowView.setTag(holder);

            } else {
                holder = (Holder) rowView.getTag();
            }

            String lang = CustomSharedPreference.getString(getActivity(), CustomSharedPreference.KEY_LANGUAGE);
            int langType = 0;
            if (lang.equalsIgnoreCase("1")) {
                langType = 1;
            } else if (lang.equalsIgnoreCase("2")) {
                langType = 2;
            }

            if (langType <= 1) {
                holder.tvName.setText(categoryModelList.get(position).getCatEngName());
            } else if (langType == 2) {
                holder.tvName.setText(categoryModelList.get(position).getCatMarName());
            }


            try {
                Picasso.get().load(Constants.categoryImagePath + "" + categoryModelList.get(position).getCatPic())
                        .placeholder(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_launcher)
                        .into(holder.imageView);
            } catch (Exception e) {
            }

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // getItemsByCategory(categoryModelList.get(position).getCatId());

                    ArrayList<GetAllItemsForRegOrder> itemList = new ArrayList<>();
                    if (staticCategoryItemList.size() > 0) {
                        for (int i = 0; i < staticCategoryItemList.size(); i++) {
                            if (staticCategoryItemList.get(i).getCatId() == categoryModelList.get(position).getCatId()) {
                                itemList = (ArrayList<GetAllItemsForRegOrder>) staticCategoryItemList.get(i).getAllItemList();

                                itemAdapter = new ItemAdapter(itemList, getContext());
                                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                                recyclerView.setLayoutManager(mLayoutManager);
                                recyclerView.setItemAnimator(new DefaultItemAnimator());
                                recyclerView.setAdapter(itemAdapter);
                            }
                        }
                    }


                }
            });

            gridViewSetting(gridView);

            return rowView;
        }
    }

    private void gridViewSetting(GridView gridview) {

        int size = staticCategoryItemList.size();
        // Log.e("Size : ", "----------" + size);
        // Calculated single Item Layout Width for each grid element ....
        int width = 130;//400

        WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        // getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;

        int totalWidth = (int) (width * size * density);
        //  Log.e("Total Width : ", "----------" + totalWidth);
        int singleItemWidth = (int) (width * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                totalWidth, LinearLayout.LayoutParams.MATCH_PARENT);

        gridview.setLayoutParams(params);
        gridview.setColumnWidth(130);
        gridview.setHorizontalSpacing(10);
        gridview.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridview.setNumColumns(size);
    }

    public void getAllItems(int distId, int hubId) {

        Log.e("PARAMETER : ", "DIST_ID : -------------- " + distId + " ------------------------- HUB ID : " + hubId);

        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<CategoryAndItems> listCall = Constants.myInterface.getAllCatAndItemsByOrderType(distId, hubId,0);
            listCall.enqueue(new Callback<CategoryAndItems>() {
                @Override
                public void onResponse(Call<CategoryAndItems> call, Response<CategoryAndItems> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("Items Data : ", "------------" + response.body());

                            CategoryAndItems data = response.body();
                            if (data == null) {
                                commonDialog.dismiss();
                            } else {

                                tvFromToDate.setText("" + data.getConfig().getDistFromTime() + " to " + data.getConfig().getDistToTime());

                                orderHeader = data.getGetOrderHeader();

                                if (staticCategoryItemList != null) {
                                    staticCategoryItemList.clear();
                                }
                                staticCategoryItemList = (ArrayList<GetCatItemList>) data.getCatItemLists();

                                Log.e("COLMUN SIZE : ", "-------------------- " + staticCategoryItemList.size());
                                gridView.setNumColumns(staticCategoryItemList.size());

                                categoryAdapter = new CategoryAdapter(staticCategoryItemList, getContext());
                                gridView.setAdapter(categoryAdapter);

                                if (data.getCatItemLists().size() > 0) {

                                    itemList.clear();

                                    itemList = (ArrayList<GetAllItemsForRegOrder>) staticCategoryItemList.get(0).getAllItemList();

                                    itemAdapter = new ItemAdapter(itemList, getContext());
                                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                                    recyclerView.setLayoutManager(mLayoutManager);
                                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                                    recyclerView.setAdapter(itemAdapter);
                                }

                                //displayTotal();

                                tvTotalAmount.setText("" + data.getGetOrderHeader().getOrderTotal());

                                tvCrates.setText("" + data.getGetOrderHeader().getPrevPendingCrateBal());
                                tvPending.setText("" + data.getGetOrderHeader().getPrevPendingAmt());


                                commonDialog.dismiss();
                            }
                        } else {
                            commonDialog.dismiss();
                            Log.e("Data Null : ", "-----------");
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Log.e("Exception : ", "-----------" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<CategoryAndItems> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveOrder(Order order) {

        Log.e("PARAMETER : ", "ORDER : -------------- " + order);

        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<ErrorMessage> listCall = Constants.myInterface.saveOrder(order);
            listCall.enqueue(new Callback<ErrorMessage>() {
                @Override
                public void onResponse(Call<ErrorMessage> call, Response<ErrorMessage> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("Order Data : ", "------------" + response.body());

                            ErrorMessage data = response.body();
                            if (data == null) {
                                commonDialog.dismiss();
                            } else {

                                if (!data.isError()) {
                                    Toast.makeText(getContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
                                    getAllItems(distributorId, hubId);
                                } else {
                                    Toast.makeText(getContext(), data.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                commonDialog.dismiss();
                            }
                        } else {
                            commonDialog.dismiss();
                            Log.e("Data Null : ", "-----------");
                            Toast.makeText(getContext(), "Unable to process", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        commonDialog.dismiss();
                        Toast.makeText(getContext(), "Unable to process", Toast.LENGTH_SHORT).show();
                        Log.e("Exception : ", "-----------" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ErrorMessage> call, Throwable t) {
                    commonDialog.dismiss();
                    Toast.makeText(getContext(), "Unable to process", Toast.LENGTH_SHORT).show();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayTotal() {
        Log.e("DISPLAY TOTAL : ", "-------------------------");
        if (staticCategoryItemList.size() > 0) {
            ArrayList<GetAllItemsForRegOrder> allItems = new ArrayList<>();
            for (int i = 0; i < staticCategoryItemList.size(); i++) {
                allItems.addAll(staticCategoryItemList.get(i).getAllItemList());
            }

            if (allItems.size() > 0) {

                ArrayList<GetAllItemsForRegOrder> itemsWithQty = new ArrayList<>();
                ArrayList<GetAllItemsForRegOrder> itemsWithOldQty = new ArrayList<>();

                ArrayList<Integer> newQtyArray = new ArrayList<>();
                ArrayList<Integer> oldQtyArray = new ArrayList<>();

                for (int i = 0; i < allItems.size(); i++) {
                    if (allItems.get(i).getIsQtyChanged() == 1) {
                        itemsWithQty.add(allItems.get(i));
                        newQtyArray.add(allItems.get(i).getItemId());
                    }
                    if (allItems.get(i).getOrderQty() > 0) {
                        itemsWithOldQty.add(allItems.get(i));
                        oldQtyArray.add(allItems.get(i).getItemId());
                    }
                }

                float total = 0;

                Log.e("OLD QTY : ", "-----------------------" + oldQtyArray);
                Log.e("NEW QTY : ", "-----------------------" + newQtyArray);

                if (newQtyArray.size() > 0) {
                    for (int i = 0; i < newQtyArray.size(); i++) {
                        oldQtyArray.remove(newQtyArray.get(i));
                    }
                }

                Log.e("OLD CHANGE QTY : ", "-----------------------" + oldQtyArray);

                if (itemsWithOldQty.size() > 0) {

                    for (int i = 0; i < itemsWithOldQty.size(); i++) {
                        for (int j = 0; j < oldQtyArray.size(); j++) {
                            if (itemsWithOldQty.get(i).getItemId() == oldQtyArray.get(j)) {
                                total = total + (itemsWithOldQty.get(i).getOrderQty() * itemsWithOldQty.get(i).getItemRate());
                            }
                        }
                    }
                    Log.e("TOTAL OLD QTY", "-------------------- " + total);
                }

                if (itemsWithQty.size() > 0) {
                    for (int i = 0; i < itemsWithQty.size(); i++) {
                        Log.e("DISPLAY TOTAL : ", "------------ITEM------------- " + allItems.get(i));
                        total = total + (itemsWithQty.get(i).getNewQty() * itemsWithQty.get(i).getItemRate());
                        Log.e("DISPLAY TOTAL : ", "---------------TOTAL------------- " + total);
                    }
                } else {
                    total = 0;
                }

                if (total == 0) {
                    tvTotalAmount.setText("" + orderHeader.getOrderTotal());
                } else {
                    tvTotalAmount.setText("" + total);
                }


            }
        }
    }

    public void showVerifyDialog(final ArrayList<GetAllItemsForRegOrder> itemArrayList) {
        final Dialog openDialog = new Dialog(getContext());
        openDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        openDialog.setContentView(R.layout.dialog_verify_order);
        openDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        Window window = openDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        // wlp.gravity = Gravity.TOP | Gravity.RIGHT;
        wlp.x = 100;
        wlp.y = 100;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(wlp);

        RecyclerView recyclerView = openDialog.findViewById(R.id.recyclerView);
        Button btnCancel = openDialog.findViewById(R.id.btnCancel);
        Button btnOrder = openDialog.findViewById(R.id.btnOrder);
        TextView tvTotal = openDialog.findViewById(R.id.tvTotal);

        VerifyOrderAdapter verifyAdapter = new VerifyOrderAdapter(itemArrayList, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(verifyAdapter);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAdapter.notifyDataSetChanged();
                openDialog.dismiss();
            }
        });


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (staticCategoryItemList.size() > 0) {

                    ArrayList<GetAllItemsForRegOrder> allItems = new ArrayList<>();

                    for (int i = 0; i < staticCategoryItemList.size(); i++) {
                        allItems.addAll(staticCategoryItemList.get(i).getAllItemList());
                    }

                    if (allItems.size() > 0) {

                        ArrayList<OrderDetail> orderDetailList = new ArrayList<>();

                        for (int i = 0; i < allItems.size(); i++) {
                            if (allItems.get(i).getIsQtyChanged() == 1 && allItems.get(i).getNewQty() > 0) {

                                GetAllItemsForRegOrder item = allItems.get(i);

                                float basicVal = ((item.getItemRate() * 100) / (100 + (item.getItemCgst() + item.getItemSgst())));

                                int qty = item.getNewQty();

                                OrderDetail detail = new OrderDetail(item.getOrderDetailId(), orderHeader.getOrderHeaderId(), item.getItemId(), item.getItemRate(), qty, qty, qty, (qty * item.getItemRate()), 0, item.getItemCgst(), item.getItemSgst(), item.getItemIgst(), basicVal);
                                orderDetailList.add(detail);
                            }
                        }


                        if (orderDetailList.size() > 0) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar calendar = Calendar.getInstance();

                            float total = Float.parseFloat(tvTotalAmount.getText().toString());

                            Order order = new Order(orderHeader.getOrderHeaderId(), 0, distributorId, sdf.format(calendar.getTimeInMillis()), total, sdf.format(calendar.getTimeInMillis()), 0, orderDetailList);
                            saveOrder(order);

                        } else {
                            Toast.makeText(getContext(), "please enter quantity", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

                openDialog.dismiss();
            }
        });


        openDialog.show();
    }

}
