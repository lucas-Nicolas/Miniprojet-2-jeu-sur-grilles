package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.LinkedList;
import java.util.List;

public abstract class Collectable extends AreaEntity implements Interactable {
    private boolean isCollected;
    public Collectable(Area area, DiscreteCoordinates position) {
        super(area, position);
        isCollected = false;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        List<DiscreteCoordinates> currentCells = new LinkedList<>();
        currentCells.add(getCurrentMainCellCoordinates());
        return currentCells;
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {

    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {

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
}
