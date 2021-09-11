package com.example.statusdownloader.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.example.statusdownloader.MainActivity;
import com.example.statusdownloader.R;

public class SplashActivity extends AppCompatActivity {


    final static int SPLASH_SCREEN =3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        //        build version inbuild aata he ye is liye hota he ki app apne aap user ki sdk ko check karta he use bata na nahi padta he


//jelly  bin he

        if (Build.VERSION.SDK_INT <16 ){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else
        {
            View decorView = getWindow().getDecorView();

            int uiOption = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOption);

        }

//        whatsapp install he kya nahi wo check karne ke liye

        if (checkInstallation("com.whatsapp")){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkPermission();
                }
            }, SPLASH_SCREEN);

        }
    }



    private void checkPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (getApplicationContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
            else
            {

//                yaha code jump hota he
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }
        else
        {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1: {

                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    finish();
                }
                else
                {
                    checkPermission();
                }
                return;
            }
        }
    }

    private boolean checkInstallation(String uri) {

        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri,PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e){
            app_installed= true;
        }
        return app_installed;
    }

}





















