package com.example.networthcalculatorteamzeus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences preferences = getSharedPreferences("MYPREFS",MODE_PRIVATE);
        final String required_email = preferences.getString("EMAIL","DEFAULT_EMAIL");
        final String required_password = preferences.getString("PASSWORD","DEFAULT_PASSWORD");
        final EditText emailLogin = (EditText) findViewById(R.id.emailLogin);
        final EditText passwordLogin = (EditText) findViewById(R.id.passwordLogin);
        final TextView signup = (TextView) findViewById(R.id.signUp);
        Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailLogin.getText().toString();
                String password = passwordLogin.getText().toString();
                String emailInput = emailLogin.getEditableText().toString().trim();
                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);

                String userDetails = preferences.getString(email + password + "data", "You are not a registered user yet. Please sign up");
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("display",userDetails);
                editor.commit();

                if (emailLogin.getText().toString().length()==0) {
                    emailLogin.setError("Field can't be empty");
                    emailLogin.requestFocus();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                    emailLogin.setError("Please enter a valid email address");
                    emailLogin.requestFocus();
                }
                if (passwordLogin.getText().toString().length()<6) {
                    passwordLogin.setError("Password should be at least 6 characters");
                    passwordLogin.requestFocus();
                }
                if(email.equals(required_email)&&password.equals(required_password)) {
                    preferences.edit().putBoolean("ISLOGGEDIN",false).commit();
                    Intent dashboard = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(dashboard);
                }
                else {
                    Toast.makeText(MainActivity.this,"Email address or password is incorrect",Toast.LENGTH_LONG).show();
                }
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
