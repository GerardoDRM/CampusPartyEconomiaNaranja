package com.craftcode.android.clicksocial.API;

import com.craftcode.android.clicksocial.models.SuccessCase;
import com.craftcode.android.clicksocial.models.SuccessCaseResults;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by gerardo on 15/05/16.
 */
public interface SuccessCases {

    @GET("api/v0/stories")
    Call<SuccessCaseResults> getSuccessCases();

    @GET("api/v0/stories/{id}")
    Call<SuccessCase> getSuccessCase(@Path("id") String id);

}
