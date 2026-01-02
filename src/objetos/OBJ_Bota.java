package objetos;

import entidades.Entidade;
import main.PainelDoJogo;

public class OBJ_Bota extends Entidade{

    public OBJ_Bota(PainelDoJogo pj){

        super(pj);
        super.nome = "Bota";
        parado_frente = configuracoes("/res/objetos/Bota", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        
    }

}
