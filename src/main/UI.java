package main;

import entidades.Entidade;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import objetos.OBJ_Coracao;
import objetos.OBJ_CristalMana;

// Esta classe vai lidar com mensagens de texto, ícones de itens, etc.
public class UI{

    PainelDoJogo pj;
    Graphics2D g2;

    Font arial, arial_2;
    BufferedImage coracao_cheio, coracao_meio, coracao_vazio;
    BufferedImage  CristalManaCheio, CristalManaVazio;
    public boolean mensagem_ligada = false;
    //public String mensagem  = "";
    //int contador_da_mensagem = 0;
    ArrayList<String> mensagem = new ArrayList<>();
    ArrayList<Integer> contador_da_mensagem = new ArrayList<>();
    public boolean jogoFinalisado = false;
    public String dialogo_atual = "";
    public int numeroDoComando = 0;
    public int estado_do_titulo_na_tela = 0;
    public int jogador_compartimento_coluna = 0; 
    public int jogador_compartimento_linha = 0;
    public int npc_compartimento_coluna = 0;
    public int npc_compartimento_linha = 0;
    int sub_estado = 0;
    int contador = 0;
    public Entidade npc;

    public UI(PainelDoJogo pj){
        this.pj = pj;
        arial = new Font("Arial", Font.PLAIN,30); // Tipo, estilo e tamanho
        arial_2 = new Font("Arial", Font.BOLD,80); // Tipo, estilo e tamanho
        
        //Cria um objeto mediador(HUD)
        Entidade coracao = new OBJ_Coracao(pj);
        coracao_vazio = coracao.imagem;
        coracao_meio = coracao.imagem2;
        coracao_cheio = coracao.imagem3;

        Entidade Cristal = new OBJ_CristalMana(pj);
        CristalManaVazio = Cristal.imagem4;
        CristalManaCheio = Cristal.imagem5;

    }

    public void mostrarMensagem(String texto){
        // Evita adicionar repetidamente a mesma mensagem se ela já estiver visível
        if(!mensagem.isEmpty()){
            int last = mensagem.size() - 1;
            if(mensagem.get(last) != null && mensagem.get(last).equals(texto) && contador_da_mensagem.get(last) < 60){
                return;
            }
        }

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
            desenharInventario(pj.jogador,true);
    } //Estado de opções
        if(pj.estado_do_jogo == pj.estado_de_opcoes){
        desenharOpcoesTela();
    }   //Estado de fim de jogo;
        if(pj.estado_do_jogo == pj.estado_fim_de_jogo){
        desenharFimDeJogoTela();
    }
     //Estado de transição;
        if(pj.estado_do_jogo == pj.estado_de_transicao){
        desenharTransicao();
    }
    //Estado de troca;
        if(pj.estado_do_jogo == pj.estado_de_troca){
        desenharTroca();
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
            g2.drawImage(coracao_meio, x, y, null);
            i++;
            if(i < pj.jogador.vida){
                g2.drawImage(coracao_cheio, x, y, null);
            }
            i++;
            x += pj.tamanhoDaPeca;
        }

        //Desenhar ManaMax

        x = (pj.tamanhoDaPeca/2) - 5;
        y = (int)(pj.tamanhoDaPeca * 1.5);
        i = 0;

        while(i < pj.jogador.mana_max){
            g2.drawImage(CristalManaVazio, x, y ,null);
            i++;
            x += 35;
        }


        //Desenha Mana
        x = (pj.tamanhoDaPeca/2) - 5;
        y = (int)(pj.tamanhoDaPeca * 1.5);
        i = 0;
        
