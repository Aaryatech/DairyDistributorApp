package com.ats.dairydistributorapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ats.dairydistributorapp.Constant.Constants;
import com.ats.dairydistributorapp.R;
import com.ats.dairydistributorapp.fragment.HomeFragment;
import com.ats.dairydistributorapp.fragment.NotificationFragment;
import com.ats.dairydistributorapp.fragment.OrderHistoryFragment;
import com.ats.dairydistributorapp.fragment.ProfileFragment;
import com.ats.dairydistributorapp.fragment.SpecialOrderFragment;
import com.ats.dairydistributorapp.model.Distributor;
import com.ats.dairydistributorapp.util.CustomSharedPreference;
import com.google.gson.Gson;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    NavigationView navigationView;
    private TextView tvMenuHome, tvMenuOrderHistory, tvMenuProfile, tvMenuLanguage, tvMenuLogout,tvMenuSpecialOrder;

    public static TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvTitle = findViewById(R.id.tvTitle);

        tvMenuHome = findViewById(R.id.tvMenuHome);
        tvMenuOrderHistory = findViewById(R.id.tvMenuOrderHistory);
        tvMenuProfile = findViewById(R.id.tvMenuProfile);
        tvMenuLanguage = findViewById(R.id.tvMenuLanguage);
        tvMenuLogout = findViewById(R.id.tvMenuLogout);
        tvMenuSpecialOrder = findViewById(R.id.tvMenuSpecialOrder);

        tvMenuHome.setOnClickListener(this);
        tvMenuOrderHistory.setOnClickListener(this);
        tvMenuProfile.setOnClickListener(this);
        tvMenuLanguage.setOnClickListener(this);
        tvMenuLogout.setOnClickListener(this);
        tvMenuSpecialOrder.setOnClickListener(this);

        Gson gson = new Gson();
        String jsonDist = CustomSharedPreference.getString(this, CustomSharedPreference.KEY_DISTRIBUTOR);
        final Distributor distributor = gson.fromJson(jsonDist, Distributor.class);

        Log.e("Distributor : ", "---------------------------" + distributor);

        if (distributor == null) {
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu);
        toggle.syncState();

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvMobile = findViewById(R.id.tvMobile);
        if (distributor != null) {

            String lang = CustomSharedPreference.getString(HomeActivity.this, CustomSharedPreference.KEY_LANGUAGE);

            int langType = 0;
            if (lang.equalsIgnoreCase("1")) {
                langType = 1;
            } else if (lang.equalsIgnoreCase("2")) {
                langType = 2;
            }

            if (langType <= 1) {
                tvName.setText("" + distributor.getDistEngName());
            } else if (langType == 2) {
                tvName.setText("" + distributor.getDistMarName());
            }
            tvMobile.setText("" + distributor.getDistContactNo());
        }

        if (savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new HomeFragment(), "Home");
            ft.commit();
        }

    }

    @Override
    public void onBackPressed() {

        Fragment home = getSupportFragmentManager().findFragmentByTag("Home");
        Fragment homeFragment = getSupportFragmentManager().findFragmentByTag("HomeFragment");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (home instanceof HomeFragment && home.isVisible() ||
                home instanceof SpecialOrderFragment && home.isVisible()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle(getResources().getString(R.string.str_confirm_action));
            builder.setMessage(getResources().getString(R.string.str_exit_msg));
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();


        } else if (homeFragment instanceof ProfileFragment && homeFragment.isVisible() ||
                homeFragment instanceof NotificationFragment && homeFragment.isVisible() ||
                homeFragment instanceof OrderHistoryFragment && homeFragment.isVisible()) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new HomeFragment(), "Home");
            ft.commit();

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_notification) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new NotificationFragment(), "HomeFragment");
            ft.commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new HomeFragment(), "Home");
            ft.commit();

        } else if (id == R.id.nav_order_history) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new OrderHistoryFragment(), "HomeFragment");
            ft.commit();

        } else if (id == R.id.nav_my_profile) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new ProfileFragment(), "HomeFragment");
            ft.commit();

        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_settings) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle(getResources().getString(R.string.str_language))
                    .setItems(R.array.lauguage, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int pos) {
                            // The 'which' argument contains the index position
                            // of the selected item
                            if (pos == 0) {
                                Constants.yourLanguage(HomeActivity.this, CustomSharedPreference.LANGUAGE_ENG);
                                CustomSharedPreference.putString(HomeActivity.this, CustomSharedPreference.LANGUAGE_ENG, CustomSharedPreference.LANGUAGE_ENG_ID);
                                CustomSharedPreference.putString(HomeActivity.this, CustomSharedPreference.KEY_LANGUAGE, CustomSharedPreference.LANGUAGE_ENG_ID);

                                //setLocale("ta");
                            } else if (pos == 1) {
                                Constants.yourLanguage(HomeActivity.this, CustomSharedPreference.LANGUAGE_MAR);
                                CustomSharedPreference.putString(HomeActivity.this, CustomSharedPreference.LANGUAGE_MAR, CustomSharedPreference.LANGUAGE_MAR_ID);
                                CustomSharedPreference.putString(HomeActivity.this, CustomSharedPreference.KEY_LANGUAGE, CustomSharedPreference.LANGUAGE_MAR_ID);
                                //setLocale("hi");
                            }
                            Intent refresh = new Intent(HomeActivity.this, HomeActivity.class);
                            startActivity(refresh);
                            finish();
                        }
                    });
            builder.create();
            builder.show();

        } else if (id == R.id.nav_logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this, R.style.AlertDialogTheme);
            builder.setTitle(getResources().getString(R.string.menu_logout));
            builder.setMessage(getResources().getString(R.string.str_logout_msg));
            builder.setPositiveButton(getResources().getString(R.string.str_ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // updateUserToken(userId, "");
                    CustomSharedPreference.deletePreference(HomeActivity.this);

                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            });
            builder.setNegativeButton(getResources().getString(R.string.str_cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tvMenuHome) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new HomeFragment(), "Home");
            ft.commit();

        } else if (v.getId() == R.id.tvMenuSpecialOrder) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new SpecialOrderFragment(), "Home");
            ft.commit();

        } else if (v.getId() == R.id.tvMenuOrderHistory) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new OrderHistoryFragment(), "HomeFragment");
            ft.commit();

        } else if (v.getId() == R.id.tvMenuProfile) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, new ProfileFragment(), "HomeFragment");
            ft.commit();

        } else if (v.getId() == R.id.tvMenuLanguage) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setTitle(getResources().getString(R.string.str_language))
                    .setItems(R.array.lauguage, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int pos) {
                            // The 'which' argument contains the index position
                            // of the selected item
                            if (pos == 0) {
                                Constants.yourLanguage(HomeActivity.this, CustomSharedPreference.LANGUAGE_ENG);
                                CustomSharedPreference.putString(HomeActivity.this, CustomSharedPreference.LANGUAGE_ENG, CustomSharedPreference.LANGUAGE_ENG_ID);
                                CustomSharedPreference.putString(HomeActivity.this, CustomSharedPreference.KEY_LANGUAGE, CustomSharedPreference.LANGUAGE_ENG_ID);

                                //setLocale("ta");
                            } else if (pos == 1) {
                                Constants.yourLanguage(HomeActivity.this, CustomSharedPreference.LANGUAGE_MAR);
                                CustomSharedPreference.putString(HomeActivity.this, CustomSharedPreference.LANGUAGE_MAR, CustomSharedPreference.LANGUAGE_MAR_ID);
                                CustomSharedPreference.putString(HomeActivity.this, CustomSharedPreference.KEY_LANGUAGE, CustomSharedPreference.LANGUAGE_MAR_ID);
                                //setLocale("hi");
                            }
                            Intent refresh = new Intent(HomeActivity.this, HomeActivity.class);
                            startActivity(refresh);
                            finish();
                        }
                    });
            builder.create();
            builder.show();

        } else if (v.getId() == R.id.tvMenuLogout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this, R.style.AlertDialogTheme);
            builder.setTitle(getResources().getString(R.string.menu_logout));
            builder.setMessage(getResources().getString(R.string.str_logout_msg));
            builder.setPositiveButton(getResources().getString(R.string.str_ok), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // updateUserToken(userId, "");
                    CustomSharedPreference.deletePreference(HomeActivity.this);

                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            });
            builder.setNegativeButton(getResources().getString(R.string.str_cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
