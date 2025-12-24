package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import objetos.OBJ_Chave;

// Esta classe vai lidar com mensagens de texto, ícones de itens, etc.
public class UI {

    PainelDoJogo pj;
    Font arial, arial_2;
    BufferedImage imagem_da_chave;
    public boolean mensagem_ligada = false;
    public String mensagem  = "";
    int contador_da_mensagem = 0;
    public boolean jogoFinalisado = false;

    double tempo_de_jogo;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(PainelDoJogo pj){
        this.pj = pj;
        arial = new Font("Arial", Font.PLAIN,30); // Tipo, estilo e tamanho
        arial_2 = new Font("Arial", Font.BOLD,80); // Tipo, estilo e tamanho
        OBJ_Chave chave = new OBJ_Chave(pj);
        imagem_da_chave = chave.imagem;
    }

    public void mostrarMensagem(String texto){
        mensagem = texto;
        mensagem_ligada = true;
    }

    public void desenhar(Graphics2D g2){

        if(jogoFinalisado == true){

            g2.setFont(arial); 
            g2.setColor(Color.white);

            String texto;
            int comprimento_do_texto, x, y;
            
            texto = "Você ganhou o jogo!";
            comprimento_do_texto = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();

            x = pj.larguraDaTela / 2 - comprimento_do_texto / 2;
            y = pj.comprimentoDaTela / 2 - (pj.tamanhoDaPeca * 3);
            g2.drawString(texto, x, y);

            // Outra mensagem
            texto = "Seu tempo foi: " + dFormat.format(tempo_de_jogo) + "!";
            comprimento_do_texto = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
            x = pj.larguraDaTela / 2 - comprimento_do_texto / 2;
            y = pj.comprimentoDaTela / 2 + (pj.tamanhoDaPeca * 4);
            g2.drawString(texto, x, y);

            // Outra mensagem
            g2.setFont(arial_2); 
            g2.setColor(Color.yellow);

            texto = "PARABÉNS!";
            comprimento_do_texto = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
            x = pj.larguraDaTela / 2 - comprimento_do_texto / 2;
            y = pj.comprimentoDaTela / 2 + (pj.tamanhoDaPeca * 2);
            g2.drawString(texto, x, y);
            pj.threadDoJogo = null;

        }
        else{
            g2.setFont(arial); 
            g2.setColor(Color.white);
            g2.drawImage(imagem_da_chave, pj.tamanhoDaPeca / 6 , pj.tamanhoDaPeca / 6, pj.tamanhoDaPeca, pj.tamanhoDaPeca, null);
            g2.drawString("x " +  pj.jogador.possuiChave , 40, 40);

            // Tempo
            tempo_de_jogo += (double)1/60;
            g2.drawString("Tempo: "+ dFormat.format(tempo_de_jogo), pj.tamanhoDaPeca * 11 , 65);
    
            // Mensagem 
            if(mensagem_ligada == true){
                g2.setFont(g2.getFont().deriveFont(20F));
                g2.drawString(mensagem, pj.tamanhoDaPeca / 2, pj.tamanhoDaPeca * 5);
    
                contador_da_mensagem++;
    
                if(contador_da_mensagem > 120){ // Após dois segundos ela irá summir porque 60 é um frame por segundo
                    contador_da_mensagem = 0;
                    mensagem_ligada = false;
                }
            }

        }

    }

}
