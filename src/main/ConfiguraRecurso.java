package main;

import entidades.NPC_random;
import objetos.OBJ_Porta;

public class ConfiguraRecurso {

    PainelDoJogo pj;

    public ConfiguraRecurso(PainelDoJogo pj){
        this.pj = pj;
    }

    public void setObjeto(){

        pj.obj[0] = new OBJ_Porta(pj);
        pj.obj[0].mundoX = pj.tamanhoDaPeca * 30;
        pj.obj[0].mundoY = pj.tamanhoDaPeca * 30;

        pj.obj[1] = new OBJ_Porta(pj);
        pj.obj[1].mundoX = pj.tamanhoDaPeca * 12;
        pj.obj[1].mundoY = pj.tamanhoDaPeca * 40;

    }

    public void setNPC(){

        pj.npc[0] = new NPC_random(pj);
        pj.npc[0].mundoX = pj.tamanhoDaPeca * 12;
        pj.npc[0].mundoY = pj.tamanhoDaPeca * 3;

        pj.npc[1] = new NPC_random(pj);
        pj.npc[1].mundoX = pj.tamanhoDaPeca * 13;
        pj.npc[1].mundoY = pj.tamanhoDaPeca * 4;

    }

}
