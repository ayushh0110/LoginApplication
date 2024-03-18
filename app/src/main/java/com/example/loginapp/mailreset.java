package com.example.loginapp;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class mailreset extends AppCompatActivity {

    private EditText emailEditText;
    private MaterialButton sendLinkButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mailreset); // Replace with the actual layout file name

        emailEditText = findViewById(R.id.eno);
        sendLinkButton = findViewById(R.id.sl);

        sendLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();

                if (isValidEmail(email)) {
                    sendPasswordResetLink(email);
                } else {
                    Toast.makeText(mailreset.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        // You can implement your email validation logic here if needed.
        // For simplicity, this example checks if the email contains "@".
        return email.contains("@");
    }

    private void sendPasswordResetLink(String email) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(mailreset.this, "Password reset link sent to your email", Toast.LENGTH_SHORT).show();
                        } else {
                            // Handle the error here
                            Toast.makeText(mailreset.this, "Failed to send reset link.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
