package main;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Som {

    Clip clip; // Usado para abrir arquivo de aúdio
    URL[] som_URL = new URL[30]; // Usado para salvar o caminho dos aúdios

    public Som(){
        som_URL[0] = getClass().getResource("/res/sons/MusicaGame.wav");
        som_URL[1] = getClass().getResource("/res/sons/dano.wav");
        som_URL[2] = getClass().getResource("/res/sons/LevelUp.wav");
        som_URL[3] = getClass().getResource("/res/sons/Beber.wav");
        som_URL[4] = getClass().getResource("/res/sons/BolaDeFogo.wav");
    }

    public void selecionaArquivo(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(som_URL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch(Exception e){

        }
    }

    public void tocar(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void para(){
        clip.stop();
    }

}
