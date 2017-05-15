package com.craftcode.android.clicksocial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.craftcode.android.clicksocial.API.ChallengeApi;
import com.craftcode.android.clicksocial.adapter.ChallengesListAdapter;
import com.craftcode.android.clicksocial.models.Challenge;
import com.craftcode.android.clicksocial.models.ChallengeResults;
import com.craftcode.android.clicksocial.utils.GeneralConst;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class ChallengesFragment extends Fragment {
    private String BASE_URL;
    private ChallengeApi apiService;
    private ChallengesListAdapter adapter;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ArrayList<Challenge> mChallenges;


    public ChallengesFragment() {
        // Required empty public constructor
        this.BASE_URL = GeneralConst.BASE_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ChallengeApi.class);

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
         adapter = new ChallengesListAdapter(getContext(), new ArrayList<Challenge>());

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

        if (mChallenges == null) {
            mChallenges = new ArrayList<Challenge>();
            getChallenges();
        }

    }

    private void getChallenges() {
        Call<ChallengeResults> call = apiService.getChallenges();
        call.enqueue(new Callback<ChallengeResults>() {

            @Override
            public void onResponse(Response<ChallengeResults> response, Retrofit retrofit) {
                int statusCode = response.code();
                ChallengeResults challenges = response.body();
                if (challenges == null) return;
                mChallenges.addAll(challenges.getResults());
                ArrayList<Challenge> tmpPromo = new ArrayList<Challenge>(mChallenges);
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
