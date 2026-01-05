package game.actors;

import game.utils.SpriteLoader;

/**
 * Sous class de enemy pour MOTH qui ca pouvoir attaquer
 **/
public class Moth extends Enemy {
    /**
     * Initialise un nouveau papillon de nuit ennemi.
     * 
     * @param positionx     position horizontale initiale
     * @param positiony     position verticale initiale
     * @param size          taille de l'entité
     * @param score         points attribués à la destruction
     * @param speed         vitesse de déplacement
     * @param health        points de vie initiaux
     * @param shootCooldown temps de recharge initial du tir
     */
    private Bee capturedBee = null;
    private boolean hasCaptured = false;

    public boolean hasCaptured() {
        return hasCaptured;
    }

    public Bee getCapturedBee() {
        return capturedBee;
    }

    public Moth(double positionx, double positiony, double size, int score, int health, double speed,
            int shootCooldown) {
        super(positionx, positiony, size, score, health, speed, shootCooldown);
    }

    /**
     * Dessine le sprite personnalisé du papillon de nuit.
     */
    public void draw() {
        if (sprite == null) {
            sprite = SpriteLoader.loadSprite("ressources/sprites/catcher.spr");
        }
        SpriteLoader.drawSprite(sprite, positionx, positiony, size);
        drawMissiles();
    }

    public void move() {
        if (soloMode) {
            positiony -= speed;
        }
    }

    public Bee capture(Player player) {
        if (!hasCaptured) {
            capturedBee = new Bee(positionx, positiony - (size * 1.2), size, 0, 1, speed * 2, 100);

            capturedBee.initialPositionX = positionx;
            capturedBee.initialPositionY = positiony - (size * 1.2);
            hasCaptured = true;
            return capturedBee;
        }
        return null;
    }

    public void releaseCapture(Player player) {
        if (hasCaptured && capturedBee != null) {
            player.setHealth(player.getHealth() + 1);
            this.hasCaptured = false;
            this.capturedBee = null;
        }
    }

    public boolean isNear(Player p) {
        double distanceX = Math.abs(this.positionx - p.positionx);
        double distanceY = Math.abs(this.positiony - p.positiony);
        return (distanceX < (size + p.size) / 2 && distanceY < size * 2);
    }

}
