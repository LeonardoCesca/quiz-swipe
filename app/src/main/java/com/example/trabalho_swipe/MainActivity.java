package com.example.trabalho_swipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout telaPrincipal;
    private TextView tvSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSwipe = findViewById(R.id.tvSwipe);
        telaPrincipal = findViewById(R.id.telaPrincipal);

        telaPrincipal.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                tvSwipe.setText("Para baixo");
            }

            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                tvSwipe.setText("Para cima");
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                tvSwipe.setText("Para esquerda");
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                tvSwipe.setText("Para direita");
            }
        });
    }
}
