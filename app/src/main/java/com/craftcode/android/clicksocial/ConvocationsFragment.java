package com.craftcode.android.clicksocial;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.craftcode.android.clicksocial.adapter.SupportListAdapter;
import com.craftcode.android.clicksocial.models.Support;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ConvocationsFragment extends Fragment {


    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.fab_opt)
    FloatingActionButton mFab;

    public ConvocationsFragment() {
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
        setUpSupportList();
        // Add button action
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show filters
                BottomSheetDialogFragment bottomSheetDialogFragment = new FilterActivity();
                Bundle bundle = new Bundle();
                bundle.putInt("FILTEROPT", 1);
                bottomSheetDialogFragment.setArguments(bundle);
                bottomSheetDialogFragment.show(getFragmentManager(), bottomSheetDialogFragment.getTag());
            }
        });

        return rootView;
    }

    /**
     * This method create a dummy list of projects
     * support
     */
    private void setUpSupportList() {
        // In order to create a dummy data we use a loop
        ArrayList<Support> supports = new ArrayList<Support>();
        String[] urls = {
                "http://www.youngmarketing.co/wp-content/uploads/2016/04/i_Stock_000052774698_Small-693x325.jpg",
                "https://userscontent2.emaze.com/images/395d1462-3a3e-4fcb-94c5-4917dfefef47/853865f9-647e-45ef-ab38-6862556cac6a.png",
                "http://www.estrategiaynegocios.net/csp/mediapool/sites/dt.common.streams.StreamServer.cls?STREAMOID=IwxOPBGLjsRcJjztt_HDC8$daE2N3K4ZzOUsqbU5sYskQgxe5dUujHkNqAWR2SqKWCsjLu883Ygn4B49Lvm9bPe2QeMKQdVeZmXF$9l$4uCZ8QDXhaHEp3rvzXRJFdy0KqPHLoMevcTLo3h8xh70Y6N_U_CryOsw6FTOdKL_jpQ-&CONTENTTYPE=image/jpeg"
        };
        String[] models = {"Municipal", "Estatal", "Federal"};

        for (int i=0; i<8; i++) {
            supports.add(new Support("Apoyo " + i, models[(int)(Math.random() * (3) + 0)], "Descripcion", "7/11/2016", "7/12/2016", urls[(int)(Math.random() * (3) + 0)]));
        }
        // Create Adapter in order to create list with fake data
        SupportListAdapter adapter = new SupportListAdapter(getContext(), supports);


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
