package objetos;

import main.PainelDoJogo;
import entidades.Entidade;
import java.awt.Rectangle;

public class OBJ_EscudoFerro extends Entidade{
 public OBJ_EscudoFerro(PainelDoJogo pj){

        super(pj);
        tipo = tipo_escudo;
        super.nome = "Escudo de ferro";
        baixo1 = configuracoes("/res/objetos/escudoFerro", pj.tamanhoDaPeca, pj.tamanhoDaPeca);

        defesaValor = 2;

        
        descricao = "[" + nome + "]\n" + "Tem maior\nproteção";
        
        area_solida = new Rectangle();
        area_solida.x = 0;
        area_solida.y = 16;
        area_solida.width = 48;
        area_solida.height = 32;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
}
}
