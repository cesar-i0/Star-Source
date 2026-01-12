package main;

public class ManipuladorDeEvento {

    PainelDoJogo pj;
    ReacaoDeEvento reacaoDeEvento[][];

    int EventoPrevioX, EventoPrevioY;
    boolean evento_possivel_de_tocar = true; // Torna possível reativar um evento após ocorrer(condicionalmente)

    public ManipuladorDeEvento(PainelDoJogo pj){

        this.pj = pj;

        // Temos um evento com retângulos em cada peça do mapa
        reacaoDeEvento = new ReacaoDeEvento[pj.maxColunasDoMundo][pj.maxLinhaDoMundo];

        int col = 0, lin = 0;

        while (col < pj.maxColunasDoMundo && lin < pj.maxLinhaDoMundo){
            reacaoDeEvento[lin][col] = new ReacaoDeEvento();
            reacaoDeEvento[lin][col].x = 23;
            reacaoDeEvento[lin][col].y = 23;
            reacaoDeEvento[lin][col].width = 2;
            reacaoDeEvento[lin][col].height = 2;
            reacaoDeEvento[lin][col].evento_com_rectangulo_padraoX = reacaoDeEvento[lin][col].x;
            reacaoDeEvento[lin][col].evento_com_rectangulo_padraoY = reacaoDeEvento[lin][col].y;

            col++;
            if(col == pj.maxColunasDoMundo){
                col = 0;
                lin++;
            }
        }

    }

    public void verificaEvento(){

        // Veridica se o jogador está a mais de uma peça de distância do último evento
        int xDistancia = Math.abs(pj.jogador.mundoX - EventoPrevioX);
        int yDistancia = Math.abs(pj.jogador.mundoY - EventoPrevioY);
        int distancia = Math.max(xDistancia, yDistancia);

        if (distancia > pj.tamanhoDaPeca) evento_possivel_de_tocar = true;

        if (evento_possivel_de_tocar == true){

            // Local da armadilha
            if(atingiu(1, 1, "cima") == true) danoDeArmadilha(1, 1,pj.estado_de_dialogo);
            // Local de cura
            if(atingiu(1, 3, "cima") == true) localDeCura(pj.estado_de_dialogo);
            // Local de teleporte
            if(atingiu(35, 17, "cima") == true) teleporte(pj.estado_de_dialogo);

        }

    }

    public boolean atingiu(int lin, int col, String direcaoRequerida){
        
        boolean atingiu = false;

        pj.jogador.area_solida.x = pj.jogador.mundoX + pj.jogador.area_solida_padraoX;
        pj.jogador.area_solida.y = pj.jogador.mundoY + pj.jogador.area_solida_padraoY;

        reacaoDeEvento[lin][col].x = col * pj.tamanhoDaPeca + reacaoDeEvento[lin][col].x;
        reacaoDeEvento[lin][col].y = lin * pj.tamanhoDaPeca + reacaoDeEvento[lin][col].y;

        if(pj.jogador.area_solida.intersects(reacaoDeEvento[lin][col])){
            if(pj.jogador.direcao.contentEquals(direcaoRequerida) || direcaoRequerida.contentEquals("any")){
                
                atingiu = true;
                EventoPrevioX = pj.jogador.mundoX;
                EventoPrevioY = pj.jogador.mundoY;

            }
        }

        pj.jogador.area_solida.x = pj.jogador.area_solida_padraoX;
        pj.jogador.area_solida.y = pj.jogador.area_solida_padraoY;
        reacaoDeEvento[lin][col].x = reacaoDeEvento[lin][col].evento_com_rectangulo_padraoX;
        reacaoDeEvento[lin][col].y = reacaoDeEvento[lin][col].evento_com_rectangulo_padraoY;

        return atingiu;

    }

    public void teleporte(int estado_do_jogo){

        pj.estado_do_jogo = estado_do_jogo;
        pj.ui.dialogo_atual = "Você teletransportou!";
        pj.jogador.mundoX = pj.tamanhoDaPeca * 34;
        pj.jogador.mundoY = pj.tamanhoDaPeca * 40;
        
    }

    public void danoDeArmadilha(int lin, int col, int estado_do_jogo){

        pj.estado_do_jogo = estado_do_jogo;
        pj.ui.dialogo_atual = "Você caiu em uma armadilha!";
        pj.jogador.vida--;
        evento_possivel_de_tocar = false;

    }

    public void localDeCura(int estado_do_jogo){
        if(pj.chaveManipuladora.enterPressionado == true){
            pj.estado_do_jogo = estado_do_jogo;
            pj.jogador.ataqueCancelado = true;
            pj.ui.dialogo_atual = "Você se curou!\nSua vida foi restaurada.";
            pj.jogador.mana = pj.jogador.mana_max;
            pj.jogador.vida = pj.jogador.vidaMaxima;
            pj.configura_recurso.setMonstro();
        }
    }
}
