package objetos;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Chave extends SuperObjetos {

    public OBJ_Chave(){
        super.nome = "Chave";
        try{
            super.imagem = ImageIO.read(getClass().getResourceAsStream("/res/objetos/chave.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
