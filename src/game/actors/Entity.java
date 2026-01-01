package game.actors;

import java.util.ArrayList;
import java.util.List;

import java.awt.Color;

/**
 * Classe abstraite mère de toutes les entités du jeu (joueur, ennemis).
 * Gère les propriétés communes et le rendu de sprites pixelisés.
 */
public abstract class Entity {
    public double getSize() {
        return size;
    }

    protected double positionx;
    public void setPositionx(double positionx) {
        this.positionx = positionx;
    }

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
     * @param game      référence au jeu principal
     */
    public Entity(double positionx, double positiony, double size, int health, double speed) {
        this.positionx = positionx;
        this.positiony = positiony;
        this.size = size;
        this.health = health;
        this.speed = speed;
        this.missiles = new ArrayList<>();

    }

    public List<Missile> getMissiles() {
        return missiles;
    }

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
     * Retourne la position horizontale.
     * 
     * @return coordonnée x
     */
    public double getPositionx() {
        return positionx;
    }

    /**
     * Retourne la position verticale.
     * 
     * @return coordonnée y
     */
    public double getPositiony() {
        return positiony;
    }

    /**
     * Dessine le sprite spécifique de l'entité (à implémenter).
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
     * @return true si health inferirieure a 0 ce quyi pose probleme
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Convertit un caractère en couleur pour les sprites pixelisés.
     * 
     * @param c caractère représentant la couleur (R,G,B,Y,W)
     * @return couleur correspondante
     */

    /**
     * Vérifie si l'entité peut tirer (à implémenter).
     * 
     * @return true si autorisé à tirer
     */

    protected void drawMissiles() {
        for (Missile missile : missiles) {
            missile.drawSprite();
        }
    }

    protected void takeDamage(int damage) {
        this.health = Math.max(0, this.health - damage);
    }

    protected void removeMissilesOOB() {
        List<Missile> rmMissiles = new ArrayList<>();
        for (Missile missile : missiles) {
            if (missile.isOutOfBound()) {
                rmMissiles.add(missile);
            }
        }
        missiles.removeAll(rmMissiles);
    }



    public void removeAllMissiles() {
        missiles.clear();
    }


}
