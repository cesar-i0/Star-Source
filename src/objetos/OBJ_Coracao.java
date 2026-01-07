package objetos;

import entidades.Entidade;
import main.PainelDoJogo;
import java.awt.Rectangle;

public class OBJ_Coracao extends Entidade{

    PainelDoJogo pj;

    public OBJ_Coracao(PainelDoJogo pj){

        super(pj);
        this.pj = pj;
        nome = "Coração";
        tipo = tipo_pegar_apenas;
        valor = 2;
        baixo1 = configuracoes("/res/objetos/coracao", pj.tamanhoDaPeca, pj.tamanhoDaPeca);


        imagem = configuracoes("/res/objetos/coracaoBranco", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        imagem2 = configuracoes("/res/objetos/coracaoMeio", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        imagem3 = configuracoes("/res/objetos/coracao", pj.tamanhoDaPeca, pj.tamanhoDaPeca);

        area_solida = new Rectangle();
        area_solida.x = 0;
        area_solida.y = 16;
        area_solida.width = 48;
        area_solida.height = 32;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
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
