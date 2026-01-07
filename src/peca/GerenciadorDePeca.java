package peca;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.FerramentaUtilitaria;
import main.PainelDoJogo;

public class GerenciadorDePeca {

    PainelDoJogo pj;
    public Peca[] peca;
    public int numeroDaPecaDoMundo[][];

    public GerenciadorDePeca(PainelDoJogo pj){
        this.pj = pj;
        peca = new Peca[10]; // Cria 10 espaços para cada peça
        numeroDaPecaDoMundo = new int[pj.maxColunasDoMundo][pj.maxLinhaDoMundo];
        getImagemDaPeca();
        carregaMapa("/res/mapas/mapa_do_mundo.txt");
    }
    // Essa classe carrega as imagens das peças.
    public void getImagemDaPeca(){

        configuracoes(0, "grama", false);
        configuracoes(1, "arvore", true);
        configuracoes(2, "chao", false);

    }

    public void configuracoes(int index, String nome_da_imagem, boolean colisao){

        FerramentaUtilitaria ferramenta = new FerramentaUtilitaria();

        try {
            peca[index] = new Peca();
            peca[index].imagem = ImageIO.read(getClass().getResourceAsStream("/res/pecas/" + nome_da_imagem + ".png"));
            peca[index].imagem = ferramenta.imagemRedimensionada(peca[index].imagem, pj.tamanhoDaPeca, pj.tamanhoDaPeca);
            peca[index].colisao = colisao;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void carregaMapa(String caminho_do_mapa){
        try{
            InputStream arq = getClass().getResourceAsStream(caminho_do_mapa);
            BufferedReader leitura = new BufferedReader(new InputStreamReader(arq));
            int col = 0, lin = 0;
            while(col < pj.maxColunasDoMundo && lin < pj.maxLinhaDoMundo){
                String linha = leitura.readLine();
                while(col < pj.maxColunasDoMundo){
                    String numeros[] = linha.split(" "); // split remove da string de acordo com o parâmetro
                    int num = Integer.parseInt(numeros[col]); // Transforma a string em inteiro
                    numeroDaPecaDoMundo[col][lin] = num;
                    col++;
                }
                if(col == pj.maxColunasDoMundo){
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

        int colunaDoMundo = 0;
        int linhaDoMundo = 0;
        // Cria o plano de fundo do jogo;
        while(colunaDoMundo < pj.maxColunasDoMundo && linhaDoMundo < pj.maxLinhaDoMundo){
            int pecaNum = numeroDaPecaDoMundo[colunaDoMundo][linhaDoMundo]; // Faz a leitura do número que estava no arquivo e informa qual é a peça
            
            int mundoX = colunaDoMundo * pj.tamanhoDaPeca;
            int mundoY = linhaDoMundo * pj.tamanhoDaPeca;
            int telaX = mundoX - pj.jogador.mundoX + pj.jogador.telaX; // Foi necessário subtrair para encontrar até onde deveria ser desenhado a tela
            int telaY = mundoY - pj.jogador.mundoY + pj.jogador.telaY; // Foi necessário subtrair para encontrar até onde deveria ser desenhado a tela
            // Foi necessária a soma para garantir que o jogador não encontrasse áreas que não estivessem desenhadas com peças e conseguisse progredir
            
            // Esse condicional garante que a peça só será desenhada se estiver dentro do limite da tela que o jogador vê
            if(mundoX + pj.tamanhoDaPeca > pj.jogador.mundoX - pj.jogador.telaX && mundoX - pj.tamanhoDaPeca < pj.jogador.mundoX + pj.jogador.telaX
            && mundoY + pj.tamanhoDaPeca > pj.jogador.mundoY - pj.jogador.telaY && mundoY - pj.tamanhoDaPeca < pj.jogador.mundoY + pj.jogador.telaY){
                g2.drawImage(peca[pecaNum].imagem, telaX, telaY, null);
            }
            colunaDoMundo++;

            if(colunaDoMundo == pj.maxColunasDoMundo){
                colunaDoMundo = 0;
                linhaDoMundo++;
            }
        }

    }

}
