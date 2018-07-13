package com.example.cgaima.cheystagram;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cgaima.cheystagram.model.Post;
import com.parse.ParseImageView;

import org.parceler.Parcels;

import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>  {

    private List<Post> mPosts;
    Context context;



    public PostAdapter(List<Post> posts)
    {
        mPosts = posts;
    }



    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_post, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(postView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder viewHolder, int i) {

        Post post = mPosts.get(i);

        //populate the views according to this data
        viewHolder.username.setText(post.getUser().getUsername());
        viewHolder.caption.setText(post.getDescription());
        viewHolder.timestamp.setText(post.getCreatedAt().toString());
        viewHolder.timestamp.setText(post.getRelativeTimeAgo());

        Glide.with(context).load(post.getImage().getUrl()).into(viewHolder.parsePic);


    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //initialize
        public TextView username;
        public TextView caption;
        public ParseImageView parsePic;
        public TextView timestamp;
        public ImageButton details;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            //lookup views
            username = (TextView) itemView.findViewById(R.id.username);
            caption = (TextView) itemView.findViewById(R.id.caption);
            parsePic = (ParseImageView) itemView.findViewById(R.id.parsePic);
            timestamp = (TextView) itemView.findViewById(R.id.timestamp);
            details = (ImageButton) itemView.findViewById(R.id.details);

            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Post post = mPosts.get(getAdapterPosition());
                    Intent detailsIntent = new Intent(context, DetailsActivity.class);
                    detailsIntent.putExtra("post", Parcels.wrap(post));
                    context.startActivity(detailsIntent);
                }
            });


        }
    }

    public void clear() {
        mPosts.clear();
        notifyDataSetChanged();
    }


}



