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

    // Este método verifica se o jogador está colidindo com algum objeto e se estiver e muda o index do objeto
    public int verificaObjeto(Entidade entidade, boolean joagador){

        int index = 999;

        for(int i = 0; i < pj.obj.length; i++){

            if(pj.obj[i] != null){
                // Pega a posição área sólida das entidades
                entidade.area_solida.x = entidade.mundoX + entidade.area_solida.x;
                entidade.area_solida.y = entidade.mundoY + entidade.area_solida.y;
                // Pega a posição área sólida dos objetos
                pj.obj[i].area_solida.x = pj.obj[i].mundoX + pj.obj[i].area_solida.x;
                pj.obj[i].area_solida.y = pj.obj[i].mundoY + pj.obj[i].area_solida.y;

                // Simulamos o movimento da entidade e verificamos onde ela vai estar após o movimento.
                switch (entidade.direcao) {
                    case "cima":
                        entidade.area_solida.y -= entidade.velocidade; break;
                    case "baixo":
                        entidade.area_solida.y += entidade.velocidade; break;
                    case "esquerda":
                        entidade.area_solida.x -= entidade.velocidade; break;
                    case "direita":
                        entidade.area_solida.x += entidade.velocidade; break;
                }
                    if(entidade.area_solida.intersects(pj.obj[i].area_solida)){
                            // System.out.println("Colisão para direita!");
                            if(pj.obj[i].colisao == true) entidade.colisao_ligada = true;
                            if(joagador == true) index = i;
                        }

                entidade.area_solida.x = entidade.area_solida_padraoX;
                entidade.area_solida.y = entidade.area_solida_padraoY;
                pj.obj[i].area_solida.x = pj.obj[i].area_solida_padraoX;
                pj.obj[i].area_solida.y = pj.obj[i].area_solida_padraoY;

            }

        }

        return index;

    }

    // Verifica colisão de NPC ou monstro
    public int verificaEntidade(Entidade entidade, Entidade[] alvo){
       
        int index = 999;

        for(int i = 0; i < alvo.length; i++){

            if(alvo[i] != null){
                // Pega a posição área sólida das entidades
                entidade.area_solida.x = entidade.mundoX + entidade.area_solida.x;
                entidade.area_solida.y = entidade.mundoY + entidade.area_solida.y;
                // Pega a posição área sólida dos objetos
                alvo[i].area_solida.x = alvo[i].mundoX + alvo[i].area_solida.x;
                alvo[i].area_solida.y = alvo[i].mundoY + alvo[i].area_solida.y;

                // Simulamos o movimento da entidade e verificamos onde ela vai estar após o movimento.
                switch (entidade.direcao) {
                    case "cima":
                        entidade.area_solida.y -= entidade.velocidade; break;
                    case "baixo":
                        entidade.area_solida.y += entidade.velocidade; break;
                    case "esquerda":
                        entidade.area_solida.x -= entidade.velocidade; break;
                    case "direita":
                        entidade.area_solida.x += entidade.velocidade; break;
                }
                
                if(entidade.area_solida.intersects(alvo[i].area_solida)){
                    if(alvo[i] != entidade){
                        entidade.colisao_ligada = true;
                        index = i;
                    }
                }

                entidade.area_solida.x = entidade.area_solida_padraoX;
                entidade.area_solida.y = entidade.area_solida_padraoY;
                alvo[i].area_solida.x = alvo[i].area_solida_padraoX;
                alvo[i].area_solida.y = alvo[i].area_solida_padraoY;

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

    public void verificaMonstro(Entidade entidade) {
        
        throw new UnsupportedOperationException("Unimplemented method 'verificaMonstro'");
    }

}
