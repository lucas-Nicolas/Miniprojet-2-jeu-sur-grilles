package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Lever extends ViewSwitch {
    private Sprite on = new Sprite("lever.big.left", 1, 1.f, this);
    private Sprite off = new Sprite("lever.big.right", 1, 1.f, this);

    public Lever(Area area, DiscreteCoordinates position) {
        super(area, position, false);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(on,off,canvas);
    }
}
