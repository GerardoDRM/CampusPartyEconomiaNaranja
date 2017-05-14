package com.campusparty.android.economianaranja.API;

import com.campusparty.android.economianaranja.models.SuccessCase;
import com.campusparty.android.economianaranja.models.SuccessCaseResults;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by gerardo on 15/05/16.
 */
public interface SuccessCases {

    @GET("api/v0/challenges")
    Call<SuccessCaseResults> getSuccessCases();

    @GET("api/v0/challenges/")
    Call<SuccessCase> getSuccessCase(@Query("iduser") long iduser);

}
