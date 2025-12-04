package game.actors;

import engine.StdDraw;
import game.Game;

/**
 * Classe représentant le jouuer.
 * A ce stade cen'est qu'un point rouge qui se déplace avec les flèches du
 * clavier.
 */
public class Enemy extends Entity {

    public Enemy(double positionx, double positiony, double size, double speed, Game game) {
        super(positionx, positiony, size, 1, speed, game);
    }

    public void update() {
        move();
        shoot();
    }

    public void move() {
        // Déplacement de l'ennemi (à implémenter)
    }

    public void shoot() {
        Missile m1 = new Missile(speed * 3, positiony, positionx, EDirectionMissile.DOWN);
        game.addMissilesEnemies(m1);
    }
}
