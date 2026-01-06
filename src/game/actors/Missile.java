package game.actors;

import engine.StdDraw;

/**
 * enum pour les directions possibles pour les missiles.
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
    private double width;
    private double heigth;

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
        this.width = 0.001;
        this.heigth = 0.01;
    }

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
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledRectangle(positionx, positiony, width, heigth);
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
    public boolean isHitingEntity(Entity e) {
        double distanceX = Math.abs(this.positionx - e.getPositionx()) - ((this.width + e.getSize()) / 2);
        double distanceY = Math.abs(this.positiony - e.getPositiony()) - ((this.heigth + e.getSize()) / 2);

        if (distanceX <= 0 && distanceY <= 0) {
            return true;
        }
        return false;
    }

}
