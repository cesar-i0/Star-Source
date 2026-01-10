package main;

import entidades.NPC_random;
import monstros.MON_Escorpiao;
import monstros.MON_Esqueleto;
import monstros.MON_Slime;
import objetos.OBJ_Coracao;
import objetos.OBJ_CristalMana;
//import objetos.OBJ_Chave;
import objetos.OBJ_Machado;
import objetos.OBJ_Moeda;
import objetos.OBJ_Pocao;
import objetos.OBJ_Porta;


public class ConfiguraRecurso {

    PainelDoJogo pj;

    public ConfiguraRecurso(PainelDoJogo pj){
        this.pj = pj;
    }


    public void setMonstro(){

        int i = 0;
        
        pj.monstros[i] = new MON_Slime(pj);
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 13;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.monstros[i] = new MON_Slime(pj);
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 31;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.monstros[i] = new MON_Escorpiao(pj);
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 30;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.monstros[i] = new MON_Escorpiao(pj);
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 28;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.monstros[i] = new MON_Esqueleto(pj);
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 27;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.monstros[i] = new MON_Esqueleto(pj);
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 26;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 21;
        i++;
    }

    public void setObjeto(){
        int i = 0;

        pj.obj[i] = new OBJ_Porta(pj);
        pj.obj[i].mundoX = pj.tamanhoDaPeca * 19;
        pj.obj[i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

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

        pj.obj[i] = new OBJ_Machado(pj);
        pj.obj[i].mundoX = pj.tamanhoDaPeca * 22;
        pj.obj[i].mundoY = pj.tamanhoDaPeca * 21;
        i++;


        pj.obj[i] = new OBJ_Moeda(pj);
        pj.obj[i].mundoX = pj.tamanhoDaPeca * 24;
        pj.obj[i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

       

    }

    public void setNPC(){

        pj.npc[0] = new NPC_random(pj);
        pj.npc[0].mundoX = pj.tamanhoDaPeca * 15;
        pj.npc[0].mundoY = pj.tamanhoDaPeca * 21;

        pj.npc[1] = new NPC_random(pj);
        pj.npc[1].mundoX = pj.tamanhoDaPeca * 13;
        pj.npc[1].mundoY = pj.tamanhoDaPeca * 21;

    }


}
