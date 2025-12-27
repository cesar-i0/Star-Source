package main;

public class ManipuladorDeEvento {

PainelDoJogo pj;
ReacaoDeEvento reacaoDeEvento[][];

int EventoPrevioX, EventoPrevioY;
boolean eventoAtivo = true;
public ManipuladorDeEvento(PainelDoJogo pj){

this.pj = pj;

reacaoDeEvento = new ReacaoDeEvento[pj.maxColunasDoMundo][pj.maxLinhaDoMundo];

int col = 0;
int row = 0;

while (col < pj.maxColunasDoMundo && row < pj.maxLinhaDoMundo){
reacaoDeEvento [col][row]= new ReacaoDeEvento();
reacaoDeEvento [col][row].x = 23;
reacaoDeEvento [col][row].y = 23;
reacaoDeEvento[col][row].width = 2;
reacaoDeEvento[col][row].height = 2;
reacaoDeEvento[col][row].x = reacaoDeEvento[col][row].x;
reacaoDeEvento[col][row].y = reacaoDeEvento[col][row].y;

col++;
if(col == pj.maxColunasDoMundo){
col = 0;
row++;
}
}

}

public void verificaEvento(){

int xDistancia = Math.abs(pj.jogador.mundoX - EventoPrevioX);
int yDistancia = Math.abs(pj.jogador.mundoY - EventoPrevioY);
int distancia = Math.max(xDistancia, yDistancia);
if (distancia > pj.tamanhoDaPeca) {
eventoAtivo = true;
}
if (eventoAtivo == true){
// Local da armadilha
if(atingiu(1, 1, "cima") == true) danoDeArmadilha(1, 1,pj.estado_de_dialogo);
// Local de cura
if(atingiu(23, 12, "cima") == true) localDeCura(23, 12,pj.estado_de_dialogo);
// Local de teleporte
if(atingiu(5, 1, "cima") == true) teleporte(5, 1, pj.estado_de_dialogo);

}
}

public boolean atingiu(int col, int row, String direcaoRequerida){
boolean atingiu = false;

pj.jogador.area_solida.x = pj.jogador.mundoX + pj.jogador.area_solida_padraoX;
pj.jogador.area_solida.y = pj.jogador.mundoY + pj.jogador.area_solida_padraoY;

reacaoDeEvento[col][row].x = col * pj.tamanhoDaPeca + reacaoDeEvento[col][row].x;
reacaoDeEvento[col][row].y = row * pj.tamanhoDaPeca + reacaoDeEvento[col][row].y;

if(pj.jogador.area_solida.intersects(reacaoDeEvento[col][row]) && reacaoDeEvento[col][row].evento_feito == false){
if(pj.jogador.direcao.contentEquals(direcaoRequerida) || direcaoRequerida.contentEquals("any")){
atingiu = true;

EventoPrevioX = pj.jogador.mundoX;
EventoPrevioY = pj.jogador.mundoY;
}
}

pj.jogador.area_solida.x = pj.jogador.area_solida_padraoX;
pj.jogador.area_solida.y = pj.jogador.area_solida_padraoY;
reacaoDeEvento[col][row].x = reacaoDeEvento[col][row].x;
reacaoDeEvento[col][row].y = reacaoDeEvento[col][row].y;

return atingiu;

}

public void teleporte(int col, int row, int estado_do_jogo){

pj.estado_do_jogo = estado_do_jogo;
pj.ui.dialogo_atual = "Teleporte!";
pj.jogador.mundoX = pj.tamanhoDaPeca * 40;
pj.jogador.mundoY = pj.tamanhoDaPeca * 25;
//reacaoDeEvento[col][row].evento_feito = true;
eventoAtivo = false;
}

public void danoDeArmadilha(int col, int row,int estado_do_jogo){

pj.estado_do_jogo = estado_do_jogo;
pj.ui.dialogo_atual = "Você caiu em uma armadilha!";
pj.jogador.vida -= 1;

}

public void localDeCura(int col, int row, int estado_do_jogo){

System.out.println("Curando!");

if(pj.chaveManipuladora.enterPressionado == true){
pj.estado_do_jogo = estado_do_jogo;
pj.ui.dialogo_atual = "Você se curou!\nSua vida foi restaurada.";
pj.jogador.vida = pj.jogador.vidaMaxima;
}
}

}
