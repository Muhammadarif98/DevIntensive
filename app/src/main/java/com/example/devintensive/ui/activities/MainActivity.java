package com.example.devintensive.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.devintensive.R;
import com.example.devintensive.utils.ConstantManager;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends BaseActivity {

    private static final String TAG = ConstantManager.TAG_PREFIX + "MainActivity";
    private ImageView mCallImg;
    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"OnCreate");

        mCallImg = findViewById(R.id.call_img);
        mCoordinatorLayout = findViewById(R.id.main_coordinator_container);
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.navigation_drawer);
        setupToolBar();
        setupDrawer();

        if (savedInstanceState == null){
//            showSnackbar("активити запускается впервые");
//            showToast("активити запускается впервые");
        }else {
//            showSnackbar("активити уже создавалось");
//            showToast("активити уже создавалось");
        }

        mCallImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showProgress();
//                runWithDelay();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSnackbar(String message){
        Snackbar.make(mCoordinatorLayout,message,Snackbar.LENGTH_LONG).show();
    }
    private void setupToolBar(){
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    private void setupDrawer(){
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                showSnackbar(item.getTitle().toString());
                item.setChecked(true);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

}