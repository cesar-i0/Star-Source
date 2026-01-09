package objetos;

import main.PainelDoJogo;
import java.awt.Rectangle;

public class OBJ_Machado extends SuperClasse{

    public OBJ_Machado(PainelDoJogo pj){

        super(pj);
        tipo = tipo_machado;
        super.nome = "Machado";
        
       
        ataqueValor = 2;

        AtaqueArea.width = 30;
        AtaqueArea.height = 30;
        descricao = "[" + nome + "]\n" + "Machado antigo mas\n pode derrubar umas árvores";

        area_solida = new Rectangle();
        area_solida.x = 0;
        area_solida.y = 16;
        area_solida.width = 48;
        area_solida.height = 32;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;

    }

    @Override
    public void getImagem() {
        estatico = configuracoes("/res/objetos/machado", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
    }

}
