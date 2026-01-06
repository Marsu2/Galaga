package game;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import engine.StdDraw;
import game.actors.*;

/**
 * Représente un niveau de jeu chargé depuis un fichier.
 * Contient les parametre du niveau, la formation d'ennemis et ses paramètres
 * des monstres.
 */
public class Level {
    private String levelName;
    private double formationSpeed;
    private List<Enemy> enemiesFormation;
    private int attackCooldownMax;
    private int shootCooldown;
    private boolean direction; // false gauche, true droite

    /**
     * Charge un niveau depuis un fichier de configuration.
     * 
     * @param filePath chemin vers le fichier de niveau
     */
    public Level(String filePath) {
        loadLevel(filePath);
        this.direction = true;
    }

    /**
     * Définit le nom du niveau.
     * 
     * @param levelName nouveau nom
     */
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    /**
     * Définit la vitesse de la formation d'ennemis.
     * 
     * @param formationSpeed nouvelle vitesse
     */
    public void setFormationSpeed(double formationSpeed) {
        this.formationSpeed = formationSpeed;
    }

    /**
     * Définit la formation d'ennemis du niveau.
     * 
     * @param enemiesFormation nouvelle liste d'ennemis
     */
    public void setEnemiesFormation(List<Enemy> enemiesFormation) {
        this.enemiesFormation = enemiesFormation;
    }

    /**
     * Définit le délai d'attaque de la formation.
     * 
     * @param attackCooldownMax nouveau cooldown
     */
    public void setattackCooldownMax(int attackCooldownMax) {
        this.attackCooldownMax = attackCooldownMax;
    }

    /**
     * Définit le délai de tir des ennemis.
     * 
     * @param shootCooldown nouveau cooldown
     */
    public void setShootCooldown(int shootCooldown) {
        this.shootCooldown = shootCooldown;
    }

    /**
     * Retourne la vitesse de la formation d'ennemis.
     * 
     * @return vitesse de formation
     */
    public double getFormationSpeed() {
        return formationSpeed;
    }

    /**
     * Retourne le délai d'attaque de la formation.
     * 
     * @return cooldown d'attaque
     */
    public int getattackCooldownMax() {
        return attackCooldownMax;
    }

    /**
     * Retourne le délai de tir des ennemis.
     * 
     * @return cooldown de tir
     */
    public int getShootCooldown() {
        return shootCooldown;
    }

    /**
     * Retourne le nom du niveau.
     * 
     * @return nom du niveau
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * Retourne la liste des ennemis du niveau.
     * 
     * @return formation d'ennemis
     */
    public List<Enemy> getEnemiesFormation() {
        return enemiesFormation;
    }

    /**
     * Charge les données du niveau depuis un fichier texte.
     * 1ere ligne: les informations du niveau
     * 2nde et suivantes: les ennemis et leurs parametres
     */
    private void loadLevel(String filePath) {

        // Lecture d'un fichier vu durant la séance du CM10
        List<String> lines = new ArrayList<>();
        Path file = Paths.get(filePath);
        // Initialisation des ennemis
        enemiesFormation = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while (((line = reader.readLine()) != null)) {
                lines.add(line);
            }
            // Initalisition du niveau
            String firstLine = lines.get(0);
            String[] firstsplit = firstLine.split(" ");
            this.levelName = firstsplit[0];
            this.formationSpeed = Double.parseDouble(firstsplit[1]);
            this.attackCooldownMax = Integer.parseInt(firstsplit[2]);
            this.shootCooldown = Integer.parseInt(firstsplit[3]);

            for (int i = 1; i < lines.size(); i++) {
                String currentLine = lines.get(i);
                String[] split = currentLine.split(" ");

                String type = split[0];
                double positionx = Double.parseDouble(split[1]);
                double positiony = Double.parseDouble(split[2]);
                double size = Double.parseDouble(split[3]);
                int score = Integer.parseInt(split[4]);
                double speed = Double.parseDouble(split[5]);

                switch (type) {
                    case "Moth":
                        enemiesFormation.add(new Moth(positionx, positiony, size, score, 1, speed, shootCooldown));
                        break;
                    case "Butterfly":
                        enemiesFormation.add(new Butterfly(positionx, positiony, size, score, 1, speed, shootCooldown));
                        break;
                    case "Bee":
                        enemiesFormation.add(new Bee(positionx, positiony, size, score, 1, speed, shootCooldown));
                        break;
                    default:
                        enemiesFormation.add(new Boss(positionx, positiony, size, score, 10, speed, shootCooldown));
                        break;
                }

            }

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Reset l'état de tous les ennemis de la formation (position, vie,
     * etc).
     */
    public void resetEnemies() {
        for (Enemy enemy : enemiesFormation) {
            enemy.reset();
        }
    }

    /**
     * Vérifie si tous les ennemis du niveau ont été éliminés.
     * 
     * @return true si la liste d'ennemis ne contient que des ennemis morts ou vide.
     */
    public boolean areAllDead() {
        for (Enemy enemy : enemiesFormation) {
            if (!enemy.isDead()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si la formation atteint les bords de l'écran et inverse la direction.
     */
    private void formationinBound() {
        for (Enemy enemy : enemiesFormation) {
            if (!enemy.isDead()) {
                if (enemy.getPositionx() < 0 || enemy.getPositionx() > 1) {
                    direction = !direction;
                    break;
                }
            }
        }
    }

    /**
     * Déplace la formation horizontalement selon la direction
     * actuelle.
     */
    public void formationMove() {
        formationinBound();
        for (Enemy enemy : enemiesFormation) {
            if (!enemy.isDead() && !enemy.isSoloMode()) {
                if (direction)
                    enemy.setPositionx(enemy.getPositionx() + formationSpeed);
                else {
                    enemy.setPositionx(enemy.getPositionx() - formationSpeed);
                }
            }
        }
    }

    /**
     * Choisis aléatoirement un ennemi éligible (en bas de la formation) pour lancer
     * une attaque en solo.
     * 
     * @return L'ennemi choisi pour attaquer, ou null si aucun n'est disponible.
     */
    public Enemy choseSolo() {
        List<Enemy> validPicks = new ArrayList<>();
        for (Enemy enemy : enemiesFormation) {
            if (!enemy.isDead() && !enemy.isSoloMode() && enemy.botttomClearedSolo(enemiesFormation)) {
                validPicks.add(enemy);
            }
        }
        if (validPicks.isEmpty()) {
            return null;
        }
        Enemy chosen = validPicks.get(new Random().nextInt(validPicks.size()));
        chosen.setSoloMode(true);
        return chosen;
    }

    /**
     * Affiche le nom du niveau au centre de l'écran.
     */
    public void drawLvlName() {
        Font font = new Font("Arial", Font.BOLD, 32);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.5, levelName);
        StdDraw.setFont();
    }
}
