package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public abstract class Collectable extends AreaEntity {
    private boolean isCollected;
    private Logic key;

    public Collectable(Area area, DiscreteCoordinates position) {
        super(area, position);
    }

    public Collectable(Area area, DiscreteCoordinates position, Safe key) {
        super(area, position);
        this.key = key;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return key.isOn();
    }


    @Override
    public boolean isViewInteractable() {
        return key.isOn();
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }


    protected void setIsCollected(boolean bool){
        isCollected = bool;
    }

    protected boolean isCollected(){
        return isCollected;
    }

    @Override
    public void update(float deltaTime) {

        if(isCollected){
            getOwnerArea().unregisterActor(this);
        }
    }

    protected Logic getKey() {
        return key;
    }

}
