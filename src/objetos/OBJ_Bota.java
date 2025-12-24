package objetos;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.PainelDoJogo;

public class OBJ_Bota extends SuperObjetos{

    PainelDoJogo pj;

    public OBJ_Bota(PainelDoJogo pj){
        super.nome = "Bota";
        try{
            super.imagem = ImageIO.read(getClass().getResourceAsStream("/res/objetos/Bota.png"));
            ferramenta.imagemRedimensionada(super.imagem, pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
