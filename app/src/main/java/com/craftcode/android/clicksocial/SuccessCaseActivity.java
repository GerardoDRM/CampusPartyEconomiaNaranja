package com.craftcode.android.clicksocial;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.craftcode.android.clicksocial.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SuccessCaseActivity extends AppCompatActivity {

    @Bind(R.id.img_circle)
    ImageView profile;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.title)
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Picasso.with(this).load("https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAVSAAAAJDc1MWJkMTdkLTYyNzAtNDMxMy1iNDkzLTQ2MjJkNDQxYTllYw.jpg").fit().centerCrop().transform(new CircleTransform()).into(profile);
        name.setTypeface(Typeface.createFromAsset(getResources().getAssets(), "Rosario-Regular.ttf"));

    }
}
