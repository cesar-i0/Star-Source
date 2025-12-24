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
    int possuiChave = 0;

    public Jogador(PainelDoJogo pj, Manipulador manipulador){
        this.pj = pj;
        this.manipulador = manipulador;

        telaX = pj.larguraDaTela / 2 - (pj.tamanhoDaPeca / 2); // Coloca o personagem no centro da tela
        telaY = pj.comprimentoDaTela / 2 - (pj.tamanhoDaPeca / 2); // Coloca o personagem no centro da tela

        area_solida =  new Rectangle();
        area_solida.x = 8;
        area_solida.y = 8;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
        area_solida.width = 32;
        area_solida.height = 32;

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
       if(manipulador.cimaPrecionado || manipulador.baixoPrecionado || manipulador.esquerdaPrecionado || manipulador.direitaPreciondo){
           // Coloque todas as condições abaixo para o personagem se mover apenas quando algum botão estiver selecionado
           if(manipulador.cimaPrecionado){
               direcao = "cima";
           }
           else if(manipulador.baixoPrecionado){
               direcao = "baixo";
           }
           else if(manipulador.esquerdaPrecionado){
               direcao = "esquerda";
           }
           else if(manipulador.direitaPreciondo){
               direcao = "direita";
           }
   
           // Verfica a colisão da peça
           colisao_ligada = false;
           pj.verifica.verificaPeca(this);

           // Verifica a colisao com objetos
           int index_do_objeto = pj.verifica.verificaObjeto(this, true);
           pegueObjeto(index_do_objeto);

           // Se a colisão for false o joagador pode se mover
           if(colisao_ligada == false){
               switch (direcao){
                   case "cima":
                       mundoY -= velocidade;
                       break;
                   case "baixo":
                       mundoY += velocidade;
                       break;
                   case "esquerda":
                       mundoX -= velocidade;
                       break;
                   case "direita":
                       mundoX += velocidade;
                       break;
               }
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

    }

    public void pegueObjeto(int index){
        // Se não for 999 então a entidade tocou no objeto
        if(index != 999){
            // pj.obj[index] = null; // Deleta o objeto tocado
            String nome_do_objeto =  pj.obj[index].nome;
            
            switch (nome_do_objeto) {
                case "Chave":
                    //pj.tocarEfeitoSonoro(1); // Toca o efeito sonoro da coleta da chave
                    possuiChave++;
                    pj.obj[index] = null;
                    System.out.println("Chave: " + possuiChave);
                    break;
                case "Porta":
                    //pj.tocarEfeitoSonoro(2); // Toca o efeito sonoro da coleta da porta
                    if(possuiChave > 0){
                        pj.obj[index] = null;
                        possuiChave--;
                    }
                    System.out.println("Chave: " + possuiChave);
                    break;
                case "Bota":
                    //pj.tocarEfeitoSonoro(3); // Toca o efeito sonoro da coleta de item
                    velocidade += 2;
                    pj.obj[index] = null;
                    break;
            }
        }

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
