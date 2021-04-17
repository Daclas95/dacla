package com.daclas.quizonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_password extends AppCompatActivity {

    private Button Send;
    private EditText EmailAddress;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
         EmailAddress = findViewById(R.id.ForgotPassword);
         Send = findViewById(R.id.btn_forgot);
        mAuth = FirebaseAuth.getInstance();

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Id= EmailAddress.getText().toString();
                if (Id.equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Your Mail ID", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.sendPasswordResetEmail(Id).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){
                                mAuth.signOut();
                                startActivity(new Intent(Forgot_password.this,LoginActivity.class));
                                Toast.makeText(getApplicationContext(), "Password Reset code sent your Email address", Toast.LENGTH_SHORT).show();
                            }else {
                                mAuth.signOut();
                                Toast.makeText(getApplicationContext(), "Error to send Email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}
