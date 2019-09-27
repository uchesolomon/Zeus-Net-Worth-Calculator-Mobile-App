package com.example.networthcalculatorteamzeus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        final EditText firstName = (EditText) findViewById(R.id.regFirstName);
        final EditText lastName = (EditText) findViewById(R.id.regLastName);
        final EditText email = (EditText) findViewById(R.id.regEmail);
        final EditText phone = (EditText) findViewById(R.id.regPhone);
        final EditText password = (EditText) findViewById(R.id.regPassword);
        final TextView signin = (TextView) findViewById(R.id.signIn);
        Button btnRegister = (Button) findViewById(R.id.btnReg);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = email.getEditableText().toString().trim();
                if (firstName.getText().toString().length()<2) {
                    firstName.setError("First name should be at least 2 characters");
                }
                if (lastName.getText().toString().length()<2) {
                    lastName.setError("Last name should be at least 2 characters");
                }
                if (email.getText().toString().length()==0) {
                    email.setError("Field can't be empty");
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                    email.setError("Please enter a valid email address");
                }
                if (phone.getText().toString().length()<8) {
                    phone.setError("Phone number should be at least 8 characters");
                }
                if (password.getText().toString().length()<6) {
                    password.setError("Password should be at least 6 characters");
                }



                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                String newFirstName = firstName.getText().toString();
                String newLastName = lastName.getText().toString();
                String newEmail = email.getText().toString();
                String newPhone = phone.getText().toString();
                String newPassword = password.getText().toString();

                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString("EMAIL", newEmail);
                    editor.commit();
                    editor.putString("PASSWORD", newPassword);
                    editor.commit();
                    editor.putString(newEmail + newPassword + "data", newFirstName + " " + newLastName);
                    editor.commit();

                    if (password.getText().toString().length()>=6 && phone.getText().toString().length()>=8
                    && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches() && lastName.getText().toString().length()>=2
                    && firstName.getText().toString().length()>=2) {

                        Intent loginScreen = new Intent(SignUp.this, MainActivity.class);
                        startActivity(loginScreen);
                    }
                    else {
                        Toast.makeText(SignUp.this,"Please fill required fields",Toast.LENGTH_LONG).show();
                    }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginScreen = new Intent(SignUp.this, MainActivity.class);
                startActivity(loginScreen);
            }
        });
    }
}
