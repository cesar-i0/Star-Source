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
        int mapa = 0;
        int i = 0;
        
        pj.monstros[mapa][i] = new MON_Slime(pj);
        pj.monstros[mapa][i].mundoX = pj.tamanhoDaPeca * 13;
        pj.monstros[mapa][i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.monstros[mapa][i] = new MON_Slime(pj);
        pj.monstros[mapa][i].mundoX = pj.tamanhoDaPeca * 31;
        pj.monstros[mapa][i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.monstros[mapa][i] = new MON_Escorpiao(pj);
        pj.monstros[mapa][i].mundoX = pj.tamanhoDaPeca * 30;
        pj.monstros[mapa][i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.monstros[mapa][i] = new MON_Escorpiao(pj);
        pj.monstros[mapa][i].mundoX = pj.tamanhoDaPeca * 28;
        pj.monstros[mapa][i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.monstros[mapa][i] = new MON_Esqueleto(pj);
        pj.monstros[mapa][i].mundoX = pj.tamanhoDaPeca * 27;
        pj.monstros[mapa][i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.monstros[mapa][i] = new MON_Esqueleto(pj);
        pj.monstros[mapa][i].mundoX = pj.tamanhoDaPeca * 26;
        pj.monstros[mapa][i].mundoY = pj.tamanhoDaPeca * 21;
        i++;
        //para botar mostros no mapa 2 so alterar o valor da variavel mapa
        // mapa = 1;
       // pj.monstros[mapa][i] = new MON_Slime(pj);
    }

    public void setObjeto(){
        int i = 0;
        int mapa = 0;
        pj.obj[mapa][i] = new OBJ_Porta(pj);
        pj.obj[mapa][i].mundoX = pj.tamanhoDaPeca * 19;
        pj.obj[mapa][i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.obj[mapa][i] = new OBJ_Pocao(pj);
        pj.obj[mapa][i].mundoX = pj.tamanhoDaPeca * 18;
        pj.obj[mapa][i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.obj[mapa][i] = new OBJ_CristalMana(pj);
        pj.obj[mapa][i].mundoX = pj.tamanhoDaPeca * 17;
        pj.obj[mapa][i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.obj[mapa][i] = new OBJ_Coracao(pj);
        pj.obj[mapa][i].mundoX = pj.tamanhoDaPeca * 20;
        pj.obj[mapa][i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

        pj.obj[mapa][i] = new OBJ_Machado(pj);
        pj.obj[mapa][i].mundoX = pj.tamanhoDaPeca * 22;
        pj.obj[mapa][i].mundoY = pj.tamanhoDaPeca * 21;
        i++;


        pj.obj[mapa][i] = new OBJ_Moeda(pj);
        pj.obj[mapa][i].mundoX = pj.tamanhoDaPeca * 24;
        pj.obj[mapa][i].mundoY = pj.tamanhoDaPeca * 21;
        i++;

       

    }

    public void setNPC(){
       int mapa = 0;
        pj.npc[mapa][0] = new NPC_random(pj);
        pj.npc[mapa][0].mundoX = pj.tamanhoDaPeca * 15;
        pj.npc[mapa][0].mundoY = pj.tamanhoDaPeca * 21;

        pj.npc[mapa][1] = new NPC_random(pj);
        pj.npc[mapa][1].mundoX = pj.tamanhoDaPeca * 13;
        pj.npc[mapa][1].mundoY = pj.tamanhoDaPeca * 21;
    }


}
