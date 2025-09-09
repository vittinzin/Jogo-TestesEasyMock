package org.vitor.model;

import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.vitor.inter.Atacante;
import org.vitor.inter.Defensor;
import org.vitor.inter.Magia;

import static org.junit.jupiter.api.Assertions.*;

class CombateTest {

    @Test
    void realizarAtaque() {
        Atacante atacante = EasyMock.createMock(Atacante.class);
        Defensor defensor = EasyMock.createMock(Defensor.class);
        Magia magia = EasyMock.createMock(Magia.class);

        Combate combate = new Combate(atacante,defensor,magia);

        EasyMock.expect(atacante.atacar()).andReturn(20);
        EasyMock.expect(defensor.defender(20)).andReturn(10);

        EasyMock.replay(atacante,defensor);

        assertEquals(10,combate.realizarAtaque());

        EasyMock.verify(atacante,defensor);
    }

    @Test
    void usarMagia() {
        Atacante atacante = EasyMock.createMock(Atacante.class);
        Defensor defensor = EasyMock.createMock(Defensor.class);
        Magia magia = EasyMock.createMock(Magia.class);

        Combate combate = new Combate(atacante,defensor,magia);

        EasyMock.expect(magia.lancarMagia("fogo")).andReturn("Explosao de fogo!");
        EasyMock.replay(magia);

        assertEquals("Explosao de fogo!", combate.usarMagia("fogo"));

        EasyMock.verify(combate);
    }

    @Test
    void defesaInvalida() {
        Atacante atacante = EasyMock.createMock(Atacante.class);
        Defensor defensor = EasyMock.createMock(Defensor.class);
        Magia magia = EasyMock.createMock(Magia.class);

        Combate combate = new Combate(atacante, defensor, magia);

        EasyMock.expect(atacante.atacar()).andReturn(20);
        EasyMock.expect(defensor.defender(20)).andThrow(new RuntimeException("defesa inválida"));

        EasyMock.replay(atacante,defensor, magia);

        assertThrows(RuntimeException.class, combate::realizarAtaque);

        EasyMock.verify(atacante,defensor, magia);
    }

    @Test
    void variosAtaques() {
        Atacante atacante = EasyMock.createMock(Atacante.class);
        Defensor defensor = EasyMock.createMock(Defensor.class);
        Magia magia = EasyMock.createMock(Magia.class);

        Combate combate = new Combate(atacante,defensor,magia);

        EasyMock.expect(atacante.atacar()).andReturn(20);
        EasyMock.expect(defensor.defender(20)).andReturn(10);

        EasyMock.expect(atacante.atacar()).andReturn(80);
        EasyMock.expect(defensor.defender(80)).andReturn(40);

        EasyMock.expect(atacante.atacar()).andReturn(100);
        EasyMock.expect(defensor.defender(100)).andReturn(60);

        EasyMock.replay(atacante,defensor);

        assertEquals(10,combate.realizarAtaque());
        assertEquals(40,combate.realizarAtaque());
        assertEquals(60,combate.realizarAtaque());

        EasyMock.verify(atacante,defensor);
    }
}