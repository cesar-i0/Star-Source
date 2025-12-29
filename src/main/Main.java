package main;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a janela de maneira correta quando o botão "X" é clicado.
        window.setResizable(false); // Impede que o usuário redimensionalize a janela.
        window.setTitle("Jogo"); // O nome do jogo em si.

        PainelDoJogo painelDoJogo = new PainelDoJogo();
        window.add(painelDoJogo); // As configurações padrões serão aplicadas no JFrame.

        window.pack(); // Modifica a tela para se encaixar no tamanho preferido e os layouts dos subcomponentesd

        window.setLocationRelativeTo(null); // A janela será aberta no centro da tela
        window.setVisible(true); // Permite que vejamos essa tela

        // System.out.println("Janela visível, iniciando configuração...");
        painelDoJogo.configuracao_do_jogo(); // Posiciona itens no mapa do jogo
        painelDoJogo.iniciarThreadDoJogo();
        // System.out.println("Thread iniciado.");
    
    }
}