package objetos;

import main.PainelDoJogo;
import entidades.Entidade;
import java.awt.Rectangle;

public class OBJ_Pocao extends Entidade {

     PainelDoJogo pj;

    public OBJ_Pocao (PainelDoJogo pj){
        
        super(pj);
        tipo = tipo_consumivel;
        super.nome = "Porção de vida";

        this.pj = pj;

        baixo1 = configuracoes("/res/objetos/pocaoVermelha", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        valor = 5;
        descricao = "[" + nome + "]\n" + "Recupera sua vida em " + valor + ".";

        area_solida = new Rectangle();
        area_solida.x = 0;
        area_solida.y = 16;
        area_solida.width = 48;
        area_solida.height = 32;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
    }

        @Override
        public boolean use(Entidade entidades){
            pj.estado_do_jogo = pj.estado_de_dialogo;
            pj.ui.dialogo_atual = "Você bebeu " + nome + "!\n" + "Você recuperou " + valor + " de vida.";
            entidades.vida += valor;

            if(pj.jogador.vida > pj.jogador.vidaMaxima){
                pj.jogador.vida = pj.jogador.vidaMaxima;
            }
            //Adicionar som
            return true;
        }

    

}
