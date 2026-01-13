package main;

public class ManipuladorDeEvento {

    PainelDoJogo pj;
    ReacaoDeEvento reacaoDeEvento[][][];

    int EventoPrevioX, EventoPrevioY;
    boolean evento_possivel_de_tocar = true; // Torna possível reativar um evento após ocorrer(condicionalmente)

    public ManipuladorDeEvento(PainelDoJogo pj){

        this.pj = pj;

        // Temos um evento com retângulos em cada peça do mapa
        reacaoDeEvento = new ReacaoDeEvento[pj.maximomapa][pj.maxColunasDoMundo][pj.maxLinhaDoMundo];
         int mapa = 0;
        int col = 0, lin = 0;

        while (mapa < pj.maximomapa && col < pj.maxColunasDoMundo && lin < pj.maxLinhaDoMundo){
            reacaoDeEvento[mapa][lin][col] = new ReacaoDeEvento();
            reacaoDeEvento[mapa][lin][col].x = 23;
            reacaoDeEvento[mapa][lin][col].y = 23;
            reacaoDeEvento[mapa][lin][col].width = 2;
            reacaoDeEvento[mapa][lin][col].height = 2;
            reacaoDeEvento[mapa][lin][col].evento_com_rectangulo_padraoX = reacaoDeEvento[mapa][lin][col].x;
            reacaoDeEvento[mapa][lin][col].evento_com_rectangulo_padraoY = reacaoDeEvento[mapa][lin][col].y;

            col++;
            if(col == pj.maxColunasDoMundo){
                col = 0;
                lin++;
                if(lin == pj.maxLinhaDoMundo){
                    lin = 0;
                    mapa++;
                }
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
            if(atingiu(0, 1, 1, "cima") == true) danoDeArmadilha(pj.estado_de_dialogo);
            // Local de cura
           else if(atingiu(0, 1, 3, "cima") == true) localDeCura(pj.estado_de_dialogo);
            // Local de teleporte
          //  if(atingiu(0, 1, 5, "cima") == true) teleporte(pj.estado_de_dialogo);
            // Local de teleporte 2
           else if(atingiu(0, 9, 9, "any") == true) {teleporte(1 , 20, 10);}
           else if(atingiu(1 ,12 , 10, "any") == true) {teleporte(0 , 10, 10);}
        }

    }

    public boolean atingiu(int mapa ,int lin, int col, String direcaoRequerida){
        
        boolean atingiu = false;
    if (mapa == pj.mapaatual){
        pj.jogador.area_solida.x = pj.jogador.mundoX + pj.jogador.area_solida_padraoX;
        pj.jogador.area_solida.y = pj.jogador.mundoY + pj.jogador.area_solida_padraoY;

        reacaoDeEvento[mapa][lin][col].x = col * pj.tamanhoDaPeca + reacaoDeEvento[mapa][lin][col].x;
        reacaoDeEvento[mapa][lin][col].y = lin * pj.tamanhoDaPeca + reacaoDeEvento[mapa][lin][col].y;

        if(pj.jogador.area_solida.intersects(reacaoDeEvento[mapa][lin][col] )){
            if(pj.jogador.direcao.contentEquals(direcaoRequerida) || direcaoRequerida.contentEquals("any")){
                
                atingiu = true;
                EventoPrevioX = pj.jogador.mundoX;
                EventoPrevioY = pj.jogador.mundoY;

            }
        }

        pj.jogador.area_solida.x = pj.jogador.area_solida_padraoX;
        pj.jogador.area_solida.y = pj.jogador.area_solida_padraoY;
        reacaoDeEvento[mapa][lin][col].x = reacaoDeEvento[mapa][lin][col].evento_com_rectangulo_padraoX;
        reacaoDeEvento[mapa][lin][col].y = reacaoDeEvento[mapa][lin][col].evento_com_rectangulo_padraoY;

        

    }
    return atingiu;
}

   /*  public void teleporte(int estado_do_jogo){

        pj.estado_do_jogo = estado_do_jogo;
        pj.ui.dialogo_atual = "Você teletransportou!";
        pj.jogador.mundoX = pj.tamanhoDaPeca * 34;
        pj.jogador.mundoY = pj.tamanhoDaPeca * 40;
        
    }*/

    public void danoDeArmadilha( int estado_do_jogo){

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

public void teleporte (int mapa , int lin , int col){
    pj.mapaatual = mapa;
    pj.jogador.mundoX = col * pj.tamanhoDaPeca;
    pj.jogador.mundoY = lin * pj.tamanhoDaPeca;
    EventoPrevioX = pj.jogador.mundoX;
    EventoPrevioY = pj.jogador.mundoY;
    evento_possivel_de_tocar = false;

}


}

