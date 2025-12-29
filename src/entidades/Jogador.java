package entidades;

import main.Manipulador;
import main.PainelDoJogo;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;

public class Jogador extends Entidade{

    Manipulador manipulador;
    public final int telaX, telaY;
    int estadoInicial = 0;

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
        direcao = "parado_frente";

        // Status do jogador
        vidaMaxima = 6;
        vida = vidaMaxima;

    }

    public void getImagemDoJogador(){
        
        cima1 = configuracoes("/res/jogador/andando_costas1");
        cima2 = configuracoes("/res/jogador/andando_costas2");
        baixo1 = configuracoes("/res/jogador/andando_frente1");
        baixo2 = configuracoes("/res/jogador/andando_frente2");
        esquerda1 = configuracoes("/res/jogador/parado_esquerda");
        esquerda2 = configuracoes("/res/jogador/andando_esquerda");
        direita1 = configuracoes("/res/jogador/parado_direita");
        direita2 = configuracoes("/res/jogador/andando_direita"); 
        parado_frente = configuracoes("/res/jogador/parado");
        parado_costas = configuracoes("/res/jogador/parado_costas");

    }

    

    public void update(){
       if(manipulador.cimaPrecionado || manipulador.baixoPrecionado || manipulador.esquerdaPrecionado || manipulador.direitaPreciondo || manipulador.enterPressionado){
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

           //Verifica a colisão com monstros
        //    int index_do_monstro = pj.verifica.verificaEntidade(this, pj.monstros);
        //     combateMonstros(index_do_monstro);

           // Verifica evento
           pj.evento.verificaEvento();
           

           // Se a colisão for false o joagador pode se mover
           if(colisao_ligada == false && manipulador.enterPressionado == false){
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

           pj.chaveManipuladora.enterPressionado = false;
   
            contadorDoEstado++;
            if(contadorDoEstado > 12){
                if(numeroDoEstado == 1){
                    numeroDoEstado = 2;
                }
                else if (numeroDoEstado == 2){
                    numeroDoEstado = 1;
                }
                contadorDoEstado = 0;
            }
        } 
        
        if(invencivel == true){
            contador_de_invencibilidade++;
            if(contador_de_invencibilidade > 60){
                invencivel = false;
                contador_de_invencibilidade = 0;
            }
        }
        
        else {
            estadoInicial++;
            if(estadoInicial == 20){
                numeroDoEstado = 1;
                estadoInicial = 0;
            }
        }
    
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

    public void desenhar(Graphics2D g2){

        BufferedImage imagem = null;
        switch (direcao){
            case "cima":
                if(numeroDoEstado == 1){
                    imagem = cima1;
                }
                if(numeroDoEstado == 2){
                    imagem = cima2;
                }
                // imagem = bogo;
                break;
            case "baixo":
                if(numeroDoEstado == 1){
                   imagem = baixo1;
                }
                if(numeroDoEstado == 2){
                   imagem = baixo2;
                }
                // imagem = bogo;
                break;
            case "esquerda":
                if(numeroDoEstado == 1){
                    imagem = esquerda1;
                }
                if(numeroDoEstado == 2){
                    imagem = esquerda2;
                }
                // imagem = bogo;
                break;
            case "direita":
                if(numeroDoEstado == 1){
                    imagem = direita1;
                }
                if(numeroDoEstado == 2){
                    imagem = direita2;
                }
                // imagem = bogo;
                break;
            case "parado_frente":
                imagem = parado_frente;
                break;
        }

        if(invencivel == true){
            ((Graphics2D) g2).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(imagem, telaX, telaY, null);

        if(invencivel == true){
            ((Graphics2D) g2).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }

        // Para ver a área de colisão usamos isso
        // g2.setColor(Color.red);
        // g2.drawRect(telaX + area_solida.x, telaY + area_solida.y, area_solida.width, area_solida.height);
    }

}