        while(i < pj.jogador.mana){
            g2.drawImage(CristalManaCheio, x, y ,null);
            i++;
            x += 35;
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
            g2.drawImage(pj.jogador.estatico, x, y, pj.tamanhoDaPeca * 2, pj.tamanhoDaPeca * 2, null);
    
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
        final int frameX = pj.tamanhoDaPeca*2;
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
        g2.drawString("Mana", textX, textY);
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

        valor = String.valueOf(pj.jogador.mana + "/" + pj.jogador.mana_max);
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
    public void desenharFimDeJogoTela(){
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,pj.larguraDaTela, pj.alturaDaTela);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Fim de Jogo!";
        g2.setColor(Color.black);
        x = getXdoTextoCentralizado(text);
        y = pj.tamanhoDaPeca*4;
        g2.drawString(text, x, y);

        //Main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        //Novamente
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Tentar de novo";
        x = getXdoTextoCentralizado(text);
        y += pj.tamanhoDaPeca*4;
        g2.drawString(text, x, y);
        if(numeroDoComando == 0){
            g2.drawString(">", x-40, y);
        }

        //Voltar a tela inicial
        text = "Sair";
        x = getXdoTextoCentralizado(text);
        y += 55;
        g2.drawString(text, x, y);
        if(numeroDoComando == 1){
            g2.drawString(">", x-40, y);
        }

    }
    public void desenharOpcoesTela(){
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        //Sub tela
        int frameX = pj.tamanhoDaPeca * 6;
        int frameY = pj.tamanhoDaPeca;
        int frameLargura = pj.tamanhoDaPeca * 8;
        int frameAltura = pj.tamanhoDaPeca * 10;
        desenharSubTela(frameX, frameY, frameLargura, frameAltura);

        switch (sub_estado) {
            case 0: topo_opcoes(frameX, frameY);
                
                break;
        
           case 1: opcoes_notificacao_telaCheia(frameX, frameY);
                break;
            case 2: opcoes_controle(frameX, frameY);
            break;
            case 3: opcoes_fimDeJogo_confimacao(frameX, frameY);
            break;
          }
          pj.chaveManipuladora.enterPressionado = false;
        }
        public void topo_opcoes(int frameX, int frameY){
            int textX;
            int textY;

            //Titulo
            String text = "Opções";
            textX = getXdoTextoCentralizado(text);
            textY = frameY + pj.tamanhoDaPeca;
            g2.drawString(text, textX, textY);

            //Tela cheia on/off
            textX = frameX + pj.tamanhoDaPeca;
            textY += pj.tamanhoDaPeca*2;
            g2.drawString("Tela Cheia", textX, textY);
            if(numeroDoComando == 0){
                g2.drawString(">", textX-25, textY);
                if(pj.chaveManipuladora.enterPressionado == true){
                    if(pj.telaCheiaLigada == false){
                        pj.telaCheiaLigada = true;
                    }
                    else if(pj.telaCheiaLigada == true) {
                        pj.telaCheiaLigada = false;
                    }
                     sub_estado = 1;
                }
               
            }

            //Musica
            textY += pj.tamanhoDaPeca;
            g2.drawString("Musica", textX, textY);
            if(numeroDoComando == 1){
                g2.drawString(">", textX-25, textY);
            }
            //Efeito sonoro
            textY += pj.tamanhoDaPeca;
            g2.drawString("Efeitos", textX, textY);
            if(numeroDoComando == 2){
                g2.drawString(">", textX-25, textY);
            }
            //Controles
            textY += pj.tamanhoDaPeca;
            g2.drawString("Controles", textX, textY);
            if(numeroDoComando == 3){
                g2.drawString(">", textX-25, textY);
                // Abrir tela de controles somente quando Enter for pressionado
                if(pj.chaveManipuladora.enterPressionado == true){
                    sub_estado = 2;
                    pj.chaveManipuladora.enterPressionado = false;
                }
            }
            //Fim do jogo
            textY += pj.tamanhoDaPeca;
            g2.drawString("Fim do Jogo", textX, textY);
            if(numeroDoComando == 4){
                g2.drawString(">", textX-25, textY);
                if(pj.chaveManipuladora.enterPressionado == true){
                    sub_estado = 3;
                    numeroDoComando = 0;
                }
            }
            //Voltar
            textY += pj.tamanhoDaPeca*2;
            g2.drawString("Voltar", textX, textY);
            if(numeroDoComando == 5){
                g2.drawString(">", textX-25, textY);
                if(pj.chaveManipuladora.enterPressionado == true){
                    pj.estado_do_jogo = pj.estado_de_jogar;
                    numeroDoComando = 0;
                }
            }

            //Verifica caixa de tela cheia
            textX = frameX + (int)(pj.tamanhoDaPeca*4.5);
            textY = frameY + pj.tamanhoDaPeca*2 + 24;
            g2.setStroke(new BasicStroke(3));
            g2.drawRect(textX, textY, 24, 24);
            if(pj.telaCheiaLigada == true){
                g2.fillRect (textX, textY, 24, 24);
            }

            //Volume da musica
            textY += pj.tamanhoDaPeca;
            g2.drawRect( textX, textY, 120, 24);
            int volumeLargura = 24 * pj.musica.escalaVolume;
            g2.fillRect(textX, textY, volumeLargura, 24);

             //Volume da EFEITO SONORO
            textY += pj.tamanhoDaPeca;
            g2.drawRect( textX, textY, 120, 24);
            volumeLargura = 24 * pj.efeitoSonoro.escalaVolume;
            g2.fillRect(textX, textY, volumeLargura, 24);


            pj.config.salvarConfig();

    }

