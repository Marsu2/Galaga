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
        // javadoc -d ../docs -sourcepath . -subpackages engine:game
        // Ligne javadoc pour faire la docu a bien faire dans src
        Game g = new Game();
        g.launch();
    }
}
