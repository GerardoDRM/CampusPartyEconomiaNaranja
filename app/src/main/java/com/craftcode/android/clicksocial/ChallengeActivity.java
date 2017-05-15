package com.craftcode.android.clicksocial;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.craftcode.android.clicksocial.API.ChallengeApi;
import com.craftcode.android.clicksocial.models.Address;
import com.craftcode.android.clicksocial.models.Challenge;
import com.craftcode.android.clicksocial.utils.GeneralConst;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class ChallengeActivity extends AppCompatActivity {
    private String BASE_URL;
    private ChallengeApi apiService;

    private String idpromo;
    private Challenge mChallenge;

    @Bind(R.id.header_img_poster)
    ImageView mHeaderImg;
    @Bind(R.id.title)
    TextView mTitle;
    @Bind(R.id.description)
    TextView mDescription;
    @Bind(R.id.challenge)
    TextView mChallengeTxt;
    @Bind(R.id.creation)
    TextView mCreation;
    @Bind(R.id.address)
    TextView mAddress;
    @Bind(R.id.likes)
    TextView mLikes;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.appbar)
    AppBarLayout appBarLayout;
    @Bind(R.id.coordinator)
    CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout.setTitle(" ");

        this.BASE_URL = GeneralConst.BASE_URL;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ChallengeApi.class);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            idpromo = arguments.getString(GeneralConst.PROMO_KEY);
            setUp();
        }

    }

    private void setUp() {
        if (mChallenge != null) {
            createView();
        } else {
            getInfo();
        }
    }

    private void getInfo() {

        Call<Challenge> call = apiService.getChallenge(idpromo);
        call.enqueue(new Callback<Challenge>() {

            @Override
            public void onResponse(Response<Challenge> response, Retrofit retrofit) {
                int statusCode = response.code();
                Challenge challenge = response.body();
                Log.d("Error", challenge.getTitle() + "");
                if (challenge == null) return;
                mChallenge = challenge;
                createView();
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("Error", t.getMessage());

                if (!GeneralConst.checkNetwork(getApplicationContext()))
                    GeneralConst.showMessageConnection(getApplicationContext());
            }
        });

    }


    private void createView() {
        Picasso.with(this).load(mChallenge.getImg()).fit().centerCrop().tag(this).into(mHeaderImg);
        mTitle.setText(mChallenge.getTitle());
        mTitle.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "Rosario-Regular.ttf"));

        mDescription.setText(mChallenge.getDescription());
        ArrayList<Address> address = mChallenge.getAddress();
        for(Address a : address) {
            mAddress.setText(a.getState() + "," + a.getCity() + "\n");
        }
        mChallengeTxt.setText(mChallenge.getChallenge());
        mLikes.setText(String.valueOf(mChallenge.getLikes()));

        SimpleDateFormat sds = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        sds.setTimeZone(TimeZone.getDefault());
        long event_date = mChallenge.getCreation_date();
        final Date date_s = new Date(event_date * 1000L);
        mCreation.setText(sds.format(date_s));
    }

}
