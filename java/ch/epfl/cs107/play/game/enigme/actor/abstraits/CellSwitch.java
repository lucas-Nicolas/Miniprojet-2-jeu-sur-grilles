package ch.epfl.cs107.play.game.enigme.actor.abstraits;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract  class CellSwitch extends Switch {

    public CellSwitch(Area area, DiscreteCoordinates position) {
        super(area, position, false);
    }


    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }
}

