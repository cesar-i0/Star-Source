package objetos;

import entidades.ProjetosTile;
import main.PainelDoJogo;
import java.awt.Rectangle;

public class OBJ_BolaDeFogo extends ProjetosTile{

    PainelDoJogo pj;

    public OBJ_BolaDeFogo (PainelDoJogo pj){
        super(pj);
        this.pj = pj;

        nome = "Bola de Fogo";
        velocidade = 10;
        vidaMaxima = 80;
        vida = vidaMaxima;
        ataques = 2;
        custo_de_uso = 1;
        vivo = false;

        area_solida = new Rectangle();
        area_solida.x = 0;
        area_solida.y = 16;
        area_solida.width = 48;
        area_solida.height = 32;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;

        getImagem();
    }

    public void getImagem(){
        baixo1 = configuracoes("/res/ProjetosTile/BolaDeFogo", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        baixo2 = configuracoes("/res/ProjetosTile/BolaDeFogo", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        cima1 = configuracoes("/res/ProjetosTile/BolaDeFogo", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        cima2 = configuracoes("/res/ProjetosTile/BolaDeFogo", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        direita1 = configuracoes("/res/ProjetosTile/BolaDeFogo", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        direita2 = configuracoes("/res/ProjetosTile/BolaDeFogo", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        esquerda1 = configuracoes("/res/ProjetosTile/BolaDeFogo", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        esquerda2 = configuracoes("/res/ProjetosTile/BolaDeFogo", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
    }
}


