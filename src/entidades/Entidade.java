package entidades;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.awt.AlphaComposite;
import java.awt.Color;

import javax.imageio.ImageIO;
import main.FerramentaUtilitaria;
import main.PainelDoJogo;

/*
 * Essa classe guarda todas as variáveis que serão utilizadas pelo jogador, monstros ou NPCs.
 */
public class Entidade {

    PainelDoJogo pj;

    // Precisamos usamos variáveis como essas para trazer as imagens que serão as animações de movimentação
    public BufferedImage cima1, cima2, baixo1, baixo2, esquerda1, esquerda2, direita1, direita2, estatico;
    public BufferedImage ataque;
    int estadoInicial = 0;
    public int area_solida_padraoX, area_solida_padraoY;
    public Rectangle area_solida;
    public Rectangle AtaqueArea = new Rectangle(0,0,0,0);
    public boolean colisao = false;
    String dialogos[] =  new String[20];
    public BufferedImage imagem, imagem2, imagem3;
    public BufferedImage imagem4, imagem5;
   
    //ESTADOS
    public int mundoX, mundoY;
    public String direcao = "estatico"; // Aqui será guardado qual imagem deve ser mostarda com determinada ação
    public boolean invencivel = false;
    int index_de_dialogo = 0;
    public boolean colisao_ligada = false;
    public int numeroDoEstado = 1;
    public boolean atacando = false;
    public boolean vivo = true;
    public boolean morrendo = false;
    public boolean hpBarraDeVidaVisivel = false;


    //CONTADORES 
    public int contadorDoEstado = 0;
    public int trava_de_contador_de_acao = 0;
    public int contador_de_invencibilidade = 0;
    public int contador_de_tiro_viavel = 0;
    public int contador_de_morte = 0;
    public int contador_de_hpBarraDeVida = 0;


    // Status do personagem
    public double vidaMaxima;
    public double vida;
    public double mana_max;
    public double mana;
    public String nome;
    public int velocidade;
    public int nivel;
    public int forca;
    public double ataques;
    public double defesa;
    public int experiencia;
    public int expProximoNivel;
    public int moedas;
    public int exp;
    public Entidade correnteEscudo;
    public Entidade correnteArma;
    public ProjeteisDePecas projeteis;

    // Atributos de Itens
    public int ataqueValor;
    public int defesaValor;
    public String descricao = "";
    public int custo_de_uso;
    public int valor;
    public boolean coletavel;

    //Tipos
    public int tipo; // 0 = jogador, 1 = npc, 2 = monstro
    public final int tipo_jogador = 0;
    public final int tipo_npc = 1;
    public final int tipo_monstro = 2;
    public final int tipo_espada = 3;
    public final int tipo_escudo = 4;
    public final int tipo_machado = 5;
    public final int tipo_consumivel = 6;
    public static int tipo_pegar_apenas = 7;


    public Entidade(PainelDoJogo pj){
        this.pj = pj;
    }


