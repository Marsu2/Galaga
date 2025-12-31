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
    protected boolean scored;
    protected boolean soloMode;

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

    public Enemy(double positionx, double positiony, double size, int score, int health, double speed, int shootCooldown) {
        super(positionx, positiony, size, health, speed);
        this.initialPositionX = positionx;
        this.initialPositionY = positiony;
        this.score = score;
        this.shootCooldownMax = shootCooldown;
        this.shootCooldown = shootCooldown + (new Random().nextInt(50)); // pour eviter que tous les ennemis tirent en
                                                                         // meme temps
        this.scored = false;
        this.soloMode = false; // par defaut un enemie est tjrs dans la formation

    }

    /**
     * Retourne les points de l'ennemi.
     * 
     * @return points accordés à la destruction
     */
    public int getScore() {
        return score;
    }

    public boolean getScored() {
        return scored;
    }

    /**
     * Met à jour l'ennemi : mouvement, tir automatique et gestion du cooldown.
     */

    public void update() {

        removeMissilesOOB();
        for (Missile missile : missiles) {
            missile.update();
        }
        move();
        if (shootCooldown > 0) {
            shootCooldown--;
        }
        thisOutofBound();

    }

    /**
     * Déplace l'ennemi selon sa logique spécifique (à implémenter).
     */

    public abstract void move();

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
            shootCooldown = shootCooldownMax;
            if (botttomClearedShoot(enemies)) {
                return true;
            }
        }
        return false;
    }

    public boolean isSoloMode() {
        return soloMode;
    }

    public void setSoloMode(boolean soloMode) {
        this.soloMode = soloMode;
    }

    public boolean botttomClearedShoot(List<Enemy> enemies) {
        double marginWidth = size / 2;
        for (Enemy enemy : enemies) {
            if (Math.abs(enemy.getPositionx() - this.positionx) < marginWidth
                    && enemy.getPositiony() < this.positiony) {
                shootCooldown = shootCooldownMax;
                return false;
            }
        }
        return true;
    }

    public boolean botttomClearedSolo(List<Enemy> enemies) {
        double minY = this.positiony;
        for (Enemy enemy : enemies) {
            if (enemy.getPositiony() < minY && !enemy.isSoloMode()) {
                minY = enemy.getPositiony();
            }
        }
        return positiony == minY;
    }

    public void reset() {
        positionx = initialPositionX;
        positiony = initialPositionY;
        removeAllMissiles();
        setShootCooldown(90 + (new Random().nextInt(100)));// 3 sec + random 0-100 frames
        soloMode = false;
    }

    public boolean checkHitBy(Player player) {
        for (Missile missile : player.getMissiles()) {
            if (missile.isHitingEntity(this)) {
                this.takeDamage(1); // perde 1 HP
                player.getMissiles().remove(missile);
                if (isDead()) {
                    // pour le faire sortir de l'ecrna
                    positionx = 10;
                    positiony = -10;
                }
                return true;
            }
        }
        return false;
    }

    public boolean canRemove() {
        return isDead() && missiles.isEmpty();
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }

    private void thisOutofBound() {
        if (positionx < -0.1 || positionx > 1.1 || positiony < -0.1 || positiony > 1.1) {
            takeDamage(1);
        }
    }
}
