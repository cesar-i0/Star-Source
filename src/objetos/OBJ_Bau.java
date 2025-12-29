package objetos;

import entidades.Entidade;
import main.PainelDoJogo;

public class OBJ_Bau extends Entidade{

    public OBJ_Bau(PainelDoJogo pj){

        super(pj);
        super.nome = "Baú";
        parado_frente = configuracoes("/res/objetos/baú");
        
    }

}
