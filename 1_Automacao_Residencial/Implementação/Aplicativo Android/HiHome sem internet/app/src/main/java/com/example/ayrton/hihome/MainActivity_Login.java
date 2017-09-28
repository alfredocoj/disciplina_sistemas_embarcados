package com.example.ayrton.hihome;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity_Login extends AppCompatActivity implements View.OnClickListener{

    private EditText user;
    private EditText pass;
    private Button botaoLogin;
    private TextView botaoCadas;

    private FirebaseAuth fbauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fbauth = FirebaseAuth.getInstance();
        if (fbauth.getCurrentUser()!=null) {
            //Autenticação automática
            Toast.makeText(MainActivity_Login.this, "Usuário já logado. Carregando configurações...", Toast.LENGTH_LONG).show();
            Intent it = new Intent(MainActivity_Login.this,HiHome.class);
            startActivity(it);
            finishAffinity();
        }

        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);

        botaoLogin = (Button) findViewById(R.id.buttLogin);
        botaoLogin.setOnClickListener(this);

        botaoCadas = (TextView) findViewById(R.id.cadastra);
        botaoCadas.setOnClickListener(this);

        }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttLogin) {
            if (user.getText().length()==0){
                Toast.makeText(MainActivity_Login.this,"Preencher usuario",Toast.LENGTH_LONG).show();
            }else if(pass.getText().length()==0)
            {
                Toast.makeText(MainActivity_Login.this,"Preencher senha",Toast.LENGTH_LONG).show();
            }else {

                String userTxt = user.getText().toString();
                String senhaTxt = pass.getText().toString();

                fbauth.signInWithEmailAndPassword(userTxt, senhaTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent it = new Intent(MainActivity_Login.this,HiHome.class);
                            startActivity(it);
                            finishAffinity();
                            Toast.makeText(MainActivity_Login.this, "Logado com sucesso. Carregando configurações...", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity_Login.this, "Credenciais não identificadas.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }

        if (v.getId() == R.id.cadastra) {
            Intent it2 = new Intent(MainActivity_Login.this,Cadastro.class);
            startActivity(it2);
        }
    }

}