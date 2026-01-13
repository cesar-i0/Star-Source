package main;

import entidades.Entidade;
import java.awt.Rectangle;

public class VerificaColisao {

    PainelDoJogo pj;

    public VerificaColisao(PainelDoJogo pj){
        this.pj = pj;
    }

    public void verificaPeca(Entidade entidade){

        int esquerdaDaEntidadeMundoX = entidade.mundoX + entidade.area_solida.x;
        int direitaDaEntidadeMundoX = entidade.mundoX + entidade.area_solida.x + entidade.area_solida.width;
        int topoDaEntidadeMundoY = entidade.mundoY + entidade.area_solida.y;
        int baseDaEntidadeMundoY = entidade.mundoY + entidade.area_solida.y + entidade.area_solida.height;

        int esquerdaDaEntidadeColuna = esquerdaDaEntidadeMundoX / pj.tamanhoDaPeca;
        int direitaDaEntidadeColuna = direitaDaEntidadeMundoX / pj.tamanhoDaPeca;
        int topoDaEntidadeLinha = topoDaEntidadeMundoY / pj.tamanhoDaPeca;
        int baseDaEntidadeLinha = baseDaEntidadeMundoY / pj.tamanhoDaPeca;

        int pecaNum1 = 0, pecaNum2 = 0;

        // Vai verificar onde o jogador vai estar indo e impedir caso não seja possível atravessar
        switch (entidade.direcao) {
            case "cima":
                topoDaEntidadeLinha = (topoDaEntidadeMundoY - entidade.velocidade) / pj.tamanhoDaPeca;
                pecaNum1 = pj.peca_tela.numeroDaPecaDoMundo[pj.mapaatual][esquerdaDaEntidadeColuna][topoDaEntidadeLinha];
                pecaNum2 = pj.peca_tela.numeroDaPecaDoMundo[pj.mapaatual][direitaDaEntidadeColuna][topoDaEntidadeLinha];
                if(pj.peca_tela.peca[pecaNum1].colisao == true || pj.peca_tela.peca[pecaNum2].colisao == true){
                    entidade.colisao_ligada = true;
                }
                break;
            case "baixo":
                baseDaEntidadeLinha = (baseDaEntidadeMundoY + entidade.velocidade) / pj.tamanhoDaPeca;
                pecaNum1 = pj.peca_tela.numeroDaPecaDoMundo[pj.mapaatual][esquerdaDaEntidadeColuna][baseDaEntidadeLinha];
                pecaNum2 = pj.peca_tela.numeroDaPecaDoMundo[pj.mapaatual][direitaDaEntidadeColuna][baseDaEntidadeLinha];
                if(pj.peca_tela.peca[pecaNum1].colisao == true || pj.peca_tela.peca[pecaNum2].colisao == true){
                    entidade.colisao_ligada = true;
                }
                break;
            case "esquerda":
                esquerdaDaEntidadeColuna = (esquerdaDaEntidadeMundoX - entidade.velocidade) / pj.tamanhoDaPeca;
                pecaNum1 = pj.peca_tela.numeroDaPecaDoMundo[pj.mapaatual][esquerdaDaEntidadeColuna][topoDaEntidadeLinha];
                pecaNum2 = pj.peca_tela.numeroDaPecaDoMundo[pj.mapaatual][esquerdaDaEntidadeColuna][baseDaEntidadeLinha];
                if(pj.peca_tela.peca[pecaNum1].colisao == true || pj.peca_tela.peca[pecaNum2].colisao == true){
                    entidade.colisao_ligada = true;
                }
                break;
            case "direita":
                esquerdaDaEntidadeColuna = (direitaDaEntidadeMundoX + entidade.velocidade) / pj.tamanhoDaPeca;
                pecaNum1 = pj.peca_tela.numeroDaPecaDoMundo[pj.mapaatual][direitaDaEntidadeColuna][topoDaEntidadeLinha];
                pecaNum2 = pj.peca_tela.numeroDaPecaDoMundo[pj.mapaatual][direitaDaEntidadeColuna][baseDaEntidadeLinha];
                if(pj.peca_tela.peca[pecaNum1].colisao == true || pj.peca_tela.peca[pecaNum2].colisao == true){
                    entidade.colisao_ligada = true;
                }
                break;
        }

    }

