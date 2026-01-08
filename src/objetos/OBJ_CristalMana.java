package objetos;

import entidades.Entidade;
import main.PainelDoJogo;
import java.awt.Rectangle;

public class OBJ_CristalMana extends Entidade{

    PainelDoJogo pj;

    public OBJ_CristalMana(PainelDoJogo pj){
        super(pj);
        this.pj = pj;

        tipo = tipo_pegar_apenas;
        nome = "Cristal Mana";
        valor = 1;
        estatico = configuracoes("/res/objetos/CristalManaCheio", pj.tamanhoDaPeca, pj.tamanhoDaPeca);

        imagem4 = configuracoes("/res/objetos/CristalManaVazio", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        imagem5 = configuracoes("/res/objetos/CristalManaCheio", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
       
        area_solida = new Rectangle();
        area_solida.x = 0;
        area_solida.y = 16;
        area_solida.width = 48;
        area_solida.height = 32;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
    }   
   // Em OBJ_CristalMana.use
    @Override
    public boolean use(Entidade entidade){
        if(entidade.mana < entidade.mana_max){
            entidade.mana = Math.min(entidade.mana + valor, entidade.mana_max);
            pj.ui.mostrarMensagem("Mana +" + valor);
            return true;
        } else {
            pj.ui.mostrarMensagem("Mana cheia");
            return false;
        }
    }
}

