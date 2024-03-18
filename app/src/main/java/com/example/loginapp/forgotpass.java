package com.example.loginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class forgotpass extends AppCompatActivity {
    EditText phoneNumberEditText;
    EditText otpEditText; // Add EditText for OTP

    private String verificationId; // To store the verification ID

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpass);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        // Reference the EditText for phone number input and OTP
        phoneNumberEditText = findViewById(R.id.Pno);
        TextView mrTextView = findViewById(R.id.mr);
        otpEditText = findViewById(R.id.otp);

        // Reference the button for initiating phone number verification and OTP verification
        Button verifyPhoneNumberButton = findViewById(R.id.sotp);
        Button verifyOtpButton = findViewById(R.id.Votp);

        // Set an OnClickListener for the verification button
        mrTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the next screen (replace NextActivity.class with the actual class)
                Intent intent = new Intent(forgotpass.this, mailreset.class);
                startActivity(intent);
            }
        });
        verifyPhoneNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = phoneNumberEditText.getText().toString();
                if (!phoneNumber.isEmpty()) {
                    // Phone number is not empty, proceed with verification
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phoneNumber,    // Phone number to verify
                            60,             // Timeout duration
                            TimeUnit.SECONDS, // Unit of timeout
                            forgotpass.this, // Activity (or other context)
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                                    // The SMS has been sent, and you can use the verificationId to confirm the OTP
                                    forgotpass.this.verificationId = verificationId; // Store the verification ID
                                    Toast.makeText(forgotpass.this, "OTP sent", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onVerificationCompleted(PhoneAuthCredential credential) {
                                    // Auto-retrieval of SMS completed (if enabled)
                                    // You can add code here to handle the verification completion event
                                }

                                @Override
                                public void onVerificationFailed(FirebaseException e) {
                                    // Failed to send the SMS or verify the phone number
                                    // You can add code here to handle the verification failure event
                                    Toast.makeText(forgotpass.this, "Cannot verify", Toast.LENGTH_SHORT).show();
                                }
                            }
                    );
                } else {
                    // Handle the case where the phone number is empty
                    Toast.makeText(forgotpass.this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Set an OnClickListener for the OTP verification button
        verifyOtpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp = otpEditText.getText().toString();
                if (!otp.isEmpty() && verificationId != null) {
                    // OTP is not empty, proceed with OTP verification
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
                    // Sign in with the OTP credential (you can add this part based on your needs)
                    Toast.makeText(forgotpass.this, "verification done", Toast.LENGTH_SHORT).show();
                    Intent ni1 = new Intent(forgotpass.this, setpass.class);
                    startActivity(ni1);
                } else {
                    // Handle the case where OTP is empty or verification ID is not available
                    Toast.makeText(forgotpass.this, "Please enter a valid OTP", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}


