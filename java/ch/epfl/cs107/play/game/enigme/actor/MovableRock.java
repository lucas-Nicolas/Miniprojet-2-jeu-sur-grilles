package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class MovableRock extends MovableAreaEntity implements Interactor {
   private class MovableRockHandler implements EnigmeInteractionVisitor{

       public void interactWith(PressurePlate plate) {
           System.out.println("sa marche");
           plate.setBlocked(true);
           plate.setIsActivated();

       }
   }

   private final MovableRockHandler handler;
   private final Sprite MOVABLE_ROCK = new Sprite("rock.1",1,1,this);

    public MovableRock(Area area, DiscreteCoordinates position) {
        super(area, position);
        handler = new MovableRockHandler();
    }


    @Override
    public void draw(Canvas canvas) {
        MOVABLE_ROCK.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
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

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);

    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return null;
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }


    @Override
    public boolean wantsViewInteraction() {
        return false;
    }

    @Override
    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);

    }

    @Override
    public boolean move(int framesForMove) {
        return super.move(framesForMove);
    }

    @Override
    public void setOrientation(Orientation orientation) {
        super.setOrientation(orientation);
    }
}
