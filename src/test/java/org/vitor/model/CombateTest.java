package org.vitor.model;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.vitor.inter.Arma;
import org.vitor.inter.Equipamento;
import org.vitor.inter.Habilidade;
import org.vitor.inter.Personagem;

import static org.junit.jupiter.api.Assertions.*;
class CombateTest {

    @Test
    void ataqueComArmaEHabilidade() {
        Personagem atacante = EasyMock.createMock(Personagem.class);
        Personagem defensor = EasyMock.createMock(Personagem.class);
        Arma arma = EasyMock.createMock(Arma.class);
        Habilidade habilidade = EasyMock.createMock(Habilidade.class);
        Equipamento equipamento = EasyMock.createMock(Equipamento.class);

        Combate combate = new Combate(atacante, defensor, arma, habilidade, equipamento, 100, 100);

        EasyMock.expect(atacante.atacar()).andReturn(20);
        EasyMock.expect(arma.calcularDano()).andReturn(30);
        EasyMock.expect(habilidade.getNome()).andReturn("Habilidade de fogo");
        EasyMock.expect(atacante.usarHabilidade("Habilidade de fogo")).andReturn(true);
        EasyMock.expect(habilidade.efeito()).andReturn(10);

        EasyMock.expect(equipamento.reduzirDano(60)).andReturn(60);
        EasyMock.expect(defensor.receberDano(60)).andReturn(60);

        EasyMock.replay(atacante, defensor, arma, habilidade, equipamento);

        assertEquals(60, combate.realizarAtaque());

        EasyMock.verify(atacante, defensor, arma, habilidade, equipamento);
    }

    @Test
    void reduzirDanoComEquipamento(){
        Personagem atacante = EasyMock.createMock(Personagem.class);
        Personagem defensor = EasyMock.createMock(Personagem.class);
        Arma arma = EasyMock.createMock(Arma.class);
        Habilidade habilidade = EasyMock.createMock(Habilidade.class);
        Equipamento equipamento = EasyMock.createMock(Equipamento.class);

        Combate combate = new Combate(atacante,defensor,arma,habilidade, equipamento,100, 100);

        EasyMock.expect(atacante.atacar()).andReturn(20);
        EasyMock.expect(arma.calcularDano()).andReturn(30);
        EasyMock.expect(habilidade.getNome()).andReturn("Habilidade de fogo");
        EasyMock.expect(atacante.usarHabilidade("Habilidade de fogo")).andReturn(true);

        EasyMock.expect(habilidade.efeito()).andReturn(10);

        EasyMock.expect(equipamento.reduzirDano(60)).andReturn(30);
        EasyMock.expect(defensor.receberDano(30)).andReturn(30);

        EasyMock.replay(atacante,defensor,arma,habilidade, equipamento);
        assertEquals(30, combate.realizarAtaque());

        EasyMock.verify(atacante,defensor,arma,habilidade, equipamento);
    }

    @Test
    void habilidadeSemMana(){
        Personagem atacante = EasyMock.createMock(Personagem.class);
        Personagem defensor = EasyMock.createMock(Personagem.class);
        Arma arma = EasyMock.createMock(Arma.class);
        Habilidade habilidade = EasyMock.createMock(Habilidade.class);
        Equipamento equipamento = EasyMock.createMock(Equipamento.class);

        Combate combate = new Combate(atacante,defensor,arma,habilidade,equipamento, 0, 50);

        EasyMock.expect(atacante.atacar()).andReturn(20);
        EasyMock.expect(arma.calcularDano()).andReturn(30);
        EasyMock.expect(habilidade.getNome()).andReturn("Habilidade de fogo");
        EasyMock.expect(atacante.usarHabilidade("Habilidade de fogo")).andReturn(false);

        EasyMock.expect(equipamento.reduzirDano(50)).andReturn(50);
        EasyMock.expect(defensor.receberDano(50)).andReturn(50);

        EasyMock.replay(atacante,defensor,arma,habilidade, equipamento);

        assertEquals(50, combate.realizarAtaque());

        EasyMock.verify(atacante,defensor,arma,habilidade, equipamento);
    }

    @Test
    void ataquePuro() {
        Personagem atacante = EasyMock.createMock(Personagem.class);
        Personagem defensor = EasyMock.createMock(Personagem.class);
        Arma arma = EasyMock.createMock(Arma.class);
        Habilidade habilidade = EasyMock.createMock(Habilidade.class);
        Equipamento equipamento = EasyMock.createMock(Equipamento.class);

        Combate combate = new Combate(atacante, defensor, arma, habilidade, equipamento, 100, 100);

        EasyMock.expect(atacante.atacar()).andReturn(20);
        EasyMock.expect(arma.calcularDano()).andReturn(0);
        EasyMock.expect(habilidade.getNome()).andReturn("Habilidade de fogo");
        EasyMock.expect(atacante.usarHabilidade("Habilidade de fogo")).andReturn(false);

        EasyMock.expect(equipamento.reduzirDano(20)).andReturn(20);
        EasyMock.expect(defensor.receberDano(20)).andReturn(20);

        EasyMock.replay(atacante, defensor, arma, habilidade, equipamento);

        assertEquals(20, combate.realizarAtaque());

        EasyMock.verify(atacante, defensor, arma, habilidade, equipamento);
    }
}