package monstros;

import java.util.Random;
import entidades.Entidade;
import main.PainelDoJogo;
import objetos.OBJ_BolaDeSlime;
import objetos.OBJ_Coracao;
import objetos.OBJ_CristalMana;
import objetos.OBJ_Moeda;

import java.awt.Rectangle; 

public class MON_Slime extends Entidade{

    PainelDoJogo pj;

    public MON_Slime(PainelDoJogo pj){

        super(pj);
        this.pj = pj;
        super.nome = "Slime";
        tipo = tipo_monstro;

        velocidade = 1;
        vidaMaxima = 6;
        vida = vidaMaxima;
        ataques = 0.5;
        defesa = 0;
        exp = 2;
        projeteis = new OBJ_BolaDeSlime(pj);

        area_solida = new Rectangle();
        area_solida.x = 5;
        area_solida.y = 15;
        area_solida.width = 32;
        area_solida.height = 20;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;

        getImagem();

    }

    public void getImagem() {
      
        baixo1 = configuracoes("/res/monstros/m1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        baixo2 = configuracoes("/res/monstros/m2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        cima1 = configuracoes("/res/monstros/m1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        cima2 = configuracoes("/res/monstros/m2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        esquerda1 = configuracoes("/res/monstros/m1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        esquerda2 = configuracoes("/res/monstros/m2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        direita1 = configuracoes("/res/monstros/m1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        direita2 = configuracoes("/res/monstros/m2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);

    }

    public void setAcao() {
        
        trava_de_contador_de_acao++;

        // A cada dois segundos seu movimento é trocado
        if(trava_de_contador_de_acao == 120){

            Random aleatorio = new Random();
            int i = aleatorio.nextInt(100) + 1; // Seleciona um número de 1 até 100
    
            if(i <= 35){
                direcao = "cima";
            }
            if(i > 25 && i <= 50){
                direcao = "baixo";
            }
            if(i > 50 && i <= 75){
                direcao = "esquerda";
            }
            if(i> 75 && i <= 100){
                direcao = "direita";
            }

            trava_de_contador_de_acao = 0;
        }

        int i = new Random().nextInt(100)+1;
        if(i > 99 && projeteis.vivo == false && contador_de_tiro_viavel == 30){
            projeteis.set(mundoX, mundoY, direcao, true, this);
            pj.listaDeProjeteisDePecas.add(projeteis);
            contador_de_tiro_viavel = 0;
        }

    }

    //Método que faz o monstro ir pra direção que o jogador está virado ao receber dano
    public void reacaoDano(){
        trava_de_contador_de_acao = 0;
        direcao = pj.jogador.direcao;      
        }

        public void verificaDrop(){

            //Quando ele morre
            int i = new Random().nextInt(100)+1;

            //Quando ele morre dropa moeda
            if(i<50){
                dropaItem(new OBJ_Moeda(pj));
            }

            if( i >= 50 && i < 75){
                dropaItem(new OBJ_Coracao(pj));
            }
        
        if( i >= 75 && i < 100){
                dropaItem(new OBJ_CristalMana(pj));
            }
        
    }
}

