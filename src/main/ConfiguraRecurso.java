package main;

import entidades.NPC_random;



public class ConfiguraRecurso {

    PainelDoJogo pj;

    public ConfiguraRecurso(PainelDoJogo pj){
        this.pj = pj;
    }


    public void setMonstro(){
        
        pj.monstros[0] = new MonstroGosma(pj);
        pj.monstros[0].mundoX = pj.tamanhoDaPeca * 21;
        pj.monstros[0].mundoY = pj.tamanhoDaPeca * 21;

        pj.monstros[1] = new MonstroGosma(pj);
        pj.monstros[1].mundoX = pj.tamanhoDaPeca * 21;
        pj.monstros[1].mundoY = pj.tamanhoDaPeca * 21;

        pj.monstros[2] = new MonstroGosma(pj);
        pj.monstros[2].mundoX = pj.tamanhoDaPeca * 21;
        pj.monstros[2].mundoY = pj.tamanhoDaPeca * 21;
    }

    


    public void setObjeto(){

        

    }

    public void setNPC(){

        pj.npc[0] = new NPC_random(pj);
        pj.npc[0].mundoX = pj.tamanhoDaPeca * 21;
        pj.npc[0].mundoY = pj.tamanhoDaPeca * 21;


        pj.npc[1] = new NPC_random(pj);
        pj.npc[1].mundoX = pj.tamanhoDaPeca * 21;
        pj.npc[1].mundoY = pj.tamanhoDaPeca * 21;

        pj.npc[2] = new NPC_random(pj);
        pj.npc[2].mundoX = pj.tamanhoDaPeca * 21;
        pj.npc[2].mundoY = pj.tamanhoDaPeca * 21;
    }

}
