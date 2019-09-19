package com.ats.dairydistributorapp.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ats.dairydistributorapp.Constant.Constants;
import com.ats.dairydistributorapp.R;
import com.ats.dairydistributorapp.adapter.ItemAdapter;
import com.ats.dairydistributorapp.adapter.OrderHistoryAdapter;
import com.ats.dairydistributorapp.model.Distributor;
import com.ats.dairydistributorapp.model.GetOrder;
import com.ats.dairydistributorapp.util.CommonDialog;
import com.ats.dairydistributorapp.util.CustomSharedPreference;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ats.dairydistributorapp.activity.HomeActivity.tvTitle;

public class OrderHistoryFragment extends Fragment implements View.OnClickListener {

    private EditText edDate;
    private LinearLayout llSearch;
    private RecyclerView recyclerView;
    private TextView tvDate;

    OrderHistoryAdapter adapter;

    int distributorId;

    int yyyy, mm, dd;
    long millis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);

        tvTitle.setText(getContext().getText(R.string.fragment_order_history));

        edDate = view.findViewById(R.id.edDate);
        llSearch = view.findViewById(R.id.llSearch);
        recyclerView = view.findViewById(R.id.recyclerView);
        tvDate = view.findViewById(R.id.tvDate);

        edDate.setOnClickListener(this);
        llSearch.setOnClickListener(this);

        Gson gson = new Gson();
        String jsonDist = CustomSharedPreference.getString(getContext(), CustomSharedPreference.KEY_DISTRIBUTOR);
        final Distributor distributor = gson.fromJson(jsonDist, Distributor.class);

        Log.e("Distributor : ", "---------------------------" + distributor);

        if (distributor != null) {
            distributorId = distributor.getDistId();
        }

        Calendar cal = Calendar.getInstance();
        yyyy = cal.get(Calendar.YEAR);
        mm = cal.get(Calendar.MONTH) + 1;
        dd = cal.get(Calendar.DAY_OF_MONTH);
        edDate.setText(dd + "-" + (mm) + "-" + yyyy);
        tvDate.setText(yyyy + "-" + mm + "-" + dd);
        millis = cal.getTimeInMillis();


        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.edDate) {

            int yr, mn, dt;

            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(millis);
            yr = cal.get(Calendar.YEAR);
            mn = cal.get(Calendar.MONTH);
            dt = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(getActivity(), R.style.AlertDialogTheme, dateListener, yr, mn, dt);
            dialog.show();

        } else if (v.getId() == R.id.llSearch) {

            if (edDate.getText().toString().isEmpty()) {
                edDate.setError("required");
            } else {
                edDate.setError(null);

                getOrderHistory(distributorId, tvDate.getText().toString());

            }

        }
    }

    private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            yyyy = year;
            mm = month + 1;
            dd = dayOfMonth;
            edDate.setText(dd + "-" + mm + "-" + yyyy);
            tvDate.setText(yyyy + "-" + mm + "-" + dd);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, dd);
            calendar.set(Calendar.MONTH, (mm - 1));
            calendar.set(Calendar.YEAR, yyyy);
            millis = calendar.getTimeInMillis();
            Log.e("CAL : ", "----------------" + calendar.getTime());
        }
    };


    public void getOrderHistory(int distId, String date) {

        Log.e("PARAMETER : ", "DIST_ID : -------------- " + distId + "  -------------  DATE : " + date);

        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<GetOrder>> listCall = Constants.myInterface.getOrderHistoryByStatus(3, distId, date);
            listCall.enqueue(new Callback<ArrayList<GetOrder>>() {
                @Override
                public void onResponse(Call<ArrayList<GetOrder>> call, Response<ArrayList<GetOrder>> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("Order Data : ", "------------" + response.body());

                            ArrayList<GetOrder> data = response.body();
                            if (data == null) {
                                commonDialog.dismiss();
                            } else {
                                if (data.size() > 0) {

                                    adapter = new OrderHistoryAdapter(data, getContext());
                                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                                    recyclerView.setLayoutManager(mLayoutManager);
                                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                                    recyclerView.setAdapter(adapter);
                                }

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
                public void onFailure(Call<ArrayList<GetOrder>> call, Throwable t) {
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }


}
