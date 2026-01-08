package objetos;

import entidades.Entidade;
import main.PainelDoJogo;
import java.awt.Rectangle;

public class OBJ_Porta extends Entidade{

    public OBJ_Porta(PainelDoJogo pj){

        super(pj);
        super.nome = "Porta";
        estatico = configuracoes("/res/objetos/porta", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        colisao = true;

        area_solida = new Rectangle();
        area_solida.x = 0;
        area_solida.y = 16;
        area_solida.width = 48;
        area_solida.height = 32;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;

        
    }

}
