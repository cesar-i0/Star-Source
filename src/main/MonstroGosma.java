package main;
import entidades.Entidade;


public class MonstroGosma extends Entidade{

    public MonstroGosma(main.PainelDoJogo pj){
        super(pj);
        
        type = 2;
        super.nome = "Monstro Gosma";

        velocidade = 1;
        vidaMaxima = 4;
        vida = vidaMaxima;

        area_solida.x = 3;
        area_solida.y = 18;
        area_solida.width = 42;
        area_solida.height = 30;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
    }

    public void getImagem() {
      
         baixo1 = configuracoes("res/monstros/Monstro_Gosma");
        baixo2 = configuracoes("res/monstros/Monstro_Gosma2");
        cima1 = configuracoes("res/monstros/Monstro_Gosma");
        cima2 = configuracoes("res/monstros/Monstro_Gosma2");
        esquerda1 = configuracoes("res/monstros/Monstro_Gosma");
        esquerda2 = configuracoes("res/monstros/Monstro_Gosma2");
        direita1 = configuracoes("res/monstros/Monstro_Gosma");
        direita2 = configuracoes("res/monstros/Monstro_Gosma2");

    }

    public void setAcao() {
        // Lógica simples para mover o monstro gosma aleatoriamente
        int randomNum = (int)(Math.random() * 100);

        if(randomNum < 25) {
            direcao = "cima";
        } else if(randomNum < 50) {
            direcao = "baixo";
        } else if(randomNum < 75) {
            direcao = "esquerda";
        } else {
            direcao = "direita";
        }
    }

}

