package com.ats.dairydistributorapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ats.dairydistributorapp.Constant.Constants;
import com.ats.dairydistributorapp.R;
import com.ats.dairydistributorapp.activity.HomeActivity;
import com.ats.dairydistributorapp.activity.LoginActivity;
import com.ats.dairydistributorapp.model.Category;
import com.ats.dairydistributorapp.model.Distributor;
import com.ats.dairydistributorapp.model.LoginResponse;
import com.ats.dairydistributorapp.model.Route;
import com.ats.dairydistributorapp.util.CommonDialog;
import com.ats.dairydistributorapp.util.CustomSharedPreference;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.ats.dairydistributorapp.activity.HomeActivity.tvTitle;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private EditText edName, edAddress, edMobile, edPassword;
    private Button btnSave;

    int distributorId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvTitle.setText(getContext().getText(R.string.fragment_profile));


        edName = view.findViewById(R.id.edName);
        edAddress = view.findViewById(R.id.edAddress);
        edMobile = view.findViewById(R.id.edMobile);
        edPassword = view.findViewById(R.id.edPassword);
        btnSave = view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(this);

        Gson gson = new Gson();
        String jsonDist = CustomSharedPreference.getString(getContext(), CustomSharedPreference.KEY_DISTRIBUTOR);
        final Distributor distributor = gson.fromJson(jsonDist, Distributor.class);

        Log.e("Distributor : ", "---------------------------" + distributor);

        if (distributor != null) {

            distributorId = distributor.getDistId();

            String lang = CustomSharedPreference.getString(getActivity(), CustomSharedPreference.KEY_LANGUAGE);

            int langType = 0;
            if (lang.equalsIgnoreCase("1")) {
                langType = 1;
            } else if (lang.equalsIgnoreCase("2")) {
                langType = 2;
            }

            if (langType <= 1) {
                edName.setText(distributor.getDistEngName());
                edAddress.setText(distributor.getDistAddEng());
            } else if (langType == 2) {
                edName.setText(distributor.getDistMarName());
                edAddress.setText(distributor.getDistAddMar());
            }

            edMobile.setText(distributor.getDistContactNo());
            edPassword.setText(distributor.getDistPwd());
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {

            String pwd = edPassword.getText().toString();

            if (pwd.isEmpty()) {
                edPassword.setError("required");
            } else {
                edPassword.setError(null);

                updatePassword(distributorId, pwd);

            }
        }
    }


    public void updatePassword(int distId, String pass) {

        if (Constants.isOnline(getContext())) {
            final CommonDialog commonDialog = new CommonDialog(getContext(), "Loading", "Please Wait...");
            commonDialog.show();

            Call<LoginResponse> listCall = Constants.myInterface.updateDistributorPassword(distId, pass);
            listCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("Update Data : ", "------------" + response.body());

                            LoginResponse data = response.body();
                            if (data == null) {
                                Toast.makeText(getContext(), "Unable to update!", Toast.LENGTH_SHORT).show();
                                commonDialog.dismiss();
                            } else {

                                if (!data.isError()) {

                                    Gson gson = new Gson();
                                    String jsonDist = gson.toJson(data.getDistributor());
                                    CustomSharedPreference.putString(getContext(), CustomSharedPreference.KEY_DISTRIBUTOR, jsonDist);

                                    edName.setText(data.getDistributor().getDistEngName());
                                    edAddress.setText(data.getDistributor().getDistAddEng());
                                    edMobile.setText(data.getDistributor().getDistContactNo());
                                    edPassword.setText(data.getDistributor().getDistPwd());

                                } else {
                                    Toast.makeText(getContext(), "Unable to update!", Toast.LENGTH_SHORT).show();
                                }

                                commonDialog.dismiss();
                            }
                        } else {
                            Toast.makeText(getContext(), "Unable to update!", Toast.LENGTH_SHORT).show();

                            commonDialog.dismiss();
                            Log.e("Data Null : ", "-----------");
                        }
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Unable to update!", Toast.LENGTH_SHORT).show();
                        commonDialog.dismiss();
                        Log.e("Exception : ", "-----------" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    commonDialog.dismiss();
                    Toast.makeText(getContext(), "Unable to update!", Toast.LENGTH_SHORT).show();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(getContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }


}
