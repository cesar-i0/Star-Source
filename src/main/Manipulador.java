package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// KeyListener recebe eventos adivindos do teclado(keystrokes).
public class Manipulador implements KeyListener {

    public boolean cimaPrecionado, baixoPrecionado, esquerdaPrecionado, direitaPreciondo;
    //DEBUG
    boolean verificaTempoDeDesenho = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // retorna o número inteiro do keyCode associado com a chave desse evento.
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) cimaPrecionado = true;
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) baixoPrecionado = true;
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) esquerdaPrecionado = true;
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) direitaPreciondo = true;

        //DEBUG
        if(code == KeyEvent.VK_T){
            if(verificaTempoDeDesenho == false){
                verificaTempoDeDesenho = true;
            }
            else if(verificaTempoDeDesenho == true){
                verificaTempoDeDesenho = false;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) cimaPrecionado = false;
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) baixoPrecionado = false;
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) esquerdaPrecionado = false;
        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) direitaPreciondo = false;
    }
}
