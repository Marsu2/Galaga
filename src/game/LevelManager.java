package game;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import engine.StdDraw;
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
    private Color[][] sprite;
    public int nbLevels = 2;

    /**
     * Initialise le gestionnaire de niveaux et charge tous les niveaux.
     * 
     * @param game référence au jeu principal
     */
    public LevelManager(Player player) {
        this.currentLevelIndex = 0;
        this.player = player;
        loadLevels();
    }

    public void update() {
        if (isRoundEnded()) {
            toNextLevel();
        }
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

    public void drawSpriteV2(double positionx, double positiony, double size, String fileName) {
        if (sprite == null) {
            sprite = SpriteLoader.loadSprite(fileName);
        }
        SpriteLoader.drawSprite(sprite, positionx, positiony, size);
    }
}