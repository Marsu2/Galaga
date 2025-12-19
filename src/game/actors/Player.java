package game.actors;

import engine.StdDraw;
import game.utils.SpriteLoader;

/**
 * Représente le joueur contrôlé par l'utilisateur.
 * Se déplace horizontalement avec les flèches et tire avec la barre espace.
 */
public class Player extends Entity {
    private int coolDownShoot;
    private int coolDownShootMax;
    private int hpMax;

    /**
     * Crée un nouveau joueur avec ses paramètres initiaux.
     * 
     * @param positionx position horizontale initiale
     * @param positiony position verticale initiale
     * @param size      taille du vaisseau
     * @param health    points de vie initiaux
     * @param speed     vitesse de déplacement horizontal
     * @param game      référence au jeu principal
     */
    public Player(double positionx, double positiony, double size, int health, double speed) {
        super(positionx, positiony, size, health, speed);
        coolDownShoot = 0;
        coolDownShootMax = 6;
        hpMax = health;
    }

    /**
     * Met à jour le joueur : mouvement et gestion de saveHighscore();s tirs.
     */
    public void update() {
        move();
        shoot();
        removeMissilesOOB();
        for (Missile missile : missiles) {
            missile.update();
        }

        if (coolDownShoot > 0) {
            coolDownShoot--;
        }
    }

    /**
     * Déplace le joueur horizontalement avec les flèches gauche/droite.
     * Respecte les limites de l'écran (0 à 1).
     */
    public void move() {
        // Si la flèche gauche est pressé
        if (StdDraw.isKeyPressed(37)) {
            if (positionx > 0) {
                positionx -= speed;
            }
        }
        // Si la flèche droite est pressé
        if (StdDraw.isKeyPressed(39)) {
            if (positionx < 1) {
                positionx += speed;
            }
        }
    }

    /**
     * Vérifie si le joueur peut tirer.
     * Limite à 3 missiles simultanés et respecte le cooldown.
     * 
     * @return true si autorisé à tirer
     */
    public boolean canShoot() {
        return missiles.size() < 3 && coolDownShoot == 0;
    }

    /**
     * Gère le tir du joueur avec la barre espace (code 32).
     * Crée un missile montant avec cooldown.
     */
    public void shoot() {
        if (StdDraw.isKeyPressed(32) && canShoot()) {
            coolDownShoot = coolDownShootMax;
            Missile m1 = new Missile(speed * 2, positionx, positiony + size / 2, EDirectionMissile.UP);
            missiles.add(m1);
        }

    }

    /**
     * Dessine le sprite du vaisseau joueur depuis le fichier ship.spr.
     */
    public void draw() {
        if (sprite == null) {
            sprite = SpriteLoader.loadSprite("ressources/sprites/ship.spr");
        }
        SpriteLoader.drawSprite(sprite, positionx, positiony, size);
        drawMissiles();
        drawHp();
    }

    private void drawHp() {
        for (int i = 0; i < health; i++) {
            SpriteLoader.drawSprite(sprite, 0.05 + i * 0.03, 0.05, 0.05);
        }
    }

    public void resetHP() {
        this.health = hpMax;
    }

}
