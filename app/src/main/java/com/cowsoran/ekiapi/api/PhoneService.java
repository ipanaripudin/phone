package com.cowsoran.ekiapi.api;

import com.cowsoran.ekiapi.model.Phone;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PhoneService {

    @GET("/pones/{id}")
    Call<Phone> getPhoneById(@Path("id") int id);

    @POST("/pones/")
    Call<Phone> createPhone(@Body Phone phone);

    @PUT("/pones/{id}")
    Call<Phone> updatePhone(@Path("id") int id, @Body Phone phone);

    @DELETE("/pones/{id}")
    Call<Void> deletePhone(@Path("id") int id);
}
