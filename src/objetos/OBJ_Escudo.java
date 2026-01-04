package objetos;

import main.PainelDoJogo;
import entidades.Entidade;

public class OBJ_Escudo extends Entidade{

    public OBJ_Escudo(PainelDoJogo pj){

        super(pj);
        nome = "Escudo de Madeira";
        baixo1 = configuracoes("/res/objetos/escudoMadeira", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
       
        defesaValor = 2;

        descricao = "[" + nome + "]\n" + "Escudo velho";
    }

}
