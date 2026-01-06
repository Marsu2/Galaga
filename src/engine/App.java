package engine;

import game.Game;

/**
 * Classe de lancement du projet
 * 
 * @author BARBIER Elouan
 * @author ESNAULT Damien
 */
public class App {
    public static void main(String[] args) throws Exception {
        // Création d'un nouveau jeu et lancement de celui-ci
        Game g = new Game();
        g.launch();
    }
}
