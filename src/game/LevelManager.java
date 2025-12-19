package game;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import engine.StdDraw;
import game.actors.Enemy;
import game.actors.Player;
import game.utils.SpriteLoader;

/**
 * Gère le chargement et la progression entre les niveaux du jeu.
 * Charge dynamiquement les niveaux depuis des fichiers .lvl.
 */
public class LevelManager {
    private Level[] levels;
    private int currentLevelIndex;
    private Player player;
    private Color[][] spriteLvl;
    public int nbLevels = 3;
    private Score score;
    private List<Enemy> enemies;

    /**
     * Initialise le gestionnaire de niveaux et charge tous les niveaux.
     * 
     * @param game référence au jeu principal
     */
    public LevelManager(Player player, Score score) {
        this.currentLevelIndex = 0;
        this.player = player;
        this.score = score;
        loadLevels();
        this.enemies = getCurrentLevel().getEnemiesFormation();
    }

    public void update() {
        playerGetHit();
        List<Enemy> enemiesRemove = new ArrayList<>();
        for (Enemy e : enemies) {
            e.update();
            if (e.canShoot(enemies)) {
                e.shoot();
            }
            e.checkHitBy(player);
            if (e.isDead()) {
                enemiesRemove.add(e);
                score.addScore(e);
            }
        }

        enemies.removeAll(enemiesRemove);

        if (isRoundEnded() && !player.isDead()) {
            toNextLevel();
            enemies = getCurrentLevel().getEnemiesFormation();
        }
    }

    public void draw() {
        for (Enemy e : enemies) {
            e.draw();
        }

        // Dessin de la zone d'information
        StdDraw.setPenColor(StdDraw.WHITE);
        // Position du pixel de départ pour la ligne du bas
        double downPx = 0;
        double downPy = 0.1;
        // Position du pixel d'arrivé pour la ligne du bas
        double downPxEnd = 700;
        double downPyEnd = 0.1;

        // On dessine le pixel en fonction de la position
        StdDraw.line(downPx, downPy, downPxEnd, downPyEnd);

        // draw le nombre de lvl passé
        drawLvlPassed(0.95, 0.07, 0.03, "ressources/sprites/level.spr");

        // Dessin de la zone de score

        // Position du pixel de départ pour la ligne du haut
        double topPx = 0;
        double topPy = 0.9;
        // Position du pixel d'arrivé pour la ligne du haut
        double topPxEnd = 700;
        double topPyEnd = 0.9;

        // On dessine le pixel en fonction de la position
        StdDraw.line(topPx, topPy, topPxEnd, topPyEnd);

    }

    /**
     * Retourne le niveau actuellement actif.
     * 
     * @return niveau courant ou null si aucun niveau
     */
    public Level getCurrentLevel() {
        if (levels.length == 0) {
            return null;
        }
        return levels[currentLevelIndex];
    }

    /**
     * Charge tous les niveaux depuis les fichiers ressources/levels/levelX.lvl.
     * 
     * @param game référence au jeu principal
     */
    private void loadLevels() {
        this.levels = new Level[nbLevels];
        for (int i = 0; i < nbLevels; i++) {
            String filePath = "ressources/levels/level" + (i + 1) + ".lvl";
            this.levels[i] = new Level(filePath);
        }
    }

    /**
     * Passe au niveau suivant si disponible.
     * Ignore la demande si déjà au dernier niveau.
     */
    public void toNextLevel() {
        if (currentLevelIndex < nbLevels - 1) {
            currentLevelIndex++;
        }
    }

    public boolean isRoundEnded() {
        return getCurrentLevel().areAllDead();
    }

    private void drawLvlPassed(double positionx, double positiony, double size, String fileName) {
        for (int i = 0; i < currentLevelIndex; i++) {
            double tempx = positionx - i * 0.04;
            drawLvl(tempx, positiony, size, fileName);
        }
    }

    private void drawLvl(double positionx, double positiony, double size, String fileName) {
        if (spriteLvl == null) {
            spriteLvl = SpriteLoader.loadSprite(fileName);
        }
        SpriteLoader.drawSprite(spriteLvl, positionx, positiony, size);
    }

    public boolean winGame() {
        return currentLevelIndex == nbLevels - 1 && isRoundEnded();
    }

    public Boolean isGameOver() {
        return player.isDead();
    }

    public void drawGameOver() {
        StdDraw.setPenColor(StdDraw.RED);
        // Position du pixel de départ pour la ligne du bas
        double Px = 0.5;
        double Py = 0.5;

        //
        Font font = new Font("Arial", Font.BOLD, 32);
        StdDraw.setFont(font);
        StdDraw.text(Px, Py, "Game Over", 0.2);
        StdDraw.setFont();
        StdDraw.text(Px - 0.1, Py - 0.1, "Score", 0.2);
        StdDraw.text(Px, Py - 0.1, "" + score.getScore(), 0.2);
    }

    public void reset() {
        loadLevels();
        this.currentLevelIndex = 0;
        player.resetHP();
        score.reset();
        enemies = getCurrentLevel().getEnemiesFormation();

    }

    public void clear() {
        for (int i = 0; i < levels.length; i++) {
            levels[i].setEnemiesFormation(new ArrayList<>());
            enemies = levels[i].getEnemiesFormation();
        }
    }

    private void playerGetHit() {
        if (player.checkHitBy(getCurrentLevel().getEnemiesFormation())) {
            getCurrentLevel().resetEnemies();
        }
    }

}