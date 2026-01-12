package main;

import entidades.NPC_random;
import monstros.MON_Escorpiao;
import monstros.MON_Esqueleto;
import monstros.MON_Slime;
import objetos.OBJ_Coracao;
import objetos.OBJ_CristalMana;
import objetos.OBJ_Moeda;
import objetos.OBJ_Pocao;


public class ConfiguraRecurso {

    PainelDoJogo pj;

    public ConfiguraRecurso(PainelDoJogo pj){
        this.pj = pj;
    }


    public void setMonstro(){

        int i = 0;
        
        pj.monstros[i] = new MON_Slime(pj);
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 31;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 18;
        i++;

        pj.monstros[i] = new MON_Escorpiao(pj);
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 16;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 24;
        i++;

        pj.monstros[i] = new MON_Esqueleto(pj);
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 21;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 10;
        i++;

    }

    public void setObjeto(){
        int i = 0;

        pj.obj[i] = new OBJ_Pocao(pj);
        pj.obj[i].mundoX = pj.tamanhoDaPeca * 18;
        pj.obj[i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.obj[i] = new OBJ_CristalMana(pj);
        pj.obj[i].mundoX = pj.tamanhoDaPeca * 17;
        pj.obj[i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.obj[i] = new OBJ_Coracao(pj);
        pj.obj[i].mundoX = pj.tamanhoDaPeca * 20;
        pj.obj[i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.obj[i] = new OBJ_Moeda(pj);
        pj.obj[i].mundoX = pj.tamanhoDaPeca * 24;
        pj.obj[i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

    }

    public void setNPC(){

        pj.npc[0] = new NPC_random(pj);
        pj.npc[0].mundoX = pj.tamanhoDaPeca * 24;
        pj.npc[0].mundoY = pj.tamanhoDaPeca * 24;


    }


}
