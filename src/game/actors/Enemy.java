package game.actors;

import java.util.List;
import java.util.Random;

/**
 * Classe abstraite représentant un ennemi générique dans le jeu.
 * Gère le comportement de base : mouvement, tir et cooldown d'attaque.
 */
public abstract class Enemy extends Entity {
    protected int score;
    protected int shootCooldownMax;
    protected int shootCooldown;
    protected double initialPositionX;
    protected double initialPositionY;

    public void setShootCooldown(int shootCooldown) {
        this.shootCooldown = shootCooldown;
    }

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

    public Enemy(double positionx, double positiony, double size, int score, double speed, int shootCooldown) {
        super(positionx, positiony, size, 1, speed);
        this.initialPositionX = positionx;
        this.initialPositionY = positiony;
        this.score = score;
        this.shootCooldownMax = shootCooldown;
        this.shootCooldown = shootCooldown;

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
        removeMissilesOOB();
        for (Missile missile : missiles) {
            missile.update();
        }

        if (shootCooldown > 0) {
            shootCooldown--;
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
        Missile m1 = new Missile(0.02, positionx, positiony - size / 2, EDirectionMissile.DOWN);
        missiles.add(m1);

    }

    /**
     * Dessine le sprite spécifique de l'ennemi
     */

    public abstract void draw();

    /**
     * Vérifie si l'ennemi peut tirer en fonction de son cooldown.
     * Initialise automatiquement le cooldown depuis le niveau courant.
     * 
     * @return true si l'ennemi peut tirer, false sinon
     */
    public boolean canShoot(List<Enemy> enemies) {
        if (shootCooldown == 0) {
            double marginWidth = size / 2;
            for (Enemy enemy : enemies) {
                if (Math.abs(enemy.getPositionx() - this.positionx) < marginWidth
                        && enemy.getPositiony() < this.positiony) {
                    shootCooldown = shootCooldownMax;
                    return false;
                }
            }
            shootCooldown = shootCooldownMax;
            return true;
        }

        return false;
    }

    public void reset() {
        positionx = initialPositionX;
        positiony = initialPositionY;
        removeAllMissiles();
        setShootCooldown(90 + (new Random().nextInt(100)));// 3 sec + random 0-100 frames
    }

    public void animationStart() {

    }

    public boolean checkHitBy(Player player) {
        for (Missile missile : player.getMissiles()) {
            if (missile.isHitingEntity(this)) {
                this.takeDamage(1); // perde 1 HP
                player.getMissiles().remove(missile);
                return true;
            }
        }
        return false;
    }
}
