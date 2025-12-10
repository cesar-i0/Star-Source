package entidades;

import java.awt.image.BufferedImage;

/*
 * Essa classe guarda todas as variáveis que serão utilizadas pelo jogador, monstros ou NPCs.
 */
public class Entidade {

    public int x, y;
    public int speed;

    // Precisamos usamos variáveis como essas para trazer as imagens que serão as animações de movimentação
    public BufferedImage bogo;
    public String direcao; // Aqui será guardado qual imagem deve ser mostarda com determinada ação

//    public int contadorDoEstado = 0;
//    public int numeroDoEstado = 1;

}
