package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.net.Uri;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class NextActivity extends AppCompatActivity {

    CheckBox checkbox;
    String SiteKey = "AIzaSyBvrGI_wFPKUMb2bCVQgO-Tv0NQtebDGLo";
    GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        checkbox = findViewById(R.id.Cbd);

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkbox.isChecked()) {
                    SafetyNet.getClient(NextActivity.this).verifyWithRecaptcha(SiteKey)
                            .addOnSuccessListener(NextActivity.this, response -> {
                                // reCAPTCHA verification successful
                                if (response.getTokenResult() != null) {
                                    Toast.makeText(NextActivity.this, "Verification successful", Toast.LENGTH_SHORT).show();
                                    checkbox.setTextColor(Color.BLUE);
                                }
                                else {
                                    // Verification token is null, indicating failure
                                    checkbox.setChecked(false);
                                    checkbox.setTextColor(Color.RED);
                                    Toast.makeText(NextActivity.this, "Verification not done", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(NextActivity.this, e -> {
                                // reCAPTCHA verification failed
                                checkbox.setChecked(false);

                                Toast.makeText(NextActivity.this, "Verification not done",Toast.LENGTH_SHORT).show();
                            });
                }
            }
        });

        TextView welcomeText = findViewById(R.id.welcomeText);
        Button openBrowserButton = findViewById(R.id.openBrowserButton);

        String username = getIntent().getStringExtra("username");
        String welcomee = getString(R.string.Welcome);
        welcomeText.setText(welcomee + "  " + username);

        openBrowserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open a browser with a URL
                String url = "https://www.bvuniversity.edu.in/";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);

            }
        });
    }
}
