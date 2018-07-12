package com.example.cgaima.cheystagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.cgaima.cheystagram.model.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseImageView;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    Button refresh;
    RecyclerView rvFeed;
    ParseUser currentUser = ParseUser.getCurrentUser();
    ParseImageView parsePic;

    ArrayList<Post> posts;
    PostAdapter postAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        refresh = findViewById(R.id.btnRefresh);
        rvFeed = findViewById(R.id.rvFeed);
        parsePic = findViewById(R.id.parsePic);
        posts = new ArrayList<>();
        postAdapter = new PostAdapter(posts);

        rvFeed.setLayoutManager(new LinearLayoutManager(this));

        //set the adapter
        rvFeed.setAdapter(postAdapter);

        loadTopPosts();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTopPosts();
            }

        });
    }



    private void loadTopPosts() {
        final Post.Query postQuery = new Post.Query();
        postQuery.getTop().withUser();

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); i++) {
                        Log.d("HomeActivity", "Post: " + i + " = "
                                + objects.get(i).getDescription()
                                + "\nusername = " + objects.get(i).getUser().getUsername());

                        Post post = new Post();
                        post = objects.get(i);
                        posts.add(post);
                        postAdapter.notifyItemInserted(posts.size() - 1);
                    }
                }
                else {
                    e.printStackTrace();
                }

            }
        });
    }

    public void camClick(View view) {
        Intent camIntent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(camIntent);
    }

    public void logOut(View view) {
        currentUser.logOut();
        finish();

    }
}
