package objetos;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.PainelDoJogo;

public class OBJ_Porta extends SuperObjetos{

    PainelDoJogo pj;

    public OBJ_Porta(PainelDoJogo pj){
        super.nome = "Porta";
        try{
            super.imagem = ImageIO.read(getClass().getResourceAsStream("/res/objetos/porta.png"));
            ferramenta.imagemRedimensionada(super.imagem, pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        colisao = true;
    }

}
