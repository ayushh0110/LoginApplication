package com.example.loginapp;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class setpass extends AppCompatActivity {
    EditText passwordEditText;
    EditText reEnterPasswordEditText;
    Button setPassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setpass);

        // Reference the EditTexts for password input
        passwordEditText = findViewById(R.id.Pno);
        reEnterPasswordEditText = findViewById(R.id.otp);

        // Reference the button for setting the password\"M:]
        //
        setPassButton = findViewById(R.id.Votp);
        setPassButton.setEnabled(false); // Initially, disable the button

        // Add a TextWatcher to check when the text in both EditTexts changes
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this example
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not needed for this example
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Check if the text in both EditTexts matches
                String password = passwordEditText.getText().toString();
                String reEnteredPassword = reEnterPasswordEditText.getText().toString();

                // Check if passwords match and meet the criteria
                if (password.equals(reEnteredPassword) && isPasswordValid(password)) {
                    // Passwords match and meet the criteria, so enable the "Set Password" button
                    setPassButton.setEnabled(true);
                } else {

                    // Passwords do not match or do not meet the criteria, so disable the button
                    setPassButton.setEnabled(true);
                }
            }
        };

        // Add the TextWatcher to both password EditTexts
        passwordEditText.addTextChangedListener(textWatcher);
        reEnterPasswordEditText.addTextChangedListener(textWatcher);

        // Set an OnClickListener for the "Set Password" button
        setPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = passwordEditText.getText().toString();
                if (!isPasswordValid(password)) {
                    // Password is not valid, display a toast message
                    Toast.makeText(setpass.this, "Password must contain at least one letter, one number, and one special character.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(setpass.this, "Password set", Toast.LENGTH_SHORT).show();
                }
                    // Password is valid, proceed with your logic to set the password
                    // For example, you can call a method to save the password to a database
                    // savePassword(password);
                }
                // Implement your logic to set the password here
                // This button will be enabled only when both passwords match and meet the criteria

        });
    }

    private boolean isPasswordValid(String password) {
        // Use a regular expression to check if the password contains a combination of letters, numbers, and symbols
        String pattern = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).*$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(password);

        return m.matches();
    }
}
