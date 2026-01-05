package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entidades.Entidade;
import objetos.OBJ_Coracao;

// Esta classe vai lidar com mensagens de texto, ícones de itens, etc.
public class UI{

    PainelDoJogo pj;
    Graphics2D g2;

    Font arial, arial_2;
    BufferedImage coracao_cheio, meio_coracao, coracao_vazio;
    public boolean mensagem_ligada = false;
    //public String mensagem  = "";
    //int contador_da_mensagem = 0;
    ArrayList<String> mensagem = new ArrayList<>();
    ArrayList<Integer> contador_da_mensagem = new ArrayList<>();
    public boolean jogoFinalisado = false;
    public String dialogo_atual = "";
    public int numeroDoComando = 0;
    public int estado_do_titulo_na_tela = 0;
    public int compartimento_coluna = 0; 
    public int compartimento_linha = 0;

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
        mensagem.add(texto);
        contador_da_mensagem.add(0);
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
            desenharMensagem();
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
        // Estado de personagem de jogo
        if(pj.estado_do_jogo == pj.estado_de_personagem){
            desenharTelaPersonagem();
            desenharInventario();

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

    public void desenharMensagem(){
        int mensagemX = pj.tamanhoDaPeca;
        int mensagemY = pj.tamanhoDaPeca*4;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for(int i = 0; i< mensagem.size(); i++){
            if(mensagem.get(i)!= null){


                g2.setColor(Color.black);
                g2.drawString(mensagem.get(i), mensagemX+2, mensagemY+2);

                g2.setColor(Color.white);
                g2.drawString(mensagem.get(i), mensagemX, mensagemY);

                int cont = contador_da_mensagem.get(i)+1;
                contador_da_mensagem.set(i, cont);
                mensagemY += 50;

                if(contador_da_mensagem.get(i)>180){
                    mensagem.remove(i);
                    contador_da_mensagem.remove(i);
                }
            }
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

    public void desenharTelaPersonagem(){

        //Cria um Frame
        final int frameX = pj.tamanhoDaPeca;
        final int frameY = pj.tamanhoDaPeca;
        final int frameLargura = pj.larguraDaTela - (pj.tamanhoDaPeca * 10);
        final int frameAltura = pj.alturaDaTela - (pj.tamanhoDaPeca * 2);
        desenharSubTela(frameX, frameY, frameLargura, frameAltura);

        //Criar texto
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32));
        int textX = frameX + 20;
        int textY = frameY + pj.tamanhoDaPeca+14;
        final int linhaAltura = 34;

        //Nomes
        g2.drawString("Nível", textX, textY);
        textY += linhaAltura;
        g2.drawString("Vida", textX, textY);
        textY += linhaAltura;
        g2.drawString("Ataque", textX, textY);
        textY += linhaAltura;
        g2.drawString("Defesa", textX, textY);
        textY += linhaAltura;
        g2.drawString("Experiência", textX, textY);
        textY += linhaAltura;
        g2.drawString("Exp", textX, textY);
        textY += linhaAltura;
        g2.drawString("Próximo nível", textX, textY);
        textY += linhaAltura;
        g2.drawString("Moedas", textX, textY);
        textY += linhaAltura+10;
        g2.drawString("Espada", textX, textY);
        textY += linhaAltura+10;
        g2.drawString("Escudo", textX, textY);
        textY += linhaAltura;

        //Valores
        int tailX = frameX + frameLargura - 30;

        //Reseta textY
        textY = frameY + pj.tamanhoDaPeca;
        String valor; 

        //Imprimimos os valores na tela
        valor = String.valueOf(pj.jogador.nivel);
        textX = getXdoTextoDireita(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += linhaAltura;

        valor = String.valueOf(pj.jogador.vida + "/" + pj.jogador.vidaMaxima);
        textX = getXdoTextoDireita(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += linhaAltura;

        valor = String.valueOf(pj.jogador.ataques);
        textX = getXdoTextoDireita(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += linhaAltura;

        valor = String.valueOf(pj.jogador.defesa);
        textX = getXdoTextoDireita(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += linhaAltura;

        valor = String.valueOf(pj.jogador.experiencia);
        textX = getXdoTextoDireita(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += linhaAltura;

        valor = String.valueOf(pj.jogador.exp);
        textX = getXdoTextoDireita(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += linhaAltura;

        valor = String.valueOf(pj.jogador.expProximoNivel);
        textX = getXdoTextoDireita(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += linhaAltura;

        valor = String.valueOf(pj.jogador.moedas);
        textX = getXdoTextoDireita(valor, tailX);
        g2.drawString(valor, textX, textY);
        textY += linhaAltura;

        //Desenhamos asimagens ao lado dasarmas e escudos
        g2.drawImage(pj.jogador.correnteArma.baixo1, tailX - pj.tamanhoDaPeca, textY-14, null );
        textY += pj.tamanhoDaPeca;

        g2.drawImage(pj.jogador.correnteEscudo.baixo1, tailX - pj.tamanhoDaPeca, textY-14, null );
        

    }

    public void desenharInventario(){
        int frameX = pj.tamanhoDaPeca*9;
        int frameY = pj.tamanhoDaPeca;
        int frameWidth = pj.tamanhoDaPeca*6;
        int frameHeight = pj.tamanhoDaPeca*5;
        desenharSubTela(frameX, frameY, frameWidth, frameHeight);

        //Compartimento

        final int compartimentoXinicio = frameX+20;
        final int compartimentoYinicio = frameY+20;
        int compartimentoX = compartimentoXinicio;
        int compartimentoY = compartimentoYinicio;
        int tamanho_compartimento = pj.tamanhoDaPeca + 3;

        //desenhar Itens do Jogador
        for(int i = 0; i < pj.jogador.inventario.size(); i++){
            
            g2.drawImage(pj.jogador.inventario.get(i).baixo1, compartimentoX, compartimentoY, null);
            compartimentoX += tamanho_compartimento;

            if(i == 4 || i == 9 || i == 14){
                compartimentoX = compartimentoXinicio;
                compartimentoY = tamanho_compartimento;
            }
        }
        

        //Cursor
        int cursorX = compartimentoXinicio + (tamanho_compartimento*compartimento_coluna);
        int cursorY = compartimentoYinicio + (tamanho_compartimento*compartimento_linha);
        int cursorLargura = pj.tamanhoDaPeca;
        int cursorAltura = pj.tamanhoDaPeca;

        //Descrição frame
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = pj.tamanhoDaPeca*3;
        desenharSubTela(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

        //Texto descrição
        int textX = dFrameX+20;
        int textY = dFrameY + pj.tamanhoDaPeca;

        g2.setFont(g2.getFont().deriveFont(28F));
        int index_item = getIndex_item_no_compartimento();

        if(index_item < pj.jogador.inventario.size()){

            for(String line: pj.jogador.inventario.get(index_item).descricao.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 32;
        }
        }

        

        //Desenhar
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorLargura, cursorAltura, 10, 10);

    }

    public int getIndex_item_no_compartimento(){
        int index_item = compartimento_coluna+(compartimento_linha*5);
        return index_item;

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

    public int getXdoTextoDireita(String nome, int tailX){

        int comprimento = (int)g2.getFontMetrics().getStringBounds(nome, g2).getWidth();
        int x = tailX - comprimento; // A mensagem será colocada no centro da tela;
        return x;

    }


}
