package com.example.cgaima.cheystagram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cgaima.cheystagram.model.Post;
import com.parse.ParseImageView;

import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

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
        //viewHolder.parsePic.setParseFile(post.getMedia());
        //viewHolder.parsePic.loadInBackground();

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //lookup views
            username = (TextView) itemView.findViewById(R.id.username);
            caption = (TextView) itemView.findViewById(R.id.caption);
            parsePic = (ParseImageView) itemView.findViewById(R.id.parsePic);
        }
    }
}
