package objetos;

import main.PainelDoJogo;

public class OBJ_Bota extends SuperClasse{

    public OBJ_Bota(PainelDoJogo pj){

        super(pj);
        super.nome = "Bota";
        
    }

    @Override
    public void getImagem() {
        estatico = configuracoes("/res/objetos/Bota", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
    }

}
