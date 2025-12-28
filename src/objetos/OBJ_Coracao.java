package objetos;

import entidades.Entidade;
import main.PainelDoJogo;

public class OBJ_Coracao extends Entidade{
    


    public OBJ_Coracao(PainelDoJogo pj){
        super(pj);

        imagem = configuracoes("/res/objetos/coracaoBranco");
        imagem2 = configuracoes("/res/objetos/coracaoMeio");
        imagem3 = configuracoes("/res/objetos/coracao");

        nome = "Coração";
      
    }   

}
