package game;

import engine.StdDraw;
import game.actors.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principale du jeu.
 * Gère la boucle de jeu, les collisions, le rendu et la logique principale.
 */
public class Game {

    private Player player; // Jouer, seul éléments actuellement dans notre jeu
    private LevelManager manager;
    private Score score;

    /**
     * Initialise le jeu avec joueur, ennemis, score et premier niveau.
     */
    public Game() {
        player = new Player(0.5, 0.15, 0.06, 12, 0.02);
        score = new Score();
        manager = new LevelManager(player, score);
    }

    /**
     * Initialise le jeu avec joueur, ennemis, score et premier niveau.
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
            StdDraw.clear(StdDraw.BLACK); // On efface tous ce qu'il y a sur l'interface

            update(); // on met a jour les attributs de chaque éléments
            draw(); // on dessine chaques éléments

            StdDraw.show(); // on montre l'interface
            StdDraw.pause(30); // on attend 30 milisecondes avant de recommencer
        }
        score.saveHighscore();
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
        player.draw();
        manager.draw();
        score.draw();

        if (manager.isGameOver()) {
            manager.drawGameOver();
        }
    }

    /**
     * Met a jour les attributs de tous les éléments du jeu
     */
    private void update() {
        player.update();
        manager.update();
        if(manager.isGameOver()){
            if(StdDraw.isKeyPressed(32)){
                manager.clear();
                manager.reset();
            }
        }
    }
}
