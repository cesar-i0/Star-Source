package objetos;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Bau extends SuperObjetos{

    public OBJ_Bau(){
        super.nome = "Baú";
        try{
            super.imagem = ImageIO.read(getClass().getResourceAsStream("/res/objetos/baú.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
