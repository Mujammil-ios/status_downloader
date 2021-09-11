package com.example.statusdownloader.Fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.statusdownloader.Adapter.ImageAdapter;
import com.example.statusdownloader.Adapter.SavedAdapter;
import com.example.statusdownloader.Models.Constant;
import com.example.statusdownloader.Models.ModelClass;
import com.example.statusdownloader.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class SavedFragment extends Fragment {

    SavedAdapter savedAdapter;
    File[] files;

    ArrayList<ModelClass> filelist =  new ArrayList<>();

    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;

    TextView no_files_found;



    public SavedFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_saved, container, false);


        recyclerView= view.findViewById(R.id.savedrecycler);
        swipeRefreshLayout = view.findViewById(R.id.savedswipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                swipeRefreshLayout.setRefreshing(true);
                viewdata();
                {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    },1000);
                }
            }
        });
        return view;

    }





    private void viewdata() {

        filelist.clear();
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        savedAdapter = new SavedAdapter(SavedFragment.this, getData());
        recyclerView.setAdapter(savedAdapter);
        savedAdapter.notifyDataSetChanged();



    }

    private ArrayList<ModelClass> getData() {

        ModelClass f;
        String targetpath = Environment.getExternalStorageDirectory().getAbsolutePath()+ Constant.SAVE_FOLDER_NAME;
        File targetdir  = new File(targetpath);
        files = targetdir.listFiles();

//        if (files != null && files.length >0){
//            Arrays.sort(files);
//        }
        for (int i = 0; i<files.length; i++){
            File  file = files[i];
            f= new ModelClass();
            f.setUri(Uri.fromFile(file));
            f.setPath(files[i].getAbsolutePath());
            f.setFilename(file.getName());
            if (file.getName().endsWith(".jpg")|| file.getName().endsWith(".png")||file.getName().endsWith(".jpeg")||file.getName().endsWith(".gif")||file.getName().endsWith(".mp4")){

                filelist.add(f);

            }
//            else {
//
//                no_files_found.setVisibility(View.VISIBLE);
//            }
//            swipeRefreshLayout.setRefreshing(false);

        }
        return filelist;
    }
}




















