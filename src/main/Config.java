package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    PainelDoJogo pj;

    public Config (PainelDoJogo pj){
        this.pj = pj;
    }

    public void salvarConfig(){
        try {
             BufferedWriter bw = new BufferedWriter(new FileWriter("config.txt"));

             //Tela cheia
             if(pj.telaCheiaLigada == true){
                bw.write("Ligada");
             }
             if(pj.telaCheiaLigada == false){
                bw.write("Desligada");
             }
             bw.newLine();

             //Volume da musica
             bw.write(String.valueOf(pj.musica.escalaVolume));
             bw.newLine();

             //Efeito sonoro
             bw.write(String.valueOf(pj.efeitoSonoro.escalaVolume));
             bw.newLine();

             bw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    public void carregar_config(){
        try { 
        BufferedReader br = new BufferedReader(new FileReader("config.txt"));
        String s = br.readLine();

        //TEla cheia
        if(s.equals("Ligada")){
            pj.telaCheiaLigada = true;

        } 
        if(s.equals("Desligada")){
            pj.telaCheiaLigada = false;
 }
            //Volume musica
            s = br.readLine();
            pj.musica.escalaVolume = Integer.parseInt(s);

             //Volume efeito
            s = br.readLine();
            pj.efeitoSonoro.escalaVolume = Integer.parseInt(s);

            br.close();
        }
        catch (IOException e) {
        e.printStackTrace();
      }
        }
      }
       


