package com.daclas.quizonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextView login_register,ForgotPW;

    private EditText email,password;
    private Button login;

    private Button log;
    public static String DacScore;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email =  findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        login =  findViewById(R.id.btn_next);

        mAuth = FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.name_enter);
                dialog.show();

                Button Enter,Cancel;
                final EditText Ennn;
                Ennn = dialog.findViewById(R.id.Ennn);
                Enter = dialog.findViewById(R.id.Enter);
                Cancel = dialog.findViewById(R.id.Cancel);

                Enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DacScore = Ennn.getText().toString();
                        dialog.dismiss();

                        String txt_email = email.getText().toString();
                        String txt_password = password.getText().toString();
                        loginUser(txt_email,txt_password);
                    }
                });
                Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

        login_register = findViewById(R.id.btn_textview_login);
        login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                finish();
            }
        });

        ForgotPW = findViewById(R.id.btn_Forgot);
        ForgotPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,Forgot_password.class));
            }
        });

        /*log = findViewById(R.id.logbtn);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(LoginActivity.this);
                dialog.setContentView(R.layout.name_enter);
                dialog.show();

                Button Enter,Cancel;
                final EditText Ennn;
                Ennn = dialog.findViewById(R.id.Ennn);
                Enter = dialog.findViewById(R.id.Enter);
                Cancel = dialog.findViewById(R.id.Cancel);

                Enter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DacScore = Ennn.getText().toString();
                        dialog.dismiss();
                    }
                });
                Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });*/
    }

    private void loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginActivity.this, "Login is successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });
        mAuth.signInWithEmailAndPassword(email, password).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Login is unsuccessful", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
