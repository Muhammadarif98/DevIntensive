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
import com.example.devintensive.data.managers.DataManager;
import com.example.devintensive.utils.ConstantManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private static final String TAG = ConstantManager.TAG_PREFIX + "MainActivity";

    private DataManager mDataManager;

    private int mCurrentEditMode = 0;

    private ImageView mCallImg;
    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private FloatingActionButton mFloatingActionButton;

    private EditText mUserPhone, mUserMail, mUserVk, mUserGit, mUserBio;
    private List<EditText> mUserInfoViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "OnCreate");

        mDataManager = DataManager.getInstance();
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

        mUserInfoViews = new ArrayList<>();
        mUserInfoViews.add(mUserPhone);
        mUserInfoViews.add(mUserMail);
        mUserInfoViews.add(mUserGit);
        mUserInfoViews.add(mUserVk);
        mUserInfoViews.add(mUserBio);


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
        loadUserInfoValue();

        List<String> test = mDataManager.getPreferenceManager().loadUsersProfileData();

        if (savedInstanceState == null) {
//            showSnackbar("активити запускается впервые");
//            showToast("активити запускается впервые");
        } else {
            mCurrentEditMode = savedInstanceState.getInt(ConstantManager.EDIT_MODE_KEY,0);
            changeEditMode(mCurrentEditMode);
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
            for (EditText userValue : mUserInfoViews) {
                userValue.setEnabled(true);
                userValue.setFocusable(true);
                userValue.setFocusableInTouchMode(true);
            }
        }else{
            mFloatingActionButton.setImageResource(R.drawable.ic_baseline_create_24);
            for (EditText userValue : mUserInfoViews) {
                userValue.setEnabled(false);
                userValue.setFocusable(false);
                userValue.setFocusableInTouchMode(false);
                saveUserInfoValue();
            }

        }
    }

    private void loadUserInfoValue() {
        List<String> userData = mDataManager.getPreferenceManager().loadUsersProfileData();
        for (int i = 0; i < userData.size(); i++){
            mUserInfoViews.get(i).setText(userData.get(i));
        }
    }

    private void saveUserInfoValue() {
        List<String> userData = new ArrayList<>();
        for (EditText userFieldViews : mUserInfoViews) {
            userData.add(userFieldViews.getText().toString());
        }
        mDataManager.getPreferenceManager().saveUsersProfileData(userData);
    }

}