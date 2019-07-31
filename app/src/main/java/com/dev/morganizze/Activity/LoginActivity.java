package com.dev.morganizze.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dev.morganizze.Helper.AutenticacaoFirebase;
import com.dev.morganizze.Helper.ValidacaoHelper;
import com.dev.morganizze.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao = AutenticacaoFirebase.autenticacaoReferencia();
    private ExtendedEditText txt_email, txt_senha;
    private ValidacaoHelper validacaoHelper;
    private Button bt_entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_email = findViewById(R.id.ext_email_lg);
        txt_senha = findViewById(R.id.ext_senha_lg);
        bt_entrar = findViewById(R.id.bt_entrar);
    }

    //ACESSO DO USUÁRIO
    public void btEntrar(){
        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txt_email.getText().toString();
                String senha = txt_senha.getText().toString();

                validacaoHelper = new ValidacaoHelper(email, senha, getApplicationContext());
                validacaoHelper.chamarIds(txt_email, txt_senha);

                if(validacaoHelper.validarAcesso()){
                autenticacao.signInWithEmailAndPassword(email, senha)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                }else{
                                    String excecao;
                                    try{
                                        throw task.getException();
                                    }catch(FirebaseAuthInvalidUserException e){
                                        txt_email.setError("Não existe usuário cadastrado com esse e-mail");
                                    }catch(FirebaseAuthInvalidCredentialsException e){
                                        txt_senha.setError("A senha digitada está incorreta");
                                    } catch(Exception e){
                                        excecao = "Falha na autenticação do usuário";
                                        Toast.makeText(LoginActivity.this, excecao, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        btEntrar();
    }
}
