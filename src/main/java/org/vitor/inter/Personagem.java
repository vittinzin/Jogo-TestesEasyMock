package org.vitor.inter;

public interface Personagem {
    int atacar();
    int receberDano(int dano);
    boolean usarHabilidade(String habilidade);
}
