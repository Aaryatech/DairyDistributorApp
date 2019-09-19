package com.ats.dairydistributorapp.interfaces;

import com.ats.dairydistributorapp.model.Category;
import com.ats.dairydistributorapp.model.CategoryAndItems;
import com.ats.dairydistributorapp.model.CategoryItems;
import com.ats.dairydistributorapp.model.Distributor;
import com.ats.dairydistributorapp.model.ErrorMessage;
import com.ats.dairydistributorapp.model.GetOrder;
import com.ats.dairydistributorapp.model.LoginResponse;
import com.ats.dairydistributorapp.model.Notification;
import com.ats.dairydistributorapp.model.Order;
import com.ats.dairydistributorapp.model.Route;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyInterface {

    @GET("getAllCatByIsUsed")
    Call<ArrayList<Category>> getAllCategories();

    @POST("loginResponseDist")
    Call<LoginResponse> distributorLogin(@Query("distContactNo") String distContactNo, @Query("distPwd") String distPwd);

    @GET("getAllRouteByIsUsed")
    Call<ArrayList<Route>> getAllRoute();

    @POST("getAllCatwiseItemListByCatId")
    Call<ArrayList<CategoryItems>> getItemsByCategory(@Query("catId") int catId);

    @POST("getAllCatwiseItemListByDistId")
    Call<ArrayList<CategoryItems>> getItemsByDistributor(@Query("distId") int distId);

    @POST("saveOrderBySetting")
    Call<ErrorMessage> saveOrder(@Body Order Order);

    @POST("getDistributorByDistId")
    Call<Distributor> getDistributorById(@Query("distId") int distId);

    @POST("updatePasswordDist")
    Call<LoginResponse> updateDistributorPassword(@Query("distId") int distId, @Query("distPwd") String distPwd);

    @POST("getOrderHistoryDistwise")
    Call<ArrayList<GetOrder>> getOrderHistory(@Query("date") String date, @Query("distId") int distId);

    @POST("getNotiByDistId")
    Call<ArrayList<Notification>> getNotifiacation(@Query("notifiTo") int notifiTo);

    @POST("getAllCatwiseItemListByDistId")
    Call<CategoryAndItems> getAllCatAndItems(@Query("distId") int distId, @Query("hubId") int hubId);

    @POST("updateDistToken")
    Call<LoginResponse> updateToken(@Query("distId") int distId, @Query("token") String token);

    @POST("getOrderByDistIdStausAndType")
    Call<ArrayList<GetOrder>> getOrderHistoryByStatus(@Query("orderStatus") int orderStatus, @Query("distId") int distId, @Query("date") String date);

    @POST("getAllCatwiseItemListByDistIdAndOrderType")
    Call<CategoryAndItems> getAllCatAndItemsByOrderType(@Query("distId") int distId, @Query("hubId") int hubId,@Query("orderType") int orderType);

}
