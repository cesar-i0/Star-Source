package entidades;

import main.PainelDoJogo;

public class ProjetosTile extends Entidade {
    Entidade user;

    public ProjetosTile (PainelDoJogo pj){
        super(pj);

    }
    
    public void set(int mundoX, int mundoY, String direcao, boolean vivo, Entidade user){

        this.mundoX = mundoX;
        this.mundoY = mundoY;
        this.direcao = direcao;
        this.vivo = vivo;
        this.user = user;
        this.vida = vidaMaxima;
    }

    
    public void Updade(){

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
}
