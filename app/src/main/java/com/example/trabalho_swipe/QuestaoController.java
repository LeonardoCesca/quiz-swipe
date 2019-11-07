package com.example.trabalho_swipe;

public interface QuestaoController {
    void initRepository();
    Questao recuperaProximaQuestao();
    Questao recuperaQuestaoAnterior();
    void respondeSim(Questao questao);
    void respondeNao(Questao questao);
    Integer recuperaQuantidadeAcertos();
    Integer recuperaQuantidadeTotal();
}
