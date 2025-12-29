package monstros;

import java.util.Random;
import entidades.Entidade;
import main.PainelDoJogo;
import java.awt.Rectangle; 

public class MON_Slime extends Entidade{
    
    public MON_Slime(PainelDoJogo pj){

        super(pj);
        super.nome = "Slime";
        tipo = 2;

        velocidade = 1;
        vidaMaxima = 4;
        vida = vidaMaxima;

        area_solida = new Rectangle();
        area_solida.x = 3;
        area_solida.y = 18;
        area_solida.width = 42;
        area_solida.height = 30;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;

        getImagem();

    }

    public void getImagem() {
      
        baixo1 = configuracoes("/res/monstros/m1");
        baixo2 = configuracoes("/res/monstros/m2");
        cima1 = configuracoes("/res/monstros/m1");
        cima2 = configuracoes("/res/monstros/m2");
        esquerda1 = configuracoes("/res/monstros/m1");
        esquerda2 = configuracoes("/res/monstros/m2");
        direita1 = configuracoes("/res/monstros/m1");
        direita2 = configuracoes("/res/monstros/m2");

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
    }

}