package com.example.trabalho_swipe;

import java.util.Objects;

public class Questao {
    String pergunta;
    Resposta respostaUsuario;
    Resposta respostaCorreta;

    public Questao(String pergunta, Resposta respostaCorreta) {
        this.pergunta = pergunta;
        this.respostaCorreta = respostaCorreta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public Resposta getRespostaUsuario() {
        return respostaUsuario;
    }

    public void setRespostaUsuario(Resposta respostaUsuario) {
        this.respostaUsuario = respostaUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Questao questao = (Questao) o;
        return Objects.equals(pergunta, questao.pergunta) &&
                respostaUsuario == questao.respostaUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pergunta, respostaUsuario);
    }
}
