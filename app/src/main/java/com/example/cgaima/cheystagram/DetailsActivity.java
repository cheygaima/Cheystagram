package com.example.cgaima.cheystagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cgaima.cheystagram.model.Post;

import org.parceler.Parcels;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    //instance vars
    PostAdapter postAdapter;
    ArrayList<Post> posts;
    ImageView parsePic;
    Post detailsPost;
    TextView username;
    TextView caption;
    TextView timestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        postAdapter = new PostAdapter(posts);
        parsePic = findViewById(R.id.parsePic);
        username = findViewById(R.id.username);
        caption = findViewById(R.id.caption);
        //timestamp = findViewById(R.id.timestamp);


        detailsPost = (Post) Parcels.unwrap(getIntent().getParcelableExtra("post"));

        username.setText(detailsPost.getUser().getUsername());
        caption.setText(detailsPost.getDescription());
        //timestamp.setText(detailsPost.getTimestamp().toString());

        Glide.with(DetailsActivity.this).load(detailsPost.getImage().getUrl()).into(parsePic);

    }

}
