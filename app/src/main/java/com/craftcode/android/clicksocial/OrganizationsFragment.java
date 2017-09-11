package com.craftcode.android.clicksocial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.craftcode.android.clicksocial.API.OrganizationApi;
import com.craftcode.android.clicksocial.adapter.OrganizationListAdapter;
import com.craftcode.android.clicksocial.models.Organization;
import com.craftcode.android.clicksocial.models.OrganizationsResults;
import com.craftcode.android.clicksocial.utils.GeneralConst;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class OrganizationsFragment extends Fragment {
    private String BASE_URL;
    private OrganizationApi apiService;
    private OrganizationListAdapter adapter;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ArrayList<Organization> mOrganizations;


    public OrganizationsFragment() {
        // Required empty public constructor
        this.BASE_URL = GeneralConst.BASE_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(OrganizationApi.class);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, rootView);

        // Create RecyclerView
        setUpData();
        return rootView;
    }

    /**
     * This method create Dummy list of solutions
     */
    private void setUpData() {

        // Create Adapter in order to create list with fake data
         adapter = new OrganizationListAdapter(getContext(), new ArrayList<Organization>());

        // Create recycler view with dynamic height behavior
        final StaggeredGridLayoutManager grid =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(grid);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mRecyclerView.setLayoutManager(grid);

        mRecyclerView.setAdapter(adapter);

        if (mOrganizations == null) {
            mOrganizations = new ArrayList<Organization>();
            getChallenges();
        }

    }

    private void getChallenges() {
        HashMap searchFilters = new HashMap<String, Serializable>();
        searchFilters.put("filters", 0);

        Call<OrganizationsResults> call = apiService.getOrganziations(searchFilters);
        call.enqueue(new Callback<OrganizationsResults>() {

            @Override
            public void onResponse(Response<OrganizationsResults> response, Retrofit retrofit) {
                int statusCode = response.code();
                OrganizationsResults organizations = response.body();
                if (organizations == null) return;
                mOrganizations.addAll(organizations.getResults());
                ArrayList<Organization> tmpPromo = new ArrayList<Organization>(mOrganizations);
                adapter.refill(tmpPromo);
            }

            @Override
            public void onFailure(Throwable t) {
                if (!GeneralConst.checkNetwork(getContext()))
                    GeneralConst.showMessageConnection(getContext());
            }
        });

    }


}
