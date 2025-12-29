package game.actors;

import java.util.List;

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
    private boolean isRespawning = false;
    private int respawnTimer = 0;
    private int respawnDuration = 60;

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
        removeMissilesOOB();
        for (Missile missile : missiles) {
            missile.update();
        }

        if (isRespawning) {
            respawnTimer--;
            if (respawnTimer <= 0) {
                isRespawning = false;
            }
            return;
        }
        move();
        shoot();
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
        drawHp();
        drawMissiles();

        if (isRespawning)
            return;

        SpriteLoader.drawSprite(sprite, positionx, positiony, size);

    }

    private void drawHp() {
        for (int i = 0; i < health; i++) {
            SpriteLoader.drawSprite(sprite, 0.05 + i * 0.03, 0.05, 0.05);
        }
        if (isRespawning && health > 0) {
            if (respawnTimer % 10 < 5) {
                SpriteLoader.drawSprite(sprite, 0.05 + health * 0.03, 0.05, 0.05);
            }
        }
    }

    public void reset() {
        resetHP();
        this.missiles.clear();
        this.positionx = 0.5;
        this.positiony = 0.15;
    }

    private void resetHP() {
        this.health = hpMax;
        isRespawning = false;
        respawnTimer = 0;
    }

    public boolean isRespawning() {
        return isRespawning;
    }

    public void setHit() {
        this.positionx = 0.5;
        this.positiony = 0.15;
        this.isRespawning = true;
        this.respawnTimer = respawnDuration;
        this.missiles.clear();
    }

    public boolean checkHitBy(List<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            double distanceX = Math.abs(this.positionx - enemy.getPositionx()) - ((this.size + enemy.getSize()) / 2);
            double distanceY = Math.abs(this.positiony - enemy.getPositiony()) - ((this.size + enemy.getSize()) / 2);
            if (distanceX <= 0 && distanceY <= 0) {
                takeDamage(1);
                enemy.takeDamage(1);
                return true;
            }
            for (Missile m : enemy.getMissiles()) {
                if (m.isHitingEntity(this)) {
                    takeDamage(1); // perde 1 HP
                    return true;
                }
            }
        }
        return false;
    }

}
