package com.dev.morganizze.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.dev.morganizze.Helper.AutenticacaoFirebase;
import com.dev.morganizze.R;
import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao = AutenticacaoFirebase.autenticacaoReferencia();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Economize");
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setElevation(0);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_sair:
                autenticacao.signOut();
                startActivity(new Intent(MainActivity.this, IntroActivity.class));
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
