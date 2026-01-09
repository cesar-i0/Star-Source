package objetos;

import entidades.Entidade;
import main.PainelDoJogo;

public abstract class SuperClasse extends Entidade {
    PainelDoJogo pj;

    public SuperClasse(PainelDoJogo pj){
        super(pj);
        this.pj = pj;
    }
    
    public abstract void getImagem();

}