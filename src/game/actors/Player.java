package game.actors;

import engine.StdDraw;

/**
 * Classe représentant le jouuer.
 * A ce stade cen'est qu'un point rouge qui se déplace avec les flèches du
 * clavier.
 */
public class Player extends Entity {
    /**
     * Créé un joueur.
     * 
     */

    public Player(double positionx, double positiony, double size, int health, double speed) {
        super(positionx, positiony, size, health, speed);
    }

    public void update() {
        move();
    }

    public void move() {
        if (StdDraw.isKeyPressed(37)) {
            positionx -= speed;
        }
        // Si la flèche droite est préssé
        if (StdDraw.isKeyPressed(39)) {
            positionx += speed;
        }
    }

    public void shoot() {

    }

}
