package peca;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.PainelDoJogo;

public class GerenciadorDePeca {

    PainelDoJogo pj;
    Peca[] peca;
    int pecaDeMapas[][];

    public GerenciadorDePeca(PainelDoJogo pj){
        this.pj = pj;
        peca = new Peca[10]; // Cria 10 espaços para cada peça
        pecaDeMapas = new int[pj.telaMaximaHorizontal][pj.telaMaximaVertical];
        getImagemDaPeca();
        carregaMapa("/res/mapas/mapa1.txt");
    }
    // Essa classe carrega as imagens das peças.
    public void getImagemDaPeca(){
        try{
            peca[0] = new Peca();
            peca[0].imagem = ImageIO.read(getClass().getResourceAsStream("/res/peças/Água.png"));
            peca[1] = new Peca();
            peca[1].imagem = ImageIO.read(getClass().getResourceAsStream("/res/peças/Parede.png"));
            /*
            peca[1] = new Peca();
            peca[1].imagem = ImageIO.read(getClass().getResourceAsStream("/res/peças/pecaTeste.png"));

            peca[2] = new Peca();
            peca[2].imagem = ImageIO.read(getClass().getResourceAsStream("/res/peças/pecaTeste.png"));

            */
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void carregaMapa(String caminho_do_mapa){
        try{
            InputStream arq = getClass().getResourceAsStream(caminho_do_mapa);
            BufferedReader leitura = new BufferedReader(new InputStreamReader(arq));
            int col = 0, lin = 0;
            while(col < pj.telaMaximaHorizontal && lin < pj.telaMaximaVertical){
                String linha = leitura.readLine();
                while(col < pj.telaMaximaHorizontal){
                    String numeros[] = linha.split(" "); // split remove da string de acordo com o parâmetro
                    int num = Integer.parseInt(numeros[col]); // Transforma a string em inteiro
                    pecaDeMapas[col][lin] = num;
                    col++;
                }
                if(col == pj.telaMaximaHorizontal){
                    col = 0;
                    lin++;
                }
            }
            leitura.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void desenhar(Graphics2D g2){

        int col = 0, lin = 0, x = 0, y = 0;
        // Cria o plano de fundo do jogo;
        while(col < pj.telaMaximaHorizontal && lin < pj.telaMaximaVertical){
            int pecaNum = pecaDeMapas[col][lin]; // Faz a leitura do número que estava no arquivo e informa qual é a peça
            g2.drawImage(peca[pecaNum].imagem, x, y, pj.tamanhoDaPeca, pj.tamanhoDaPeca, null);
            col++;
            x += pj.tamanhoDaPeca;
            if(col == pj.telaMaximaHorizontal){
                col = 0;
                x = 0;
                lin++;
                y += pj.tamanhoDaPeca;
            }
        }

    }

}
