package objetos;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.PainelDoJogo;

public class OBJ_Coracao extends SuperObjetos{
    
    PainelDoJogo pj;

    public OBJ_Coracao(PainelDoJogo pj){
        this.pj = pj;

        nome = "Coração";
        try{
            imagem = ImageIO.read(getClass().getResourceAsStream("/res/objetos/coracaoBranco.png"));
            imagem2 = ImageIO.read(getClass().getResourceAsStream("/res/objetos/coracaoMeio.png"));
            imagem3 = ImageIO.read(getClass().getResourceAsStream("/res/objetos/coracao.png"));
            imagem = ferramenta.imagemRedimensionada(imagem, pj.tamanhoDaPeca, pj.tamanhoDaPeca);
            imagem2 = ferramenta.imagemRedimensionada(imagem2, pj.tamanhoDaPeca, pj.tamanhoDaPeca);
            imagem3 = ferramenta.imagemRedimensionada(imagem3, pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }   

}
