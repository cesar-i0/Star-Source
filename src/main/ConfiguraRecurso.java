package main;

import entidades.NPC_random;
import monstros.MON_Slime;
//import objetos.OBJ_Chave;
import objetos.OBJ_Escudo;
import objetos.OBJ_EscudoFerro;
import objetos.OBJ_Machado;
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
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 8;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 6;
        i++;

        pj.monstros[i] = new MON_Slime(pj);
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 4;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 5;
        i++;

        pj.monstros[i] = new MON_Slime(pj);
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 23;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 3;
        i++;

        pj.monstros[i] = new MON_Slime(pj);
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 2;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 7;
        i++;

        pj.monstros[i] = new MON_Slime(pj);
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 9;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 8;
        i++;

        pj.monstros[i] = new MON_Slime(pj);
        pj.monstros[i].mundoX = pj.tamanhoDaPeca * 8;
        pj.monstros[i].mundoY = pj.tamanhoDaPeca * 1;
        i++;
    }

    public void setObjeto(){
        int i = 0;

        pj.obj[i] = new OBJ_Porta(pj);
        pj.obj[i].mundoX = pj.tamanhoDaPeca * 30;
        pj.obj[i].mundoY = pj.tamanhoDaPeca * 30;
        i++;

        pj.obj[i] = new OBJ_Pocao(pj);
        pj.obj[i].mundoX = pj.tamanhoDaPeca * 1;
        pj.obj[i].mundoY = pj.tamanhoDaPeca * 4;
        i++;

        pj.obj[i] = new OBJ_Escudo(pj);
        pj.obj[i].mundoX = pj.tamanhoDaPeca * 1;
        pj.obj[i].mundoY = pj.tamanhoDaPeca * 3;
        i++;

        pj.obj[i] = new OBJ_EscudoFerro(pj);
        pj.obj[i].mundoX = pj.tamanhoDaPeca * 1;
        pj.obj[i].mundoY = pj.tamanhoDaPeca * 2;
        i++;

        pj.obj[i] = new OBJ_Machado(pj);
        pj.obj[i].mundoX = pj.tamanhoDaPeca * 1;
        pj.obj[i].mundoY = pj.tamanhoDaPeca * 1;
        i++;


        pj.obj[i] = new OBJ_Pocao(pj);
        pj.obj[i].mundoX = pj.tamanhoDaPeca * 1;
        pj.obj[i].mundoY = pj.tamanhoDaPeca * 5;
        i++;

       

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
