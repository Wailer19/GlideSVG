package com.example.mario.glidesvg;

import android.content.ContentResolver;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.util.Preconditions;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SVGActivity";

    private ImageView imageViewRes;
    private ImageView imageViewNet;
    private RequestBuilder<PictureDrawable> requestBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewNet = (ImageView) findViewById(R.id.image);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);

        requestBuilder = GlideApp.with(MainActivity.this)
                .as(PictureDrawable.class)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .override(5,5)
                .priority(Priority.HIGH)
                .skipMemoryCache(true)
                .listener(new SvgSoftwareLayerSetter(){
                    @Override
                    public boolean onLoadFailed(GlideException e, Object model, Target<PictureDrawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;

                    }

                    @Override
                    public boolean onResourceReady(PictureDrawable resource, Object model, Target<PictureDrawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                });

        loadNet();
    }



    private void loadNet() {
        Uri uri = Uri.parse("");
        requestBuilder.load(uri).into(imageViewNet);

    }
}
