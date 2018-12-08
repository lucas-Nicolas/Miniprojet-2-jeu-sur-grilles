package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.abstraits.Switch;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Lever extends Switch {
    private Sprite on = new Sprite("lever.big.left", 1, 1.f, this);
    private Sprite off = new Sprite("lever.big.righ",1,1.f, this);
    public Lever(Area area, DiscreteCoordinates position) {
        super(area, position, false);
    }



    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }
}
