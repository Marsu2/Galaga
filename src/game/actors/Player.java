package game.actors;

import engine.StdDraw;
import game.Game;

/**
 * Classe représentant le jouuer.
 * A ce stade cen'est qu'un point rouge qui se déplace avec les flèches du
 * clavier.
 */
public class Player extends Entity {
    private int coolDownShoot;
    private int coolDownShootMax;

    public Player(double positionx, double positiony, double size, int health, double speed, Game game) {
        super(positionx, positiony, size, health, speed, game);
        coolDownShoot = 0;
        coolDownShootMax = 7;
    }

    public void update() {
        move();
        shoot();

        if (coolDownShoot > 0) {
            coolDownShoot--;
        }
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

    public boolean canShootPlayer() {
        return game.getMissilesPlayers().size() < 3 && coolDownShoot == 0;
    }

    public void shoot() {
        if (StdDraw.isKeyPressed(32) && canShootPlayer()) {
            coolDownShoot = coolDownShootMax;
            Missile m1 = new Missile(speed * 3, positionx, positiony + size / 2, EDirectionMissile.UP);
            game.addMissilesPlayers(m1);
        }

    }

    public void drawSprite() {
        super.drawSpriteV2(positionx, positiony, size, "ressources/sprites/ship.spr");
    }

}
