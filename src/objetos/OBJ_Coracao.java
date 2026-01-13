package objetos;

import entidades.Entidade;
import main.PainelDoJogo;
import java.awt.Rectangle;

public class OBJ_Coracao extends SuperClasse{

    PainelDoJogo pj;

    public OBJ_Coracao(PainelDoJogo pj){

        super(pj);
        this.pj = pj;
        nome = "Coração";
        getImagem();

        area_solida = new Rectangle();
        area_solida.x = 8;
        area_solida.y = 8;
        area_solida.width = 32;
        area_solida.height = 30;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
    }

    @Override
    public void getImagem() {
        estatico = configuracoes("/res/objetos/coracaoC", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        imagem = configuracoes("/res/objetos/coracaoV", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        imagem2 = configuracoes("/res/objetos/coracaoM", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        imagem3 = configuracoes("/res/objetos/coracaoC", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
    }
     
    @Override
    public boolean use(Entidade entidade){
        if(entidade.vida < entidade.vidaMaxima){
            entidade.vida += valor;
            if(entidade.vida > entidade.vidaMaxima) entidade.vida = entidade.vidaMaxima;
            pj.ui.mostrarMensagem("Vida +" + valor);
            return true;
        } else {
            pj.ui.mostrarMensagem("Vida cheia");
            return false;
        }
    }

}
