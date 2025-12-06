package game.actors;

import engine.StdDraw;
import game.Game;

public class Moth extends Enemy {

    public Moth(double positionx, double positiony, double size, double speed, Game game) {
        super(positionx, positiony, size, speed, game);
    }

    public void drawSprite() {
        super.drawSpriteV2(positionx, positiony, size, "ressources/sprites/catcher.spr");
    }

}
