package com.example.ayrton.hihome;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ayrton.hihome.bancoDeDados.PessoaDB;
import com.example.ayrton.hihome.bancoDeDados.clienteBanco;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Cadastro extends AppCompatActivity implements View.OnClickListener{

    EditText pin;
    EditText login;
    EditText senha;
    EditText senha2;

    Button buttCadas;

    private FirebaseAuth fbauth;
    private DatabaseReference fbdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        login = (EditText) findViewById(R.id.txtLogin);
        senha = (EditText) findViewById(R.id.txtSenha);
        senha2 = (EditText) findViewById(R.id.txtSenha2);

        buttCadas = (Button) findViewById(R.id.buttNewCadas);
        buttCadas.setOnClickListener(this);
    }

    public void onClick(View view) {

        final int lampada = 0;
        final int ac = 0;
        final int portao = 0;
        final String loginTxt = login.getText().toString();
        final String senhaTxt = senha.getText().toString();
        String senha2Txt = senha2.getText().toString();

        if (login.getText().length() == 0) {
            Toast.makeText(Cadastro.this, "Insira o Login", Toast.LENGTH_SHORT).show();

        } else if (senha.getText().length() <= 5) {
            Toast.makeText(Cadastro.this, "Insira uma senha com no mínimo 6 caracteres", Toast.LENGTH_SHORT).show();

        } else if (senha2.getText().length() == 0) {
            Toast.makeText(Cadastro.this, "Confirme sua senha", Toast.LENGTH_SHORT).show();

        }else if (!senhaTxt.equals(senha2Txt)) {
                    Toast.makeText(Cadastro.this, "As senhas nao estao iguais", Toast.LENGTH_SHORT).show();

         } else {

            Toast.makeText(Cadastro.this, "Criando usuário. Aguarde...", Toast.LENGTH_SHORT).show();

            fbauth = FirebaseAuth.getInstance();
            fbauth.createUserWithEmailAndPassword(loginTxt, senhaTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Cadastro.this, "Usuário registrado com sucesso", Toast.LENGTH_SHORT).show();
                        limparCampos();
                        clienteBanco cliente = new clienteBanco(loginTxt, lampada, ac, portao);

                        fbdb = FirebaseDatabase.getInstance().getReference().child("usuarios");
                        fbdb.push().setValue(cliente);
                    }else{
                        Toast.makeText(Cadastro.this, "Problema ao registrar o usuário", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void limparCampos() {
        login.setText("");
        senha.setText("");
        senha2.setText("");
    }
}
