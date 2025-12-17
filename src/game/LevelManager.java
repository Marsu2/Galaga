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
    private Color[][] spriteHP;
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

    public void draw(){
        
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
        
        //draw le nombre de lvl passé
        drawNbLvl(0.95, 0.07, 0.03, "ressources/sprites/level.spr");
        
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

    public void drawNbLvl(double positionx, double positiony, double size, String fileName) {
        if (spriteHP == null) {
            spriteHP = SpriteLoader.loadSprite(fileName);
        }
        SpriteLoader.drawSprite(spriteHP, positionx, positiony, size);
    }
}