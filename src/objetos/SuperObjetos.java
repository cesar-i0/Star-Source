package objetos;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.FerramentaUtilitaria;
import main.PainelDoJogo;

public class SuperObjetos {
    
    public BufferedImage imagem;
    public String nome;
    public boolean colisao = false;
    public int mundoX, mundoY;
    public Rectangle area_solida = new Rectangle(0,0,48,48); // Aqui definimos a área que é sólida no objeto
    public int area_solida_padraoX = 0;
    public int area_solida_padraoY = 0;
    FerramentaUtilitaria ferramenta =  new FerramentaUtilitaria();

    public void desenhar(Graphics2D g2, PainelDoJogo pj){
        int telaX = mundoX - pj.jogador.mundoX + pj.jogador.telaX; // Foi necessário subtrair para encontrar até onde deveria ser desenhado a tela
        int telaY = mundoY - pj.jogador.mundoY + pj.jogador.telaY; // Foi necessário subtrair para encontrar até onde deveria ser desenhado a tela

        if(mundoX + pj.tamanhoDaPeca > pj.jogador.mundoX - pj.jogador.telaX && mundoX - pj.tamanhoDaPeca < pj.jogador.mundoX + pj.jogador.telaX
        && mundoY + pj.tamanhoDaPeca > pj.jogador.mundoY - pj.jogador.telaY && mundoY - pj.tamanhoDaPeca < pj.jogador.mundoY + pj.jogador.telaY){
            g2.drawImage(imagem, telaX, telaY, pj.tamanhoDaPeca, pj.tamanhoDaPeca, null);
        }
    }

}
