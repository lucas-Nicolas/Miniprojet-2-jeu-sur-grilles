package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class CellSwitch extends Switch {
    private boolean switchedOnPreviousFrame;
    private boolean canBeSwitched;


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
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(switchedOnPreviousFrame){
            canBeSwitched = false;

        }else {
            canBeSwitched = true;
        }
        switchedOnPreviousFrame = false;

    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        if(canBeSwitched) {
            super.acceptInteraction(v);
        }
        switchedOnPreviousFrame = true;

    }

}
