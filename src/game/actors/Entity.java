package game.actors;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.awt.Color;

import engine.StdDraw;
import game.Game;

public abstract class Entity {
    protected double positionx;
    protected double positiony;
    protected double size;
    protected int health;
    protected double speed;
    protected Game game;

    public Entity(double positionx, double positiony, double size, int health, double speed, Game game) {
        this.positionx = positionx;
        this.positiony = positiony;
        this.size = size;
        this.health = health;
        this.speed = speed;
        this.game = game;

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, health);
    }

    public double getPositionx() {
        return positionx;
    }

    public double getPositiony() {
        return positiony;
    }

    public abstract void drawSprite();

    public abstract void update();

    public abstract void move();

    public abstract void shoot();

    public boolean isDead() {
        return health <= 0;
    }

    public void drawSpriteV2(double positionx, double positiony, double size, String fileName) {
        // Lecture d'un fichier vu durant la séance du CM10
        Path p = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(p)) {
            List<String> lines = new ArrayList<>();
            String line;
            while (((line = reader.readLine()) != null)) {
                lines.add(line);
            }
            double height = lines.size();
            double width = lines.get(0).length();

            double pixelSize = size / width;
            double startX = positionx - (size / 2);
            double startY = positiony + (size / 2);

            for (int row = 0; row < lines.size(); row++) {
                String currentLine = lines.get(row);
                for (int column = 0; column < currentLine.length(); column++) {
                    char c = currentLine.charAt(column);
                    Color color = decodeColor(c);
                    // Position du pixel actuel en se basant sur le point de départ
                    double px = startX + (column * pixelSize) + (pixelSize / 2);
                    double py = startY - (row * pixelSize) - (pixelSize / 2);

                    // On dessine kle pixel en fonction de la position
                    StdDraw.setPenColor(color);
                    StdDraw.filledSquare(px, py, pixelSize / 2);

                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private Color decodeColor(char c) {
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
            default:
                return StdDraw.BLACK;
        }
    }

    public abstract boolean canShoot();

}
