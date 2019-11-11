package com.example.trabalho_swipe;

public interface QuestaoController {
    void initRepository();
    Questao recuperaProximaQuestao();
    Questao recuperaQuestaoAnterior();
    Questao respondeSim();
    Questao respondeNao();
    Integer recuperaQuantidadeAcertos();
    Integer recuperaQuantidadeTotal();
}
