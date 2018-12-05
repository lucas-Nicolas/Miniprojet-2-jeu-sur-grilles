package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.Collections;
import java.util.List;

public class EnigmePlayer extends MovableAreaEntity {
    private boolean isPassingDoor;
    private final Sprite GHOST = new Sprite("ghost.1", 1, 1.f, this);
    private final static int ANIMATION_DURATION = 8;
    private Door passedDoor;


    public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        this.isPassingDoor = false;
    }

    public EnigmePlayer(Area area, DiscreteCoordinates position) {
        super(area, position);
        this.isPassingDoor = false;
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
        getOwnerArea().unregisterActor(this);
        setOwnerArea(area);
        isPassingDoor = false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);


        //on crée des boutons de la même façon que dans demo 1 pour chaque direction
        Keyboard keyboard = getOwnerArea().getWindow().getKeyboard();
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

    public void setIsPassingDoor(Door door){
        isPassingDoor= true;
        passedDoor = door;

    }
    public Door getpassedDoor(){
        return passedDoor;
    }

    public boolean isPassingDoor() {
        return isPassingDoor;
    }



}
