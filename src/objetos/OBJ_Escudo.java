package objetos;

import main.PainelDoJogo;
import entidades.Entidade;
import java.awt.Rectangle;

public class OBJ_Escudo extends Entidade{

    public OBJ_Escudo(PainelDoJogo pj){

        super(pj);
        tipo = tipo_escudo;
        nome = "Escudo de Madeira";
        baixo1 = configuracoes("/res/objetos/escudoMadeira", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
       
        defesaValor = 1;

       
        descricao = "[" + nome + "]\n" + "Escudo velho";

        area_solida = new Rectangle();
        area_solida.x = 0;
        area_solida.y = 16;
        area_solida.width = 48;
        area_solida.height = 32;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
    }

}
