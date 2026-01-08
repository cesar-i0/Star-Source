package main;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Som {
    FloatControl fc;
    int escalaVolume = 3;
    float volume;

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
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            verifica_volume();
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

    public void verifica_volume(){
        switch (escalaVolume) {
            case 0: volume = -80f;
                break;
        case 1: volume = -20f;
                break;
         case 2: volume = -12f;
                break;
        case 3: volume = -5f;
                break;
         case 4: volume = 1f;
                break;
        case 5: volume = 6f;
                break;
        }
        // Protege contra chamada quando nenhum clip/FloatControl foi inicializado
        if(fc != null){
            fc.setValue(volume);
        }
    }
}
