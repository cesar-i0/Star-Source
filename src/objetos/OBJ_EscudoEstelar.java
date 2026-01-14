package objetos;

import main.PainelDoJogo;
import java.awt.Rectangle;

public class OBJ_EscudoEstelar extends SuperClasse{

    public OBJ_EscudoEstelar(PainelDoJogo pj){

        super(pj);
        tipo = tipo_escudo;
        nome = "Escudo Estelar";
        getImagem();
       
        defesaValor = 1;

       
        descricao = "[" + nome + "]\n" + "Escudo Estelar";

        area_solida = new Rectangle();
        area_solida.x = 0;
        area_solida.y = 16;
        area_solida.width = 48;
        area_solida.height = 32;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
    }

    @Override
    public void getImagem() {
        estatico = configuracoes("/res/objetos/escudoEstelar", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
    }

}
