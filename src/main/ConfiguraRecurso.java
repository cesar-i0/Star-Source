package main;

import entidades.NPC_random;

public class ConfiguraRecurso {

    PainelDoJogo pj;

    public ConfiguraRecurso(PainelDoJogo pj){
        this.pj = pj;
    }

    public void setObjeto(){

        

    }

    public void setNPC(){

        pj.npc[0] = new NPC_random(pj);
        pj.npc[0].mundoX = pj.tamanhoDaPeca * 21;
        pj.npc[0].mundoY = pj.tamanhoDaPeca * 21;

    }

}
