package game.actors;

import engine.StdDraw;
import game.Game;
import game.LevelManager;

/**
 * Classe représentant le jouuer.
 * A ce stade cen'est qu'un point rouge qui se déplace avec les flèches du
 * clavier.
 */
public abstract class Enemy extends Entity {
    protected int score;
    protected int coolDownShootMax;
    protected int coolDownShoot;

    public Enemy(double positionx, double positiony, double size, int score, double speed, Game game) {
        super(positionx, positiony, size, 1, speed, game);
        this.score = score;
        this.coolDownShootMax = 0;
        this.coolDownShoot = coolDownShootMax;

    }

    public void update() {
        move();
        if (canShoot()) {
            shoot();

        }

        if (coolDownShoot > 0) {
            coolDownShoot--;
        }

    }

    public void move() {
        // Déplacement de l'ennemi (à implémenter)
    }

    public void shoot() {
        Missile m1 = new Missile(speed * 2, positionx, positiony - size / 2, EDirectionMissile.DOWN);
        game.addMissilesEnemies(m1);

    }

    public abstract void drawSprite();

    public boolean canShoot() {
        if (coolDownShootMax == 0) {
            coolDownShootMax = (int) game.getLevelManager().getCurrentLevel().getShootCooldown() / 100;
            coolDownShoot = coolDownShootMax;
        }
        if (coolDownShoot == 0) {
            coolDownShoot = coolDownShootMax;
            return true;
        }

        return false;
    }
}
