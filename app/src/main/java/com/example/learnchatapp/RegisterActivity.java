package com.example.learnchatapp;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static android.widget.Toast.makeText;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar actionbarRegister;
    private EditText txtUsername, txtEmail, txtPassword;
    private Button btnRegister,btnLoginRegister;

    private FirebaseAuth auth;


    public void init() {

        actionbarRegister = (Toolbar) findViewById(R.id.actionbarRegister);
        setSupportActionBar(actionbarRegister);
        getSupportActionBar().setTitle("Hesap Oluştur");


        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtParola);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLoginRegister = (Button) findViewById(R.id.btnLoginRegister);

        auth = FirebaseAuth.getInstance();


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createNewAccount();

            }
        });

        btnLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLoginActivity();
            }
        });

    }

    private void goToLoginActivity() {

        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }




    private void createNewAccount() { //kullanıcı olustururken edittexte yazılıcak aanları getr

        String username = txtUsername.getText().toString();
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "email alanı boş olamaz", Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "şifre alanı boş olamaz", Toast.LENGTH_LONG).show();
        } else {

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult> task) { //işlem tamamlanırsa olacaklar

                    if (task.isSuccessful()) {

                        Toast.makeText(RegisterActivity.this, "hesabınız oluşturuldu", Toast.LENGTH_LONG).show();
                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish();

                    } else {

                        Toast.makeText(RegisterActivity.this, "hata oluştu", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

    }
}
