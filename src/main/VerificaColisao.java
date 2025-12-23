package main;

import entidades.Entidade;

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
                pecaNum1 = pj.peca_tela.numeroDaPecaDoMundo[esquerdaDaEntidadeColuna][topoDaEntidadeLinha];
                pecaNum2 = pj.peca_tela.numeroDaPecaDoMundo[direitaDaEntidadeColuna][topoDaEntidadeLinha];
                if(pj.peca_tela.peca[pecaNum1].colisao == true || pj.peca_tela.peca[pecaNum2].colisao == true){
                    entidade.colisao_ligada = true;
                }
                break;
            case "baixo":
                baseDaEntidadeLinha = (baseDaEntidadeMundoY + entidade.velocidade) / pj.tamanhoDaPeca;
                pecaNum1 = pj.peca_tela.numeroDaPecaDoMundo[esquerdaDaEntidadeColuna][baseDaEntidadeLinha];
                pecaNum2 = pj.peca_tela.numeroDaPecaDoMundo[direitaDaEntidadeColuna][baseDaEntidadeLinha];
                if(pj.peca_tela.peca[pecaNum1].colisao == true || pj.peca_tela.peca[pecaNum2].colisao == true){
                    entidade.colisao_ligada = true;
                }
                break;
            case "esquerda":
                esquerdaDaEntidadeColuna = (esquerdaDaEntidadeMundoX - entidade.velocidade) / pj.tamanhoDaPeca;
                pecaNum1 = pj.peca_tela.numeroDaPecaDoMundo[esquerdaDaEntidadeColuna][topoDaEntidadeLinha];
                pecaNum2 = pj.peca_tela.numeroDaPecaDoMundo[esquerdaDaEntidadeColuna][baseDaEntidadeLinha];
                if(pj.peca_tela.peca[pecaNum1].colisao == true || pj.peca_tela.peca[pecaNum2].colisao == true){
                    entidade.colisao_ligada = true;
                }
                break;
            case "direita":
                esquerdaDaEntidadeColuna = (direitaDaEntidadeMundoX + entidade.velocidade) / pj.tamanhoDaPeca;
                pecaNum1 = pj.peca_tela.numeroDaPecaDoMundo[direitaDaEntidadeColuna][topoDaEntidadeLinha];
                pecaNum2 = pj.peca_tela.numeroDaPecaDoMundo[direitaDaEntidadeColuna][baseDaEntidadeLinha];
                if(pj.peca_tela.peca[pecaNum1].colisao == true || pj.peca_tela.peca[pecaNum2].colisao == true){
                    entidade.colisao_ligada = true;
                }
                break;
        }

    }

}
