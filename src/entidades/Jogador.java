package entidades;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import main.Manipulador;
import main.PainelDoJogo;
import objetos.OBJ_BolaDeFogo;
import objetos.OBJ_Chave;
import objetos.OBJ_Escudo;
import objetos.OBJ_Espada;


public class Jogador extends Entidade{

    

    Manipulador manipulador;
    public final int telaX, telaY;
    int estadoInicial = 0;
    public boolean ataqueCancelado = false;
    public ArrayList<Entidade> inventario = new ArrayList<>();
    public final int tamanho_max_inventario = 20;
   

    public Jogador(PainelDoJogo pj, Manipulador manipulador){
        
        super(pj);  // Aqui estamos passando o painel do jogo para a super classe "Entidade"

        this.manipulador = manipulador;

        telaX = pj.larguraDaTela / 2 - (pj.tamanhoDaPeca / 2); // Coloca o personagem no centro da tela
        telaY = pj.alturaDaTela / 2 - (pj.tamanhoDaPeca / 2); // Coloca o personagem no centro da tela

        area_solida =  new Rectangle();
        area_solida.x = 8;
        area_solida.y = 8;
        area_solida_padraoX = area_solida.x;
        area_solida_padraoY = area_solida.y;
        area_solida.width = 32;
        area_solida.height = 32;
        //AtaqueArea.width = 36;
        //AtaqueArea.height = 36;

        

        setValoresPadroes();
        getImagemDoJogador();
        getAtaqueJogadorImagem();
        setItens();
    }

    public void setValoresPadroes(){

        mundoX = pj.tamanhoDaPeca * 23; // Essa linha vai indicar em que posição do mapa o jogador inicia.
        mundoY = pj.tamanhoDaPeca * 21; // Essa linha vai indicar em que posição do mapa o jogador inicia.  
        velocidade = 4;
        direcao = "estatico";

        // Status do jogador
        vidaMaxima = 6;
        vida = vidaMaxima;
        nivel = 1;
        forca = 2;
        mana_max = 4;
        mana = mana_max;
        experiencia = 0;
        correnteArma = new OBJ_Espada(pj);
        correnteEscudo = new OBJ_Escudo(pj);
        projeteis = new OBJ_BolaDeFogo(pj);
        ataques = getAtaques();
        defesa = getDefesa();
        expProximoNivel = 5;
        moedas = 0;
        exp = 0;
        custo_de_uso = 1;

    }

    public void setPosicoesPadrao(){
         mundoX = pj.tamanhoDaPeca * 23; 
         mundoY = pj.tamanhoDaPeca * 21;
         direcao = "baixo";
    }
    public void restauraVidaMana(){
        vida = vidaMaxima;
        mana = mana_max;
        invencivel = false;
    }


    //Seta os objetos para dentro do inventário
    public void setItens(){
        inventario.clear();
        inventario.add(correnteArma);
        inventario.add(correnteEscudo);
        inventario.add(new OBJ_Chave(pj));
        inventario.add(new OBJ_Chave(pj));
    }

    public int getAtaques(){
        AtaqueArea = correnteArma.AtaqueArea;
        return ataques = forca * correnteArma.ataqueValor;
    }

    public int getDefesa(){
        return defesa = experiencia * correnteEscudo.defesaValor;
    }

