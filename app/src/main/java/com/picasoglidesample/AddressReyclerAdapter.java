package com.picasoglidesample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;


public class AddressReyclerAdapter extends RecyclerView.Adapter<AddressReyclerAdapter.ViewHolder>{

    private Context mContext;
    private OnClickItem mInterface;
    private  int lastCheckedPosition=0;
    private List<String> dataList;

    public AddressReyclerAdapter(Context con, List<String> data, OnClickItem _interface) {
        if(con!=null) {
            mContext = con;
            mInterface=_interface;
            dataList=data;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_address_item, parent, false);
        ViewHolder vh = new ViewHolder(v);


        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
//=====================Picaso=================
        //  Constants.loadSquareImage(dataList.get(position),holder.ivImage);


        holder.linMain.setTag(position);
        holder.linMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastCheckedPosition=position;
                mInterface.onListItemClick(view.getId(),position);
            }
        });
        holder.progress_circular.setVisibility(View.VISIBLE);


        GlideApp.with(mContext)
                .load(dataList.get(position))
                .transition(DrawableTransitionOptions.withCrossFade()) //Optional
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progress_circular.setVisibility(View.GONE);

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progress_circular.setVisibility(View.GONE);

                        return false;
                    }


                })
                .into(holder.ivImage);

    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView linMain;
        public ImageView ivImage;
        public ProgressBar progress_circular;
        // public RoundedImageView imageView;
        // public TextView tv_discussion;

        public ViewHolder(View view) {
            super(view);

            linMain = (CardView) view.findViewById(R.id.linMain);
            ivImage = (ImageView) view.findViewById(R.id.ivImage);
            progress_circular = (ProgressBar) view.findViewById(R.id.progress_circular);
           /* rvMain = (RelativeLayout) view.findViewById(R.id.rvMain);
            nameTextView = view.findViewById(R.id.nameTextView);
            imageView = view.findViewById(R.id.imageView);
*/

        }
    }
}

