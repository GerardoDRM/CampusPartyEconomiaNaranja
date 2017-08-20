package com.craftcode.android.clicksocial.API;

import com.craftcode.android.clicksocial.models.Organization;
import com.craftcode.android.clicksocial.models.OrganizationsResults;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by gerardo on 15/05/16.
 */
public interface OrganizationApi {

    @GET("api/v0/organizations")
    Call<OrganizationsResults> getOrganziations();

    @GET("api/v0/organization/{id}")
    Call<Organization> getOrganizatio(@Path("id") String id);

}
