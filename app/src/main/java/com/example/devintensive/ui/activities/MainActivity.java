package com.example.devintensive.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.devintensive.R;
import com.example.devintensive.utils.ConstantManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private int mCurrentEditMode = 0;
    private static final String TAG = ConstantManager.TAG_PREFIX + "MainActivity";
    private ImageView mCallImg;
    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private FloatingActionButton mFloatingActionButton;
    private EditText mUserPhone, mUserMail, mUserVk, mUserGit, mUserBio;
    private List<EditText> mUserInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "OnCreate");

        mCallImg = findViewById(R.id.call_img);
        mCoordinatorLayout = findViewById(R.id.main_coordinator_container);
        mToolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.navigation_drawer);
        mFloatingActionButton = findViewById(R.id.floatAction);
        mUserPhone = findViewById(R.id.phone);
        mUserMail = findViewById(R.id.email_et);
        mUserGit = findViewById(R.id.github);
        mUserVk = findViewById(R.id.vk);
        mUserBio = findViewById(R.id.osebe);

        mUserInfo = new ArrayList<>();
        mUserInfo.add(mUserPhone);
        mUserInfo.add(mUserMail);
        mUserInfo.add(mUserGit);
        mUserInfo.add(mUserVk);
        mUserInfo.add(mUserBio);


        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSnackbar("click");
                if (mCurrentEditMode==0){

                    changeEditMode(1);
                    mCurrentEditMode = 1;
                }else {
                    changeEditMode(0);
                    mCurrentEditMode = 0;
                }
            }
        });
        setupToolBar();
        setupDrawer();



        if (savedInstanceState == null) {
            showSnackbar("активити запускается впервые");
            showToast("активити запускается впервые");
        } else {
            mCurrentEditMode = savedInstanceState.getInt(ConstantManager.EDIT_MODE_KEY,0);
            changeEditMode(mCurrentEditMode);
            showSnackbar("активити уже создавалось");
            showToast("активити уже создавалось");
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
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ConstantManager.EDIT_MODE_KEY, mCurrentEditMode);
    }

    private void showSnackbar(String message) {
        Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    private void setupToolBar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupDrawer() {
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

    private void changeEditMode(int mode) {
        if (mode == 1) {
            mFloatingActionButton.setImageResource(R.drawable.ic_baseline_done_24);
            for (EditText userValue : mUserInfo) {
                userValue.setEnabled(true);
                userValue.setFocusable(true);
                userValue.setFocusableInTouchMode(true);
            }
        }else{
            mFloatingActionButton.setImageResource(R.drawable.ic_baseline_create_24);
            for (EditText userValue : mUserInfo) {
                userValue.setEnabled(false);
                userValue.setFocusable(false);
                userValue.setFocusableInTouchMode(false);
            }

        }
    }

    private void loadUserInfoValue() {

    }

    private void saveUserInfoValue() {

    }

}