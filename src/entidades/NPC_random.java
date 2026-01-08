package entidades;

import main.PainelDoJogo;
import java.awt.Rectangle;
import java.util.Random;

public class NPC_random extends Entidade {
    
    public NPC_random(PainelDoJogo pj){
        
        super(pj);

        direcao = "baixo";
        velocidade = 1;

        area_solida =  new Rectangle();
        area_solida.x = 8;
        area_solida.y = 8;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
        area_solida.width = 32;
        area_solida.height = 32;
        
        getImagemDoNPC();
        setDialogo();

    }

    public void getImagemDoNPC(){
        
        cima1 = configuracoes("/res/npc/irineuC1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        cima2 = configuracoes("/res/npc/irineuC2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        baixo1 = configuracoes("/res/npc/irineuF1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        baixo2 = configuracoes("/res/npc/irineuF2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        esquerda1 = configuracoes("/res/npc/irineuE1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        esquerda2 = configuracoes("/res/npc/irineuE2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        direita1 = configuracoes("/res/npc/irineuD1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        direita2 = configuracoes("/res/npc/irineuD2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // cima1, cima2, baixo1, baixo2, esquerda1, esquerda2, direita1, direita2;

    }

    public void setDialogo(){

        dialogos[0] = "Olá Visitante!\nO que trás você em minhas terras?";
        dialogos[1] = "Você parece perdido,\nprecisa de algum suporte?";
        dialogos[2] = "Você tem talento!\nContinue se esforçando";
        dialogos[3] = "Até mais!";

    }

    @Override
    public void setAcao(){

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

    @Override
    public void falar(){
        super.falar();    
    }


}
