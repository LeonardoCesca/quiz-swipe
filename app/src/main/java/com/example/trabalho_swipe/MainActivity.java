package com.example.trabalho_swipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements EstadoQuestaoListener {

    private ConstraintLayout telaPrincipal;
    private TextView tvSwipe;
    private QuestaoControllerImpl questaoController;
    private Questao currentQuestion;

    private void initQuestions() {
        questaoController = new QuestaoControllerImpl(this);
        currentQuestion = questaoController.questoesRepository.get(0);
        tvSwipe.setText(currentQuestion.pergunta);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSwipe = findViewById(R.id.tvSwipe);
        telaPrincipal = findViewById(R.id.telaPrincipal);

        initQuestions();
        telaPrincipal.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeBottom() {
                super.onSwipeBottom();
                currentQuestion = questaoController.respondeNao();
                tvSwipe.setText(currentQuestion.pergunta);
            }

            @Override
            public void onSwipeTop() {
                super.onSwipeTop();
                currentQuestion = questaoController.respondeSim();
                tvSwipe.setText(currentQuestion.pergunta);
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                currentQuestion = questaoController.recuperaQuestaoAnterior();
                tvSwipe.setText(currentQuestion.pergunta);
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                currentQuestion = questaoController.recuperaProximaQuestao();
                tvSwipe.setText(currentQuestion.pergunta);
            }
        });
    }

    private void animateTransition() {
        ObjectAnimator fadeInAnimator = ObjectAnimator.ofFloat(tvSwipe, "alpha", 1f, 0f);
        fadeInAnimator.setDuration(800L);
        ObjectAnimator fadeOutAnimator = ObjectAnimator.ofFloat(tvSwipe, "alpha", 0f, 1f);
        fadeOutAnimator.setDuration(800L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(fadeInAnimator, fadeOutAnimator);
        animatorSet.start();
    }

    @Override
    public void OnNextQuestion() {
        animateTransition();
    }

    @Override
    public void OnPreviousQuestion() {
        animateTransition();
    }

    @Override
    public void ExibeAlertComAcertos() {
        String acertos = questaoController.recuperaQuantidadeAcertos().toString();
        String total = questaoController.recuperaQuantidadeTotal().toString();

            Toast.makeText(this, "voce acertou " +
                    acertos
                    + " de " + total, Toast.LENGTH_LONG).show();

    }

}
