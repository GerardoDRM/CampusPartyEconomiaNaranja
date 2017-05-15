package com.craftcode.android.clicksocial;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.craftcode.android.clicksocial.adapter.ViewPagerMenu;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @Bind(R.id.container)
    ViewPager mViewPager;
    @Bind(R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.header_img_poster)
    KenBurnsView mHeaderImage;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsing;
    @Bind(R.id.appbar)
    AppBarLayout mAppBar;
    @Bind(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        // Create a drawer layout
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();

//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        View header = navigationView.getHeaderView(0);
//        ImageView image = (ImageView) header.findViewById(R.id.imageProfile);
//        Picasso.with(this).load("https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAVSAAAAJDc1MWJkMTdkLTYyNzAtNDMxMy1iNDkzLTQ2MjJkNDQxYTllYw.jpg").fit().centerCrop().transform(new CircleTransform()).into(image);

        // Tab View which has 3 sections
        String[] mTitles = {"Historias", "Convocatorias", "Retos"};
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        mCollapsing.setScrimsShown(false);
        ViewPagerMenu mSectionsPagerAdapter = new ViewPagerMenu(getSupportFragmentManager(), mTitles, 3);

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        // Fancy effect on imageview =) using KenBurns effect
        AccelerateDecelerateInterpolator ACCELERATE_DECELERATE = new AccelerateDecelerateInterpolator();

        RandomTransitionGenerator generator = new RandomTransitionGenerator(9000, ACCELERATE_DECELERATE);
        mHeaderImage.setTransitionGenerator(generator);


    }

//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//            Intent i = new Intent(this, ConnectionsAcitvity.class);
//            startActivity(i);
//        } else if (id == R.id.nav_gallery) {
//            Intent i = new Intent(this, MessagesListActivity.class);
//            startActivity(i);
//        } else if (id == R.id.nav_slideshow) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}
