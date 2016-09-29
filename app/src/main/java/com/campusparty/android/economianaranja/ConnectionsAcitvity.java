package com.campusparty.android.economianaranja;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.campusparty.android.economianaranja.adapter.SearchNetAdapter;
import com.campusparty.android.economianaranja.models.User;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ConnectionsAcitvity extends AppCompatActivity {
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connections_acitvity);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create Dummy Data on RecyvlerView
        setUpRecyclerView();

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

        for (int i=0; i<4; i++) {
            users.add(new User("Person " + i, models[(int)(Math.random() * (3) + 0)], "Profesion", urls[(int)(Math.random() * (3) + 0)]));
        }
        // Create Adapter in order to create list with fake data
        SearchNetAdapter adapter = new SearchNetAdapter(this, users, this);


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