    // Este método verifica se o jogador está colidindo com algum objeto e se estiver e muda o index do objeto
    public int verificaObjeto(Entidade entidade, boolean joagador){

        int index = 999;

        for(int i = 0; i < pj.obj[1].length; i++){

            if(pj.obj[pj.mapaatual][i] == null) {
                continue;
            }

            // Se a área sólida do objeto não foi inicializada (nula), ignoramos este objeto
            if(pj.obj[pj.mapaatual][i].area_solida == null){
                continue;
            }

            if(entidade.area_solida == null){
                // Entidade sem área sólida configurada, pula
                continue;
            }

            // Pega a posição área sólida das entidades
            entidade.area_solida.x = entidade.mundoX + entidade.area_solida.x;
            entidade.area_solida.y = entidade.mundoY + entidade.area_solida.y;
            // Pega a posição área sólida dos objetos
            pj.obj[pj.mapaatual][i].area_solida.x = pj.obj[pj.mapaatual][i].mundoX + pj.obj[pj.mapaatual][i].area_solida.x;
            pj.obj[pj.mapaatual][i].area_solida.y = pj.obj[pj.mapaatual][i].mundoY + pj.obj[pj.mapaatual][i].area_solida.y;

            // Simulamos o movimento da entidade e verificamos onde ela vai estar após o movimento.
            switch (entidade.direcao) {
                case "cima":
                    entidade.area_solida.y -= entidade.velocidade;
                    break;
                case "baixo":
                    entidade.area_solida.y += entidade.velocidade;
                    break;
                case "esquerda":
                    entidade.area_solida.x -= entidade.velocidade;
                    break;
                case "direita":
                    entidade.area_solida.x += entidade.velocidade;
                    break;
            }
                if(entidade.area_solida.intersects(pj.obj[pj.mapaatual][i].area_solida)){
                        // System.out.println("Colisão para direita!");
                        if(pj.obj[pj.mapaatual][i].colisao == true) entidade.colisao_ligada = true;
                        if(joagador == true) index = i;
                    }

            entidade.area_solida.x = entidade.area_solida_padraoX;
            entidade.area_solida.y = entidade.area_solida_padraoY;
            pj.obj[pj.mapaatual][i].area_solida.x = pj.obj[pj.mapaatual][i].area_solida_padraoX;
            pj.obj[pj.mapaatual][i].area_solida.y = pj.obj[pj.mapaatual][i].area_solida_padraoY;

        }

        return index;

    }

    // Verifica colisão de NPC ou monstro
    public int verificaEntidade(Entidade entidade, Entidade[][] alvo){
       
        int index = 999;

        for(int i = 0; i < alvo[1].length; i++){

            if(alvo [pj.mapaatual][i] != null){
                // Pega a posição área sólida das entidades
                entidade.area_solida.x = entidade.mundoX + entidade.area_solida.x;
                entidade.area_solida.y = entidade.mundoY + entidade.area_solida.y;
                // Pega a posição área sólida dos objetos
                alvo[pj.mapaatual][i].area_solida.x = alvo[pj.mapaatual][i].mundoX + alvo[pj.mapaatual][i].area_solida.x;
                alvo[pj.mapaatual][i].area_solida.y = alvo[pj.mapaatual][i].mundoY + alvo[pj.mapaatual][i].area_solida.y;

                // Simulamos o movimento da entidade e verificamos onde ela vai estar após o movimento.
                switch (entidade.direcao) {
                    case "cima":
                        entidade.area_solida.y -= entidade.velocidade;
                        break;
                    case "baixo":
                        entidade.area_solida.y += entidade.velocidade;
                        break;
                    case "esquerda":
                        entidade.area_solida.x -= entidade.velocidade;
                        break;
                    case "direita":
                        entidade.area_solida.x += entidade.velocidade;
                        break;
                }
                
                if(entidade.area_solida.intersects(alvo[pj.mapaatual][i].area_solida)){
                    if(alvo[pj.mapaatual][i] != entidade){
                        entidade.colisao_ligada = true;
                        index = i;
                    }
                }

                entidade.area_solida.x = entidade.area_solida_padraoX;
                entidade.area_solida.y = entidade.area_solida_padraoY;
                alvo[pj.mapaatual][i].area_solida.x = alvo[pj.mapaatual][i].area_solida_padraoX;
                alvo[pj.mapaatual][i].area_solida.y = alvo[pj.mapaatual][i].area_solida_padraoY;

            }

        }

        return index; 
    }
    

