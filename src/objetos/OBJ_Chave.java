package objetos;

import entidades.Entidade;
import main.PainelDoJogo;

public class OBJ_Chave extends Entidade {

    public OBJ_Chave(PainelDoJogo pj){

        super(pj);
        super.nome = "Chave";
        baixo1 = configuracoes("/res/objetos/chave", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        

        descricao = "[" + nome + "]\n" + "Abre portas";
    }

}
