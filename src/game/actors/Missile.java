package game.actors;

import engine.StdDraw;

/**
 * Directions possibles pour les missiles.
 */
enum EDirectionMissile {
    UP, DOWN
}

/**
 * Représente un missile tiré par le joueur ou les ennemis.
 * Gère le mouvement, la détection de collision et le rendu.
 */
public class Missile {
    private double speed;
    private double positiony;
    private double positionx;
    private EDirectionMissile direction;

    /**
     * Retourne la position verticale du missile.
     * 
     * @return coordonnée y
     */
    public double getPositiony() {
        return positiony;
    }

    /**
     * Retourne la position horizontale du missile.
     * 
     * @return coordonnée x
     */
    public double getPositionx() {
        return positionx;
    }

    /**
     * Crée un nouveau missile avec ses paramètres initiaux.
     * 
     * @param speed     vitesse de déplacement
     * @param positionx position horizontale initiale
     * @param positiony position verticale initiale
     * @param direction direction de tir (UP ou DOWN)
     */
    public Missile(double speed, double positionx, double positiony, EDirectionMissile direction) {
        this.speed = speed;
        this.positiony = positiony;
        this.positionx = positionx;
        this.direction = direction;
    }

    /**
     * Met à jour la position du missile selon sa direction.
     */
    public void update() {
        if (direction == EDirectionMissile.UP) {
            positiony += speed;
        } else
            positiony -= speed;
    }

    /**
     * Dessine le missile comme un petit rectangle rouge.
     */
    public void drawSprite() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledRectangle(positionx, positiony, 0.005, 0.01);
    }

    /**
     * Vérifie si le missile est sorti des limites de l'écran.
     * 
     * @return true si positiony on mettre
     */
    public boolean isOutOfBound() {
        return positiony > 1 || positiony < 0;
    }

    /**
     * Vérifie la collision entre ce missile et une entité.
     * 
     * @param e entité à tester (joueur ou ennemi)
     * @return true si collision détectée
     */
    public boolean ishitingEntity(Entity e) {
        double distanceX = this.positionx - e.positionx;
        double distanceY = this.positiony - e.positiony;
        double distance = distanceX * distanceX + distanceY * distanceY;
        double hitboxes = e.size / 2 + 0.01; // taille du missile

        if (distance <= hitboxes * hitboxes) {
            return true;
        }
        return false;
    }

}
