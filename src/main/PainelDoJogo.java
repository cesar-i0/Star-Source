package main;

import entidades.Entidade;
import entidades.Jogador;
import peca.GerenciadorDePeca;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.*;
import java.util.Collections;
import java.util.Comparator;

public class PainelDoJogo extends JPanel implements Runnable {
    // Configurações de tela
    final int tamanhoOriginalDaPeca = 16; // 16x16 por peça(tile), ou seja, personagens ou npcs.
    final int escala = 3;

    public final int tamanhoDaPeca = tamanhoOriginalDaPeca * escala; // 48x48 por peça
    public final int telaMaximaHorizontal = 16; // Tamanho máximo de colunas. 3:4 = 16; 16:9 = 32.
    public final int telaMaximaVertical = 12; // Tamanho máximo de linhas. 3:4 = 12; 16:9 = 18.
    public final int larguraDaTela = tamanhoDaPeca * telaMaximaHorizontal; // 48*16 = 768 pixels ou 48*32 = 1536 pixels. WIDTH
    public final int alturaDaTela = tamanhoDaPeca * telaMaximaVertical; // 48*12 = 576 pixels ou 48*18 = 864 pixels. LENGHT

    // Condigurações do mundo
    public final int maxColunasDoMundo = 50;
    public final int maxLinhaDoMundo = 50;

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
    Thread threadDoJogo; // ALgo que podemos iniciar e parar a fim de deixar o programa rodando.
    
    // Entidade e Objeto
    public Jogador jogador = new Jogador(this, chaveManipuladora);
    public Entidade obj[] = new Entidade[10]; // Torna possível mostrar 10 objetos no mesmo display/tela
    public Entidade npc[] = new Entidade[10];
    public Entidade monstros[] = new Entidade[20];
    public ArrayList<Entidade> projetosTileList = new ArrayList<>();
    ArrayList<Entidade> listaDeEntidades = new ArrayList<>();

    // Estado do jogo
    public int estado_do_jogo;
    public final int estado_de_titulo = 0;
    public final int estado_de_jogar = 1;
    public final int estado_de_pausa = 2;
    public final int estado_de_dialogo = 3;
    public final int estado_de_personagem = 4;

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
        if(estado_do_jogo == estado_de_jogar){
            // Jogador
            jogador.update();
            // NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
            // Monstros
            for(int i = 0; i < monstros.length; i++){
                if(monstros[i] != null){
                    if(monstros[i].vivo == true && monstros[i].morrendo == false){
                        monstros[i].update();
                    }
                    if(monstros[i].vivo == false){
                        monstros[i] = null;
                    }
                }
            }

             for(int i = 0; i < projetosTileList.size(); i++){
                if(projetosTileList.get(i)!= null){
                    if(projetosTileList.get(i).vivo == true ){
                        projetosTileList.get(i).update();
                    }
                    if(projetosTileList.get(i).vivo == false){
                        projetosTileList.remove(i);
                    
                    }
                }
            }

        }
        if(estado_do_jogo == estado_de_pausa){
            // nada por enquanto
        }
        
}

    // A classe "Graphics" tem muitas funções para desenhar objetos na tela.
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        // A classe Graphics2D extende a classe Graphics para promover um controle mais sofisticado de geometria,
        // transformações de coordenadas, gerenciamento de cor, e layout de texto.
        Graphics2D g2 = (Graphics2D)g; // Muda os gráficos de g para g2.

        // Tela de título
        if(estado_do_jogo == estado_de_titulo){
            ui.desenhar(g2);
        }
        else{
            // Peça
            peca_tela.desenhar(g2); 
            
            // Adiciona entidades para a lista
            listaDeEntidades.add(jogador);
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    listaDeEntidades.add(npc[i]);
                }
            }
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    listaDeEntidades.add(obj[i]);
                }
            }
            for(int i = 0; i < monstros.length; i++){
                if(monstros[i] != null){
                    listaDeEntidades.add(monstros[i]);
                }
            }
            for(int i = 0; i < projetosTileList.size(); i++){
                if(projetosTileList.get(i) != null){
                    listaDeEntidades.add(projetosTileList.get(i));
                }
            }

            // SORT
            Collections.sort(listaDeEntidades, new Comparator<Entidade>() {
                @Override
                public int compare(Entidade e1, Entidade e2) {
                    int resultado = Integer.compare(e1.mundoX, e2.mundoY);
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
    
            g2.dispose(); // Descarta isso do contexto de graphics e libera qualquer recursos que estão sendo usandos.
        }
    
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