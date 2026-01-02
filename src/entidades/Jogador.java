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
        AtaqueArea.width = 36;
        AtaqueArea.height = 36;

        setValoresPadroes();
        getImagemDoJogador();
        getAtaqueJogadorImagem();
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
        
        cima1 = configuracoes("/res/jogador/andando_costas1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        cima2 = configuracoes("/res/jogador/andando_costas2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        baixo1 = configuracoes("/res/jogador/andando_frente1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        baixo2 = configuracoes("/res/jogador/andando_frente2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        esquerda1 = configuracoes("/res/jogador/parado_esquerda", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        esquerda2 = configuracoes("/res/jogador/andando_esquerda", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        direita1 = configuracoes("/res/jogador/parado_direita",pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        direita2 = configuracoes("/res/jogador/andando_direita", pj.tamanhoDaPeca, pj.tamanhoDaPeca); 
        parado_frente = configuracoes("/res/jogador/parado", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        parado_costas = configuracoes("/res/jogador/parado_costas", pj.tamanhoDaPeca, pj.tamanhoDaPeca);

    }

    public void getAtaqueJogadorImagem(){

        ataque = configuracoes("/res/jogador/ataque.png",  pj.tamanhoDaPeca, pj.tamanhoDaPeca); //Busca a imagem deataque
       /* ataqueCima2 = configuracoes("/res/jogador/ataque",  pj.tamanhoDaPeca, pj.tamanhoDaPeca*2); //Multiplica para ficar maior e enquadrar a parte da arma do jogador
        ataqueBaixo1 = configuracoes("/res/jogador/ataque",  pj.tamanhoDaPeca, pj.tamanhoDaPeca*2);
        ataqueBaixo2 = configuracoes("/res/jogador/ataque",  pj.tamanhoDaPeca, pj.tamanhoDaPeca*2);
        ataqueEsquerda1 = configuracoes("/res/jogador/ataque",  pj.tamanhoDaPeca*2, pj.tamanhoDaPeca);
        ataqueEsquerda2 = configuracoes("/res/jogador/ataque",  pj.tamanhoDaPeca*2, pj.tamanhoDaPeca);
        ataqueDireita1 = configuracoes("/res/jogador/ataque",  pj.tamanhoDaPeca*2, pj.tamanhoDaPeca);
        ataqueDireita2 = configuracoes("/res/jogador/ataque",  pj.tamanhoDaPeca*2, pj.tamanhoDaPeca);
*/ 
    }

    

    public void update(){

        if(atacando == true){
           atacando();
        } 
        else if(manipulador.cimaPrecionado || manipulador.baixoPrecionado || manipulador.esquerdaPrecionado || manipulador.direitaPreciondo || manipulador.enterPressionado){
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
            int index_do_monstro = pj.verifica.verificaEntidade(this, pj.monstros);
            contatoComMonstros(index_do_monstro);

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
        else {
            estadoInicial++;
            if(estadoInicial == 20){
                numeroDoEstado = 1;
                estadoInicial = 0;
            }
        }
        
        if(invencivel == true){
            contador_de_invencibilidade++;
            if(contador_de_invencibilidade > 60){
                invencivel = false;
                contador_de_invencibilidade = 0;
            }
        }
        
    
    }

    public void atacando(){
        contadorDoEstado++;
        if(contadorDoEstado <= 5){
            numeroDoEstado = 1;
        }
        if(contadorDoEstado > 5 && contadorDoEstado <= 25){
            numeroDoEstado = 2;
            int correnteMundoX = mundoX;
            int correnteMundoY = mundoY;
            int area_solidaWidth = area_solida.width;
            int area_solidaHeight = area_solida.height;

            // Ajusta a área de ataque dependendo da direção
            switch(direcao){
                case "cima":
                    mundoY -= AtaqueArea.height; break;
                case "baixo":
                    mundoY += area_solidaHeight; break;
                case "esquerda":
                    mundoX -= AtaqueArea.width; break;
                case "direita":
                    mundoX += area_solidaWidth; break;
            }

            //Area de ataque se torna sólida
            area_solida.width = AtaqueArea.width;
            area_solida.height = AtaqueArea.height;

            //Checa colisão com monstros
            int index_do_monstro = pj.verifica.verificaEntidade(this, pj.monstros);
            danoMonstro(index_do_monstro);

            //Reverte as posições e tamanhos após o ataque
            mundoX = correnteMundoX;
            mundoY = correnteMundoY;
            area_solida.width = area_solidaWidth;
            area_solida.height = area_solidaHeight;

        }
        if(contadorDoEstado > 25){
            numeroDoEstado = 1;
            contadorDoEstado = 0;
            atacando = false;
        }
    }

    public void pegueObjeto(int i){
        // Se não for 999 então a entidade tocou no objeto
        if(i != 999){
            
            

        }

    }

    public void interacaoNPC(int i){

        if(pj.chaveManipuladora.enterPressionado == true){

             if(i != 999){

            if(pj.chaveManipuladora.enterPressionado == true){
                // System.out.println("Colidindo com NPC");
                pj.estado_do_jogo = pj.estado_de_dialogo;
                pj.npc[i].falar();
            }
        } else {
            atacando = true;
        }
    }
}


       

    public void contatoComMonstros(int i){
        if(i != 999){
            if(invencivel == false){
                vida--;
                invencivel = true;
            }
        }
    }

    public void danoMonstro(int i){
        if(i != 999){
            if(pj.monstros[i].invencivel == false){
                pj.monstros[i].vida-=1;
                pj.monstros[i].invencivel = true;

                // System.out.println("Dano no monstro: " + pj.monstros[i].vida);

                if(pj.monstros[i].vida <= 0){
                    pj.monstros[i] = null;
                    // System.out.println("Monstro derrotado!");
                }
            }
        }
    }


    public void desenhar(Graphics2D g2){

        BufferedImage imagem = null;
        int TempTelaX = telaX;
        int TempTelaY = telaY;  

        switch (direcao){
            case "cima":
                if(atacando == false){
                if(numeroDoEstado == 1){
                    imagem = cima1;
                }
                if(numeroDoEstado == 2){
                    imagem = cima2;
                }
            } 
            if(atacando == true){
                //TempTelaY = telaY - pj.tamanhoDaPeca; // Ajusta a posição da imagem de ataque para cima
                 if(numeroDoEstado == 1){ imagem = ataque;
                }
                if(numeroDoEstado == 2){ imagem = ataque;
                }
            }
                break;

            case "baixo":
                if(atacando == false){
                if(numeroDoEstado == 1){ imagem = baixo1;
                }
                if(numeroDoEstado == 2){ imagem = baixo2;
                }
            } 
            if(atacando == true){
                 if(numeroDoEstado == 1){ imagem = ataque;
                }
                if(numeroDoEstado == 2){ imagem = ataque;
                }
            }
                break;

            case "esquerda":
                if(atacando == false){
                if(numeroDoEstado == 1){
                    imagem = esquerda1;
                }
                if(numeroDoEstado == 2){
                    imagem = esquerda2;
                }
            }
            if(atacando == true){
               // TempTelaX = telaX - pj.tamanhoDaPeca; // Ajusta a posição da imagem de ataque para esquerda (usar somente se precisar reajustar a imagem do ataque)
                 if(numeroDoEstado == 1){ imagem = ataque;
                }
                if(numeroDoEstado == 2){ imagem = ataque;
                }
            }
                break;

            case "direita":
                if(atacando == false){
                if(numeroDoEstado == 1){
                    imagem = direita1;
                }
                if(numeroDoEstado == 2){
                    imagem = direita2;
                }
            }
            if(atacando == true){
                 if(numeroDoEstado == 1){ imagem = ataque;
                }
                if(numeroDoEstado == 2){ imagem = ataque;
                }
            }
                break;
                
            case "parado_frente":
                imagem = parado_frente;
                break;
        }

        if(invencivel == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // Deixa o jogado meio transparente
        }

        g2.drawImage(imagem, TempTelaX, TempTelaY, null);
        
        // Resta o alpha
        if(invencivel == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }

        // DEBUG
        // g2.setFont(new Font("Arial",Font.PLAIN, 26));
        // g2.setColor(Color.white);
        // g2.drawString("Invencível: " + contador_de_invencibilidade, 10, 400);

        // Para ver a área de colisão usamos isso
        // g2.setColor(Color.red);
        // g2.drawRect(telaX + area_solida.x, telaY + area_solida.y, area_solida.width, area_solida.height);

            }
        }
        
