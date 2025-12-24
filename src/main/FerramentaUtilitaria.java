package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

// Usamos essa classe para melhorar o desempenho ao desenhar as peças do jogo
public class FerramentaUtilitaria {

    public BufferedImage imagemRedimensionada(BufferedImage original, int largura, int comprimento){

        BufferedImage imagemRedimensionada = new BufferedImage(largura, comprimento, original.getType());
        Graphics2D g2 = imagemRedimensionada.createGraphics();
        g2.drawImage(original, 0, 0, largura, comprimento, null);
        g2.dispose();

        return imagemRedimensionada;
    }

}
