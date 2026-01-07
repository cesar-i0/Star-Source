package objetos;

import entidades.Entidade;
import main.PainelDoJogo;

public class OBJ_Coracao extends Entidade{

    public OBJ_Coracao(PainelDoJogo pj){

        super(pj);
        nome = "Coração";
        imagem = configuracoes("/res/objetos/coracaoV", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        imagem2 = configuracoes("/res/objetos/coracaoM", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        imagem3 = configuracoes("/res/objetos/coracaoC", pj.tamanhoDaPeca, pj.tamanhoDaPeca);

    }   

}
