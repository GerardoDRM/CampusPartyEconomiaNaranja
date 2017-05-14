package com.campusparty.android.economianaranja.API;

import com.campusparty.android.economianaranja.models.Challenge;
import com.campusparty.android.economianaranja.models.ChallengeResults;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by gerardo on 15/05/16.
 */
public interface ChallengeApi {

    @GET("api/v0/challenges")
    Call<ChallengeResults> getChallenges();

    @GET("api/v0/challenges/")
    Call<Challenge> getChalllenge(@Query("iduser") long iduser);

}
