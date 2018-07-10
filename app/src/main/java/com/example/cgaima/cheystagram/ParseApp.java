package com.example.cgaima.cheystagram;

import android.app.Application;

import com.example.cgaima.cheystagram.model.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        final Parse.Configuration config = new Parse.Configuration.Builder(this)
                .applicationId("walt-notwhite")
                .clientKey("cocoanutgaima")
                .server("http://cheystagram.herokuapp.com/parse")
                .build();
        Parse.initialize(config);
    }
}
