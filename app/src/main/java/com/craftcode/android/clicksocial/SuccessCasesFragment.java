package com.craftcode.android.clicksocial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.craftcode.android.clicksocial.API.SuccessCases;
import com.craftcode.android.clicksocial.adapter.SuccessCasesAdapter;
import com.craftcode.android.clicksocial.models.SuccessCase;
import com.craftcode.android.clicksocial.models.SuccessCaseResults;
import com.craftcode.android.clicksocial.utils.GeneralConst;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class SuccessCasesFragment extends Fragment {


    private String BASE_URL;
    private SuccessCases apiService;
    private SuccessCasesAdapter adapter;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ArrayList<SuccessCase> mCases;


    public SuccessCasesFragment() {
        // Required empty public constructor
        this.BASE_URL = GeneralConst.BASE_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(SuccessCases.class);

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

        // Create Data on RecyvlerView
        setUpRecyclerView();
        return rootView;
    }


    /**
     * Method void which create a recycler view
     * with users dummy data
     */
    private void setUpRecyclerView() {
        // Create Adapter in order to create list with fake data
        adapter = new SuccessCasesAdapter(getContext(), new ArrayList<SuccessCase>(), getActivity());

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

        if (mCases == null) {
            mCases = new ArrayList<SuccessCase>();
            Log.d("ERROR", "ERROR");
            getSuccessCases();
        }


    }



    private void getSuccessCases() {
        Call<SuccessCaseResults> call = apiService.getSuccessCases();
        call.enqueue(new Callback<SuccessCaseResults>() {

            @Override
            public void onResponse(Response<SuccessCaseResults> response, Retrofit retrofit) {
                int statusCode = response.code();
                SuccessCaseResults mcases = response.body();
                if (mcases == null) return;
                mCases.addAll(mcases.getResults());
                ArrayList<SuccessCase> tmpPromo = new ArrayList<SuccessCase>(mCases);
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
