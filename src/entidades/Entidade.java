package entidades;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.FerramentaUtilitaria;
import main.PainelDoJogo;

/*
 * Essa classe guarda todas as variáveis que serão utilizadas pelo jogador, monstros ou NPCs.
 */
public class Entidade {

    PainelDoJogo pj;

    public int mundoX, mundoY;
    public int velocidade;

    // Precisamos usamos variáveis como essas para trazer as imagens que serão as animações de movimentação
    public BufferedImage cima1, cima2, baixo1, baixo2, esquerda1, esquerda2, direita1, direita2, parado_frente, parado_costas;
    public String direcao = "parado_frente"; // Aqui será guardado qual imagem deve ser mostarda com determinada ação
    public int contadorDoEstado = 0;
    public int numeroDoEstado = 1;
    int estadoInicial = 0;
    public Rectangle area_solida;
    public int area_solida_padraoX, area_solida_padraoY;
    public boolean colisao_ligada = false;
    public int trava_de_contador_de_acao = 0;
    String dialogos[] =  new String[20];
    int index_de_dialogo = 0;
    public BufferedImage imagem, imagem2, imagem3;
    public String nome;
    public boolean colisao = false;

    // Status do personagem
    public int vidaMaxima;
    public int vida;

    public Entidade(PainelDoJogo pj){
        this.pj = pj;
    }

    public BufferedImage configuracoes(String caminho_da_imagem){

        FerramentaUtilitaria ferramenta = new FerramentaUtilitaria();

        BufferedImage imagem = null;

        try {

            imagem = ImageIO.read(getClass().getResourceAsStream(caminho_da_imagem +".png"));
            imagem = ferramenta.imagemRedimensionada(imagem, pj.tamanhoDaPeca, pj.tamanhoDaPeca);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return imagem;
    }

    public void setAcao(){}

    public void falar(){

        if(dialogos[index_de_dialogo] == null) index_de_dialogo = 0;

        pj.ui.dialogo_atual = dialogos[index_de_dialogo];
        index_de_dialogo++;

        switch (pj.jogador.direcao) {
            case "cima":
                direcao = "baixo";
                break;
            case "baixo":
                direcao = "cima";
            break;
            case "direita":
                direcao = "direita";
                break;
            case "esquerda":
                direcao = "esquerda";
                break;
        }

    }

    public void update(){

        setAcao();

        // As ações abaixo verificam as possíveis colisões
        colisao_ligada = false;
        pj.verifica.verificaPeca(this);
        pj.verifica.verificaObjeto(this, false);
        pj.verifica.verificaJogador(this);

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

            else{
                estadoInicial++;
                if(estadoInicial == 20){
                    numeroDoEstado = 1;
                    estadoInicial = 0;
                }
            }

    }

    public void desenhar(Graphics2D g2){
        
        BufferedImage imagem = null;

        int telaX = mundoX - pj.jogador.mundoX + pj.jogador.telaX; // Foi necessário subtrair para encontrar até onde deveria ser desenhado a tela
        int telaY = mundoY - pj.jogador.mundoY + pj.jogador.telaY; // Foi necessário subtrair para encontrar até onde deveria ser desenhado a tela

        if(mundoX + pj.tamanhoDaPeca > pj.jogador.mundoX - pj.jogador.telaX && mundoX - pj.tamanhoDaPeca < pj.jogador.mundoX + pj.jogador.telaX
        && mundoY + pj.tamanhoDaPeca > pj.jogador.mundoY - pj.jogador.telaY && mundoY - pj.tamanhoDaPeca < pj.jogador.mundoY + pj.jogador.telaY){
            
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

            g2.drawImage(imagem, telaX, telaY, pj.tamanhoDaPeca, pj.tamanhoDaPeca, null);
        }
        
    }

}
