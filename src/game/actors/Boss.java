package game.actors;

import game.utils.SpriteLoader;

/**
 * boss ennemi qui vole dans le jeu.
 * Hérite des comportements Enemy de base avec son propre sprite.
 */
public class Boss extends Enemy {

    /**
     * Crée un nouveau papillon ennemi aux coordonnées spécifiées.
     * 
     * @param positionx position horizontale du boss
     * @param positiony position verticale du boss
     * @param size      taille du boss
     * @param score     points accordés quand le boss est détruit
     * @param speed     vitesse de déplacement du boss
     * @param game      référence au jeu principal
     */

    public Boss(double positionx, double positiony, double size, int score, int health, double speed, int shootCooldown) {
        super(positionx, positiony, size, score, health, speed, shootCooldown);
    }

    /**
     * Dessine le sprite spécifique du boss à sa position actuelle.
     */
    public void draw() {
        if (sprite == null) {
            sprite = SpriteLoader.loadSprite("ressources/sprites/boss.spr");
        }
        SpriteLoader.drawSprite(sprite, positionx, positiony, size);
        drawMissiles();
    }

    public void move() {
        if (soloMode) {
            return;
        }
    }

}
