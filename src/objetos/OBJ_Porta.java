package objetos;


import entidades.Entidade;
import main.PainelDoJogo;

public class OBJ_Porta extends Entidade{

 

    public OBJ_Porta(PainelDoJogo pj){
        super(pj);
        super.nome = "Porta";
        
        colisao = true;
    }

}
