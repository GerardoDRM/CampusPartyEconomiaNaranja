package com.campusparty.android.economianaranja;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.campusparty.android.economianaranja.adapter.SearchNetAdapter;
import com.campusparty.android.economianaranja.models.User;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SearchFragment extends Fragment {


    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.fab_opt)
    FloatingActionButton mFab;

    public SearchFragment() {
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
        setUpRecyclerView();
        // Add button action
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show filters
                BottomSheetDialogFragment bottomSheetDialogFragment = new FilterActivity();
                Bundle bundle = new Bundle();
                bundle.putInt("FILTEROPT", 0);
                bottomSheetDialogFragment.setArguments(bundle);
                bottomSheetDialogFragment.show(getFragmentManager(), bottomSheetDialogFragment.getTag());
            }
        });

        return rootView;
    }


    /**
     * Method void which create a recycler view
     * with users dummy data
     */
    private void setUpRecyclerView() {
        // In order to create a dummy user we use a loop
        ArrayList<User> users = new ArrayList<User>();
        String[] urls = {
                "https://odesk-blog-content.s3.amazonaws.com/uploads/2014/10/02123010/profile-photo_friendly.jpg",
                "https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAVSAAAAJDc1MWJkMTdkLTYyNzAtNDMxMy1iNDkzLTQ2MjJkNDQxYTllYw.jpg",
                "http://www.yourinternalgps.com/images/RandyColor.jpeg"
        };
        String[] models = {"Empresario", "Emprendedor", "Organizacion"};

        for (int i=0; i<10; i++) {
            users.add(new User("Person " + i, models[(int)(Math.random() * (3) + 0)], "Profesion", urls[(int)(Math.random() * (3) + 0)]));
        }
        // Create Adapter in order to create list with fake data
        SearchNetAdapter adapter = new SearchNetAdapter(getContext(), users, getActivity());


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


    }

}
