package game;

import java.util.List;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import engine.StdDraw;
import game.actors.*;
import game.utils.SpriteLoader;

/**
 * Gère le score actuel et le highscore du joueur.
 * Sauvegarde le highscore dans le fichier highscore/highscore.sc.
 */
public class Score {

    private int score;
    private int highscore;

    /**
     * Constructeur.
     * Initialise le score à 0 et charge le highscore depuis le fichier.
     */
    public Score() {
        this.score = 0;
        this.highscore = readHighscore();
    }

    /**
     * Lit le highscore depuis le fichier highscore/highscore.sc.
     * Retourne 0 si le fichier n'existe pas ou lecture échouée.
     * 
     * @return highscore lu ou 0 par défaut
     */
    private int readHighscore() {
        List<String> lines = SpriteLoader.loadFile("ressources/highscore/highscore.sc");
        if (lines.isEmpty())
            return 0;
        return Integer.parseInt(lines.get(0));

    }

    /**
     * Ajoute le score d'un ennemi vaincu au score actuel.
     * 
     * @param e l'ennemi vaincu dont on récupère le score
     */
    public void addScore(Enemy e) {
        score += e.getScore();
    }

    /**
     * Sauvegarde le meilleur score dans le fichier si le score actuel est
     * supérieur.
     */
    public void saveHighscore() {
        if (highscore < score) {
            // écriture du highscore dans le fichier CM9
            Path cheminDuFichier = Paths.get("ressources/highscore/highscore.sc");
            try (BufferedWriter writer = Files.newBufferedWriter(cheminDuFichier)) {
                writer.write(score + "\n");
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Retourne le score actuel de la partie.
     * 
     * @return score en cours
     */
    public int getScore() {
        return score;
    }

    /**
     * Retourne le highscore.
     * 
     * @return meilleur score
     */
    public int getHighscore() {
        return highscore;
    }

    /**
     * Reset le score courant et recharge le highscore.
     */
    public void reset() {
        saveHighscore();
        this.highscore = readHighscore();
        this.score = 0;

    }

    /**
     * Affiche le score et le highscore sur l'interface du jeu.
     */
    public void draw() {
        // on dessine score/High score
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(0.15, 0.97, "SCORE");
        StdDraw.text(0.5, 0.97, "HIGH SCORE");

        // on dessine le score/High score en dessous du dessin de score/High score
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.15, 0.92, "" + score);
        StdDraw.text(0.5, 0.92, "" + highscore);
    }
}
