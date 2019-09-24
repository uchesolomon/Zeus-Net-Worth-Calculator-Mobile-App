package com.example.networthcalculatorteamzeus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        Button btnRegister = (Button) findViewById(R.id.btnReg);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                String newFirstName = firstName.getText().toString();
                String newLastName = lastName.getText().toString();
                String newEmail = email.getText().toString();
                String newPhone = phone.getText().toString();
                String newPassword = password.getText().toString();

                SharedPreferences.Editor editor = preferences.edit();

                editor.putString(newEmail, newEmail);
                editor.commit();
                editor.putString(newPassword, newPassword);
                editor.commit();
                editor.putString(newEmail + newPassword + "data", newFirstName + newLastName + "\n" + newEmail + "\n" + newPhone);
                editor.commit();

                Intent loginScreen = new Intent(SignUp.this, MainActivity.class);
                startActivity(loginScreen);

            }
        });
    }
}
