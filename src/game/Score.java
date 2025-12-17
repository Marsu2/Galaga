package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import game.actors.*;

/**
 * Gère le score actuel et le highscore persistant du joueur.
 * Sauvegarde le highscore dans le fichier highscore/highscore.sc.
 */
public class Score {
    /**
     * @param: score, highscore
     * 
     **/
    private int score;
    private int highscore;

    /**
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
        Path cheminDuFichier = Paths.get("ressources/highscore/highscore.sc");
        try (BufferedReader reader = Files.newBufferedReader(cheminDuFichier)) {
            String line = null;
            while (((line = reader.readLine()) != null)) {
                return Integer.parseInt(line);
            }
        } catch (IOException e) {
        }
        return 0;

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
     * Sauvegarde le meilleur score dans le fichier.
     * 
     *
     */

    public void saveHighscore() {
        if (highscore < score) {
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
     * Retourne le highscore persistant.
     * 
     * @return meilleur score historique
     */
    public int getHighscore() {
        return highscore;
    }
}
