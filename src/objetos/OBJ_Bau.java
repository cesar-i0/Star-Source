package objetos;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.PainelDoJogo;

public class OBJ_Bau extends SuperObjetos{

    PainelDoJogo pj;

    public OBJ_Bau(PainelDoJogo pj){
        super.nome = "Baú";
        try{
            super.imagem = ImageIO.read(getClass().getResourceAsStream("/res/objetos/baú.png"));
            ferramenta.imagemRedimensionada(super.imagem, pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
