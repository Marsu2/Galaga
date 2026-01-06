package game.actors;

import game.utils.SpriteLoader;

/**
 * Sous class de enemy pour MOTH qui ca pouvoir attaquer
 **/
public class Moth extends Enemy {
    private Bee capturedBee = null;
    private boolean hasCaptured = false;

    /**
     * Initialise un nouveau papillon de nuit ennemi.
     * 
     * @param positionx     position horizontale initiale
     * @param positiony     position verticale initiale
     * @param size          taille de l'entité
     * @param score         points attribués à la destruction
     * @param speed         vitesse de déplacement
     * @param health        points de vie initiaux
     * @param shootCooldown temps de recharge initial du tir
     */
    public Moth(double positionx, double positiony, double size, int score, int health, double speed,
            int shootCooldown) {
        super(positionx, positiony, size, score, health, speed, shootCooldown);
    }

    /**
     * Indique s'il a capturé le joueur.
     * * @return true si une capture est active, false sinon.
     */
    public boolean hasCaptured() {
        return hasCaptured;
    }

    /**
     * Dessine le sprite personnalisé du papillon de nuit.
     */
    public void draw() {
        if (sprite == null) {
            sprite = SpriteLoader.loadSprite("ressources/sprites/catcher.spr");
        }
        SpriteLoader.drawSprite(sprite, positionx, positiony, size);
        drawMissiles();
    }

    /**
     * Gère le déplacement si en soloMode alors piqué vers le bas.
     */
    public void move() {
        if (soloMode) {
            positiony -= speed;
        }
    }

    /**
     * Tente de capturer le joueur et génère une abeille à sa place.
     * 
     * @param player Le joueur cible de la capture.
     * @return L'ennemi de type Bee créé (le vaisseau capturé), ou null si la
     *         capture échoue.
     */
    public Bee capture(Player player) {
        if (!hasCaptured) {
            capturedBee = new Bee(positionx, positiony - (size * 1.2), size, 0, 1, speed * 2, 100);

            capturedBee.initialPositionX = positionx;
            capturedBee.initialPositionY = positiony - (size * 1.2);
            hasCaptured = true;
            return capturedBee;
        }
        return null;
    }

    /**
     * Libère le vaisseau capturé (rend une vie au joueur) si le Moth est tué.
     * 
     * @param player Le joueur à qui rendre la vie.
     */
    public void releaseCapture(Player player) {
        if (hasCaptured && capturedBee != null) {
            player.setHealth(player.getHealth() + 1);
            this.hasCaptured = false;
            this.capturedBee = null;
        }
    }

    /**
     * Vérifie si le joueur est suffisamment proche pour déclencher une capture.
     * 
     * @param p Le joueur à tester.
     * @return true si le joueur est à portée, false sinon.
     */
    public boolean isNear(Player p) {
        double distanceX = Math.abs(this.positionx - p.positionx);
        double distanceY = Math.abs(this.positiony - p.positiony);
        return (distanceX < (size + p.size) / 2 && distanceY < size * 2);
    }

}
