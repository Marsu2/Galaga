package game.actors;

import engine.StdDraw;
import game.Game;

public class Bee extends Enemy {

    public Bee(double positionx, double positiony, double size, double speed, Game game) {
        super(positionx, positiony,size, speed, game);
    }

    public void drawSprite() {
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(positionx, positiony, size / 2);
    }
    
}
