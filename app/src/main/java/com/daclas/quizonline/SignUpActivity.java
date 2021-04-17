package com.daclas.quizonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private TextView register_login;

    private EditText email;
    private EditText password;
    private EditText passW;
    private Button register;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        email = findViewById(R.id.loginEmail1);
        passW = findViewById(R.id.loginPassword1);
        password = findViewById(R.id.loginPassword2);
        register = findViewById(R.id.btn_register);

        mAuth = FirebaseAuth.getInstance();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                String txt_password1 = passW.getText().toString();

                if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                    Toast.makeText(SignUpActivity.this, "Empty credentials", Toast.LENGTH_SHORT).show();

                } else if (txt_password.length() < 8) {
                    Toast.makeText(SignUpActivity.this, "password too short", Toast.LENGTH_SHORT).show();
                }
                  else {
                      if (txt_password1.equals(txt_password)){
                    registerUser(txt_email, txt_password); }
                      else {
                          Toast.makeText(SignUpActivity.this, "password is not match", Toast.LENGTH_SHORT).show();
                      }
                }

            }
        });

        register_login = findViewById(R.id.btn_textview_login1);
        register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void registerUser(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Registering user successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(SignUpActivity.this, "Registering fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
