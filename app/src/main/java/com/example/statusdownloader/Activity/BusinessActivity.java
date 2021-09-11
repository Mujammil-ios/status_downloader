package com.example.statusdownloader.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.statusdownloader.Adapter.BusinessTabAdapter;
import com.example.statusdownloader.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class BusinessActivity extends AppCompatActivity {


    Toolbar btoolbar;
    TabLayout btabLayout;
    TabItem bimagetab, bvideotab, bsavedtab;
    ViewPager bviewPager;
    BusinessTabAdapter businessTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);





        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("Whatsapp Business Saver");


        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.bcustom_toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.bfab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("wa://")));
                Intent fabintent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");


                if (fabintent.resolveActivity(getPackageManager())== null) {
                    Toast.makeText(getApplicationContext(), "Whatsapp Not Installed!!!", Toast.LENGTH_SHORT).show();
                }
                startActivity(fabintent);
            }
        });




        btabLayout = (TabLayout) findViewById(R.id.btablayout);
        bimagetab = (TabItem)findViewById(R.id.bimagetab);
        bvideotab = (TabItem)findViewById(R.id.bvideotab);
        bsavedtab = (TabItem)findViewById(R.id.bsavedtab);
        bviewPager = (ViewPager) findViewById(R.id.btabpager);






//        set Adapter

        bviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(btabLayout));

        businessTabAdapter= new BusinessTabAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,btabLayout.getTabCount());
        bviewPager.setAdapter(businessTabAdapter);


        btabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                bviewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}