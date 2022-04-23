package com.example.statusdownloader.Adapter;

import android.content.Intent;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.statusdownloader.Activity.PictureActivity;
import com.example.statusdownloader.Activity.VideoActivity;
import com.example.statusdownloader.Fragments.BImageFragment;
import com.example.statusdownloader.Fragments.ImageFragment;
import com.example.statusdownloader.Models.Constant;
import com.example.statusdownloader.Models.ModelClass;
import com.example.statusdownloader.R;

import java.util.ArrayList;

public class BImageAdapter extends RecyclerView.Adapter<BImageAdapter.ViewHolder> {

    BImageFragment context;
    ArrayList<ModelClass> fileslist;

    public BImageAdapter(BImageFragment context, ArrayList<ModelClass> fileslist) {
        this.context = context;
        this.fileslist = fileslist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context.getActivity()).inflate(R.layout.status_layout,null, false);
        return new BImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        final ModelClass modelClass = fileslist.get(position);
        if (modelClass.getUri().toString().endsWith(".mp4")){
            holder.play.setVisibility(View.VISIBLE);
        }
        else {
            holder.play.setVisibility(View.INVISIBLE);
        }

        Glide.with(context).load(modelClass.getUri()).into(holder.mainstatus);


//        hold

        holder.mainstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modelClass.getUri().toString().endsWith(".mp4")){

                    final String path = fileslist.get(position).getPath();
                    String destpath = Environment.getExternalStorageDirectory().getAbsolutePath()+ Constant.SAVE_FOLDER_NAME;
                    Intent intent = new Intent(context.getActivity(), VideoActivity.class);
                    intent.putExtra("DEST_PATH_VIDEO", destpath);
                    intent.putExtra("FILE_VIDEO", path);
                    intent.putExtra("FILENAME_VIDEO", modelClass.getFilename());
                    intent.putExtra("URI_VIDEO", modelClass.getUri().toString());
                    context.startActivity(intent);

                }

                else
                {
                    final String path = fileslist.get(position).getPath();
                    String destpath = Environment.getExternalStorageDirectory().getAbsolutePath()+Constant.SAVE_FOLDER_NAME;
                    Intent intent = new Intent(context.getActivity(), PictureActivity.class);
//

                    intent.putExtra("DEST_PATH", destpath);
                    intent.putExtra("FILE", path);
                    intent.putExtra("FILENAME", modelClass.getFilename());
                    intent.putExtra("URI", modelClass.getUri().toString());
                    context.startActivity(intent);

//
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return fileslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mainstatus, play;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mainstatus = itemView.findViewById(R.id.thumbnailofimage);
            play = itemView.findViewById(R.id.play);
        }
    }
}




































