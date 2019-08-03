package com.dev.morganizze.Activity;

import com.dev.morganizze.R;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class IntroMainActivity extends IntroActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullscreen(true);
        super.onCreate(savedInstanceState);

        //COLEÇÃO DE SLIDES
        setButtonNextVisible(false);
        setButtonBackVisible(false);

        addSlide(
                new FragmentSlide.Builder()
                    .background(R.color.colorBackground)
                    .fragment(R.layout.slide_1)
                    .build());

        addSlide(
                new FragmentSlide.Builder()
                    .background(R.color.colorBackground)
                    .fragment(R.layout.slide_2)
                    .build());

        addSlide(
                new FragmentSlide.Builder()
                    .background(R.color.colorBackground)
                    .fragment(R.layout.slide_3)
                    .build());

        addSlide(
                new FragmentSlide.Builder()
                    .background(R.color.colorBackground)
                    .fragment(R.layout.slide_4)
                    .build());

        addSlide(
                new FragmentSlide.Builder()
                    .fragment(R.layout.slide_5)
                    .background(R.color.colorBackground)
                    .canGoBackward(false)
                    .canGoForward(false)
                    .build());
    }

    public void irCadastrar(View view){
        startActivity(new Intent(this, CadastrarActivity.class));
        finish();
    }

    public void irLogar(View view){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
