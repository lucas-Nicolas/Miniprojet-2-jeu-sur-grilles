package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntityAnimated;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class Suiveur extends MovableAreaEntityAnimated {

    private boolean needToMove;
    private Orientation neededOriantation;


    public Suiveur(Area area, DiscreteCoordinates position, String SPRITE) {
        super(area, position, SPRITE);
    }
//todo corriger les fautes
    /*
    Because it has to folow someone all his attribut regarding moving is public

     */

    public void setNeededOrientation(Orientation orientation) {
        neededOriantation=orientation;
    }

    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        setCurrentPosition(position.toVector());
        this.resetMotion();
        getOwnerArea().unregisterActor(this);
        setOwnerArea(area);
    }

    @Override
    public DiscreteCoordinates getCurrentMainCellCoordinates() {
        return super.getCurrentMainCellCoordinates();
    }

    public void setNeedToMove(boolean needToMove){
        this.needToMove=needToMove;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(!isMoving()){
            setOrientation(neededOriantation);
            if(needToMove) {
                move();
            }
            needToMove=false;


        }

    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
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
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {

    }
}
