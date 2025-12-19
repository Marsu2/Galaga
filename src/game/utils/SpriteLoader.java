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

    public static Color[][] loadSprite(String fileName) {
        List<String> lines = loadFile(fileName);
        Color[][] res = new Color[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                res[i][j] = decodeColor(lines.get(i).charAt(j));
            }
        }
        return res;
    }

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
            case 'N':
                return StdDraw.BLACK;
            default:
                return null;
        }
    }

    public static void drawSprite(Color[][] sprite, double positionx, double positiony, double size) {
        double height = sprite.length;
        double width = sprite[0].length;

        double pixelSize = size / width;
        double startX = positionx - (size / 2);
        double startY = positiony + (size / 2);

        for (int row = 0; row < height; row++) {
            Color[] currentLine = sprite[row];
            for (int column = 0; column < currentLine.length; column++) {
                Color c = currentLine[column];
                if (c != null) {
                    // Position du pixel que lon va dessiner
                    double px = startX + (column * pixelSize) + (pixelSize / 2);
                    double py = startY - (row * pixelSize) - (pixelSize / 2);

                    StdDraw.setPenColor(c);
                    StdDraw.filledSquare(px, py, (pixelSize / 2) * 1.1); // *1.1 pour eviter les trous dans les sprites
                }
            }
        }
    }
}
