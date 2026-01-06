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
    private boolean isRespawning;
    private int respawnTimer;
    private int respawnDuration;

    /**
     * Crée un nouveau joueur avec ses paramètres initiaux.
     * 
     * @param positionx position horizontale initiale
     * @param positiony position verticale initiale
     * @param size      taille du vaisseau
     * @param health    points de vie initiaux
     * @param speed     vitesse de déplacement horizontal
     */
    public Player(double positionx, double positiony, double size, int health, double speed) {
        super(positionx, positiony, size, health, speed);
        coolDownShoot = 0;
        coolDownShootMax = 6;
        hpMax = health;
        respawnTimer = 0;
        respawnDuration = 60;
        isRespawning = false;
    }

    /**
     * Vérifie si le joueur est en phase de réapparition (invulnérable/invisible).
     *
     * @return true si le joueur est en train de réapparaître
     */
    public boolean isRespawning() {
        return isRespawning;
    }

    /**
     * Met à jour le joueur : mouvement missiles et respawn.
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
     * Respecte les limites de l'ecran.
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
     * Limite à 3 missiles simultanes et respecte le cooldown.
     * 
     * @return true s'il peut tirer
     */
    public boolean canShoot() {
        return missiles.size() < 3 && coolDownShoot == 0;
    }

    /**
     * Gère le tir du joueur avec la barre espace.
     * Créer un missile qui monte avec gestion du cooldown.
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

    /**
     * Dessine les points de vie restants du joueur à l'écran.
     */
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

    /**
     * Reset la position, les HP et les missiles du joueur.
     */
    public void reset() {
        resetHP();
        removeAllMissiles();
        resetPosition();
    }

    /**
     * Reset les points de vie au maximum et annule le statut de
     * réapparition.
     */
    private void resetHP() {
        this.health = hpMax;
        isRespawning = false;
        respawnTimer = 0;
    }

    /**
     * Reset la position du joueur au centre bas de l'écran.
     */
    public void resetPosition() {
        this.positionx = 0.5;
        this.positiony = 0.15;
    }

    /**
     * Inflige des dégâts au joueur et commence le systeme de respawn.
     *
     * @param damage le montant de dégâts reçus
     */
    public void setHit(int damage) {
        takeDamage(damage);
        resetPosition();
        this.isRespawning = true;
        this.respawnTimer = respawnDuration;
        removeAllMissiles();
    }

    /**
     * Vérifie si le joueur est touché par l'un des ennemis ou leurs projectiles.
     *
     * @param enemies la liste des ennemis actifs
     * @return true si le player est touché
     */
    public boolean checkHitBy(List<Enemy> enemies) {
        for (Enemy enemy : enemies) {

            if (enemy.isColliding(this)) {
                enemy.takeDamage(1);
                return true;
            }
            for (Missile m : enemy.getMissiles()) {
                if (m.isHitingEntity(this)) {
                    return true;
                }
            }
        }
        return false;
    }

}
