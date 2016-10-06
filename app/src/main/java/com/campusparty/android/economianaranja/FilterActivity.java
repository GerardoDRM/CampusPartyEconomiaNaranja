package com.campusparty.android.economianaranja;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FilterActivity extends BottomSheetDialogFragment {

    @Bind(R.id.user_spinner)
    Spinner userSpinner;
    @Bind(R.id.area_spinner)
    Spinner areaSpinner;
    @Bind(R.id.filter_one)
    TextView mFilterOne;
    @Bind(R.id.filter_two)
    TextView mFilterTwo;

    private int filterOpt = 0;

    public FilterActivity() {
    }

    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        final View contentView = View.inflate(getContext(), R.layout.activity_filter, null);
        ButterKnife.bind(this, contentView);

        Bundle bundle = this.getArguments();
        filterOpt = bundle.getInt("FILTEROPT", 0);

        if (filterOpt == 0) {
            mFilterOne.setText("Por tipo de usuario");
            mFilterTwo.setText("Por área");
            addSpinnerData(R.array.users_array, R.array.area_array);
        } else if (filterOpt == 1) {
            mFilterOne.setText("Por tipo de ámbitos");
            mFilterTwo.setText("Por área");
            addSpinnerData(R.array.model_array, R.array.area_array);
        } else if (filterOpt == 2) {
            mFilterOne.setText("Por área");
            addSpinnerData(R.array.area_array, -1);
        }


        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();

        if( behavior != null && behavior instanceof BottomSheetBehavior ) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(bottomSheetCallback);
        }
    }

    private void addSpinnerData(int array_one, int array_two) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
              array_one , android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        userSpinner.setAdapter(adapter);
        if (array_two != -1) {

            ArrayAdapter<CharSequence> areaAdapter = ArrayAdapter.createFromResource(getContext(),
                    array_two , android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // Apply the adapter to the spinner
            areaSpinner.setAdapter(areaAdapter);
        } else {
            mFilterTwo.setVisibility(View.INVISIBLE);
            areaSpinner.setVisibility(View.INVISIBLE);
        }
    }
}
