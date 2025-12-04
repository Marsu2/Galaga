package game.actors;

import engine.StdDraw;

public abstract class Entity {
    protected double positionx;
    protected double positiony;
    protected double size;
    protected int health;
    protected double speed;

    public Entity(double positionx, double positiony, double size, int health, double speed) {
        this.positionx = positionx;
        this.positiony = positiony;
        this.size = size;
        this.health = health;
        this.speed = speed;

    }

    public void drawSprite() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(positionx, positiony, size / 2);
    }

    public abstract void update();

    public abstract void move();

    public abstract void shoot();

}
