package com.ats.dairydistributorapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ats.dairydistributorapp.Constant.Constants;
import com.ats.dairydistributorapp.R;
import com.ats.dairydistributorapp.adapter.NotificationAdapter;
import com.ats.dairydistributorapp.adapter.OrderHistoryAdapter;
import com.ats.dairydistributorapp.model.Distributor;
import com.ats.dairydistributorapp.model.GetOrder;
import com.ats.dairydistributorapp.model.Notification;
import com.ats.dairydistributorapp.util.CommonDialog;
import com.ats.dairydistributorapp.util.CustomSharedPreference;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ats.dairydistributorapp.activity.HomeActivity.tvTitle;

public class NotificationFragment extends Fragment {

    private RecyclerView recyclerView;
    NotificationAdapter adapter;

    int distributorId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        tvTitle.setText(getContext().getText(R.string.fragment_notification));

        recyclerView = view.findViewById(R.id.recyclerView);

        Gson gson = new Gson();
        String jsonDist = CustomSharedPreference.getString(getContext(), CustomSharedPreference.KEY_DISTRIBUTOR);
        final Distributor distributor = gson.fromJson(jsonDist, Distributor.class);

        Log.e("Distributor : ", "---------------------------" + distributor);

        if (distributor != null) {
            distributorId = distributor.getDistId();
            getNotification(distributorId);
        }


        return view;
    }


    public void getNotification(int distId) {

        Log.e("PARAMETER : ", "DIST_ID : -------------- " + distId);

        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<ArrayList<Notification>> listCall = Constants.myInterface.getNotifiacation(distId);
            listCall.enqueue(new Callback<ArrayList<Notification>>() {
                @Override
                public void onResponse(Call<ArrayList<Notification>> call, Response<ArrayList<Notification>> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("NOtification Data : ", "------------" + response.body());

                            ArrayList<Notification> data = response.body();
                            if (data == null) {
                                commonDialog.dismiss();
                            } else {
                                if (data.size() > 0) {

                                    adapter = new NotificationAdapter(data, getContext());
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
                public void onFailure(Call<ArrayList<Notification>> call, Throwable t) {
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
