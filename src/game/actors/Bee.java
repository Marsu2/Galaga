package game.actors;

import engine.StdDraw;
import game.Game;

public class Bee extends Enemy {

    public Bee(double positionx, double positiony, double size, int score, double speed, Game game) {
        super(positionx, positiony, size, score, speed, game);
    }

    public void drawSprite() {
        super.drawSpriteV2(positionx, positiony, size, "ressources/sprites/bee.spr");
    }
    
    
}
