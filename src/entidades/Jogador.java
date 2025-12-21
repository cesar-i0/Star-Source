package entidades;

import main.Manipulador;
import main.PainelDoJogo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Jogador extends Entidade{

    PainelDoJogo jp;
    Manipulador manipulador;

    public Jogador(PainelDoJogo jp, Manipulador manipulador){
        this.jp = jp;
        this.manipulador = manipulador;
        setValoresPadroes();
        getImagemDoJogador();
    }

    public void setValoresPadroes(){
        x = 100;
        y = 100;
        speed = 4;
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
            y -= speed;
        }
        else if(manipulador.baixoPrecionado){
            direcao = "baixo";
            y += speed;
        }
        else if(manipulador.esquerdaPrecionado){
            direcao = "esquerda";
            x -= speed;
        }
        else if(manipulador.direitaPreciondo){
            direcao = "direita";
            x += speed;
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
        g2.drawImage(image,x, y, jp.tamanhoDaPeca, jp.tamanhoDaPeca, null);
    }

}
