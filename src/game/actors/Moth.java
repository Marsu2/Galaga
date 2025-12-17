package game.actors;

import engine.StdDraw;
import game.Game;
import game.utils.SpriteLoader;

/**
 * Sous class de enemy pour MOTH qui ca pouvoir attaquer
 **/
public class Moth extends Enemy {
    /**
     * Initialise un nouveau papillon de nuit ennemi.
     * 
     * @param positionx position horizontale initiale
     * @param positiony position verticale initiale
     * @param size      taille de l'entité
     * @param score     points attribués à la destruction
     * @param speed     vitesse de déplacement
     * @param game      référence à l'objet principal du jeu
     */

    public Moth(double positionx, double positiony, double size, int score, double speed, int shootCooldown) {
        super(positionx, positiony, size, score, speed, shootCooldown);
    }

    /**
     * Dessine le sprite personnalisé du papillon de nuit.
     */
    public void drawSprite() {
        if (sprite == null) {
            sprite = SpriteLoader.loadSprite("ressources/sprites/catcher.spr");
        }
        SpriteLoader.drawSprite(sprite, positionx, positiony, size);
        drawMissiles();
    }

}
