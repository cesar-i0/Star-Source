package objetos;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Bota extends SuperObjetos{

    public OBJ_Bota(){
        super.nome = "Bota";
        try{
            super.imagem = ImageIO.read(getClass().getResourceAsStream("/res/objetos/Bota.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
