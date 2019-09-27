package com.example.networthcalculatorteamzeus;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
        String display = preferences.getString("display","");

        TextView displayInfo = (TextView) findViewById(R.id.dashText);
        displayInfo.setText(display);

        final EditText assets = (EditText) findViewById(R.id.assetValue);
        final EditText cash = (EditText) findViewById(R.id.cashValue);
        final EditText liabilities = (EditText) findViewById(R.id.liabilityValue);
        final TextView netWorth = (TextView) findViewById(R.id.networthVal);
        Button btnCalculate = (Button) findViewById(R.id.btnNetWorth);
        int ans = 0;


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (assets.getText().toString().length()==0){
                    assets.setError("This field is required");
                }
                if (cash.getText().toString().length()==0){
                    cash.setError("This field is required");
                }
                if (liabilities.getText().toString().length()==0){
                    liabilities.setError("This field is required");
                }
                else {
                    double assetVal = Double.parseDouble(assets.getText().toString());
                    double cashVal = Double.parseDouble(cash.getText().toString());
                    double liabilityVal = Double.parseDouble(liabilities.getText().toString());


                    double result = assetVal + cashVal - liabilityVal;
                    netWorth.setText(String.format(Locale.getDefault(), "%f", result));
                }
            }
        });
    }
}
