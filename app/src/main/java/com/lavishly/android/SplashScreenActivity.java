package com.lavishly.android;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.facebook.shimmer.ShimmerFrameLayout;

public class SplashScreenActivity extends AppCompatActivity {

    private static int splashTimeOut = 4000;
    private ImageView SplashScreenImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ShimmerFrameLayout containerSplashScreen = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container_activity_splash_screen);
        SplashScreenImage = (ImageView) findViewById(R.id.ivSplashScreen);
        Animation SplashScreenAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        SplashScreenImage.startAnimation(SplashScreenAnimation);
        containerSplashScreen.startShimmer();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent openLoginActivityFromSplashScreen = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(openLoginActivityFromSplashScreen);
                finish();

            }
        },splashTimeOut);
    }
}
