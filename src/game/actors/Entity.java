package game.actors;

import java.util.List;
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

    public void drawSprite() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(positionx, positiony, size / 2);
        try {
            // List<String> lines =
            // Files.readAllLines(Paths.get("ressources/sprites/catcher.spr"));
            // for (String line : lines) {
            // System.out.println(line);
            // }

        } catch (IllegalArgumentException ie) {
        }
    }

    public abstract void update();

    public abstract void move();

    public abstract void shoot();

    public boolean isDead() {
        return health <= 0;
    }

}
