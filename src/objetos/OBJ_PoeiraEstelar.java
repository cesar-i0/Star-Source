package objetos;

import entidades.Entidade;
import entidades.ProjeteisDePecas;
import main.PainelDoJogo;
import java.awt.Rectangle;

public class OBJ_PoeiraEstelar extends ProjeteisDePecas{

    PainelDoJogo pj;

    public OBJ_PoeiraEstelar (PainelDoJogo pj){
        super(pj);
        this.pj = pj;

        nome = "Bola de Fogo";
        velocidade = 5;
        vidaMaxima = 80;
        vida = vidaMaxima;
        ataques = 2;
        custo_de_uso = 0.1;
        vivo = false;

        area_solida = new Rectangle();
        area_solida.x = 16;
        area_solida.y = 15;
        area_solida.width = 15;
        area_solida.height = 15;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;

        getImagem();
    }

    public void getImagem(){
        baixo1 = configuracoes("/res/projeteis/poeiraEstB", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        baixo2 = configuracoes("/res/projeteis/poeiraEstB", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        cima1 = configuracoes("/res/projeteis/poeiraEstC", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        cima2 = configuracoes("/res/projeteis/poeiraEstC", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        direita1 = configuracoes("/res/projeteis/poeiraEstD", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        direita2 = configuracoes("/res/projeteis/poeiraEstD", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        esquerda1 = configuracoes("/res/projeteis/poeiraEstE", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        esquerda2 = configuracoes("/res/projeteis/poeiraEstE", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
    }

    public boolean temRecurso(Entidade user){
        // Permite atirar se houver mana suficiente (>= custo)
        return user.mana >= custo_de_uso;
    }

    public void subtrai_Recurso(Entidade user){
        // Subtrai o custo e garante que não fique negativo
        user.mana -= custo_de_uso;
        if(user.mana < 0) user.mana = 0;
    }
}


