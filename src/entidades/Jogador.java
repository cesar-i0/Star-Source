package entidades;

import main.Manipulador;
import main.PainelDoJogo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jogador extends Entidade{

    PainelDoJogo pj;
    Manipulador manipulador;

    public final int telaX, telaY;

    public Jogador(PainelDoJogo pj, Manipulador manipulador){
        this.pj = pj;
        this.manipulador = manipulador;
        telaX = pj.larguraDaTela / 2 - (pj.tamanhoDaPeca / 2); // Coloca o personagem no centro da tela
        telaY = pj.comprimentoDaTela / 2 - (pj.tamanhoDaPeca / 2); // Coloca o personagem no centro da tela
        setValoresPadroes();
        getImagemDoJogador();
    }

    public void setValoresPadroes(){
        mundoX = pj.tamanhoDaPeca * 23; // Essa linha vai indicar em que posição do mapa o jogador inicia.
        mundoY = pj.tamanhoDaPeca * 21; // Essa linha vai indicar em que posição do mapa o jogador inicia.  
        velocidade = 4;
        direcao = "cima";
    }

    public void getImagemDoJogador(){
        try{
            bogo = ImageIO.read(getClass().getResourceAsStream("/res/jogador/personagem.png")); // Mudei
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
//        if(manipulador.cimaPrecionado || manipulador.baixoPrecionado || manipulador.esquerdaPrecionado || manipulador.direitaPreciondo){
//            // Coloque todas as condições abaixo para o personagem se mover apenas quando algum botão estiver selecionado
//        }
        if(manipulador.cimaPrecionado){
            direcao = "cima";
            mundoY -= velocidade;
        }
        else if(manipulador.baixoPrecionado){
            direcao = "baixo";
            mundoY += velocidade;
        }
        else if(manipulador.esquerdaPrecionado){
            direcao = "esquerda";
            mundoX -= velocidade;
        }
        else if(manipulador.direitaPreciondo){
            direcao = "direita";
            mundoX += velocidade;
        }

//        contadorDoEstado++;
//        if(numeroDoEstado > 12){
//            if(numeroDoEstado == 1){
//                numeroDoEstado = 2;
//            }
//            else if (numeroDoEstado == 2){
//                numeroDoEstado = 1;
//            }
//            numeroDoEstado = 0;
//        }

    }

    public void desenhar(Graphics g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, jp.tamanhoDaPeca, jp.tamanhoDaPeca); // Vai desenhar o retangulo e completa com a cor especificada.
        BufferedImage image = null;
        switch (direcao){
            case "cima":
//                if(numeroDoEstado == 1){
//                    image = bogo;
//                }
//                if(numeroDoEstado){
//                    image = bogo;
//                }
                image = bogo;
                break;
            case "baixo":
//                if(numeroDoEstado == 1){
//                    image = bogo;
//                }
//                if(numeroDoEstado){
//                    image = bogo;
//                }
                image = bogo;
                break;
            case "esquerda":
//                if(numeroDoEstado == 1){
//                    image = bogo;
//                }
//                if(numeroDoEstado){
//                    image = bogo;
//                }
                image = bogo;
                break;
            case "direita":
//                if(numeroDoEstado == 1){
//                    image = bogo;
//                }
//                if(numeroDoEstado){
//                    image = bogo;
//                }
                image = bogo;
                break;
        }
        g2.drawImage(image, telaX, telaY, pj.tamanhoDaPeca, pj.tamanhoDaPeca, null);
    }

}
