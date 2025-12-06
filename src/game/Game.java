package game;

import engine.StdDraw;
import game.actors.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe du jeu principal.
 * Gère la création de l'espace de jeu et la boucle de jeu en temps réel.
 */
public class Game {

    public Player player; // Jouer, seul éléments actuellement dans notre jeu
    private List<Missile> missilesPlayers;
    private List<Missile> missilesEnemies;
    private List<Entity> enemies;

    public void addMissilesPlayers(Missile m) {
        missilesPlayers.add(m);
    }

    public void addMissilesEnemies(Missile m) {
        missilesEnemies.add(m);
    }

    /**
     * Créé un jeu avec tous les éléments qui le composent
     */
    public Game() {
        player = new Player(0.5, 0.1, 0.08, 5, 0.01, this);
        enemies = new LinkedList<>();
        Enemy enemy1 = new Moth(0.5, 0.9, 0.05, 0.01, this);
        enemies.add(enemy1);
        missilesPlayers = new LinkedList<>();
        missilesEnemies = new LinkedList<>();
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
        for (Missile m : missilesEnemies) {
            m.drawSprite();
        }
        for (Entity e : enemies) {
            e.drawSprite();
        }
    }

    /**
     * Met a jour les attributs de tous les éléments du jeu
     */
    private void update() {
        player.update();
        for (Entity e : enemies) {
            e.update();
        }

        List<Missile> hiddenMissiles = new ArrayList<>();
        for (Missile m : missilesPlayers) {
            if (m.isOutOfBound()) {
                hiddenMissiles.add(m);
            }
            m.update();
        }

        for (Missile m : missilesEnemies) {
            if (m.isOutOfBound()) {
                hiddenMissiles.add(m);
            }
            m.update();
        }
        missilesPlayers.removeAll(hiddenMissiles);
        missilesEnemies.removeAll(hiddenMissiles);
        checkHit();
    }

    private void checkHit() {
        List<Missile> missilesDead = new ArrayList<>();
        List<Entity> enemiesDead = new ArrayList<>();
        for (Missile m : missilesPlayers) {
            for (Entity e : enemies) {
                if (m.hitEntity(e)) {
                    e.setHealth(e.getHealth() - 1);
                    if (e.isDead()) {
                        enemiesDead.add(e);
                    }
                    missilesDead.add(m);
                }
            }
        }
        enemies.removeAll(enemiesDead);
        missilesPlayers.removeAll(missilesDead);
    }

    public boolean canShootPlayer() {
        return missilesPlayers.size() < 3;
    }
}
