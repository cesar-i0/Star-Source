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

    
    public void update(){

        if(user == pj.jogador){
            int indexMonstro = pj.verifica.verificaEntidade(this, pj.monstros);
            if(indexMonstro != 999){
                pj.jogador.danoMonstro(indexMonstro, ataques);
                vivo = false;
            }

        }
        if(user != pj.jogador){
            boolean contatoJogador = pj.verifica.verificaJogador(this);
            if(pj.jogador.invencivel == false && contatoJogador == true){
                danoJogador(ataques);
                vivo = false;
            }
        }
            switch(direcao){
            case "cima": mundoY -= velocidade; break;
            case "baixo": mundoY += velocidade; break;
            case "esquerda": mundoX -= velocidade; break;
            case "direita": mundoX +=velocidade; break;
        }

        vida--;
        if(vida <= 0){
            vivo = false;
        }

        contadorDoEstado++;
        if(contadorDoEstado>12){
            if(numeroDoEstado==1){
                numeroDoEstado = 2;
            }
            else if(numeroDoEstado == 2){
                numeroDoEstado = 1;
            }
            contadorDoEstado = 0;
        }

    }

      public boolean temRecurso(Entidade user){
        boolean tem_recurso = false;
        return tem_recurso;
    }

    public void subtrai_Recurso(Entidade user){}
}
