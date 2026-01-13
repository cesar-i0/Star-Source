package main;

import entidades.Entidade;
import entidades.Jogador;
import peca.GerenciadorDePeca;
import peca.peca_interativa.Peca_Interativa;

import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Comparator;

public class PainelDoJogo extends JPanel implements Runnable {
    // Configurações de tela
    final int tamanhoOriginalDaPeca = 16; // 16x16 por peça(tile), ou seja, personagens ou npcs.
    final int escala = 3;

    public final int tamanhoDaPeca = tamanhoOriginalDaPeca * escala; // 48x48 por peça
    public final int telaMaximaHorizontal = 20; // Tamanho máximo de colunas. 3:4 = 16; 16:9 = 32.
    public final int telaMaximaVertical = 12; // Tamanho máximo de linhas. 3:4 = 12; 16:9 = 18.
    public final int larguraDaTela = tamanhoDaPeca * telaMaximaHorizontal; // 48*16 = 768 pixels ou 48*32 = 1536 pixels. WIDTH
    public final int alturaDaTela = tamanhoDaPeca * telaMaximaVertical; // 48*12 = 576 pixels ou 48*18 = 864 pixels. LENGHT
    public final int maximomapa = 2; // Quantidade máxima de mapas
    public  int mapaatual = 0; // Mapa atual


    // Condigurações do mundo
    public final int maxColunasDoMundo = 50;
    public final int maxLinhaDoMundo = 50;

    //Para tela cheia
    int larguraDaTela2 = larguraDaTela;
    int alturaDaTela2 = alturaDaTela;
    BufferedImage tempTela;
    Graphics2D g2;
    public boolean telaCheiaLigada = false;
    // Frames Per Second
    int FPS = 60; 

    // Sistema
    
    GerenciadorDePeca peca_tela = new GerenciadorDePeca(this);
    public Manipulador chaveManipuladora = new Manipulador(this);
    Som musica = new Som();
    Som efeitoSonoro =  new Som();
    public VerificaColisao verifica = new VerificaColisao(this);
    public ConfiguraRecurso configura_recurso = new ConfiguraRecurso(this);
    public UI ui =  new UI(this);
    public ManipuladorDeEvento evento = new ManipuladorDeEvento(this);
    Config config = new Config(this);
    Thread threadDoJogo; // ALgo que podemos iniciar e parar a fim de deixar o programa rodando.
    
    // Entidade e Objeto
    // o segundo [] é para indicar em qual mapa o mostro vai ficar 
    public Jogador jogador = new Jogador(this, chaveManipuladora);
    public Entidade obj[][] = new Entidade[maximomapa][20]; // Torna possível mostrar 10 objetos no mesmo display/tela
    public Entidade npc[][] = new Entidade[maximomapa][10];
    public Entidade monstros[][] = new Entidade[maximomapa][20];
    public Peca_Interativa iPeca[][] = new Peca_Interativa[maximomapa][50];
    public ArrayList<Entidade> listaDeProjeteisDePecas = new ArrayList<>();
    ArrayList<Entidade> listaDeEntidades = new ArrayList<>();

    // Estado do jogo
    public int estado_do_jogo;
    public final int estado_de_titulo = 0;
    public int estado_de_jogar = 1;
    public final int estado_de_pausa = 2;
    public final int estado_de_dialogo = 3;
    public final int estado_de_personagem = 4;
    public final int estado_de_opcoes = 5;
    public final int estado_fim_de_jogo = 6;
    public final int estado_de_transicao = 7;
    public final int estado_de_troca = 8;


    public PainelDoJogo() {
        this.setPreferredSize(new Dimension(larguraDaTela, alturaDaTela));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Toda a pintura do componente será feita se fora da tela pela pintura do buffer. (Pode melhorar a performance do jogo)
        this.addKeyListener(chaveManipuladora);
        this.setFocusable(true); // Com isso, o PainelDoJogo pode focar em receber a key de entrada

    }