    public boolean verificaJogador(Entidade entidade){

        boolean contatoComJogador = false;

        // Pega a posição área sólida das entidades
        entidade.area_solida.x = entidade.mundoX + entidade.area_solida.x;
        entidade.area_solida.y = entidade.mundoY + entidade.area_solida.y;
        // Pega a posição área sólida dos objetos
        pj.jogador.area_solida.x = pj.jogador.mundoX + pj.jogador.area_solida.x;
        pj.jogador.area_solida.y = pj.jogador.mundoY + pj.jogador.area_solida.y;

        // Simulamos o movimento da entidade e verificamos onde ela vai estar após o movimento.
        switch (entidade.direcao) {
            case "cima":
                entidade.area_solida.y -= entidade.velocidade;
                break;
            case "baixo":
                entidade.area_solida.y += entidade.velocidade;
                break;
            case "esquerda":
                entidade.area_solida.x -= entidade.velocidade;
                break;
            case "direita":
                entidade.area_solida.x += entidade.velocidade;
                break;
        }

        if(entidade.area_solida.intersects(pj.jogador.area_solida)){
                    entidade.colisao_ligada = true;
                    contatoComJogador = true;
        }

        entidade.area_solida.x = entidade.area_solida_padraoX;
        entidade.area_solida.y = entidade.area_solida_padraoY;
        pj.jogador.area_solida.x = pj.jogador.area_solida_padraoX;
        pj.jogador.area_solida.y = pj.jogador.area_solida_padraoY;

        return contatoComJogador;

    }
    
    public boolean ataqueBateNaParede(Rectangle areaAtaque) {

    int esquerdaMundoX = areaAtaque.x;
    int direitaMundoX  = areaAtaque.x + areaAtaque.width;
    int topoMundoY     = areaAtaque.y;
    int baseMundoY     = areaAtaque.y + areaAtaque.height;

    int colunaEsq = esquerdaMundoX / pj.tamanhoDaPeca;
    int colunaDir = direitaMundoX  / pj.tamanhoDaPeca;
    int linhaCima = topoMundoY     / pj.tamanhoDaPeca;
    int linhaBaixo= baseMundoY     / pj.tamanhoDaPeca;

    int pecaNum1, pecaNum2;

    // Verifica os 4 cantos do ataque
    pecaNum1 = pj.peca_tela.numeroDaPecaDoMundo[colunaEsq][linhaCima];
    pecaNum2 = pj.peca_tela.numeroDaPecaDoMundo[colunaDir][linhaCima];
    if (pj.peca_tela.peca[pecaNum1].colisao || pj.peca_tela.peca[pecaNum2].colisao) {
        return true;
    }

    pecaNum1 = pj.peca_tela.numeroDaPecaDoMundo[colunaEsq][linhaBaixo];
    pecaNum2 = pj.peca_tela.numeroDaPecaDoMundo[colunaDir][linhaBaixo];
    if (pj.peca_tela.peca[pecaNum1].colisao || pj.peca_tela.peca[pecaNum2].colisao) {
        return true;
    }

    return false;
}


}
