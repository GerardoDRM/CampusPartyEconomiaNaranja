package com.campusparty.android.economianaranja;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campusparty.android.economianaranja.adapter.SolutionsListAdapter;
import com.campusparty.android.economianaranja.models.Solutions;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SolutionsFragment extends Fragment {
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;


    public SolutionsFragment() {
        // Required empty public constructor
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
        setUpSolutionsList();
        return rootView;
    }

    /**
     * This method create Dummy list of solutions
     */
    private void setUpSolutionsList() {

// In order to create a dummy data we use a loop
        ArrayList<Solutions> solutions = new ArrayList<Solutions>();
        String[] urls = {
                "http://www.portafolio.co/files/article_main/uploads/2016/02/04/56b3fc9430d7b.jpeg",
                "http://images.hoy.com.py/uploads/41358/1596116_10201597057705239_632663031_o__destacado.jpeg",
                "http://inspercom.org/wp-content/uploads/fgfgfgf1-2y4lkyr04cac85kbzm7z7u.png"
        };

        for (int i=0; i<5; i++) {
            solutions.add(new Solutions("Problematica " + i, "Descripcion", (int)(Math.random() * (100-1) + 1), urls[(int)(Math.random() * (3) + 0)]));
        }
        // Create Adapter in order to create list with fake data
        SolutionsListAdapter adapter = new SolutionsListAdapter(getContext(), solutions);


        // Create recycler view with dynamic height behavior
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        mRecyclerView.setAdapter(adapter);

    }


}
