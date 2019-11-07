package com.example.trabalho_swipe;

import java.util.ArrayList;
import java.util.List;

public class QuestaoControllerImpl implements QuestaoController {

    List<Questao> questoesRepository = new ArrayList<>();
    Integer currentPosition = 0;
    Integer listSize;
    EstadoQuestaoListener estadoQuestaoListener;
    Integer quantidadeAcertos;

    public QuestaoControllerImpl(EstadoQuestaoListener listener) {
        initRepository();
        this.estadoQuestaoListener = listener;
    }

    @Override
    public void initRepository() {
        List<Questao> questoes = new ArrayList<Questao>();
        // TODO MONTAR AS QUESTOES
        Questao questao1 = new Questao("pergunta 1", Resposta.NAO);
        Questao questao2 = new Questao("pergunta 2", Resposta.SIM);
        Questao questao3 = new Questao("pergunta 3", Resposta.NAO);

        questoes.add(questao1);
        questoes.add(questao2);
        questoes.add(questao3);
        questoesRepository.addAll(questoes);
        listSize = questoesRepository.size();
    }

    @Override
    public Questao recuperaProximaQuestao() {
        // TODO ver se o indice é valido
        Questao currentQuestion = questoesRepository.get(currentPosition + 1);
        estadoQuestaoListener.OnNextQuestion();
        if(questoesRepository.size() == currentPosition) {
            estadoQuestaoListener.ExibeAlertComAcertos();
        }
        currentPosition = currentPosition + 1;

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
    public void respondeSim(Questao questao) {
        questao.setRespostaUsuario(Resposta.SIM);
        if(questao.respostaCorreta == questao.respostaUsuario) {
            quantidadeAcertos = quantidadeAcertos + 1;
        }
        questoesRepository.add(currentPosition, questao);
    }

    @Override
    public void respondeNao(Questao questao) {
        questao.setRespostaUsuario(Resposta.NAO);
        if(questao.respostaCorreta == questao.respostaUsuario) {
            quantidadeAcertos = quantidadeAcertos + 1;
        }
        questoesRepository.add(currentPosition, questao);
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
