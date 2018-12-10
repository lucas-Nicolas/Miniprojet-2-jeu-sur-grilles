package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
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

        @Override
        public void interactWith(PressurePlate plate) {
            plate.setIsActivated();
        }

        @Override
        public void interactWith(MovableRock movableRock) {
            movableRock.setOrientation(getOrientation());
            movableRock.move(8);
            vie.setDeltaVie(-10);
        }

        @Override
        public void interactWith(HealthPotion potion) {
            vie.setDeltaVie(10);
            potion.setIsCollected(true);
        }
    }


    private boolean isPassingDoor;
    private final Sprite SPRITE;
    private int ANIMATION_DURATION = 8;
    private Door passedDoor;
    private final EnigmePlayerHandler handler;
    private Suiveur suiveur;
    //vie
    private Vie vie;



    public EnigmePlayer(Area area, Orientation orientation, DiscreteCoordinates position, String SPRITE) {
        super(area, orientation, position);
        this.isPassingDoor = false;
        handler = new EnigmePlayerHandler();
        this.SPRITE = new Sprite(SPRITE, 1, 1.f, this);
        this.vie = new Vie(100,this);
    }

    public EnigmePlayer(Area area, DiscreteCoordinates position, String SPRITE,Suiveur suiveur) {
        super(area, position);
        this.isPassingDoor = false;
        handler = new EnigmePlayerHandler();
        this.SPRITE= new Sprite(SPRITE, 1, 1.f, this);
        this.suiveur=suiveur;
        this.vie = new Vie(100,this);
    }

    public EnigmePlayerHandler getHandler() {
        return handler;
    }

    @Override
    public void draw(Canvas canvas) {
        SPRITE.draw(canvas);
        vie.draw(canvas);
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
        suiveur.enterArea(area,position);
        getOwnerArea().suspend();
        setOwnerArea(area);
        getOwnerArea().setViewCandidate(this);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        manageSuiveur();


        Keyboard keyboard = getOwnerArea().getKeyboard();

        //on crée des boutons de la même façon que dans demo 1 pour chaque direction
        Button leftArrow = keyboard.get(Keyboard.LEFT);
        Button downArrow = keyboard.get(Keyboard.DOWN);
        Button upArrow = keyboard.get(Keyboard.UP);
        Button rightArrow = keyboard.get(Keyboard.RIGHT);
        Button K = keyboard.get(Keyboard.K);
        //for run implementation
        if(K.isDown()){
            ANIMATION_DURATION=4;
            suiveur.setFrameForMove(3);
        }else{
            ANIMATION_DURATION=8;
            suiveur.setFrameForMove(7);
        }
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

    private void manageSuiveur() {
        int vectorXWithSuiveur = (int)(getPosition().x - suiveur.getPosition().x);
        int vectorYWithSuiveur = (int)(getPosition().y - suiveur.getPosition().y);
        float distance = (float) Math.sqrt(Math.pow(vectorYWithSuiveur, 2) + Math.pow(vectorXWithSuiveur, 2));
        if (vectorXWithSuiveur == 0) {
            if (vectorYWithSuiveur >= 1) {
                suiveur.setNeededOrientation(Orientation.UP);
            } else if (vectorYWithSuiveur<=-1){
                suiveur.setNeededOrientation(Orientation.DOWN);
            }
        } else if (vectorXWithSuiveur >=1) {
            suiveur.setNeededOrientation(Orientation.RIGHT);
        } else if (vectorXWithSuiveur<=-1){
            suiveur.setNeededOrientation(Orientation.LEFT);
        }

        if (distance != 1) {
            suiveur.setNeedToMove(true);
        }
        if(distance >4){
            suiveur.setCurrentPosition(getPosition());
        }


    }

    protected Vie getVie() {
        return vie;
    }
    public boolean isDead(){
        return (vie.getVie() <= 0);
    }
}
