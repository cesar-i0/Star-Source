package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entidades.Entidade;
import objetos.OBJ_Coracao;



// Esta classe vai lidar com mensagens de texto, ícones de itens, etc.
public class UI {

    PainelDoJogo pj;
    Graphics2D g2;

    Font arial, arial_2;
    BufferedImage coracao_cheio, meio_coracao, coracao_vazio;
    public boolean mensagem_ligada = false;
    public String mensagem  = "";
    int contador_da_mensagem = 0;
    public boolean jogoFinalisado = false;
    public String dialogo_atual = "";
    public int numeroDoComando = 0;
    public int estado_do_titulo_na_tela = 0;

    public UI(PainelDoJogo pj){
        this.pj = pj;
        arial = new Font("Arial", Font.PLAIN,30); // Tipo, estilo e tamanho
        arial_2 = new Font("Arial", Font.BOLD,80); // Tipo, estilo e tamanho
        
        //Cria um objeto mediador(HUD)
        Entidade coracao = new OBJ_Coracao(pj);
        coracao_vazio = coracao.imagem;
        meio_coracao = coracao.imagem2;
        coracao_cheio = coracao.imagem3;

    }

    public void mostrarMensagem(String texto){
        mensagem = texto;
        mensagem_ligada = true;
    }

    public void desenhar(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(arial);
        g2.setColor(Color.white);

        // Estado de título de jogo;
        if(pj.estado_do_jogo == pj.estado_de_titulo){
            desenharTelaDeTitulo();
        }

        // Estado de jogo em curso
        if(pj.estado_do_jogo == pj.estado_de_jogar){
            desenharVidaDoJogador();
        }
        // Estado de pausa de jogo
        if(pj.estado_do_jogo == pj.estado_de_pausa){
            desenharTelaDePausa();
        }
        // Estado de diálogo de jogo
        if(pj.estado_do_jogo == pj.estado_de_dialogo){
            desenharVidaDoJogador();
            desenharTelaDeDialogo();
        }

    }

    public void desenharVidaDoJogador(){

        int x = pj.tamanhoDaPeca / 2;
        int y = pj.tamanhoDaPeca / 2;
        int i = 0;

        // Desenhar os corações que representam a vida máxima(obs: só modificar depois)
        while(i < pj.jogador.vidaMaxima / 2){

            g2.drawImage(coracao_vazio, x, y, null);
            i++;
            x += pj.tamanhoDaPeca;

        }

        // Reset
        x = pj.tamanhoDaPeca / 2;
        y = pj.tamanhoDaPeca / 2;
        i = 0;

        // Desenhar vida atual
        while(i < pj.jogador.vida){
            g2.drawImage(meio_coracao, x, y, null);
            i++;
            if(i < pj.jogador.vida){
                g2.drawImage(coracao_cheio, x, y, null);
            }
            i++;
            x += pj.tamanhoDaPeca;
        }

    }

    public void desenharTelaDeTitulo(){

        if(estado_do_titulo_na_tela == 0){
            
            g2.setColor(new Color(0,0,0));
            g2.fillRect(0, 0, pj.larguraDaTela,pj.alturaDaTela);
    
            // Nome do título do jogo
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String texto = "Star Source";
            int x = getXdoTextoCentralizado(texto);
            int y = pj.tamanhoDaPeca * 3;
    
            // Sombra
            g2.setColor(Color.GRAY);
            g2.drawString(texto, x + 5, y + 5);
    
            // Cor principal
            g2.setColor(Color.white);
            g2.drawString(texto, x, y);
    
            // Personagem principal
            x = pj.larguraDaTela / 2 - (pj.tamanhoDaPeca * 2) / 2;
            y += pj.tamanhoDaPeca * 2;
            g2.drawImage(pj.jogador.parado_frente, x, y, pj.tamanhoDaPeca * 2, pj.tamanhoDaPeca * 2, null);
    
            // Menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
    
            texto = "Novo Jogo";
            x = getXdoTextoCentralizado(texto);
            y += pj.tamanhoDaPeca * 3.5;
            g2.drawString(texto, x, y);
            if(numeroDoComando == 0){
                g2.drawString(">", x - pj.tamanhoDaPeca, y);
            }
    
            texto = "Carrega Jogo";
            x = getXdoTextoCentralizado(texto);
            y += pj.tamanhoDaPeca;
            g2.drawString(texto, x, y);
            if(numeroDoComando == 1){
                g2.drawString(">", x - pj.tamanhoDaPeca, y);
            }
    
            texto = "Sair";
            x = getXdoTextoCentralizado(texto);
            y += pj.tamanhoDaPeca;
            g2.drawString(texto, x, y);
            if(numeroDoComando == 2){
                g2.drawString(">", x - pj.tamanhoDaPeca, y);
            }

        }
        else if(estado_do_titulo_na_tela == 1){

            // Vídeo 17

        }

    }

    public void desenharTelaDePausa(){

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String texto = "PAUSA";
        int x = getXdoTextoCentralizado(texto);
        int y = pj.alturaDaTela / 2;

        g2.drawString(texto, x, y);

    }

    public int getXdoTextoCentralizado(String texto){

        int comprimento = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
        int x = pj.larguraDaTela / 2 - comprimento / 2; // A mensagem será colocada no centro da tela;
        return x;

    }

    public void desenharTelaDeDialogo(){

        // Janela
        int x = pj.tamanhoDaPeca * 2;
        int y = pj.tamanhoDaPeca / 2;
        int largura = pj.larguraDaTela - (pj.tamanhoDaPeca * 4);
        int altura = pj.tamanhoDaPeca * 4;

        desenharSubTela(x, y, largura, altura);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += pj.tamanhoDaPeca;
        y += pj.tamanhoDaPeca;

        for(String linha : dialogo_atual.split("\n")){
            g2.drawString(linha, x, y);
            y += 40;
        }

    }

    public void desenharSubTela(int x, int y, int largura, int altura){

        Color c = new Color(0,0,0, 220); // Cria uma cor personalizada
        g2.setColor(c);
        g2.fillRoundRect(x, y, largura, altura, 35, 35);

        c = new Color(255,255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5)); // Define o contorno
        g2.drawRoundRect(x + 5, y + 5, largura - 10, altura - 10, 25, 25);

    }

}
