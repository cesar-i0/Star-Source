package objetos;

import entidades.Entidade;
import main.PainelDoJogo;
import java.awt.Rectangle;

public class OBJ_Moeda extends Entidade {

    PainelDoJogo pj; 

    public OBJ_Moeda(PainelDoJogo pj) {
        super(pj);
        this.pj = pj;

        nome = "Moeda";
        tipo = tipo_pegar_apenas;
        valor = 1;

        baixo1 = configuracoes("/res/objetos/moeda", pj.tamanhoDaPeca, pj.tamanhoDaPeca);

        area_solida = new Rectangle();
        area_solida.x = 0;
        area_solida.y = 16;
        area_solida.width = 48;
        area_solida.height = 32;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
    }

    @Override
    public boolean use(Entidade entidade) {
        pj.ui.mostrarMensagem("Moeda +" + valor);
        pj.jogador.moedas += valor;
        return true;
    }
}
