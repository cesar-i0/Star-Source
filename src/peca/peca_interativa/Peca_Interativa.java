package peca.peca_interativa;

import entidades.Entidade;
import main.PainelDoJogo;

public class Peca_Interativa extends Entidade{

    PainelDoJogo pj;
    public boolean destrutivel = false;

    public Peca_Interativa (PainelDoJogo pj){
        super(pj);
        this.pj = pj;
    }

    public boolean itemCorreto(Entidade entidade){
        boolean itemCorreto = false;
        return itemCorreto;
    }
    public void update(){
             
    }

}
