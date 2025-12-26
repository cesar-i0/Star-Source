package main;

import java.awt.Rectangle;

public class ManipuladorDeEvento {

    PainelDoJogo pj;
    Rectangle evento_com_rectangulo;
    int evento_com_rectangulo_padraoX, evento_com_rectangulo_padraoY;

    public ManipuladorDeEvento(PainelDoJogo pj){

        this.pj = pj;

        evento_com_rectangulo = new Rectangle();
        evento_com_rectangulo.x = 23;
        evento_com_rectangulo.y = 23;
        evento_com_rectangulo.width = 2;
        evento_com_rectangulo.height = 2;
        evento_com_rectangulo_padraoX = evento_com_rectangulo.x;
        evento_com_rectangulo_padraoY = evento_com_rectangulo.y;

    }

    public void verificaEvento(){

        // Local da armadilha
        if(atingiu(1,1, "cima") == true) danoDeArmadilha(pj.estado_de_dialogo);
        // Local de cura
        if(atingiu(3, 1, "cima") == true) localDeCura(pj.estado_de_dialogo);
        // Local de teleporte
        if(atingiu(5, 1, "cima") == true) teleporte(pj.estado_de_dialogo);

    }

    public boolean atingiu(int eventoCol, int enventoLin, String direcaoRequerida){
        
        boolean atingiu = false;

        pj.jogador.area_solida.x = pj.jogador.mundoX + pj.jogador.area_solida.x;
        pj.jogador.area_solida.y = pj.jogador.mundoY + pj.jogador.area_solida.y;

        evento_com_rectangulo.x = eventoCol * pj.tamanhoDaPeca + evento_com_rectangulo.x;
        evento_com_rectangulo.y = enventoLin * pj.tamanhoDaPeca + evento_com_rectangulo.y;

        if(pj.jogador.area_solida.intersects(evento_com_rectangulo)){
            if(pj.jogador.direcao.contentEquals(direcaoRequerida) || direcaoRequerida.contentEquals("any")){
                atingiu = true;
            }
        }

        pj.jogador.area_solida.x = pj.jogador.area_solida_padraoX;
        pj.jogador.area_solida.y = pj.jogador.area_solida_padraoY;
        evento_com_rectangulo.x = evento_com_rectangulo_padraoX;
        evento_com_rectangulo.y = evento_com_rectangulo_padraoY;

        return atingiu;

    }

    public void teleporte(int estado_do_jogo){

        pj.estado_do_jogo = estado_do_jogo;
        pj.ui.dialogo_atual = "Teleporte!";
        pj.jogador.mundoX = pj.tamanhoDaPeca * 40;
        pj.jogador.mundoY = pj.tamanhoDaPeca * 25;

    }

    public void danoDeArmadilha(int estado_do_jogo){

        pj.estado_do_jogo = estado_do_jogo;
        pj.ui.dialogo_atual = "Você caiu em uma armadilha!";
        pj.jogador.vida -= 1;

    }

    public void localDeCura(int estado_do_jogo){

        System.out.println("Curando!");

        if(pj.chaveManipuladora.enterPressionado == true){
            pj.estado_do_jogo = estado_do_jogo;
            pj.ui.dialogo_atual = "Você se curou!\nSua vida foi restaurada.";
            pj.jogador.vida = pj.jogador.vidaMaxima;
        }
    }

}