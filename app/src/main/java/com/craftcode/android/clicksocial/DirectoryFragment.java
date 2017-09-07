package com.craftcode.android.clicksocial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.craftcode.android.clicksocial.API.DirectoryApi;
import com.craftcode.android.clicksocial.API.OrganizationApi;
import com.craftcode.android.clicksocial.adapter.DirectoryListAdapter;
import com.craftcode.android.clicksocial.adapter.OrganizationListAdapter;
import com.craftcode.android.clicksocial.models.Directory;
import com.craftcode.android.clicksocial.models.DirectoryResults;
import com.craftcode.android.clicksocial.models.Organization;
import com.craftcode.android.clicksocial.models.OrganizationsResults;
import com.craftcode.android.clicksocial.utils.GeneralConst;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class DirectoryFragment extends Fragment {
    private String BASE_URL;
    private DirectoryApi apiService;
    private DirectoryListAdapter adapter;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ArrayList<Directory> mOrganizations;


    public DirectoryFragment() {
        // Required empty public constructor
        this.BASE_URL = GeneralConst.BASE_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(DirectoryApi.class);

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
         adapter = new DirectoryListAdapter(getContext(), new ArrayList<Directory>());

        // Create recycler view with dynamic height behavior
        final StaggeredGridLayoutManager grid =
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(grid);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mRecyclerView.setLayoutManager(grid);

        mRecyclerView.setAdapter(adapter);

        if (mOrganizations == null) {
            mOrganizations = new ArrayList<Directory>();
            getChallenges();
        }

    }

    private void getChallenges() {
        Call<DirectoryResults> call = apiService.getDirectory();
        call.enqueue(new Callback<DirectoryResults>() {

            @Override
            public void onResponse(Response<DirectoryResults> response, Retrofit retrofit) {
                int statusCode = response.code();
                DirectoryResults organizations = response.body();
                if (organizations == null) return;
                mOrganizations.addAll(organizations.getResults());
                ArrayList<Directory> tmpPromo = new ArrayList<Directory>(mOrganizations);
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
