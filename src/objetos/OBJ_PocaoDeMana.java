package objetos;

import main.PainelDoJogo;
import entidades.Entidade;
import java.awt.Rectangle;

public class OBJ_PocaoDeMana extends SuperClasse {

     PainelDoJogo pj;

    public OBJ_PocaoDeMana (PainelDoJogo pj){
        
        super(pj);
        tipo = tipo_consumivel;
        super.nome = "Porção de mana";

        this.pj = pj;

        getImagem();

        valor = 1;
        descricao = "[" + nome + "]\n" + "Recupera sua \nmana em " + valor + ".";
        preco = 4;

        area_solida = new Rectangle();
        area_solida.x = 10;
        area_solida.y = 8;
        area_solida.width = 30;
        area_solida.height = 30;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
    }

    @Override
    public void getImagem() {
        estatico = configuracoes("/res/objetos/pocaoM", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
    }

    @Override
    public boolean use(Entidade entidades){
        pj.estado_do_jogo = pj.estado_de_dialogo;
        pj.ui.dialogo_atual = "Você bebeu " + nome + "!\n" + "Você recuperou " + valor + " de mana.";
        entidades.vida += valor;

        if(pj.jogador.mana > pj.jogador.mana_max){
            pj.jogador.mana = pj.jogador.mana_max;
        }
        //Adicionar som
        return true;
    }

    

}
