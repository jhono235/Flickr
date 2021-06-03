package com.example.android.flickrapp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android.flickrapp.R;
import com.example.android.flickrapp.model.StringConstants;
import com.example.android.flickrapp.model.room.FavoriteList;
import com.example.android.flickrapp.view.FullSizeImageActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavoritesActivityRvAdapter extends RecyclerView.Adapter<FavoritesActivityRvAdapter.ViewHolderInternal> {
    private final List<FavoriteList> favoriteList;
    Context context;

    public FavoritesActivityRvAdapter(List<FavoriteList> favoriteList, Context context) {
        this.favoriteList = favoriteList;
        this.context = context;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolderInternal onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return  new ViewHolderInternal(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.photo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolderInternal holder, int position) {
        FavoriteList bindedFavList = favoriteList.get(position);

        holder.tvTitle.setText(bindedFavList.getTitle());

        Glide
                .with(holder.itemView)
                .load(bindedFavList.getUrl())
                .centerCrop()
                .apply(new RequestOptions().override(800, 800))
                .into(holder.ivThumbnail);

    }

    @Override
    public int getItemCount() {
        if (favoriteList!= null) {
            return  favoriteList.size();
        }

        return 0;
    }

    public class ViewHolderInternal extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivThumbnail;

        public ViewHolderInternal(@NonNull @NotNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);

            itemView.setOnClickListener(
                    new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            String url = favoriteList.get(getAbsoluteAdapterPosition()).getUrl();

                            Intent intent = new Intent(view.getContext(), FullSizeImageActivity.class);
                            intent.putExtra(StringConstants.passedUrl, url);
                            intent.putExtra(StringConstants.passedTitle, favoriteList.get(getAbsoluteAdapterPosition()).getTitle());
                            intent.putExtra(StringConstants.passedId, favoriteList.get(getAbsoluteAdapterPosition()).getId());
                            view.getContext().startActivity(intent);



                        }
                    }
            );
        }
    }
}
