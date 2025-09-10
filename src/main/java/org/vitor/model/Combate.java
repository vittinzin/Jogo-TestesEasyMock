package org.vitor.model;

import org.vitor.inter.*;

public class Combate {
    private Personagem atacante;
    private Personagem defensor;
    private Arma arma;
    private Habilidade habilidade;
    private Equipamento equipamento;
    private int manaAtacante;
    private int manaDefensor;

    public Combate(Personagem atacante, Personagem defensor, Arma arma, Habilidade habilidade, Equipamento equipamento, int manaAtacante, int manaDefensor) {
        this.atacante = atacante;
        this.defensor = defensor;
        this.arma = arma;
        this.habilidade = habilidade;
        this.equipamento = equipamento;
        this.manaAtacante = manaAtacante;
        this.manaDefensor = manaDefensor;
    }

    public int realizarAtaque() {
        int danoBase = atacante.atacar() + arma.calcularDano();

        if(atacante.usarHabilidade(habilidade.getNome())) {
            danoBase += habilidade.efeito();
        }

        int danoFinal = equipamento.reduzirDano(danoBase);
        return defensor.receberDano(danoFinal);
    }
}