package game.actors;

import engine.StdDraw;
import game.Game;
import game.utils.SpriteLoader;

/**
 * Abeille ennemie qui vole dans le jeu.
 * Hérite des comportements Enemy de base avec son propre sprite.
 */
public class Bee extends Enemy {

    /**
     * Crée une nouvelle abeille ennemie aux coordonnées spécifiées.
     * 
     * @param positionx position horizontale de l'abeille
     * @param positiony position verticale de l'abeille
     * @param size      taille de l'abeille
     * @param score     points accordés quand l'abeille est détruite
     * @param speed     vitesse de déplacement de l'abeille
     * @param game      référence au jeu principal
     */

    public Bee(double positionx, double positiony, double size, int score, double speed, int shootCooldown) {
        super(positionx, positiony, size, score, speed, shootCooldown);
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

}
