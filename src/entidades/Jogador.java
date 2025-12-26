package entidades;

import main.Manipulador;
import main.PainelDoJogo;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Jogador extends Entidade{

    Manipulador manipulador;
    public final int telaX, telaY;
    // int estadoInicial = 0;

    public Jogador(PainelDoJogo pj, Manipulador manipulador){
        
        super(pj);  // Aqui estamos passando o painel do jogo para a super classe "Entidade"

        this.manipulador = manipulador;

        telaX = pj.larguraDaTela / 2 - (pj.tamanhoDaPeca / 2); // Coloca o personagem no centro da tela
        telaY = pj.alturaDaTela / 2 - (pj.tamanhoDaPeca / 2); // Coloca o personagem no centro da tela

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

        // Status do jogador
        vidaMaxima = 6;
        vida = vidaMaxima;

    }

    public void getImagemDoJogador(){
        
        bogo = configuracoes("/res/jogador/personagem");
        // cima1, cima2, baixo1, baixo2, esquerda1, esquerda2, direita1, direita2;

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

           // Verifica a colisão com NPC
           int index_do_NPC = pj.verifica.verificaEntidade(this, pj.npc);
           interacaoNPC(index_do_NPC);

           // Verifica evento
           pj.evento.verificaEvento();
           pj.chaveManipuladora.enterPressionado = false;

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
   
            // contadorDoEstado++;
            // if(numeroDoEstado > 12){
            //     if(numeroDoEstado == 1){
            //         numeroDoEstado = 2;
            //     }
            //     else if (numeroDoEstado == 2){
            //         numeroDoEstado = 1;
            //     }
            //     numeroDoEstado = 0;
            // }
        }
    //    else{
    //         estadoInicial++;
    //         if(estadoInicial == 20){
    //             numeroDoEstado = 1;
    //             estadoInicial = 0;
    //         }
    //    }

    }

    public void pegueObjeto(int i){
        // Se não for 999 então a entidade tocou no objeto
        if(i != 999){
            
            

        }

    }

    public void interacaoNPC(int i){

        if(i != 999){
            if(pj.chaveManipuladora.enterPressionado == true){
                // System.out.println("Colidindo com NPC");
                pj.estado_do_jogo = pj.estado_de_dialogo;
                pj.npc[i].falar();
            }
        }
    }

    public void desenhar(Graphics g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, jp.tamanhoDaPeca, jp.tamanhoDaPeca); // Vai desenhar o retangulo e completa com a cor especificada.
        BufferedImage imagem = null;
        switch (direcao){
            case "cima":
//                if(numeroDoEstado == 1){
//                    image = bogo;
//                }
//                if(numeroDoEstado){
//                    image = bogo;
//                }
                imagem = bogo;
                break;
            case "baixo":
//                if(numeroDoEstado == 1){
//                    image = bogo;
//                }
//                if(numeroDoEstado){
//                    image = bogo;
//                }
                imagem = bogo;
                break;
            case "esquerda":
//                if(numeroDoEstado == 1){
//                    image = bogo;
//                }
//                if(numeroDoEstado){
//                    image = bogo;
//                }
                imagem = bogo;
                break;
            case "direita":
//                if(numeroDoEstado == 1){
//                    image = bogo;
//                }
//                if(numeroDoEstado){
//                    image = bogo;
//                }
                imagem = bogo;
                break;
        }
        g2.drawImage(imagem, telaX, telaY, null);

        // Para ver a área de colisão usamos isso
        g2.setColor(Color.red);
        g2.drawRect(telaX + area_solida.x, telaY + area_solida.y, area_solida.width, area_solida.height);
    }

}
