package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntityAnimated;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

import java.util.Collections;
import java.util.List;

public class Suiveur extends MovableAreaEntityAnimated {

    private boolean needToMove;
    private Orientation neededOriantation;
    private int frameForMove=7;


    public Suiveur(Area area, DiscreteCoordinates position, String SPRITE) {
        super(area, position, SPRITE);
    }

    public Suiveur(Area area, DiscreteCoordinates position, String SPRITE, int numberOfSprites) {
        super(area, position, SPRITE, numberOfSprites);
    }
    //todo corriger les fautes
    /*
    Because it his a folower all his attribut regarding moving is public, so the actor that posseses him can control him

     */

    public void setNeededOrientation(Orientation orientation) {
        neededOriantation=orientation;
    }

    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        setCurrentPosition(position.toVector());
        this.resetMotion();
        setOwnerArea(area);
    }



    public void setNeedToMove(boolean needToMove){
        this.needToMove=needToMove;
    }

    public void setFrameForMove(int frameForMove) {
        this.frameForMove = frameForMove;
    }

    @Override
    protected void setCurrentPosition(Vector v) {
        super.setCurrentPosition(v);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        setOrientation(neededOriantation);
        if(!isMoving()){
            if(needToMove) {
                move(frameForMove);
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
