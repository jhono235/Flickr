package com.example.android.flickrapp.view.adapter;

import android.content.Intent;
import android.util.Log;
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
import com.example.android.flickrapp.model.listResults.PhotoItem;
import com.example.android.flickrapp.view.FullSizeImageActivity;
import com.example.android.flickrapp.model.StringConstants;

import java.util.List;

public class MainActivityRvAdapter extends RecyclerView.Adapter<MainActivityRvAdapter.ViewHolderInternal> {

    private final List<PhotoItem> photoListResults;




    public MainActivityRvAdapter(List<PhotoItem> photoListResults){
        this.photoListResults = photoListResults;
    }

    @NonNull
    @Override
    public ViewHolderInternal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return  new ViewHolderInternal(LayoutInflater
       .from(parent.getContext())
       .inflate(R.layout.photo_item, parent, false));
    }




    @Override
    public void onBindViewHolder(@NonNull  ViewHolderInternal holder, int position) {
        PhotoItem currentPhoto = photoListResults.get(position);

        //Url needed by Glide to display photo
        String url = "https://live.staticflickr.com/"+currentPhoto.getServer()+"/"
                +currentPhoto.getId()+"_"
                +currentPhoto.getSecret()+"_b.jpg";

        holder.tvTitle.setText(currentPhoto.getTitle());




        Glide
                .with(holder.itemView)
                .load(url)
                .centerCrop()
                 .apply(new RequestOptions().override(800, 800))
                .into(holder.ivThumbnail);

    }

    @Override
    public int getItemCount() {
        if (photoListResults != null){
            return photoListResults.size();
        }
        return 0;
    }

    class ViewHolderInternal extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivThumbnail;

        ViewHolderInternal(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);



            itemView.setOnClickListener(
                    new View.OnClickListener(){
                        @Override
                        public void onClick(View view) {
                            String url = "https://live.staticflickr.com/"+photoListResults.get(getAbsoluteAdapterPosition()).getServer()+"/"
                                    +photoListResults.get(getAbsoluteAdapterPosition()).getId()+"_"
                                    +photoListResults.get(getAbsoluteAdapterPosition()).getSecret()+"_b.jpg";

                            Intent intent = new Intent(view.getContext(), FullSizeImageActivity.class);
                            intent.putExtra(StringConstants.passedUrl, url);
                            intent.putExtra(StringConstants.passedTitle, photoListResults.get(getAbsoluteAdapterPosition()).getTitle());
                            intent.putExtra(StringConstants.passedId, photoListResults.get(getAbsoluteAdapterPosition()).getId());
                            view.getContext().startActivity(intent);




                        }
                    }
            );
        }
    }

}
