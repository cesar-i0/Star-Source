package objetos;

import main.PainelDoJogo;
import entidades.Entidade;

public class OBJ_Espada extends Entidade{

    public OBJ_Espada(PainelDoJogo pj){


        super(pj);
        nome = "Espada de Madeira";
        baixo1 = configuracoes("/res/objetos/espadaMadeira", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
       
        ataqueValor = 1;

        descricao = "[" + nome + "]\n" + "Espada velha";
    }

}