    public void configuracao_do_jogo(){

        configura_recurso.setObjeto();
        configura_recurso.setNPC();
        configura_recurso.setMonstro(); // Adiciona os monstros
      
        // tocarMusica(0);
        estado_do_jogo = estado_de_titulo;
       
        tempTela = new BufferedImage(larguraDaTela, alturaDaTela, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempTela.getGraphics();
        //Se quisermos que o jogo não seja exibido em tela cheia é só comentar o método abaixo
        if(telaCheiaLigada == true){
             setTelaCheia();
        }
       
    }
    public void novamente(){
        jogador.setPosicoesPadrao();
        jogador.restauraVidaMana();
        configura_recurso.setNPC();
        configura_recurso.setMonstro();
    }
    public void recomecar(){
        jogador.setValoresPadroes();
        jogador.setPosicoesPadrao();
        jogador.restauraVidaMana();
        jogador.setItens();
         configura_recurso.setObjeto();
        configura_recurso.setNPC();
        configura_recurso.setMonstro();
    }

    public void setTelaCheia(){
        //Pegar o devido local da tela
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        //Tela cheia largura e altura
        larguraDaTela2 = Main.window.getWidth();
        alturaDaTela2 = Main.window.getHeight();
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
                //repaint();
                desenharTempTela();
                desenharTela();
                delta--;
                //contadorDeDesenhar++; // Extra para contador e FPS
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
        if(estado_do_jogo == estado_de_jogar){
            // Jogador
            jogador.update();
            // NPC
            for(int i = 0; i < npc[1].length; i++){
                if(npc[mapaatual][i] != null){
                    npc[mapaatual][i].update();
                }
            }
            // Monstros
            for(int i = 0; i < monstros[1].length; i++){
                if(monstros[mapaatual][i] != null){
                    if(monstros[mapaatual][i].vivo == true && monstros[mapaatual][i].morrendo == false){
                        monstros[mapaatual][i].update();
                    }
                    if(monstros[mapaatual][i].vivo == false){
                        monstros[mapaatual][i].verificaDrop();
                        monstros[mapaatual][i] = null;
                    }
                }
            }

            for(int i = 0; i < listaDeProjeteisDePecas.size(); i++){
                if(listaDeProjeteisDePecas.get(i)!= null){
                    if(listaDeProjeteisDePecas.get(i).vivo == true ) listaDeProjeteisDePecas.get(i).update();
                    if(listaDeProjeteisDePecas.get(i).vivo == false) listaDeProjeteisDePecas.remove(i);
                }
            }

        }
        for(int i = 0; i < iPeca[1].length; i++){
            if(iPeca[mapaatual][i] != null){
                iPeca[mapaatual][i].update();
            }
        }
        if(estado_do_jogo == estado_de_pausa){
            // nada por enquanto
        }
        
}

    public void desenharTempTela(){

          // Tela de título
        if(estado_do_jogo == estado_de_titulo){
            ui.desenhar(g2);
        }
        else{
            // Peça
            peca_tela.desenhar(g2); 

            // Peças interativas
            for(int i = 0; i < iPeca[1].length; i++){
            if(iPeca[mapaatual][i] != null){
                iPeca[mapaatual][i].desenhar(g2);
            }
        }
            
            // Adiciona entidades para a lista
            listaDeEntidades.add(jogador);
            for(int i = 0; i < npc[1].length; i++){
                if(npc[mapaatual][i] != null){
                    listaDeEntidades.add(npc[mapaatual][i]);
                }
            }
            for(int i = 0; i < obj[1].length; i++){
                if(obj[mapaatual][i] != null){
                    listaDeEntidades.add(obj[mapaatual][i]);
                }
            }
            for(int i = 0; i < monstros[1].length; i++){
                if(monstros[mapaatual][i] != null){
                    listaDeEntidades.add(monstros[mapaatual][i]);
                }
            }
            for(int i = 0; i < listaDeProjeteisDePecas.size(); i++){
                if(listaDeProjeteisDePecas.get(i) != null){
                    listaDeEntidades.add(listaDeProjeteisDePecas.get(i));
                }
            }

            // SORT
            Collections.sort(listaDeEntidades, new Comparator<Entidade>() {
                @Override
                public int compare(Entidade e1, Entidade e2) {
                    // Ordena primeiro pela coordenada Y para desenhar de cima para baixo;
                    int resultado = Integer.compare(e1.mundoY, e2.mundoY);
                    // Se empatar em Y, ordenda por X(da esquerda para a direita)
                    if(resultado == 0) resultado = Integer.compare(e1.mundoX, e2.mundoX);
                    return resultado;
                }
            });

            // Desenha entidades
            for(int i = 0; i < listaDeEntidades.size(); i++){
                listaDeEntidades.get(i).desenhar(g2);
            }

            // Lista vazia de entidades
            for(int i = 0; i < listaDeEntidades.size(); i++){
                listaDeEntidades.remove(i);
            }
            // listaDeEntidades.clear();

            // UI
            ui.desenhar(g2);
    

    }
  
    
    }

    public void desenharTela(){
        Graphics g = getGraphics();
        g.drawImage(tempTela, 0, 0, larguraDaTela2, alturaDaTela2, null);
        g.dispose();
    }

    public void tocarMusica(int i){

        musica.selecionaArquivo(i);
        musica.tocar();
        musica.loop();

    }

    public void pararMusica(){
        musica.para();
    }

    public void tocarEfeitoSonoro(int i){

        efeitoSonoro.selecionaArquivo(i);
        efeitoSonoro.tocar();

    }

}