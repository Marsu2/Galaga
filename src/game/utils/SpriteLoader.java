package game.utils;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import engine.StdDraw;

public class SpriteLoader {

    /**
     * Charge un sprite depuis un fichier .spr et le convertit en matrice de
     * Color.
     *
     * @param fileName le chemin du fichier contenant le sprite
     * @return une matrice de couleurs représentant le sprite
     */
    public static Color[][] loadSprite(String fileName) {
        List<String> lines = loadFile(fileName);
        if (lines.isEmpty()) {
            return new Color[0][0];
        }
        Color[][] res = new Color[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                res[i][j] = decodeColor(lines.get(i).charAt(j));
            }
        }
        return res;
    }

    /**
     * Lit le contenu complet d'un fichier .spr ligne par ligne.
     *
     * @param fileName le chemin du fichier à lire
     * @return une liste contenant toutes les lignes du fichier
     */
    public static List<String> loadFile(String fileName) {
        // Lecture d'un fichier vu durant la séance du CM9
        List<String> lines = new ArrayList<>();
        Path p = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(p)) {
            String line;
            while (((line = reader.readLine()) != null)) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return lines;
    }

    /**
     * Convertit un code caractère en objet Color.
     *
     * @param c le caractère représentant la couleur ('R' pour Rouge)
     * @return la couleur correspondante ou Noir par défaut
     */
    private static Color decodeColor(char c) {
        switch (c) {
            case 'R':
                return StdDraw.RED;
            case 'G':
                return StdDraw.GREEN;
            case 'B':
                return StdDraw.BLUE;
            case 'Y':
                return StdDraw.YELLOW;
            case 'W':
                return StdDraw.WHITE;
            case 'V':
                return new Color(128, 0, 128); // Violet
            default:
                return StdDraw.BLACK;
        }
    }

    /**
     * Dessine un sprite à l'écran à une position donnée.
     *
     * @param sprite    la matrice de couleurs à dessiner
     * @param positionx la position horizontale du centre du sprite
     * @param positiony la position verticale du centre du sprite
     * @param size      la taille d'affichage du sprite
     */
    public static void drawSprite(Color[][] sprite, double positionx, double positiony, double size) {
        double height = sprite.length;
        double width = sprite[0].length;

        double pSize = size / width;
        double startX = positionx - (size / 2);
        double startY = positiony + (size / 2);

        for (int row = 0; row < height; row++) {
            Color[] line = sprite[row];
            for (int column = 0; column < line.length; column++) {
                Color c = line[column];
                if (c != null) {
                    // Position du pixel que lon va dessiner
                    double px = startX + (column * pSize) + (pSize / 2);
                    double py = startY - (row * pSize) - (pSize / 2);

                    StdDraw.setPenColor(c);
                    StdDraw.filledSquare(px, py, (pSize / 2) * 1.1); // *1.1 pour eviter les trous dans les sprites
                }
            }
        }
    }

}
