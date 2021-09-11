package com.example.statusdownloader.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.statusdownloader.R;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class PictureActivity extends AppCompatActivity {

    ImageView particularimage, downloadd, delete, share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);









        particularimage = findViewById(R.id.particularimage);
        share = findViewById(R.id.share);
        downloadd = findViewById(R.id.download);
        delete= findViewById(R.id.delete);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Share Using", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String Body = "Download this app";
                String Sub = "http://play.google.com";
                intent.putExtra(Intent.EXTRA_TEXT, Body);
                intent.putExtra(Intent.EXTRA_TEXT, Sub);
                startActivity(intent.createChooser(intent, "Share Using"));
            }
        });


        Intent intent = getIntent();
        String destpath = intent.getStringExtra("DEST_PATH");
        String file = intent.getStringExtra("FILE");
        String uri = intent.getStringExtra("URI");
        String filename = intent.getStringExtra("FILENAME");




        File destpath2 = new File(destpath);
        File file1 = new File(file);



        Glide.with(getApplicationContext()).load(uri).into(particularimage);



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileDeleteStrategy.NORMAL.delete(file1);
                    FileDeleteStrategy.NORMAL.delete(destpath2);

                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                MediaScannerConnection.scanFile(getApplicationContext(),
                        new String[]{destpath + filename},
                        new String[]{"*/*"},
                        new MediaScannerConnection.MediaScannerConnectionClient() {
                            @Override
                            public void onMediaScannerConnected() {

                            }

                            @Override
                            public void onScanCompleted(String path, Uri uri) {

                            }
                        });
                Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
//                Dialog dialog= new Dialog(SaveActivity.this);
//                dialog.setContentView(R.layout.delete_dialog_box);
//                dialog.show();
//                Button button= dialog.findViewById(R.id.okbutton);
//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        finish();
//                    }
//                });
            }
        });

        downloadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    org.apache.commons.io.FileUtils.copyFileToDirectory(file1, destpath2);

                    FileUtils.copyFileToDirectory(file1, destpath2);


                } catch (IOException e) {
                    e.printStackTrace();
                }


                MediaScannerConnection.scanFile(getApplicationContext(),
                        new String[]{destpath + filename},
                        new String[]{"*/*"},
                        new MediaScannerConnection.MediaScannerConnectionClient() {
                            @Override
                            public void onMediaScannerConnected() {

                            }

                            @Override
                            public void onScanCompleted(String path, Uri uri) {

                            }
                        });
                Toast.makeText(getApplicationContext(), "downloaded", Toast.LENGTH_SHORT).show();
//                Dialog dialog= new Dialog(PictureActivity.this);
//                dialog.setContentView(R.layout.custom_dialog);
//                dialog.show();
////                Button button= dialog.findViewById(R.id.okbutton);
//                button.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        finish();
//
//                    }
//                });


            }


        });
        
        
        


    }
}