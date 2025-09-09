package org.vitor.model;

import org.vitor.inter.Atacante;
import org.vitor.inter.Defensor;
import org.vitor.inter.Magia;

public class Combate {
    private Atacante atacante;
    private Defensor defensor;
    private Magia magia;

    public Combate(Atacante atacante, Defensor defensor, Magia magia) {
        this.atacante = atacante;
        this.defensor = defensor;
        this.magia = magia;
    }

    public int realizarAtaque() {
        int dano = atacante.atacar();
        return defensor.defender(dano);
    }

    public String usarMagia(String tipo) {
        return magia.lancarMagia(tipo);
    }

}