package com.example.yemensolutiontest;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN = 2000;
    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        lottieAnimationView=findViewById(R.id.animation_view);
//
//        ValueAnimator animator=ValueAnimator.ofFloat(0f,1f);
//        animator.addUpdateListener(valueAnimator -> {
//            lottieAnimationView.setProgress((Float) valueAnimator.getAnimatedValue());
//        });
//        animator.start();

        TextView wallpapers=findViewById(R.id.wallpaperTxt);
        TextView wallpapers2=findViewById(R.id.explore);
        // To add rotate animation
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.translate_anim);
        wallpapers.startAnimation(animation);

        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_animation);
        wallpapers2.startAnimation(animation2);

        openMain();
    }


    public void openMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}