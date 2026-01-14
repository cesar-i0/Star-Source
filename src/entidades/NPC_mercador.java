package entidades;

import java.awt.Rectangle;
import main.PainelDoJogo;
import objetos.OBJ_EscudoEstelar;
import objetos.OBJ_PocaoDeCura;
import objetos.OBJ_PocaoDeMana;

public class NPC_mercador extends Entidade {
     public NPC_mercador(PainelDoJogo pj){
        
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
        setitens();
    }

    public void getImagemDoNPC(){
        
        estatico = configuracoes("/res/npc/merchant", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // cima1 = configuracoes("/res/npc/merchant", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // cima2 = configuracoes("/res/npc/merchant", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // baixo1 = configuracoes("/res/npc/merchant", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // baixo2 = configuracoes("/res/npc/merchant", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // esquerda1 = configuracoes("/res/npc/merchant", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // esquerda2 = configuracoes("/res/npc/merchant", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // direita1 = configuracoes("/res/npc/merchant", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // direita2 = configuracoes("/res/npc/merchant", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // cima1, cima2, baixo1, baixo2, esquerda1, esquerda2, direita1, direita2;

    }

    public void setDialogo(){
        dialogos[0] = "O que você está comprando ?";
    }

    public void setitens(){ 
        inventario.add(new OBJ_EscudoEstelar(pj));
        inventario.add(new OBJ_PocaoDeCura(pj));
        inventario.add(new OBJ_PocaoDeMana(pj));
    }

    @Override
    public void setAcao(){

    }

    @Override
    public void falar(){
        // super.falar();
        if(dialogos[index_de_dialogo] == null) index_de_dialogo = 0;
        pj.ui.dialogo_atual = dialogos[index_de_dialogo];
        index_de_dialogo++;
        pj.estado_do_jogo = pj.estado_de_troca;
        pj.ui.npc = this;
    }

}