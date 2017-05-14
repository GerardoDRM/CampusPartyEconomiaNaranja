package com.craftcode.android.clicksocial.API;

import com.craftcode.android.clicksocial.models.Convocation;
import com.craftcode.android.clicksocial.models.ConvocationResults;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by gerardo on 15/05/16.
 */
public interface ConvocationsApi {

    @GET("api/v0/challenges")
    Call<ConvocationResults> getConvocations();

    @GET("api/v0/challenges/{id}")
    Call<Convocation> getConvocation(@Path("id") String id);

}
