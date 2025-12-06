package game.actors;

import engine.StdDraw;
import game.Game;

/**
 * Classe représentant le jouuer.
 * A ce stade cen'est qu'un point rouge qui se déplace avec les flèches du
 * clavier.
 */
public class Player extends Entity {
    /**
     * Créé un joueur
     * 
     * 
     */

    public Player(double positionx, double positiony, double size, int health, double speed, Game game) {
        super(positionx, positiony, size, health, speed, game);
    }

    public void update() {
        move();
        shoot();
    }

    public void move() {
        if (StdDraw.isKeyPressed(37)) {
            if (positionx > 0) {
                positionx -= speed;
            }
        }
        // Si la flèche droite est préssé
        if (StdDraw.isKeyPressed(39)) {
            if (positionx < 1) {
                positionx += speed;

            }
        }
    }

    public void shoot() {
        if (StdDraw.isKeyPressed(32)) {
            Missile m1 = new Missile(speed * 3, positiony, positionx, EDirectionMissile.UP);
            game.addMissilesPlayers(m1);
        }

    }

    public void drawSprite() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(positionx, positiony, size / 2);
    }

}
