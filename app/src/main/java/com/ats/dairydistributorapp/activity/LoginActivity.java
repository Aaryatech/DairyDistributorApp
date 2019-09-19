package com.ats.dairydistributorapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ats.dairydistributorapp.Constant.Constants;
import com.ats.dairydistributorapp.R;
import com.ats.dairydistributorapp.adapter.ItemAdapter;
import com.ats.dairydistributorapp.fcm.SharedPrefManager;
import com.ats.dairydistributorapp.fragment.HomeFragment;
import com.ats.dairydistributorapp.model.CategoryAndItems;
import com.ats.dairydistributorapp.model.GetAllItemsForRegOrder;
import com.ats.dairydistributorapp.model.GetCatItemList;
import com.ats.dairydistributorapp.model.LoginResponse;
import com.ats.dairydistributorapp.util.CommonDialog;
import com.ats.dairydistributorapp.util.CustomSharedPreference;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edMobile, edPassword;
    private Button btnLogin;

    String strMobile, strPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edMobile = findViewById(R.id.edMobile);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        CustomSharedPreference.putString(LoginActivity.this, CustomSharedPreference.KEY_LANGUAGE, CustomSharedPreference.LANGUAGE_ENG_ID);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            strMobile = edMobile.getText().toString();
            strPassword = edPassword.getText().toString();

            if (strMobile.isEmpty()) {
                edMobile.setError("required");
            } else if (strMobile.length() != 10) {
                edMobile.setError("required 10 digits");
            } else if (strPassword.isEmpty()) {
                edMobile.setError(null);
                edPassword.setError("required");
            } else {
                edMobile.setError(null);
                edPassword.setError(null);

                loginDistributor(strMobile, strPassword);
            }

        }
    }


    public void loginDistributor(String mobile, String pass) {

        if (Constants.isOnline(this)) {
            final CommonDialog commonDialog = new CommonDialog(this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<LoginResponse> listCall = Constants.myInterface.distributorLogin(mobile, pass);
            listCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("Login Data : ", "------------" + response.body());

                            LoginResponse data = response.body();
                            if (data == null) {
                                Toast.makeText(LoginActivity.this, "Unable to login!", Toast.LENGTH_SHORT).show();
                                commonDialog.dismiss();
                            } else {

                                if (!data.isError()) {

                                    Gson gson = new Gson();
                                    String jsonDist = gson.toJson(data.getDistributor());
                                    CustomSharedPreference.putString(LoginActivity.this, CustomSharedPreference.KEY_DISTRIBUTOR, jsonDist);

                                    String token = SharedPrefManager.getmInstance(LoginActivity.this).getDeviceToken();
                                    Log.e("Token : ", "---------" + token);

                                    updateToken(data.getDistributor().getDistId(), token);

                                } else {
                                    Toast.makeText(LoginActivity.this, "Unable to login!", Toast.LENGTH_SHORT).show();
                                }

                                commonDialog.dismiss();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Unable to login!", Toast.LENGTH_SHORT).show();

                            commonDialog.dismiss();
                            Log.e("Data Null : ", "-----------");
                        }
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "Unable to login!", Toast.LENGTH_SHORT).show();
                        commonDialog.dismiss();
                        Log.e("Exception : ", "-----------" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    commonDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Unable to login!", Toast.LENGTH_SHORT).show();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(this, "No Internet Connection!", Toast.LENGTH_SHORT).show();
        }
    }


    public void updateToken(int distId, String token) {

        Log.e("PARAMETER : ", "DIST_ID : -------------- " + distId + " ------------------------- TOKEN : " + token);

        if (Constants.isOnline(LoginActivity.this)) {
            final CommonDialog commonDialog = new CommonDialog(LoginActivity.this, "Loading", "Please Wait...");
            commonDialog.show();

            Call<LoginResponse> listCall = Constants.myInterface.updateToken(distId, token);
            listCall.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    try {
                        if (response.body() != null) {

                            Log.e("Update Token Data : ", "------------" + response.body());

                            LoginResponse data = response.body();
                            if (data == null) {
                                Toast.makeText(LoginActivity.this, "Unable to login!", Toast.LENGTH_SHORT).show();
                                commonDialog.dismiss();
                            } else {

                                if (!data.isError()) {
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    finish();

                                } else {
                                    Toast.makeText(LoginActivity.this, "Unable to login!", Toast.LENGTH_SHORT).show();
                                }

                                commonDialog.dismiss();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Unable to login!", Toast.LENGTH_SHORT).show();
                            commonDialog.dismiss();
                            Log.e("Data Null : ", "-----------");
                        }
                    } catch (Exception e) {
                        Toast.makeText(LoginActivity.this, "Unable to login!", Toast.LENGTH_SHORT).show();
                        commonDialog.dismiss();
                        Log.e("Exception : ", "-----------" + e.getMessage());
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Unable to login!", Toast.LENGTH_SHORT).show();
                    commonDialog.dismiss();
                    Log.e("onFailure : ", "-----------" + t.getMessage());
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(LoginActivity.this, "No Internet Connection !", Toast.LENGTH_SHORT).show();
        }
    }


}
