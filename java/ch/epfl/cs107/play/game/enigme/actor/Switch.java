package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

import java.util.Collections;
import java.util.List;

public abstract class Switch extends AreaEntity implements Logic {
    boolean isActivated;

    public Switch(Area area, DiscreteCoordinates position, boolean isActivated) {
        super(area, position);
        this.isActivated = isActivated;
    }
    protected boolean isActivated(){
        return isActivated;
    }
    protected void setIsActivated(){
        isActivated = !isActivated;
    }
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }

    @Override
    public boolean isOn() {
        return isActivated;
    }


}
