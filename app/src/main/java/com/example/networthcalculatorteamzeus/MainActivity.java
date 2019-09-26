package com.example.networthcalculatorteamzeus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText emailLogin = (EditText) findViewById(R.id.emailLogin);
        final EditText passwordLogin = (EditText) findViewById(R.id.passwordLogin);
        final TextView signup = (TextView) findViewById(R.id.signUp);
        Button loginButton = (Button) findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailLogin.getText().toString();
                String password = passwordLogin.getText().toString();
                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);

                String userDetails = preferences.getString(email + password + "data", "You are not a registered user yet. Please sign up");
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("display",userDetails);
                editor.commit();

                Intent dashboard = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(dashboard);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerScreen = new Intent(MainActivity.this, SignUp.class);
                startActivity(registerScreen);
            }
        });
    }
}
