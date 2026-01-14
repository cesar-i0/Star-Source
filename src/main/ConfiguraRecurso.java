package main;

import entidades.NPC_mercador;
import entidades.NPC_VelhoIrineu;
import monstros.MON_Escorpiao;
import monstros.MON_Esqueleto;
import monstros.MON_Slime;
import objetos.OBJ_CristalMana;
import objetos.OBJ_Moeda;
import objetos.OBJ_PocaoDeCura;


public class ConfiguraRecurso {

    PainelDoJogo pj;

    public ConfiguraRecurso(PainelDoJogo pj){
        this.pj = pj;
    }


    public void setMonstro(){
        int mapa = 0;
        int i = 0;
        
        pj.monstros[mapa][i] = new MON_Slime(pj);
        pj.monstros[mapa][i].mundoX = pj.tamanhoDaPeca * 14;
        pj.monstros[mapa][i].mundoY = pj.tamanhoDaPeca * 14;
        i++;

        pj.monstros[mapa][i] = new MON_Slime(pj);
        pj.monstros[mapa][i].mundoX = pj.tamanhoDaPeca * 21;
        pj.monstros[mapa][i].mundoY = pj.tamanhoDaPeca * 10;
        i++;

        pj.monstros[mapa][i] = new MON_Escorpiao(pj);
        pj.monstros[mapa][i].mundoX = pj.tamanhoDaPeca * 32;
        pj.monstros[mapa][i].mundoY = pj.tamanhoDaPeca * 22;
        i++;

        pj.monstros[mapa][i] = new MON_Escorpiao(pj);
        pj.monstros[mapa][i].mundoX = pj.tamanhoDaPeca * 36;
        pj.monstros[mapa][i].mundoY = pj.tamanhoDaPeca * 22;
        i++;

        pj.monstros[mapa][i] = new MON_Esqueleto(pj);
        pj.monstros[mapa][i].mundoX = pj.tamanhoDaPeca * 36;
        pj.monstros[mapa][i].mundoY = pj.tamanhoDaPeca * 23;
        i++;

        pj.monstros[mapa][i] = new MON_Esqueleto(pj);
        pj.monstros[mapa][i].mundoX = pj.tamanhoDaPeca * 35;
        pj.monstros[mapa][i].mundoY = pj.tamanhoDaPeca * 23;
        i++;
        //para botar mostros no mapa 2 so alterar o valor da variavel mapa
        // mapa = 1;
       // pj.monstros[mapa][i] = new MON_Slime(pj);
    }

    public void setObjeto(){
        int i = 0;
        int mapa = 0;

        pj.obj[mapa][i] = new OBJ_PocaoDeCura(pj);
        pj.obj[mapa][i].mundoX = pj.tamanhoDaPeca * 18;
        pj.obj[mapa][i].mundoY = pj.tamanhoDaPeca * 34;
        i++;

        pj.obj[mapa][i] = new OBJ_CristalMana(pj);
        pj.obj[mapa][i].mundoX = pj.tamanhoDaPeca * 25;
        pj.obj[mapa][i].mundoY = pj.tamanhoDaPeca * 23;
        i++;

        // pj.obj[mapa][i] = new OBJ_Coracao(pj);
        // pj.obj[mapa][i].mundoX = pj.tamanhoDaPeca * 20;
        // pj.obj[mapa][i].mundoY = pj.tamanhoDaPeca * 21;
        // i++;

        pj.obj[mapa][i] = new OBJ_Moeda(pj);
        pj.obj[mapa][i].mundoX = pj.tamanhoDaPeca * 24;
        pj.obj[mapa][i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

    }

    public void setNPC(){
       int mapa = 0;
       int i = 0;

        pj.npc[mapa][i] = new NPC_VelhoIrineu(pj);
        pj.npc[mapa][i].mundoX = pj.tamanhoDaPeca * 35;
        pj.npc[mapa][i].mundoY = pj.tamanhoDaPeca * 40;
        mapa++;

        pj.npc[mapa][i] = new NPC_mercador(pj);
        pj.npc[mapa][i].mundoX = pj.tamanhoDaPeca * 15;
        pj.npc[mapa][i].mundoY = pj.tamanhoDaPeca * 35;

        
    }


}
