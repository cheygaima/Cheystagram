package com.example.cgaima.cheystagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private EditText userInput;
    private EditText passInput;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = findViewById(R.id.username);
        passInput = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);

        loginBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //log the user in
                final String username = userInput.getText().toString();
                final String password = passInput.getText().toString();

                logIn(username, password);
            }
        });



    }

    private void logIn(String username, String password) {
        System.out.println("Username" + username);
        System.out.println("Password" + password);


        ParseUser.logInInBackground(username, password, new LogInCallback() {

            @Override
            public void done(ParseUser user, ParseException e) {
                if (e == null) {
                    //user was logged in correctly
                    Log.d("LoginActivity", "You're in!");

                    final Intent intent = new Intent(MainActivity.this, FeedActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("LoginActivity", "Failed :(");
                    e.printStackTrace();
                }

            }
        });

    }

}
