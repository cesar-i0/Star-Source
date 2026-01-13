package entidades;

import java.awt.Rectangle;
import main.PainelDoJogo;
import objetos.OBJ_Escudo;
import objetos.OBJ_EscudoFerro;
import objetos.OBJ_Espada;
import objetos.OBJ_Pocao;

public class NPC_mercador extends Entidade {
     public NPC_mercador(PainelDoJogo pj){
        
        super(pj);

        direcao = "baixo";
        velocidade = 1;

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
        
        cima1 = configuracoes("/res/npc/merchan", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        cima2 = configuracoes("/res/npc/merchan", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        baixo1 = configuracoes("/res/npc/merchan", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        baixo2 = configuracoes("/res/npc/merchan", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        esquerda1 = configuracoes("/res/npc/merchan", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        esquerda2 = configuracoes("/res/npc/merchan", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        direita1 = configuracoes("/res/npc/merchan", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        direita2 = configuracoes("/res/npc/merchan", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        // cima1, cima2, baixo1, baixo2, esquerda1, esquerda2, direita1, direita2;

    }

    public void setDialogo(){

        dialogos[0] = "O que você está comprando ?";
       

    }
public void setitens(){ 
      inventario.add(new OBJ_Escudo(pj));

      inventario.add(new OBJ_EscudoFerro(pj));
      inventario.add(new OBJ_Espada(pj));
      inventario.add(new OBJ_Pocao(pj));
}

}
