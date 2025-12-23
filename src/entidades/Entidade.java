package entidades;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/*
 * Essa classe guarda todas as variáveis que serão utilizadas pelo jogador, monstros ou NPCs.
 */
public class Entidade {

    public int mundoX, mundoY;
    public int velocidade;

    // Precisamos usamos variáveis como essas para trazer as imagens que serão as animações de movimentação
    public BufferedImage bogo; // up, down, left, right
    public String direcao; // Aqui será guardado qual imagem deve ser mostarda com determinada ação
//    public int contadorDoEstado = 0;
//    public int numeroDoEstado = 1;
    public Rectangle area_solida;
    public boolean colisao_ligada = false;

}
