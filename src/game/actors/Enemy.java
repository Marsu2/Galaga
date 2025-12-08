package game.actors;

import engine.StdDraw;
import game.Game;
import game.LevelManager;

/**
 * Classe abstraite représentant un ennemi générique dans le jeu.
 * Gère le comportement de base : mouvement, tir et cooldown d'attaque.
 */
public abstract class Enemy extends Entity {
    protected int score;
    protected int coolDownShootMax;
    protected int coolDownShoot;

    /**
     * Crée un nouvel ennemi avec ses paramètres de base.
     * 
     * @param positionx position horizontale initiale
     * @param positiony position verticale initiale
     * @param size      taille de l'ennemi
     * @param score     points attribués à la destruction
     * @param speed     vitesse de déplacement
     * @param game      référence au jeu principal
     */

    public Enemy(double positionx, double positiony, double size, int score, double speed, Game game) {
        super(positionx, positiony, size, 1, speed, game);
        this.score = score;
        this.coolDownShootMax = 0;
        this.coolDownShoot = coolDownShootMax;

    }

    /**
     * Retourne les points de l'ennemi.
     * 
     * @return points accordés à la destruction
     */
    public int getScore() {
        return score;
    }

    /**
     * Met à jour l'ennemi : mouvement, tir automatique et gestion du cooldown.
     */

    public void update() {
        move();
        if (canShoot()) {
            shoot();

        }

        if (coolDownShoot > 0) {
            coolDownShoot--;
        }

    }

    /**
     * Déplace l'ennemi selon sa logique spécifique (à implémenter).
     */

    public void move() {
    }

    /**
     * Tire un missile vers le bas (vers le joueur).
     */
    public void shoot() {
        Missile m1 = new Missile(speed * 2, positionx, positiony - size / 2, EDirectionMissile.DOWN);
        game.addMissilesEnemies(m1);

    }

    /**
     * Dessine le sprite spécifique de l'ennemi (à implémenter).
     */

    public abstract void drawSprite();

    /**
     * Vérifie si l'ennemi peut tirer en fonction de son cooldown.
     * Initialise automatiquement le cooldown depuis le niveau courant.
     * 
     * @return true si l'ennemi peut tirer, false sinon
     */
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
