package game.actors;

import engine.StdDraw;

enum EDirectionMissile {
    UP, DOWN
}

public class Missile {
    private double speed;
    private double positiony;

    public double getPositiony() {
        return positiony;
    }

    public double getPositionx() {
        return positionx;
    }

    private double positionx;
    private EDirectionMissile direction;

    public Missile(double speed, double positiony, double positionx, EDirectionMissile direction) {
        this.speed = speed;
        this.positiony = positiony;
        this.positionx = positionx;
        this.direction = direction;
    }

    public boolean hit(Entity e) {
        return e.getPositiony() == this.positiony;
    }

    public void update() {
        if (direction == EDirectionMissile.UP) {
            positiony += speed;
        } else
            positiony -= speed;
    }

    public void drawSprite() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledRectangle(positionx, positiony, 0.005, 0.01);
    }

    public boolean isOutOfBound() {
        return positiony > 1 || positiony < 0;
    }

    public boolean hitEntity(Entity e) {
        if (((positiony - e.getPositiony()) < 0.001) && ((positionx - e.getPositionx()) < 0.001)) {
            e.setHealth(e.getHealth() - 1);
            return true;
        }
        return false;
    }

}
