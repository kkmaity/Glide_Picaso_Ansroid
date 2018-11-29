package com.picasoglidesample;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView ivImage;
    private AddressReyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivImage=findViewById(R.id.ivImage);
        ivImage.setLayoutManager(new LinearLayoutManager(this));
        String url="https://s3.ap-south-1.amazonaws.com/eutigo/staging/daily_wishes/good_morning/cover_picture.jpg";
        //String url="https://homepages.cae.wisc.edu/~ece533/images/baboon.png";
       /* GlideApp.with(this)
                .load(url)
                .placeholder(R.drawable.placeholder)
                .fitCenter()
                .into(ivImage);
*/
        List<String> imgList=new ArrayList<>();
        for (int i=0;i<800;i++){
            imgList.add(url);
        }

        adapter= new AddressReyclerAdapter(MainActivity.this, imgList, new OnClickItem() {
            @Override
            public void onListItemClick(Object o, int position) {

            }
        });
        ivImage.setAdapter(adapter);
        adapter.notifyDataSetChanged();

/*
        GlideApp.with(this)
                .load(url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade()) //Optional
                .skipMemoryCache(true)  //No memory cache
                .diskCacheStrategy(DiskCacheStrategy.NONE)   //No disk cache
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(ivImage);*/
    }
}
