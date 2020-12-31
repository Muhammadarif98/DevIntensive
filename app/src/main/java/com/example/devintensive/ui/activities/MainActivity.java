package com.example.devintensive.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.devintensive.R;
import com.example.devintensive.utils.ConstantManager;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends BaseActivity {

    private static final String TAG = ConstantManager.TAG_PREFIX + "MainActivity";
    private ImageView mCallImg;
    private CoordinatorLayout mCoordinatorLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"OnCreate");

        mCallImg = findViewById(R.id.call_img);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.main_coordinator_container);

        if (savedInstanceState == null){
            showSnackbar("активити запускается впервые");
            showToast("активити запускается впервые");
        }else {
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

    private void showSnackbar(String message){
        Snackbar.make(mCoordinatorLayout,message,Snackbar.LENGTH_LONG).show();
    }

//    private void runWithDelay(){
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//            //TODO: Выполнить с задержкой
//                hideProgress();
//
//            }
//        },5000);
//    }
}