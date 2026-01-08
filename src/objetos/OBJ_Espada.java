package objetos;

import main.PainelDoJogo;
import entidades.Entidade;
import java.awt.Rectangle;


public class OBJ_Espada extends Entidade{

    public OBJ_Espada(PainelDoJogo pj){


        super(pj);
        tipo = tipo_espada;
        nome = "Espada de Madeira";
        estatico = configuracoes("/res/objetos/espadaMadeira", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
       
        ataqueValor = 1;

        AtaqueArea.width = 36;
        AtaqueArea.height = 36;
        
        descricao = "[" + nome + "]\n" + "Espada velha";

        area_solida = new Rectangle();
        area_solida.x = 0;
        area_solida.y = 16;
        area_solida.width = 48;
        area_solida.height = 32;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
    }

}
