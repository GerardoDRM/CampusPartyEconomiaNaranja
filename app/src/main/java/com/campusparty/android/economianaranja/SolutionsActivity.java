package com.campusparty.android.economianaranja;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.campusparty.android.economianaranja.utils.CircleTransform;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SolutionsActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solutions);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageView image = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load("https://media.licdn.com/mpr/mpr/shrinknp_400_400/AAEAAQAAAAAAAAVSAAAAJDc1MWJkMTdkLTYyNzAtNDMxMy1iNDkzLTQ2MjJkNDQxYTllYw.jpg").fit().centerCrop().transform(new CircleTransform()).into(image);
    }
}