    public BufferedImage configuracoes(String caminho_da_imagem, int width, int height) {

        FerramentaUtilitaria ferramenta = new FerramentaUtilitaria();
        BufferedImage imagem = null;

        try {
            // 1 garante que o caminho começa com /
            String caminho = caminho_da_imagem.startsWith("/")
                    ? caminho_da_imagem
                    : "/" + caminho_da_imagem;

            // 2 adiciona .png automaticamente se não tiver
            if (!caminho.endsWith(".png")) {
                caminho += ".png";
            }

            // 3 carrega pelo classpath corretamente
            InputStream is = Entidade.class.getResourceAsStream(caminho);

            if (is == null) {
                throw new RuntimeException("Imagem não encontrada: " + caminho);
            }
            // System.out.println(is);
            // System.out.println(is);

            imagem = ImageIO.read(is);
            imagem = ferramenta.imagemRedimensionada(imagem, width, height);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return imagem;
    }
  

    public void setAcao(){}
    public void reacaoDano(){}
    public void falar(){

        if(dialogos[index_de_dialogo] == null) index_de_dialogo = 0;

        pj.ui.dialogo_atual = dialogos[index_de_dialogo];
        index_de_dialogo++;

        switch (pj.jogador.direcao) {
            case "cima":
                direcao = "cima";
            break;
            case "baixo":
                direcao = "baixo";
                break;
            case "direita":
                direcao = "direita";
                break;
            case "esquerda":
                direcao = "esquerda";
                break;
        }
    }

    public boolean use (Entidade entidade){ return false; }

    public void verificaDrop(){

    }

    public void dropaItem( Entidade ItemDropado){
        for(int i = 0; i < pj.obj.length; i++){
            if(pj.obj[i] == null){
                pj.obj[i] = ItemDropado;
                pj.obj[i].mundoX = mundoX; //O monstro morto do mapa
                pj.obj[i].mundoY = mundoY;
                break;
            }
        }
    }

    public void update(){

        setAcao();

        // As ações abaixo verificam as possíveis colisões
        colisao_ligada = false;
        pj.verifica.verificaPeca(this);
        pj.verifica.verificaObjeto(this, false);
        pj.verifica.verificaEntidade(this, pj.npc);
        pj.verifica.verificaEntidade(this, pj.monstros);
        boolean contatoComJogador = pj.verifica.verificaJogador(this);

        if(this.tipo == tipo_monstro && contatoComJogador == true){ // Tipo 2 é monstro
            // System.out.println("Contato com o jogador");
            danoJogador(ataques);
        }
       
        // Se a colisão for false o joagador pode se mover
            if(colisao_ligada == false){
                switch (direcao){
                    case "cima":
                        mundoY -= velocidade;
                        break;
                    case "baixo":
                        mundoY += velocidade;
                        break;
                    case "esquerda":
                        mundoX -= velocidade;
                        break;
                    case "direita":
                        mundoX += velocidade;
                        break;
                }
            }

            contadorDoEstado++;
            if(contadorDoEstado > 15){
                if(numeroDoEstado == 1){
                    numeroDoEstado = 2;
                }
                else if (numeroDoEstado == 2){
                     numeroDoEstado = 1;
                }
                contadorDoEstado = 0;
            }

            //Trazemos o invencivel para conseguir atacar o monstro 
            if(invencivel == true){
                contador_de_invencibilidade++;
                if(contador_de_invencibilidade > 40){
                    invencivel = false;
                    contador_de_invencibilidade = 0;
                }
            }
            else{
                estadoInicial++;
                if(estadoInicial == 20){
                    numeroDoEstado = 1;
                    estadoInicial = 0;
                }
            }
            if(contador_de_tiro_viavel < 30){
                contador_de_tiro_viavel++;
            }

    }

    public void danoJogador(double ataques){
        if(pj.jogador.invencivel == false){
            pj.tocarEfeitoSonoro(1);
            double dano = ataques - pj.jogador.defesa;
            if(dano < 0){
                dano = 0;
            }
            pj.jogador.vida -= dano;
            pj.jogador.invencivel = true;
        }
    }

    public void desenhar(Graphics2D g2){
        
        BufferedImage imagem = null;

        int telaX = mundoX - pj.jogador.mundoX + pj.jogador.telaX; // Foi necessário subtrair para encontrar até onde deveria ser desenhado a tela
        int telaY = mundoY - pj.jogador.mundoY + pj.jogador.telaY; // Foi necessário subtrair para encontrar até onde deveria ser desenhado a tela

        if(mundoX + pj.tamanhoDaPeca > pj.jogador.mundoX - pj.jogador.telaX && mundoX - pj.tamanhoDaPeca < pj.jogador.mundoX + pj.jogador.telaX
        && mundoY + pj.tamanhoDaPeca > pj.jogador.mundoY - pj.jogador.telaY && mundoY - pj.tamanhoDaPeca < pj.jogador.mundoY + pj.jogador.telaY){
            
            switch (direcao){
                case "cima":
                if(numeroDoEstado == 1){
                   imagem = cima1;
                }
                if(numeroDoEstado == 2){
                   imagem = cima2;
                }
                // imagem = bogo;
                break;
                case "baixo":
                    if(numeroDoEstado == 1){
                    imagem = baixo1;
                    }
                    if(numeroDoEstado == 2){
                    imagem = baixo2;
                    }
                    // imagem = bogo;
                    break;
                case "esquerda":
                    if(numeroDoEstado == 1){
                        imagem = esquerda1;
                    }
                    if(numeroDoEstado == 2){
                        imagem = esquerda2;
                    }
                    // imagem = bogo;
                    break;
                case "direita":
                    if(numeroDoEstado == 1){
                        imagem = direita1;
                    }
                    if(numeroDoEstado == 2){
                        imagem = direita2;
                    }
                    // imagem = bogo;
                    break;
                case "estatico":
                    imagem = estatico;
                    break;
            }

                //Se for monstro desenha a barra de vida
                if(tipo == tipo_monstro && hpBarraDeVidaVisivel == true ){
                    //System.out.println("DESENHANDO BARRA DE VIDA");

                    double umaEscala = (double)pj.tamanhoDaPeca /vidaMaxima;
                    double hpBarraDeVida = umaEscala*vida;

                    g2.setColor(new Color(35, 35, 35));
                    g2.fillRect(telaX-1, telaY-16, pj.tamanhoDaPeca+2, 12);
                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(telaX, telaY-15, (int)hpBarraDeVida, 10);

                contador_de_hpBarraDeVida++;
                    if(contador_de_hpBarraDeVida > 180){
                        contador_de_hpBarraDeVida = 0;
                        hpBarraDeVidaVisivel = false;
                }  
            }

                if(invencivel == true){
                hpBarraDeVidaVisivel = true;
                contador_de_hpBarraDeVida = 0;
                mudaAlpha(g2, 0.4f); // Deixa o jogador meio transparente
            }

        if(morrendo == true){
           
            animaçãoMorte(g2);
        }
            g2.drawImage(imagem, telaX, telaY, pj.tamanhoDaPeca, pj.tamanhoDaPeca, null);
            // Desenha a área de colisão
            g2.setColor(Color.red);
            g2.drawRect(telaX + area_solida.x, telaY + area_solida.y, area_solida.width, area_solida.height);

        }

        if(invencivel == true){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    //Quantidade de frames que a animação de morte vai durar(Intervalos)
        public void animaçãoMorte(Graphics2D g2){

            contador_de_morte++;

            int i = 10;

            if(contador_de_morte <= i){
               mudaAlpha(g2, 0f);
            }
             if(contador_de_morte > i*2 && contador_de_morte <= i*3){
                 mudaAlpha(g2, 1f);
            }
             if(contador_de_morte > i*3 && contador_de_morte <= i*4){
                mudaAlpha(g2, 0f);
            }
             if(contador_de_morte > i*4 && contador_de_morte <= i*5){
                mudaAlpha(g2, 1f);
            }
            if( contador_de_morte > i*5 && contador_de_morte <= i*6){
                mudaAlpha(g2, 0f);
            }
             if(contador_de_morte > i*6 && contador_de_morte <= i*7){
                mudaAlpha(g2, 1f);
            }
            if(contador_de_morte > i*7 && contador_de_morte <= i*8){
                mudaAlpha(g2, 0f);
            }
            if(contador_de_morte > i*8){
               vivo = false;
            }
    }

    //Método para mudar a transparência
    public void mudaAlpha(Graphics2D g2, float alphaContador){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaContador));
    }

}

