package com.craftcode.android.clicksocial.API;

import com.craftcode.android.clicksocial.models.Challenge;
import com.craftcode.android.clicksocial.models.ChallengeResults;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by gerardo on 15/05/16.
 */
public interface ChallengeApi {

    @GET("api/v0/challenges")
    Call<ChallengeResults> getChallenges();

    @GET("api/v0/challenges/{id}")
    Call<Challenge> getChallenge(@Path("id") String id);

}
