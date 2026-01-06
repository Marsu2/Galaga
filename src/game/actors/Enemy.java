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

    /**
     * Crée un nouvel ennemi avec ses paramètres de base.
     *
     * @param positionx     position horizontale initiale
     * @param positiony     position verticale initiale
     * @param size          taille de l'ennemi
     * @param score         points attribués à la destruction
     * @param health        points de vie de l'ennemi
     * @param speed         vitesse de déplacement
     * @param shootCooldown temps de recharge initial du tir
     */
    public Enemy(double positionx, double positiony, double size, int score, int health, double speed,
            int shootCooldown) {
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
     * Déplace l'ennemi selon sa logique spécifique.
     */
    public abstract void move();

    /**
     * Dessine le sprite spécifique de l'ennemi
     */
    public abstract void draw();

    /**
     * Active ou désactive le mode solo pour cet ennemi.
     *
     * @param soloMode true pour activer le mode solo
     */
    public void setSoloMode(boolean soloMode) {
        this.soloMode = soloMode;
    }

    /**
     * Définit si le score de cet ennemi a été comptabilisé.
     *
     * @param scored true si le score a été compté
     */
    public void setScored(boolean scored) {
        this.scored = scored;
    }

    /**
     * Définit le temps de recharge avant le prochain tir.
     *
     * @param shootCooldown le nombre de frames avant le prochain tir
     */
    public void setShootCooldown(int shootCooldown) {
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
     * Indique si les points de cet ennemi ont déjà été comptabilisés.
     *
     * @return true si le score a été compté
     */
    public boolean getScored() {
        return scored;
    }

    /**
     * Vérifie si l'ennemi est en mode "solo" (Il part en mission).
     *
     * @return true si l'ennemi est en mode solo
     */
    public boolean isSoloMode() {
        return soloMode;
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
     * Réinitialise l'ennemi à sa position initiale et ses états par défaut.
     */
    public void reset() {
        positionx = initialPositionX;
        positiony = initialPositionY;
        removeAllMissiles();
        setShootCooldown(90 + (new Random().nextInt(100)));// 3 sec + random 0-100 frames
        soloMode = false;
    }

    /**
     * Tire un missile vers le bas (vers le joueur).
     */
    public void shoot() {
        Missile m1 = new Missile(0.02, positionx, positiony - size / 2, EDirectionMissile.DOWN);
        missiles.add(m1);
    }

    /**
     * Vérifie si l'ennemi peut tirer (cooldown écoulé et personne en dessous).
     *
     * @param enemies la liste des autres ennemis pour vérifier si qqln en dessous
     * @return true si l'ennemi peut tirer
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

    /**
     * Vérifie s'il n'y a pas d'autres ennemis devant celui-ci avant de tirer.
     *
     * @param enemies la liste des ennemis
     * @return true si la voie est libre
     */
    public boolean botttomClearedShoot(List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            if (Math.abs(enemy.getPositionx() - this.positionx) < (size / 2)
                    && enemy.getPositiony() < this.positiony) {
                shootCooldown = shootCooldownMax;
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si l'ennemi est le plus bas de sa colonne pour lancer une attaque
     * solo.
     *
     * @param enemies la liste des ennemis
     * @return true si l'ennemi est éligible pour une attaque solo
     */
    public boolean botttomClearedSolo(List<Enemy> enemies) {
        double minY = this.positiony;
        for (Enemy enemy : enemies) {
            if (enemy.getPositiony() < minY && !enemy.isSoloMode()) {
                minY = enemy.getPositiony();
            }
        }
        return positiony == minY;
    }

    /**
     * Vérifie si l'ennemi est touché par un missile du joueur.
     *
     * @param player le joueur (pour accéder à ses missiles)
     * @return true si l'ennemi a été touché
     */
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

    /**
     * Vérifie si l'ennemi peut être retiré du jeu (mort et plus de missiles
     * actifs).
     *
     * @return true si l'ennemi peut être supprimé
     */
    public boolean canRemove() {
        return isDead() && missiles.isEmpty();
    }

    /**
     * Vérifie si l'ennemi sort des limites du jeu et lui inflige des dégâts si
     * c'est le cas pour qu'il meurt.
     */
    private void thisOutofBound() {
        if (positionx < -0.1 || positionx > 1.1 || positiony < -0.1 || positiony > 1.1) {
            takeDamage(1);
        }
    }

    /**
     * Vérifie si l'ennemi entre en collision physique avec le joueur.
     *
     * @param p le joueur
     * @return true s'il y a collision
     */
    public boolean isColliding(Player p) {
        double distanceX = Math.abs(this.positionx - p.getPositionx()) - ((this.size + p.getSize()) / 2);
        double distanceY = Math.abs(this.positiony - p.getPositiony()) - ((this.size + p.getSize()) / 2);
        return (distanceX <= 0 && distanceY <= 0);
    }
}
