package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class EnigmePlayer extends MovableAreaEntity implements Interactor  {
    private class EnigmePlayerHandler implements EnigmeInteractionVisitor {
        @Override
        public void interactWith(Door door){
            //Fait en sorte que le joueur passe la porte
        passedDoor(door);
        }

        @Override
        public void interactWith(Apple apple){
            //fait en sorte que la pomme soit ramassée
            apple.setIsCollected(true);
        }

        @Override
        public void interactWith(Key key) {
            key.setIsCollected(true);
        }

        @Override
        public void interactWith(Switch bouton) {
            bouton.setIsActivated();
        }
    }


    private boolean isPassingDoor;
    private final Sprite GHOST = new Sprite("ghost.1", 1, 1.f, this);
    private final static int ANIMATION_DURATION = 8;
    private Door passedDoor;
    private final EnigmePlayerHandler handler;


    public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        this.isPassingDoor = false;
        handler = new EnigmePlayerHandler();
    }

    public EnigmePlayer(Area area, DiscreteCoordinates position) {
        super(area, position);
        this.isPassingDoor = false;
        handler = new EnigmePlayerHandler();
    }

    public EnigmePlayerHandler getHandler() {
        return handler;
    }

    @Override
    public void draw(Canvas canvas) {
        GHOST.draw(canvas);

    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }


    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }


    public void enterArea(Area area, DiscreteCoordinates position) {
        area.registerActor(this);
        setCurrentPosition(position.toVector());
        this.resetMotion();
        isPassingDoor = false;
        getOwnerArea().unregisterActor(this);
        getOwnerArea().suspend();
        setOwnerArea(area);
        getOwnerArea().setViewCandidate(this);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);



        Keyboard keyboard = getOwnerArea().getKeyboard();

        //on crée des boutons de la même façon que dans demo 1 pour chaque direction
        Button leftArrow = keyboard.get(Keyboard.LEFT);
        Button downArrow = keyboard.get(Keyboard.DOWN);
        Button upArrow = keyboard.get(Keyboard.UP);
        Button rightArrow = keyboard.get(Keyboard.RIGHT);


        //pour chaque direction si le personnage est déjà orienté vers
        if (leftArrow.isDown()) {
            if (this.getOrientation() == Orientation.LEFT) {
                move(ANIMATION_DURATION);
            } else {
                this.setOrientation(Orientation.LEFT);
            }

        } else if (downArrow.isDown()) {

            if (this.getOrientation().equals(Orientation.DOWN)) {
                move(ANIMATION_DURATION);
            } else {
                this.setOrientation(Orientation.DOWN);
            }

        } else if (upArrow.isDown()) {
            if (this.getOrientation() == Orientation.UP) {
                move(ANIMATION_DURATION);
            } else {
                this.setOrientation(Orientation.UP);
            }

        } else if (rightArrow.isDown()) {

            if (this.getOrientation() == Orientation.RIGHT) {
                move(ANIMATION_DURATION);
            } else {
                this.setOrientation(Orientation.RIGHT);
            }
        }
    }

    public void passedDoor(Door door){
        isPassingDoor= true;
        passedDoor = door;

    }

    public void setPassingDoor(boolean passingDoor) {
        isPassingDoor = passingDoor;
    }

    public Door getpassedDoor(){
        return passedDoor;
    }

    public boolean isPassingDoor() {
        return isPassingDoor;
    }
    public void interactWith(Interactable other){
        other.acceptInteraction(handler);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);

    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        List<DiscreteCoordinates> coord = getCurrentCells();
        List<DiscreteCoordinates> fieldOfView = new LinkedList<>();
        for (DiscreteCoordinates coords : coord) {
            fieldOfView.add(coords.jump(getOrientation().toVector()));
        }
        return fieldOfView;
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        Keyboard keyboard = getOwnerArea().getKeyboard();
        Button l = keyboard.get(Keyboard.L);
        return l.isPressed() ;
    }
}
