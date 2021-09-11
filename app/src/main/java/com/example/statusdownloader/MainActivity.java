package com.example.statusdownloader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.statusdownloader.Activity.BusinessActivity;
import com.example.statusdownloader.Adapter.TabAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {



    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    TabLayout tabLayout;
    TabItem imagetab,videotab,savedtab;
    ViewPager viewPager;

    TabAdapter tabAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("Status Downloader");



        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);




        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("wa://")));
                Intent fabintent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                Toast.makeText(getApplicationContext(), "View Status First for download", Toast.LENGTH_SHORT).show();


                if (fabintent.resolveActivity(getPackageManager())== null) {
                    Toast.makeText(getApplicationContext(), "Whatsapp Not Installed!!!", Toast.LENGTH_SHORT).show();
                }
                startActivity(fabintent);
            }
        });





        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        imagetab = (TabItem)findViewById(R.id.imagetab);
        videotab = (TabItem)findViewById(R.id.videotab);
        savedtab = (TabItem)findViewById(R.id.savedtab);
        viewPager = (ViewPager) findViewById(R.id.tabpager);

//        define navigationview components like drawerlayout,nav toggle (search toggle on google)

        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        navigationView =(NavigationView) findViewById(R.id.nav);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


//        this code blongs to tab adapter and its defining tabcount and view pager

        tabAdapter = new TabAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,tabLayout.getTabCount());
        viewPager.setAdapter(tabAdapter);


//        tablayout listner


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



//        viewpager listner for tabitem


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//                BHAI YAHA PAR EK ERROR AA RAHA HE JAB NAVIGATION VIEW ME ITEM KO SELECT KARTA HU TO TO TAB PAR CODE JUMP HONA CHOAYE
//                BUT MEKO USKO CODE MALUM NAHI HE ISKO SOLVER KARNA BAKI HE


                int id = item.getItemId();
                if (item.isChecked()){

                    drawerLayout.closeDrawer(GravityCompat.START);
                    return false;

                }

                if (id == R.id.itemwhatsapp){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    Toast.makeText(getApplicationContext(), "You selected Whatsapp", Toast.LENGTH_SHORT).show();

                }
                else if (id == R.id.itembusiness){
                    startActivity(new Intent(getApplicationContext(), BusinessActivity.class));
                    Toast.makeText(getApplicationContext(), "You selected Whatsapp Business", Toast.LENGTH_SHORT).show();

                }
                else if (id == R.id.itemratus){

                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com")));
                    }catch (ActivityNotFoundException e){
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://play.google.com")));
                    }

                }
                else if (id == R.id.itemprivacy){
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://thebestfounder.com")));
                    }
                    catch (ActivityNotFoundException e){
                        Toast.makeText(getApplicationContext(), "Url is invalid", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (id == R.id.itemterms){
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("https://thebestfounder.com")));
                    }
                    catch (ActivityNotFoundException e ){
                        Toast.makeText(getApplicationContext(), "Url is not found", Toast.LENGTH_SHORT).show();
                    }
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });









    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);

    }

    //
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.nav_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id  == R.id.itembusiness){
//            startActivity(new Intent(getApplicationContext(), BusinessActivity.class));
//
//        }
//        return true;
//    }





    @Override
    public void onBackPressed() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Are You Want to Exit")
                .setCancelable(false)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })

                .setNegativeButton("NO", null)
                .show();
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}






























