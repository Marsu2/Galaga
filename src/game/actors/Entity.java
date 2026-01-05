package game.actors;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;

/**
 * Classe abstraite mère de toutes les entités du jeu (joueur, ennemis).
 * Gère les propriétés communes.
 */
public abstract class Entity {
    protected double positionx;
    protected double positiony;
    protected double size;
    protected int health;
    protected double speed;
    protected List<Missile> missiles;
    protected Color[][] sprite;

    /**
     * Crée une nouvelle entité avec ses propriétés de base.
     * 
     * @param positionx position horizontale initiale
     * @param positiony position verticale initiale
     * @param size      taille de l'entité
     * @param health    points de vie initiaux
     * @param speed     vitesse de déplacement
     */
    public Entity(double positionx, double positiony, double size, int health, double speed) {
        this.positionx = positionx;
        this.positiony = positiony;
        this.size = size;
        this.health = health;
        this.speed = speed;
        this.missiles = new ArrayList<>();

    }

    /**
     * Retourne la taille de l'entité.
     *
     * @return la taille de l'entité
     */
    public double getSize() {
        return size;
    }

    /**
     * Définit la positionX de l'entité.
     *
     * @param positionx la nouvelle coordonnée x
     */
    public void setPositionx(double positionx) {
        this.positionx = positionx;
    }

    /**
     * Retourne la liste des missiles de l'entité.
     *
     * @return la liste des missiles
     */

    public List<Missile> getMissiles() {
        return missiles;
    }

    /**
     * Définit la liste des missiles de l'entité.
     *
     * @param missiles la nouvelle liste de missiles
     */
    public void setMissiles(List<Missile> missiles) {
        this.missiles = missiles;
    }

    /**
     * Retourne les points de vie actuels.
     * 
     * @return points de vie restants
     */

    public int getHealth() {
        return health;
    }

    /**
     * Met à jour les points de vie (jamais en négatif).
     * 
     * @param health nouveaux points de vie
     */
    public void setHealth(int health) {
        this.health = Math.max(0, health);
    }

    /**
     * Retourne la position X.
     * 
     * @return coordonnée x
     */
    public double getPositionx() {
        return positionx;
    }

    /**
     * Retourne la position Y.
     * 
     * @return coordonnée y
     */
    public double getPositiony() {
        return positiony;
    }

    /**
     * Dessine le sprite spécifique de l'entité.
     */
    public abstract void draw();

    /**
     * Met à jour l'état de l'entité (mouvement, tirs, etc.).
     */
    public abstract void update();

    /**
     * Déplace l'entité selon sa logique.
     */
    public abstract void move();

    /**
     * Fait tirer l'entité.
     */
    public abstract void shoot();

    /**
     * Vérifie si l'entité est morte.
     * 
     * @return true si health est inférieur ou égal à 0
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Dessine tous les missiles tirés par cette entité.
     */
    protected void drawMissiles() {
        for (Missile missile : missiles) {
            missile.drawSprite();
        }
    }

    /**
     * Inflige des dégâts à l'entité sans dépasser 0.
     *
     * @param damage le montant des dégâts à soustraire aux points de vie
     */
    protected void takeDamage(int damage) {
        this.health = Math.max(0, this.health - damage);
    }

    /**
     * Supprime les missiles qui sont sortis des limites de l'écran.
     */
    protected void removeMissilesOOB() {
        List<Missile> rmMissiles = new ArrayList<>();
        for (Missile missile : missiles) {
            if (missile.isOutOfBound()) {
                rmMissiles.add(missile);
            }
        }
        missiles.removeAll(rmMissiles);
    }

    /**
     * Supprime tous les missiles de l'entité.
     */
    public void removeAllMissiles() {
        missiles.clear();
    }

}
