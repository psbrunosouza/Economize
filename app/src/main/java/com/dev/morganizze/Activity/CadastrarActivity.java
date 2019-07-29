package com.dev.morganizze.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dev.morganizze.Helper.AutenticacaoFirebase;
import com.dev.morganizze.Helper.ValidacaoHelper;
import com.dev.morganizze.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import studio.carbonylgroup.textfieldboxes.ExtendedEditText;

public class CadastrarActivity extends AppCompatActivity {

    private ExtendedEditText txt_nome, txt_email, txt_senha;
    private FirebaseAuth autenticacao = AutenticacaoFirebase.autenticacaoReferencia();
    private ValidacaoHelper validacaoHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        txt_nome = findViewById(R.id.ext_nome_cd);
        txt_email = findViewById(R.id.ext_email_cd);
        txt_senha = findViewById(R.id.ext_senha_cd);
    }

    //CADASTRO DO USUÁRIO
    public void btCadastrar(View view){

        String nome = txt_nome.getText().toString();
        String email = txt_email.getText().toString();
        String senha = txt_senha.getText().toString();

        validacaoHelper = new ValidacaoHelper(nome, email, senha, getApplicationContext());

        validacaoHelper.chamarIds(txt_nome, txt_email, txt_senha);

        if(validacaoHelper.validarCadastro()){
            autenticacao.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(CadastrarActivity.this, "Usuário cadastrado com sucesso!",
                                Toast.LENGTH_SHORT).show();
                        if(autenticacao.getCurrentUser() != null){
                            startActivity(new Intent(CadastrarActivity.this, MainActivity.class));
                        }
                    }else{
                        String excecao = "";

                        try{
                            throw task.getException();
                        }catch(FirebaseAuthWeakPasswordException e){
                            txt_senha.setError("Digite uma senha mais forte");
                        }catch(FirebaseAuthUserCollisionException e){
                            txt_email.setError("O e-mail escolhido já está sendo usado por outra conta");
                        }catch(FirebaseAuthInvalidCredentialsException e){
                            txt_email.setError("Formato de e-mail digitado não é permitido");
                        } catch(Exception e){
                            excecao = "Falha ao realizar cadastro de usuário";
                            e.printStackTrace();
                        }

                        Toast.makeText(CadastrarActivity.this, excecao, Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            });
        }
    }
}