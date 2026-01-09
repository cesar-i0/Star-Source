package objetos;

import main.PainelDoJogo;

public class OBJ_Bau extends SuperClasse{

    public OBJ_Bau(PainelDoJogo pj){

        super(pj);
        super.nome = "Baú";
        
    }

    @Override
    public void getImagem() {
        estatico = configuracoes("/res/objetos/baú", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
    }

}

