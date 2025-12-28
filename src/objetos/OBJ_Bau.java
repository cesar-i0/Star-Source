package objetos;


import entidades.Entidade;
import main.PainelDoJogo;

public class OBJ_Bau extends Entidade{


    public OBJ_Bau(PainelDoJogo pj){
        super(pj);
        super.nome = "Baú";

        baixo1 = configuracoes("/objetos/bau");
       

}
}
