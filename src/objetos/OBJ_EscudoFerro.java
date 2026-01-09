package objetos;

import main.PainelDoJogo;
import java.awt.Rectangle;

public class OBJ_EscudoFerro extends SuperClasse{

        public OBJ_EscudoFerro(PainelDoJogo pj){

                super(pj);
                tipo = tipo_escudo;
                super.nome = "Escudo de ferro";
                getImagem();

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

        @Override
        public void getImagem() {
                estatico = configuracoes("/res/objetos/escudoFerro", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        }

}
