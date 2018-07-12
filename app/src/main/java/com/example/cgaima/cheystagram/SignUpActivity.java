package com.example.cgaima.cheystagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignUpActivity extends AppCompatActivity {

    Button signppBtn;
    private EditText userInput;
    private EditText passInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signppBtn = findViewById(R.id.signupBtn);
        userInput = findViewById(R.id.username);
        passInput = findViewById(R.id.password);

        signppBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();

            }
        });
    }

    private void signup() {

        ParseUser user = new ParseUser();
        user.setUsername(userInput.getText().toString());
        user.setPassword(passInput.getText().toString());

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Log.d("LoginActivity", "Welcome");

                    final Intent intent = new Intent(SignUpActivity.this, FeedActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong

                    Log.e("LoginActivity", "Failed :(");
                    e.printStackTrace();
                }
            }
        });


    }

}