    public void opcoes_notificacao_telaCheia(int frameX, int frameY){
        int textX = frameX + pj.tamanhoDaPeca;
        int textY = frameY + pj.tamanhoDaPeca * 3;

        dialogo_atual = "A mudança ocorrerá \n" + //
                        "assim que reiniciar\n" + //
                        "o jogo.";
        for(String line: dialogo_atual.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //Voltar
        textY = frameY + pj.tamanhoDaPeca*9;
        g2.drawString("Voltar", textX, textY);
        if(numeroDoComando == 0){
            g2.drawString(">", textX-25, textY);
        }
        if(pj.chaveManipuladora.enterPressionado == true){
            sub_estado = 0;
        }
    }

    public void opcoes_controle(int frameX, int frameY){
        int textX;
        int textY;

        // String text = "Controles";
        textX = getXdoTextoCentralizado(dialogo_atual);
        textY = frameY + pj.tamanhoDaPeca;

        textX = frameX + pj.tamanhoDaPeca;
        textY += pj.tamanhoDaPeca;
        g2.drawString("Mover", textX, textY); textY += pj.tamanhoDaPeca;
        g2.drawString("Confirmar", textX, textY); textY += pj.tamanhoDaPeca;
        g2.drawString("Atirar", textX, textY); textY += pj.tamanhoDaPeca;
        g2.drawString("Inventátio", textX, textY); textY += pj.tamanhoDaPeca;
        g2.drawString("Pausa", textX, textY); textY += pj.tamanhoDaPeca;
        g2.drawString("Opções", textX, textY); textY += pj.tamanhoDaPeca;

        textX = frameX + pj.tamanhoDaPeca*5;
        textY = frameY + pj.tamanhoDaPeca*2;
        g2.drawString("WASD", textX, textY); textY += pj.tamanhoDaPeca;
        g2.drawString("ENTER", textX, textY);  textY += pj.tamanhoDaPeca;
        g2.drawString("F", textX, textY);  textY += pj.tamanhoDaPeca;
        g2.drawString("C", textX, textY);  textY += pj.tamanhoDaPeca;
        g2.drawString("P", textX, textY);  textY += pj.tamanhoDaPeca;
        g2.drawString("ESC", textX, textY);  textY += pj.tamanhoDaPeca;

        //vOLTAR
        textX = frameX + pj.tamanhoDaPeca;
        textY = frameY + pj.tamanhoDaPeca*9;
        g2.drawString("Voltar", textX, textY);
        if(numeroDoComando == 0){
            g2.drawString(">", textX-25, textY);
            if(pj.chaveManipuladora.enterPressionado == true){
            sub_estado = 0;
            numeroDoComando = 3;
          }
      }
    }

    public void opcoes_fimDeJogo_confimacao(int frameX, int frameY){
        int textX = frameX + pj.tamanhoDaPeca;
        int textY = frameY + pj.tamanhoDaPeca*3;

        dialogo_atual = "Sair do jogo \nretornar para tela \ninicial?";

        for(String line: dialogo_atual.split("\n") ){
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //Sim
        String text = "Sim!";
        textX = getXdoTextoCentralizado(text);
        textY += pj.tamanhoDaPeca*2;
        g2.drawString(text, textX, textY);
        if(numeroDoComando == 0){
            g2.drawString(">", textX-25, textY);
            if (pj.chaveManipuladora.enterPressionado == true){
                sub_estado = 0;
                pj.estado_do_jogo = pj.estado_de_titulo;
            } 
                
            }

            //NÃO
        text = "Não!";
        textX = getXdoTextoCentralizado(text);
        textY += pj.tamanhoDaPeca;
        g2.drawString(text, textX, textY);
        if(numeroDoComando == 1){
            g2.drawString(">", textX-25, textY);
            if (pj.chaveManipuladora.enterPressionado == true){
                sub_estado = 0;
                numeroDoComando = 4;
                pj.estado_de_jogar = pj.estado_de_titulo;
                
            } 
                
            }
        }
        
     public void desenharTransicao(){
        
        contador++;
        g2.setColor(new Color(0,0,0, contador*5));
         g2.fillRect(0, 0, pj.larguraDaTela, pj.alturaDaTela);
         if(contador == 50){
            contador = 0;
            pj.estado_do_jogo = pj.estado_de_jogar;
            pj.mapaatual = pj.evento.tempmap;
          pj.jogador.mundoX= pj.tamanhoDaPeca * pj.evento.tempcol;
          pj.jogador.mundoY = pj.tamanhoDaPeca * pj.evento.templin;
          pj.evento.EventoPrevioX = pj.jogador.mundoX;
          pj.evento.EventoPrevioY = pj.jogador.mundoY;
        

            }
     }
    public void desenharInventario(Entidade entidade , boolean cursor){
        
        int frameX = 0;
        int frameY = 0 ;
        int frameWidth = 0;
        int frameHeight = 0 ;
        int slotcoluna = 0;
        int slotlinha = 0;
        if(entidade == pj.jogador){
            frameX = pj.tamanhoDaPeca*12;
            frameY = pj.tamanhoDaPeca;
            frameWidth = pj.tamanhoDaPeca*6;
            frameHeight = pj.tamanhoDaPeca*5;
            slotcoluna = jogador_compartimento_coluna;
            slotlinha = jogador_compartimento_linha;

        }
        else{
            frameX = pj.tamanhoDaPeca*2;
            frameY = pj.tamanhoDaPeca;
            frameWidth = pj.tamanhoDaPeca*6;
            frameHeight = pj.tamanhoDaPeca*5;
            slotcoluna = npc_compartimento_coluna;
            slotlinha = npc_compartimento_linha;
        }




   
        desenharSubTela(frameX, frameY, frameWidth, frameHeight);

        //Compartimento

        final int compartimentoXinicio = frameX+20;
        final int compartimentoYinicio = frameY+20;
        int compartimentoX = compartimentoXinicio;
        int compartimentoY = compartimentoYinicio;
        int tamanho_compartimento = pj.tamanhoDaPeca + 3;

        //desenhar Itens do Jogador
        for(int i = 0; i < entidade.inventario.size(); i++){

             //Equipar cursor
             if(entidade.inventario.get(i) == entidade.correnteArma || entidade.inventario.get(i) == entidade.correnteEscudo){
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(compartimentoX, compartimentoY, pj.tamanhoDaPeca, pj.tamanhoDaPeca, 10, 10);

             }
             
        
            
            g2.drawImage(entidade.inventario.get(i).baixo1, compartimentoX, compartimentoY, null);
            compartimentoX += tamanho_compartimento;

            if(i == 4 || i == 9 || i == 14){
                compartimentoX = compartimentoXinicio;
                compartimentoY += tamanho_compartimento;
            }

        

        }
        

        //Cursor
        if(cursor == true){
  int cursorX = compartimentoXinicio + (tamanho_compartimento*jogador_compartimento_coluna);
        int cursorY = compartimentoYinicio + (tamanho_compartimento*jogador_compartimento_linha);
        int cursorLargura = pj.tamanhoDaPeca;
        int cursorAltura = pj.tamanhoDaPeca;

        //Descrição frame
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = pj.tamanhoDaPeca*3;
       
        //Texto descrição
        int textX = dFrameX+20;
        int textY = dFrameY + pj.tamanhoDaPeca;

        g2.setFont(g2.getFont().deriveFont(28F));
        int index_item = getIndex_item_no_compartimento(slotcoluna , slotlinha);

        if(index_item < entidade.inventario.size()){
            desenharSubTela(dFrameX, dFrameY, dFrameWidth, dFrameHeight);


            for(String line: entidade.inventario.get(index_item).descricao.split("\n")){
            g2.drawString(line, textX, textY);
            textY += 32;
        }
        }
  //Desenhar
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorLargura, cursorAltura, 10, 10);
        }
      

        

      

    }

  public int getIndex_item_no_compartimento(){
        int index_item = jogador_compartimento_coluna+(jogador_compartimento_linha*5);
        return index_item;

    }


    public void desenharTelaDeDialogo(){

        // Janela
        int x = pj.tamanhoDaPeca * 3;
        int y = pj.tamanhoDaPeca / 2;
        int largura = pj.larguraDaTela - (pj.tamanhoDaPeca * 6);
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




public void desenharTroca(){
    switch(sub_estado){
       case 0: selecao_troca();break;
       case 1: compra_troca();break;
       case 2: venda_troca();break;
    }

}
    public void selecao_troca(){
        //Desenha a tela de seleção de troca
        desenharTelaDeDialogo();
        int x = pj.tamanhoDaPeca*15;
        int y = pj.tamanhoDaPeca*4;
        int largura = pj.tamanhoDaPeca*4;
        int altura = (int)(pj.tamanhoDaPeca * 3.5);

        desenharSubTela(x,y,largura,altura);
        // texto de compra
        x += pj.tamanhoDaPeca;
        y += pj.tamanhoDaPeca;
        g2.drawString("Comprar", x, y);
        if (numeroDoComando == 0){
            g2.drawString(">", x - 24, y);
            if(pj.chaveManipuladora.enterPressionado == true){
                sub_estado = 1;
                
            }
        }
        y += pj.tamanhoDaPeca;
        g2.drawString("Vender", x, y);
        if (numeroDoComando == 1){
            g2.drawString(">", x - 24, y);
             if(pj.chaveManipuladora.enterPressionado == true){
                sub_estado = 2;
                
            }
        }
        y += pj.tamanhoDaPeca;
        g2.drawString("Sair", x, y);
        if (numeroDoComando == 2){
            g2.drawString(">", x - 24, y);
             if(pj.chaveManipuladora.enterPressionado == true){
               numeroDoComando = 0;
                pj.estado_do_jogo = pj.estado_de_jogar;
              //  dialogo_atual = "volte a qualquer hora!";
                
            }
        }
      
    }

public void compra_troca(){

}
public void venda_troca(){
   public int getIndex_item_no_compartimento( int slotcoluna, int slotlinha){
        int index_item = npc_compartimento_coluna+(npc_compartimento_linha*5);
        return index_item;
}

}
}
