package entidades;

import main.PainelDoJogo;
import java.awt.Rectangle;


public class NPC_VelhoIrineu extends Entidade {
    
    public NPC_VelhoIrineu(PainelDoJogo pj){
        
        super(pj);

        direcao = "estatico";


        velocidade = 0;

        area_solida =  new Rectangle();
        area_solida.x = 15;
        area_solida.y = 10;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
        area_solida.width = 18;
        area_solida.height = 30;
        
        getImagemDoNPC();
        setDialogo();

    }

    public void getImagemDoNPC(){
        
        estatico = configuracoes("/res/npc/irineuF1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // cima1 = configuracoes("/res/npc/irineuC1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // cima2 = configuracoes("/res/npc/irineuC2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // baixo1 = configuracoes("/res/npc/irineuF1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // baixo2 = configuracoes("/res/npc/irineuF2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // esquerda1 = configuracoes("/res/npc/irineuE1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // esquerda2 = configuracoes("/res/npc/irineuE2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // direita1 = configuracoes("/res/npc/irineuD1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // direita2 = configuracoes("/res/npc/irineuD2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // cima1, cima2, baixo1, baixo2, esquerda1, esquerda2, direita1, direita2;

    }

    public void setDialogo(){

        dialogos[0] = "Olá, meu jovem. O que o traz por essas\nterras tão distantes?";
        dialogos[1] = "Engraçado, não é? A gente passa a vida inteira\ntentando entender o caminho, mas ninguém\npergunta se o caminho entende a gente.";
        dialogos[2] = "Já fiquei parado aqui tempo demais… tempo\n suficiente pra ver heróis chegarem cheios\n de certezas… e partirem cheios de silêncio";
        dialogos[3] = "Tem momentos que a vida nos presenteia com \numa lista enorme de tarefas… e um prazo\nminúsculo para solucioná-las";
        dialogos[4] = "Chamam isso de desafio.";
        dialogos[5] = "Eu chamo de tentar colocar um dragão inteiro\ndentro de uma ampola.";
        dialogos[6] = "Alguns perguntam quem eu sou.\nOutros perguntam pra onde ir";
        dialogos[7] = "Eu respondo do mesmo jeito: andando.";
        dialogos[8] = "Porque no fim, toda escolha parece clara…\nsó depois que já ficou tarde demais pra voltar.";
        dialogos[9] = "Talvez você esteja esperando uma resposta";
        dialogos[10] = "Mas respostas são como mapas antigos:\nsempre faltam pedaços.";
        dialogos[11] = "Se decidir seguir em frente, siga leve.\nE se decidir ficar… fique em paz.";
        dialogos[12] = "…agora vá.\nO mundo não gosta de espectadores!";

    }

    @Override
    public void setAcao(){
        // trava_de_contador_de_acao++;

        // A cada dois segundos seu movimento é trocado
        // if(trava_de_contador_de_acao == 120){

        //     Random aleatorio = new Random();
        //     int i = aleatorio.nextInt(100) + 1; // Seleciona um número de 1 até 100
    
        //     if(i <= 35){
        //         direcao = "cima";
        //     }
        //     if(i > 25 && i <= 50){
        //         direcao = "baixo";
        //     }
        //     if(i > 50 && i <= 75){
        //         direcao = "esquerda";
        //     }
        //     if(i> 75 && i <= 100){
        //         direcao = "direita";
        //     }

        //     trava_de_contador_de_acao = 0;
        // }

    }

    @Override
    public void falar(){
        // super.falar();
        if(dialogos[index_de_dialogo] == null) index_de_dialogo = 0;
        pj.ui.dialogo_atual = dialogos[index_de_dialogo];
        index_de_dialogo++;
    }


}
