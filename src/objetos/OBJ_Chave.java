package objetos;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.PainelDoJogo;

public class OBJ_Chave extends SuperObjetos {

    PainelDoJogo pj;

    public OBJ_Chave(PainelDoJogo pj){
        super.nome = "Chave";
        try{
            super.imagem = ImageIO.read(getClass().getResourceAsStream("/res/objetos/chave.png"));
            ferramenta.imagemRedimensionada(super.imagem, pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
