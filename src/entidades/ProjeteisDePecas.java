package entidades;

import main.PainelDoJogo;

public class ProjeteisDePecas extends Entidade {
    
    Entidade user;

    public ProjeteisDePecas(PainelDoJogo pj){
        super(pj);
    }
    
    public void set(int mundoX, int mundoY, String direcao, boolean vivo, Entidade user){

        this.mundoX = mundoX;
        this.mundoY = mundoY;
        this.direcao = direcao;
        this.vivo = vivo;
        this.user = user;
        this.vida = this.vidaMaxima;

    }

    
   public void update() {

        colisao_ligada = false; // reseta colisão

        // Verifica colisão com tiles sólidos ANTES de mover
        pj.verifica.verificaPeca(this);
    
        // Se colidiu com parede, destrói o projétil
        if (colisao_ligada) {
        vivo = false;
        return;
    }

        // Verifica colisão com entidades
        if(user == pj.jogador){
        int indexMonstro = pj.verifica.verificaEntidade(this, pj.monstros);
        if(indexMonstro != 999){
            pj.jogador.danoMonstro(indexMonstro, ataques);
            vivo = false;
            return;
        }
    } else {
        boolean contatoJogador = pj.verifica.verificaJogador(this);
        if(!pj.jogador.invencivel && contatoJogador){
            danoJogador(ataques);
            vivo = false;
            return;
        }
    }

        // Move o projétil
        switch(direcao){
        case "cima":    mundoY -= velocidade; break;
        case "baixo":   mundoY += velocidade; break;
        case "esquerda":mundoX -= velocidade; break;
        case "direita": mundoX += velocidade; break;
    }

        // Diminui vida do projétil
        vida--;
        if(vida <= 0){
        vivo = false;
    }

        // Atualiza animação do projétil
        contadorDoEstado++;
        if(contadorDoEstado > 12){
        numeroDoEstado = (numeroDoEstado == 1) ? 2 : 1;
        contadorDoEstado = 0;
    }
}


      public boolean temRecurso(Entidade user){
        boolean tem_recurso = false;
        return tem_recurso;
    }

    public void subtrai_Recurso(Entidade user){}
}
