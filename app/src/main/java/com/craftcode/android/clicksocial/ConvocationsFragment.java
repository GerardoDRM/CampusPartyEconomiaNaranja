package com.craftcode.android.clicksocial;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.craftcode.android.clicksocial.API.ConvocationsApi;
import com.craftcode.android.clicksocial.adapter.ConvocationsListAdapter;
import com.craftcode.android.clicksocial.models.Convocation;
import com.craftcode.android.clicksocial.models.ConvocationResults;
import com.craftcode.android.clicksocial.utils.GeneralConst;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class ConvocationsFragment extends Fragment {


    private String BASE_URL;
    private ConvocationsApi apiService;
    private ConvocationsListAdapter adapter;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ArrayList<Convocation> mConvocations;

    public ConvocationsFragment() {
        // Required empty public constructor
        this.BASE_URL = GeneralConst.BASE_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ConvocationsApi.class);
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

        // Create Dummy Data on RecyvlerView
        setUpSupportList();
        return rootView;
    }

    /**
     * This method create a dummy list of projects
     * support
     */
    private void setUpSupportList() {
        // In order to create a dummy data we use a loop
// Create Adapter in order to create list with fake data
        adapter = new ConvocationsListAdapter(getContext(), new ArrayList<Convocation>());

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

        if (mConvocations == null) {
            mConvocations = new ArrayList<Convocation>();
            getConvocations();
        }

    }

    private void getConvocations() {
        Call<ConvocationResults> call = apiService.getConvocations();
        call.enqueue(new Callback<ConvocationResults>() {

            @Override
            public void onResponse(Response<ConvocationResults> response, Retrofit retrofit) {
                int statusCode = response.code();
                ConvocationResults convovations = response.body();
                if (convovations == null) return;
                mConvocations.addAll(convovations.getResults());
                ArrayList<Convocation> tmpPromo = new ArrayList<Convocation>(mConvocations);
                adapter.refill(tmpPromo);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("ERORRR", t.getMessage());
                if (!GeneralConst.checkNetwork(getContext()))
                    GeneralConst.showMessageConnection(getContext());
            }
        });

    }

}
