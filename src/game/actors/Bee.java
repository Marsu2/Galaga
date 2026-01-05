package game.actors;

import java.util.Random;

import game.utils.SpriteLoader;

/**
 * Abeille ennemie qui vole dans le jeu.
 * Hérite des comportements Enemy de base avec son propre sprite.
 */
public class Bee extends Enemy {

    private double target;

    /**
     * Crée une nouvelle abeille ennemie aux coordonnées spécifiées.
     * 
     * @param positionx     position horizontale de l'abeille
     * @param positiony     position verticale de l'abeille
     * @param size          taille de l'abeille
     * @param score         points accordés quand l'abeille est détruite
     * @param speed         vitesse de déplacement de l'abeille
     * @param health        points de vie de l'abeille
     * @param shootCooldown temps de recharge initial du tir
     */

    public Bee(double positionx, double positiony, double size, int score, int health, double speed,
            int shootCooldown) {
        super(positionx, positiony, size, score, health, speed, shootCooldown);
        this.target = new Random().nextDouble();
    }

    /**
     * Dessine le sprite spécifique de l'abeille à sa position actuelle.
     */
    public void draw() {
        if (sprite == null) {
            sprite = SpriteLoader.loadSprite("ressources/sprites/bee.spr");
        }
        SpriteLoader.drawSprite(sprite, positionx, positiony, size);
        drawMissiles();
    }

    public void move() {
        if (soloMode) {
            double distance = Math.abs(positionx - target);
            if (distance < 0.05) {
                // on choisit une nouvelle cible de l'autre coté
                if (target < 0.5) {
                    target = Math.min(0.9, (0.5 + new Random().nextDouble() / 2));
                } else {
                    target = Math.max(0.1, new Random().nextDouble() / 2);
                }
            }

            if (positionx < target) {
                positionx += speed;
            } else {
                positionx -= speed;
            }
            positiony -= speed / 2;
        }
    }

}
