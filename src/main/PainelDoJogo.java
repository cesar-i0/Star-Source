package main;

import entidades.Jogador;


import javax.swing.JPanel;
import java.awt.*;

public class PainelDoJogo extends JPanel implements Runnable {
    // Configurações de tela
    final int tamanhoOriginalDaPeca = 16; // 16x16 por peça(tile), ou seja, personagens ou npcs.
    final int escala = 3;

    public final int tamanhoDaPeca = tamanhoOriginalDaPeca * escala; // 48x48 por peça
    final int telaMaximaHorizontal = 16; // Tamanho máximo de colunas. 3:4 = 16; 16:9 = 32.
    final int telaMaximaVertical = 12; // Tamanho máximo de linhas. 3:4 = 12; 16:9 = 18.
    final int larguraDaTela = tamanhoDaPeca * telaMaximaHorizontal; // 48*16 = 768 pixels ou 48*32 = 1536 pixels.
    final int comprimentoDaTela = tamanhoDaPeca * telaMaximaVertical; // 48*12 = 576 pixels ou 48*18 = 864 pixels.

    int FPS = 60; // Frames Per Second

    Manipulador keyManipulador = new Manipulador();
    Thread threadDoJogo; // ALgo que podemos iniciar e parar a fim de deixar o programa rodando.
    Jogador jogador = new Jogador(this, keyManipulador);

    // Definição da posição inicial do jogador
    int jogadorX = 100;
    int jogadorY = 100;
    int velocidadeDoJogador = 4;


    public PainelDoJogo() {
        this.setPreferredSize(new Dimension(larguraDaTela, comprimentoDaTela));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Toda a pintura do componente será feita se fora da tela pela pintura do buffer. (Pode melhorar a performance do jogo)
        this.addKeyListener(keyManipulador);
        this.setFocusable(true); // Com isso, o PainelDoJogo pode focar em receber a key de entrada

    }

    public void iniciarThreadDoJogo() {
        threadDoJogo = new Thread(this); // Usamos o "this" para referenciar a classe "PainelDoJogo".
        threadDoJogo.start(); // Vai automaticamente iniciar o metodo "run"
    }

    // Aqui vamos usar o game loop chamado "Delta/Acumulator"
    @Override
    public void run() {
        double desenheNoIntervalo = 1000000000 / FPS;
        double delta = 0;
        long ultimoTempo = System.nanoTime();
        long tempoAtual;
        // long temporizador = 0; // Extra para ver os FPS.
        // int contadorDeDesenhar = 0; // Extra para os FPS.
        while (threadDoJogo != null) {
            tempoAtual = System.nanoTime();
            delta += (tempoAtual - ultimoTempo) / desenheNoIntervalo;
            ultimoTempo = tempoAtual;
            // temporizador += tempoAtual - ultimoTempo; // Extra;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                // contadorDeDesenhar++; // Extra para contador e FPS
            }
            /* Extra para mostrar os FPS
            if (temporizador >= 1000000000) {
                System.out.println("FPS: " + contadorDeDesenhar);
                contadorDeDesenhar = 0;
                temporizador = 0;
            }*/
        }
    }

    public void update(){
        jogador.update();
    }

    // A classe "Graphics" tem muitas funções para desenhar objetos na tela.
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; // Muda os gráficos de g para g2.
        // A classe Graphics2D extende a classe Graphics para promover um controle mais sofisticado de geometria,
        // transformações de coordenadas, gerenciamento de cor, e layout de texto.
        jogador.desenhar(g2);
        g2.dispose(); // Descarta isso do contexto de graphics e libera qualquer recursos que estão sendo usandos.
    }

}
