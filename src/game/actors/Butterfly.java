package game.actors;

import game.utils.SpriteLoader;

/**
 * Papillon ennemi qui vole dans le jeu.
 * Hérite des comportements Enemy de base avec son propre sprite.
 */
public class Butterfly extends Enemy {

    /**
     * Crée un nouveau papillon ennemi aux coordonnées spécifiées.
     * 
     * @param positionx position horizontale du papillon
     * @param positiony position verticale du papillon
     * @param size      taille du papillon
     * @param score     points accordés quand le papillon est détruit
     * @param speed     vitesse de déplacement du papillon
     * @param game      référence au jeu principal
     */

    public Butterfly(double positionx, double positiony, double size, int score, double speed, int shootCooldown) {
        super(positionx, positiony, size, score, speed, shootCooldown);
    }

    /**
     * Dessine le sprite spécifique du papillon à sa position actuelle.
     */
    public void drawSprite() {
        if (sprite == null) {
            sprite = SpriteLoader.loadSprite("ressources/sprites/butterfly.spr");
        }
        SpriteLoader.drawSprite(sprite, positionx, positiony, size);
        drawMissiles();
    }

}
