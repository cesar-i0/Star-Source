package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// KeyListener recebe eventos adivindos do teclado(keystrokes).
public class Manipulador implements KeyListener {

    PainelDoJogo pj;

    public boolean cimaPrecionado, baixoPrecionado, esquerdaPrecionado, direitaPreciondo, enterPressionado, tiroPressionado;
    //DEBUG
    boolean verificaTempoDeDesenho = false;

    public Manipulador(PainelDoJogo pj){

        this.pj = pj;
        
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode(); // retorna o número inteiro do keyCode associado com a chave desse evento.
        
        // Estado de Titulo
        if(pj.estado_do_jogo == pj.estado_de_titulo){ estado_de_titulo(code); }
        // Estado de jogar
        else if(pj.estado_do_jogo == pj.estado_de_jogar){ estado_de_jogar(code); }
        // Estado de Dialogo
        else if(pj.estado_do_jogo == pj.estado_de_dialogo){ estado_de_dialogo(code);}
           //Estado de Personagem
        else if(pj.estado_do_jogo == pj.estado_de_personagem){estado_de_personagem(code);}

    }

    public void estado_de_titulo(int code){
        if(pj.ui.estado_do_titulo_na_tela == 0){

                if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                    pj.ui.numeroDoComando--;
                    if(pj.ui.numeroDoComando < 0){
                        pj.ui.numeroDoComando = 2;
                    }
                }
                if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                    pj.ui.numeroDoComando++;
                    if(pj.ui.numeroDoComando > 2){
                        pj.ui.numeroDoComando = 0;
                    }
                }
    
                if(code == KeyEvent.VK_ENTER){
                    
                    if(pj.ui.numeroDoComando == 0){
                        pj.estado_do_jogo = pj.estado_de_jogar;
                        pj.tocarMusica(0);
                    }
    
                    if(pj.ui.numeroDoComando == 1){
    
                    }
    
                    if(pj.ui.numeroDoComando == 2){
                        System.exit(0);
                    }
    
                }
            }



    }

    public void estado_de_jogar(int code){

         if((code == KeyEvent.VK_W) || (code == KeyEvent.VK_UP)){
                cimaPrecionado = true;
            }
            if((code == KeyEvent.VK_S) || (code == KeyEvent.VK_DOWN)){
                baixoPrecionado = 
                true;
            }
            if((code == KeyEvent.VK_A) || (code == KeyEvent.VK_LEFT)){
                esquerdaPrecionado = true;
            }
            if((code == KeyEvent.VK_D) || (code == KeyEvent.VK_RIGHT)){
                direitaPreciondo = true;
            }
            if(code == KeyEvent.VK_ENTER){
                enterPressionado = true;
            }
             if(code == KeyEvent.VK_F){
                tiroPressionado = true;
            }
            //Estado de pausa
            if(code == KeyEvent.VK_ESCAPE){
                if(pj.estado_do_jogo == pj.estado_de_jogar) pj.estado_do_jogo = pj.estado_de_pausa;
                else if(pj.estado_do_jogo == pj.estado_de_pausa) pj.estado_do_jogo = pj.estado_de_jogar;
            }
             if(code == KeyEvent.VK_C){
                pj.estado_do_jogo = pj.estado_de_personagem;
            }
           

    }

    public void estado_de_dialogo(int code){
        if(code == KeyEvent.VK_ENTER){
                pj.estado_do_jogo = pj.estado_de_jogar;
            }
    }

    public void estado_de_personagem(int code){
         if(code == KeyEvent.VK_C){
                pj.estado_do_jogo = pj.estado_de_jogar;
            }
            //Cursor do inventário com limites
        if(code == KeyEvent.VK_W){
            if(pj.ui.compartimento_linha != 0){
                pj.ui.compartimento_linha --;
            }
        } 
        if(code == KeyEvent.VK_A){
            if(pj.ui.compartimento_coluna != 0){
                pj.ui.compartimento_coluna --;
            } 
        } 
        if(code == KeyEvent.VK_S){
            if(pj.ui.compartimento_linha != 3){
                pj.ui.compartimento_linha ++;
            } 
        } 
        if(code == KeyEvent.VK_D){
            if(pj.ui.compartimento_coluna != 4){
                pj.ui.compartimento_coluna ++;
            }
            
        } 
        if(code == KeyEvent.VK_ENTER){
            pj.jogador.selecionarItem();
        }
        

    }

    
   
   @Override
   public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) cimaPrecionado = false;
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) baixoPrecionado = false;
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) esquerdaPrecionado = false;
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) direitaPreciondo = false;
        if(code == KeyEvent.VK_F) tiroPressionado = false;
    }
 
    }

    
