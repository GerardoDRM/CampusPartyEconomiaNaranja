package com.campusparty.android.economianaranja.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.campusparty.android.economianaranja.R;

/**
 * Created by gerardo on 28/03/16.
 */
public class GeneralConst {
    public static final String IMAGE_KEY = "IMAGE";
    public static final String SAUCER_KEY = "SAUCER";
    public static final String STORED_INFO = "STORED";
    public static final String PAGE_KEY = "PAGE";
    public static final String CUISINE_KEY = "CUISINE";
    public static final String SORT_KEY = "SORT";
    public static final String LATLON_KEY = "LATLON";
    public static final String RANDOM = "RANDOM";
    public static final String FILTERS_KEY = "FILTERS";
    public static final String TITLE_KEY = "TITLE";
    public static final String DESCRIPTION_KEY = "DESCRIPTION";
    public static final String CODE_INFO = "CODE";
    public static final String BUDGET_MAX_KEY = "MAX_BUDGET";
    public static final String BUDGET_MIN_KEY = "MIN_BUDGET";
    public static final String BEACON_KEY = "BEACON";
    public static final String FROMCARD = "FROMCARD";
    public static String BASE_URL = "http://www.clicksocial.com/";
    public static String PROMO_KEY = "PROMO";
    public static String RESTAURANT_URI = "URI";
    public static String RESTAURANT_CLOSED = "Closed";
    public static String LAUNCH_KEY = "LAUNCH";
    public static String RANGE_KEY = "RANGE";




//    /**
//     * This method update drawer options
//     *
//     * @param check          if session is activated
//     * @param context        we need context to access to activity components
//     * @param navigationView The object which will change
//     * @param user           Textview to display
//     */
//    public static void updateDrawer(boolean check, Context context, NavigationView navigationView, TextView user, TextView points) {
//        SharedPreferences sharedPreferences = getPreferences(context);
//        if (check) {
//            String name = sharedPreferences.getString(context.getString(R.string.user_name), null);
//            user.setText(name);
//            // Navegation Menu
//            navigationView.getMenu().findItem(R.id.nav_add).setVisible(false);
//            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
//            navigationView.getMenu().findItem(R.id.nav_sign_out).setVisible(true);
//
//        } else {
//            // Navegation Menu
//            navigationView.getMenu().findItem(R.id.nav_add).setVisible(true);
//            navigationView.getMenu().findItem(R.id.nav_login).setVisible(true);
//            navigationView.getMenu().findItem(R.id.nav_sign_out).setVisible(false);
//        }
//    }



    public static void createDialog(AppCompatActivity activity, String message) {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(message)
                .setTitle(R.string.dialog_title_error);

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    /**
     *
     * @param context
     * @return a boolean variable
     */
    public static boolean checkNetwork(Context context) {
        if (context == null) return true;
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    public static void showMessageConnection(Context context) {
        if (context == null) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(context.getString(R.string.no_internet))
                .setTitle(R.string.no_internet_title)
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showMessagePermission(Context context, String message, final int code, final Activity activity, final String [] perms) {
        if (context == null) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle(R.string.permission_required)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(activity,
                                perms,
                                code);
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

//    public static void updateLocalPermission(Context context, int permCode) {
//        // Check permission code
//        SharedPreferences sharedPreferences = getPreferences(context);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        // Location services
//        if (permCode == 0) {
//            editor.putBoolean(context.getString(R.string.location_perm), true);
//        } else if (permCode == 1) { // Coarse Location
//            editor.putBoolean(context.getString(R.string.coarse_perm), true);
//        }
//        editor.apply();
//    }

    public static void showMessageSettingPerm(final Context context, String message) {
        if (context == null) return;
        String msg = context.getResources().getString(R.string.permission_never_ask) + " " + message;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(msg)
                .setTitle(R.string.permission_required)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setData(Uri.parse("package:" + context.getPackageName()));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        context.startActivity(intent);

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
