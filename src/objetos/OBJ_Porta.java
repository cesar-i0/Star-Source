package objetos;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Porta extends SuperObjetos{

    public OBJ_Porta(){
        super.nome = "Porta";
        try{
            super.imagem = ImageIO.read(getClass().getResourceAsStream("/res/objetos/porta.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
