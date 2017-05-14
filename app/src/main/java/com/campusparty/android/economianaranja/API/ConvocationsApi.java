package com.campusparty.android.economianaranja.API;

import com.campusparty.android.economianaranja.models.Convocation;
import com.campusparty.android.economianaranja.models.ConvocationResults;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by gerardo on 15/05/16.
 */
public interface ConvocationsApi {

    @GET("api/v0/challenges")
    Call<ConvocationResults> getConvocations();

    @GET("api/v0/challenges/")
    Call<Convocation> getConvocation(@Query("iduser") long iduser);

}
