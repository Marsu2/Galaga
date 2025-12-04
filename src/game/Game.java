package game;

import engine.StdDraw;
import game.actors.Missile;
import game.actors.Player;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe du jeu principal.
 * Gère la création de l'espace de jeu et la boucle de jeu en temps réel.
 */
public class Game {

    public Player player; // Jouer, seul éléments actuellement dans notre jeu
    private List<Missile> missilesPlayers;

    public void addMissilesPlayers(Missile m) {
        missilesPlayers.add(m);
    }

    /**
     * Créé un jeu avec tous les éléments qui le composent
     */
    public Game() {
        player = new Player(0.5, 0.1, 0.1, 5, 0.01, this);
        missilesPlayers = new LinkedList<>();
    }

    /**
     * Initialise l'espace de jeu
     */
    private void init() {
        int canvaHeight = 700;
        int canvasWidth = 700;
        StdDraw.setCanvasSize(canvaHeight, canvasWidth);
        StdDraw.enableDoubleBuffering();
    }

    /**
     * Initialise le jeu et lance la boucle de jeu en temps réel
     */
    public void launch() {
        init();

        while (isGameRunning()) {
            StdDraw.clear(); // On efface tous ce qu'il y a sur l'interface

            update(); // on met a jour les attributs de chaque éléments
            draw(); // on dessine chaques éléments

            StdDraw.show(); // on montre l'interface
            StdDraw.pause(30); // on attend 30 milisecondes avant de recommencer
        }
    }

    /**
     * Condition d'arrêt du jeu
     * 
     * @return true car on n'as pas encore de conidtions d'arrêt
     */
    private boolean isGameRunning() {
        return true;
    }

    /**
     * Dessin tous les éléments du jeu
     */
    public void draw() {
        player.drawSprite();
        for (Missile m : missilesPlayers) {
            m.drawSprite();
        }
    }

    /**
     * Met a jour les attributs de tous les éléments du jeu
     */
    private void update() {
        player.update();
        for (Missile m : missilesPlayers) {
            m.update();
        }
    }
}
