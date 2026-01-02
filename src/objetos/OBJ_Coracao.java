package objetos;

import entidades.Entidade;
import main.PainelDoJogo;

public class OBJ_Coracao extends Entidade{

    public OBJ_Coracao(PainelDoJogo pj){

        super(pj);
        nome = "Coração";
        imagem = configuracoes("/res/objetos/coracaoBranco", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        imagem2 = configuracoes("/res/objetos/coracaoMeio", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        imagem3 = configuracoes("/res/objetos/coracao", pj.tamanhoDaPeca, pj.tamanhoDaPeca);

    }   

}