    public void getImagemDoJogador(){
        
        cima1 = configuracoes("/res/jogador/andando_costas1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        cima2 = configuracoes("/res/jogador/andando_costas2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        baixo1 = configuracoes("/res/jogador/andando_frente1", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        baixo2 = configuracoes("/res/jogador/andando_frente2", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        esquerda1 = configuracoes("/res/jogador/parado_esquerda", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        esquerda2 = configuracoes("/res/jogador/andando_esquerda", pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        direita1 = configuracoes("/res/jogador/parado_direita",pj.tamanhoDaPeca, pj.tamanhoDaPeca);
        direita2 = configuracoes("/res/jogador/andando_direita", pj.tamanhoDaPeca, pj.tamanhoDaPeca); 
        estatico = configuracoes("/res/jogador/parado", pj.tamanhoDaPeca, pj.tamanhoDaPeca);

    }

    public void getAtaqueJogadorImagem(){

        if(correnteArma.tipo == tipo_espada){
        ataque = configuracoes("/res/jogador/ataque.png",  pj.tamanhoDaPeca, pj.tamanhoDaPeca); //Busca a imagem deataque
       /* ataqueCima2 = configuracoes("/res/jogador/ataque",  pj.tamanhoDaPeca, pj.tamanhoDaPeca*2); //Multiplica para ficar maior e enquadrar a parte da arma do jogador
        ataqueBaixo1 = configuracoes("/res/jogador/ataque",  pj.tamanhoDaPeca, pj.tamanhoDaPeca*2);
        ataqueBaixo2 = configuracoes("/res/jogador/ataque",  pj.tamanhoDaPeca, pj.tamanhoDaPeca*2);
        ataqueEsquerda1 = configuracoes("/res/jogador/ataque",  pj.tamanhoDaPeca*2, pj.tamanhoDaPeca);
        ataqueEsquerda2 = configuracoes("/res/jogador/ataque",  pj.tamanhoDaPeca*2, pj.tamanhoDaPeca);
        ataqueDireita1 = configuracoes("/res/jogador/ataque",  pj.tamanhoDaPeca*2, pj.tamanhoDaPeca);
        ataqueDireita2 = configuracoes("/res/jogador/ataque",  pj.tamanhoDaPeca*2, pj.tamanhoDaPeca);
*/ 
        }

        //Fazemos um if para cada tipo de arma que o personagem for usar, juntamente com as imagens dele
        
    }

    

    public void update(){

        if(atacando == true){
           atacando();
        } 
        else if(manipulador.cimaPrecionado || manipulador.baixoPrecionado || manipulador.esquerdaPrecionado || manipulador.direitaPreciondo || manipulador.enterPressionado){
            // Coloque todas as condições abaixo para o personagem se mover apenas quando algum botão estiver selecionado
            if(manipulador.cimaPrecionado){
                direcao = "cima";
            }
            else if(manipulador.baixoPrecionado){
                direcao = "baixo";
            }
            else if(manipulador.esquerdaPrecionado){
                direcao = "esquerda";
            }
            else if(manipulador.direitaPreciondo){
                direcao = "direita";
            }
    
            // Verfica a colisão da peça
            colisao_ligada = false;
            pj.verifica.verificaPeca(this);

            // Verifica a colisao com objetos
            int index_do_objeto = pj.verifica.verificaObjeto(this, true);
            pegueObjeto(index_do_objeto);


            // Verifica a colisão com NPC
            int index_do_NPC = pj.verifica.verificaEntidade(this, pj.npc);
            interacaoNPC(index_do_NPC);

            //Verifica a colisão com monstros
            int index_do_monstro = pj.verifica.verificaEntidade(this, pj.monstros);
            contatoComMonstros(index_do_monstro);

            //Verifica a colisão com peça interativa
            //int iPeca_index = pj.verifica.verificaEntidade(this, pj.iPeca);

            // Verifica evento
            pj.evento.verificaEvento();
            

            // Se a colisão for false o joagador pode se mover
            if(colisao_ligada == false && manipulador.enterPressionado == false){
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

            //Para que o som de ataque nãos e sobreponha ao de cura ou outros
           if(pj.chaveManipuladora.enterPressionado == true && ataqueCancelado == false){
            pj.tocarEfeitoSonoro(1);
            atacando = true;
            contadorDoEstado = 0;
           }

           ataqueCancelado = false;

            pj.chaveManipuladora.enterPressionado = false;
   
            contadorDoEstado++;
            if(contadorDoEstado > 12){
                if(numeroDoEstado == 1){
                    numeroDoEstado = 2;
                }
                else if (numeroDoEstado == 2){
                    numeroDoEstado = 1;
                }
                contadorDoEstado = 0;
            }
        } 
        else {
            estadoInicial++;
            if(estadoInicial == 20){
                numeroDoEstado = 1;
                estadoInicial = 0;
            }
        }

        if(pj.chaveManipuladora.tiroPressionado == true && projeteis.vivo == false && contador_de_tiro_viavel >= 30 && projeteis.temRecurso(this) == true){
            // ProjeteisDePecas novoProjetil = new OBJ_BolaDeFogo(pj);
            // Seleciona coordenadas padrões, direção e o úsuário
            projeteis.set(mundoX, mundoY, direcao, true, this);
            // Adiciona a lista
            pj.listaDeProjeteisDePecas.add(projeteis);
            contador_de_tiro_viavel = 0; // Tempo resetado
            pj.tocarEfeitoSonoro(4);
            pj.chaveManipuladora.tiroPressionado = false; 
            
            //Subtração
            projeteis.subtrai_Recurso(this);

        }
        
        if(invencivel == true){
            contador_de_invencibilidade++;
            if(contador_de_invencibilidade > 60){
                invencivel = false;
                contador_de_invencibilidade = 0;
            }
        }

        // Garante que o jogador só atire nesse espaço de tempo
        if(contador_de_tiro_viavel < 30){
            contador_de_tiro_viavel++;
        }
        
        if(vida > vidaMaxima){
            vida = vidaMaxima;
        }

        if(mana > mana_max){
            mana = mana_max;
        }
        if(vida <= 0){
            pj.estado_do_jogo = pj.estado_fim_de_jogo;
            pj.ui.numeroDoComando = -1; // Reseta o número do comando para evitar bugs no menu de fim de jogo e quando voçê atacar não volta imediatamente para o jogo
            pj.pararMusica(); //para a música de fundo na morte 
            // Aqui podemos adicionar uma música de morte se quisermos ex:: pj.tocarMusica(5); 

        }
    }

    public void atacando(){
        contadorDoEstado++;
        if(contadorDoEstado <= 5){
            numeroDoEstado = 1;
        }
        if(contadorDoEstado > 5 && contadorDoEstado <= 25){
            numeroDoEstado = 2;
            int correnteMundoX = mundoX;
            int correnteMundoY = mundoY;
            int area_solidaWidth = area_solida.width;
            int area_solidaHeight = area_solida.height;

            // Ajusta a área de ataque dependendo da direção
            switch(direcao){
                case "cima":
                    mundoY -= AtaqueArea.height; break;
                case "baixo":
                    mundoY += area_solidaHeight; break;
                case "esquerda":
                    mundoX -= AtaqueArea.width; break;
                case "direita":
                    mundoX += area_solidaWidth; break;
            }

            //Area de ataque se torna sólida
            area_solida.width = AtaqueArea.width;
            area_solida.height = AtaqueArea.height;

            //Checa colisão com monstros
            int index_do_monstro = pj.verifica.verificaEntidade(this, pj.monstros);
            danoMonstro(index_do_monstro, ataques);

            int iPeca_index = pj.verifica.verificaEntidade(this, pj.iPeca);
            danoPecaInterativa(iPeca_index);
            //Reverte as posições e tamanhos após o ataque
            mundoX = correnteMundoX;
            mundoY = correnteMundoY;
            area_solida.width = area_solidaWidth;
            area_solida.height = area_solidaHeight;

        }
        if(contadorDoEstado > 25){
            numeroDoEstado = 1;
            contadorDoEstado = 0;
            atacando = false;
        }
       
    }

    public void pegueObjeto(int i){
        // Se não for 999 então a entidade tocou no objeto
        if(i != 999){

            //Apenas pegar itens
            if(pj.obj[i].tipo == tipo_pegar_apenas){
                boolean consumido = pj.obj[i].use(this);
                if(consumido) pj.obj[i] = null;
            }
            else{


           //Inventário de itens
            String text;
            
          if(inventario.size() != tamanho_max_inventario){
                inventario.add(pj.obj[i]);

                text = "Você pegou " + pj.obj[i].nome;
               
            }
            else {
                text = "Inventario cheio";
            }
            pj.ui.mostrarMensagem(text);
            pj.obj[i]=null;
        }

    }
    }

    public void interacaoNPC(int i){

        if(pj.chaveManipuladora.enterPressionado == true){
            if(i != 999){
                ataqueCancelado = true;
                if(pj.chaveManipuladora.enterPressionado == true){
                    // System.out.println("Colidindo com NPC");
                    pj.estado_do_jogo = pj.estado_de_dialogo;
                    pj.npc[i].falar();
                }
            }
        }
    }

    public void contatoComMonstros(int i){
        
        if(i != 999){
            if(invencivel == false && pj.monstros[i].morrendo == false){
               
                int dano = pj.monstros[i].ataques - defesa;
                if(dano < 0){
                    dano = 0;
                }
                vida-= dano;
                pj.tocarEfeitoSonoro(1);
    
                invencivel = true;
            }
        }
    }

    public void danoMonstro(int i, int ataque){
        if(i != 999){
         
            if(pj.monstros[i].invencivel == false && pj.monstros[i].morrendo == false){
                 
            //    int dano = ataques - pj.monstros[i].defesa;
                int dano =  pj.monstros[i].ataques - defesa;
                if(dano < 0){
                    dano = 0;

                } 
                pj.monstros[i].vida-=dano;
                pj.ui.mostrarMensagem(dano + " dano");
                pj.monstros[i].invencivel = true;
                reacaoDano();
                pj.tocarEfeitoSonoro(1);

                // System.out.println("Dano no monstro: " + pj.monstros[i].vida);
                // System.out.println(pj.monstros[i].vida);

                if(pj.monstros[i].vida <= 0){
                pj.monstros[i].morrendo = true;
                pj.ui.mostrarMensagem("Vocêmatou " + pj.monstros[i].nome);
                pj.ui.mostrarMensagem("Exp " + pj.monstros[i].exp);
                exp += pj.monstros[i].exp;
                checaLevelUp();
                 
                    // System.out.println("Monstro derrotado!");
                }
            }
        }
        
    }

    public void danoPecaInterativa(int i){
        if(i != 999 && pj.iPeca[i].destrutivel == true && pj.iPeca[i].itemCorreto(this) == true){
            pj.iPeca[i] = null;
        }
    }
    public void checaLevelUp(){
        if(exp >= expProximoNivel){
            nivel++;
            expProximoNivel = expProximoNivel*2;
            vidaMaxima+=2;
            forca ++;
            experiencia++;
            ataques = getAtaques();
            defesa = getDefesa();

            pj.tocarEfeitoSonoro(2);

            pj.estado_do_jogo = pj.estado_de_dialogo;
            pj.ui.dialogo_atual = "Você subiu para o nível " + nivel+ "\n" + "Você se sente mais forte";
        }
    }

    public void selecionarItem(){
        int index_item = pj.ui.getIndex_item_no_compartimento();
        if(index_item < inventario.size()){
            Entidade selecionaItem  = inventario.get(index_item);
            if(selecionaItem.tipo == tipo_espada || selecionaItem.tipo == tipo_machado){
                correnteArma = selecionaItem;
                ataques = getAtaques();
                //Adicionamos aqui o ataque imagens para que ele puxe as imagens com a arma certa;
            }
            if(selecionaItem.tipo == tipo_escudo){
                correnteEscudo = selecionaItem;
                defesa = getDefesa();
            }
            if(selecionaItem.tipo == tipo_consumivel){
                selecionaItem.use(this);
                inventario.remove(index_item);
                pj.tocarEfeitoSonoro(3);
            }
        }
    }
    public void desenhar(Graphics2D g2){

        BufferedImage imagem = null;
        int TempTelaX = telaX;
        int TempTelaY = telaY;  

        switch (direcao){
            case "cima":
                if(atacando == false){
                if(numeroDoEstado == 1){
                    imagem = cima1;
                }
                if(numeroDoEstado == 2){
                    imagem = cima2;
                }
            } 
            if(atacando == true){
                //TempTelaY = telaY - pj.tamanhoDaPeca; // Ajusta a posição da imagem de ataque para cima
                 if(numeroDoEstado == 1){ imagem = ataque;
                }
                if(numeroDoEstado == 2){ imagem = ataque;
                }
            }
                break;

            case "baixo":
                if(atacando == false){
                if(numeroDoEstado == 1){ imagem = baixo1;
                }
                if(numeroDoEstado == 2){ imagem = baixo2;
                }
            } 
            if(atacando == true){
                 if(numeroDoEstado == 1){ imagem = ataque;
                }
                if(numeroDoEstado == 2){ imagem = ataque;
                }
            }
                break;

            case "esquerda":
                if(atacando == false){
                if(numeroDoEstado == 1){
                    imagem = esquerda1;
                }
                if(numeroDoEstado == 2){
                    imagem = esquerda2;
                }
            }
            if(atacando == true){
               // TempTelaX = telaX - pj.tamanhoDaPeca; // Ajusta a posição da imagem de ataque para esquerda (usar somente se precisar reajustar a imagem do ataque)
                 if(numeroDoEstado == 1){ imagem = ataque;
                }
                if(numeroDoEstado == 2){ imagem = ataque;
                }
            }
                break;

            case "direita":
                if(atacando == false){
                if(numeroDoEstado == 1){
                    imagem = direita1;
                }
                if(numeroDoEstado == 2){
                    imagem = direita2;
                }
            }
            if(atacando == true){
                 if(numeroDoEstado == 1){ imagem = ataque;
                }
                if(numeroDoEstado == 2){ imagem = ataque;
                }
            }
                break;
                
            case "estatico":
                imagem = estatico;
                break;
        }

        if(invencivel == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // Deixa o jogado meio transparente
        }

        g2.drawImage(imagem, TempTelaX, TempTelaY, null);
        
        // Resta o alpha
        if(invencivel == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }

        // DEBUG
        // g2.setFont(new Font("Arial",Font.PLAIN, 26));
        // g2.setColor(Color.white);
        // g2.drawString("Invencível: " + contador_de_invencibilidade, 10, 400);

        // Para ver a área de colisão usamos isso
        // g2.setColor(Color.red);
        // g2.drawRect(telaX + area_solida.x, telaY + area_solida.y, area_solida.width, area_solida.height);

            }
        }
        
