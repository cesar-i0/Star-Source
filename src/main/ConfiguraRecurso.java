package main;

import objetos.OBJ_Bau;
import objetos.OBJ_Bota;
import objetos.OBJ_Chave;
import objetos.OBJ_Porta;

public class ConfiguraRecurso {

    PainelDoJogo pj;

    public ConfiguraRecurso(PainelDoJogo pj){
        this.pj = pj;
    }

    public void setObjeto(){

        pj.obj[0] = new OBJ_Chave();
        pj.obj[0].mundoX = 24 * pj.tamanhoDaPeca;
        pj.obj[0].mundoY = 2 * pj.tamanhoDaPeca;

        pj.obj[2] = new OBJ_Chave();
        pj.obj[2].mundoX = 26 * pj.tamanhoDaPeca;
        pj.obj[2].mundoY = 2 * pj.tamanhoDaPeca;

        pj.obj[3] = new OBJ_Porta();
        pj.obj[3].mundoX = 26 * pj.tamanhoDaPeca;
        pj.obj[3].mundoY = 23 * pj.tamanhoDaPeca;

        pj.obj[4] = new OBJ_Bau();
        pj.obj[4].mundoX = 26 * pj.tamanhoDaPeca;
        pj.obj[4].mundoY = 48 * pj.tamanhoDaPeca;

        pj.obj[5] = new OBJ_Bota();
        pj.obj[5].mundoX = 37 * pj.tamanhoDaPeca;
        pj.obj[5].mundoY = 42 * pj.tamanhoDaPeca;

    }

}
