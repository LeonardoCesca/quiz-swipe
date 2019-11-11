package com.example.trabalho_swipe;

import java.util.ArrayList;
import java.util.List;

public class QuestaoControllerImpl implements QuestaoController {

    List<Questao> questoesRepository = new ArrayList<>();
    Integer currentPosition = 0;
    Integer listSize = 0;
    EstadoQuestaoListener estadoQuestaoListener;
    Integer quantidadeAcertos = 0;

    public QuestaoControllerImpl(EstadoQuestaoListener listener) {
        initRepository();
        this.estadoQuestaoListener = listener;
    }

    @Override
    public void initRepository() {

        Questao questao1 = new Questao("pergunta 1", Resposta.NAO);
        Questao questao2 = new Questao("pergunta 2", Resposta.SIM);
        Questao questao3 = new Questao("pergunta 3", Resposta.NAO);

        questoesRepository.add(questao1);
        questoesRepository.add(questao2);
        questoesRepository.add(questao3);
        listSize = questoesRepository.size();
    }

    @Override
    public Questao recuperaProximaQuestao() {
        Questao currentQuestion = null;
        currentPosition = currentPosition + 1;
        if(questoesRepository.size() == currentPosition) {
            estadoQuestaoListener.ExibeAlertComAcertos();
        } else {
            currentQuestion  = questoesRepository.get(currentPosition);
        }
        estadoQuestaoListener.OnNextQuestion();
        return currentQuestion;
    }

    @Override
    public Questao recuperaQuestaoAnterior() {
        Questao currentQuestion = questoesRepository.get(currentPosition - 1);
        estadoQuestaoListener.OnPreviousQuestion();
        currentPosition = currentPosition - 1;
        return currentQuestion;
    }

    @Override
    public Questao respondeSim() {
        Questao question = questoesRepository.get(currentPosition);
        question.setRespostaUsuario(Resposta.SIM);
        if(question.respostaCorreta == question.respostaUsuario) {
            quantidadeAcertos = quantidadeAcertos + 1;
        }
        questoesRepository.set(currentPosition, question);
        return recuperaProximaQuestao();
    }

    @Override
    public Questao respondeNao() {
        Questao question = questoesRepository.get(currentPosition);
        question.setRespostaUsuario(Resposta.NAO);
        if(question.respostaCorreta == question.respostaUsuario) {
            quantidadeAcertos = quantidadeAcertos + 1;
        }
        questoesRepository.set(currentPosition, question);
        return recuperaProximaQuestao();
    }

    @Override
    public Integer recuperaQuantidadeAcertos() {
        return quantidadeAcertos;
    }

    @Override
    public Integer recuperaQuantidadeTotal() {
        return listSize;
    }
}
