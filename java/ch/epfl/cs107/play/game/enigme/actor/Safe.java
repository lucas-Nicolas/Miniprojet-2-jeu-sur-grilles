package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class Safe extends AreaEntity implements Logic {
    private Logic key;
    private boolean open;
    private final Sprite coffre = new Sprite("safe.2",1,1,this);
    private Dialog messageNotKey;
    private long timeMessageShown;


    public Safe(Area area,DiscreteCoordinates position,Logic key) {
        super(area, position);
        this.key=key;
        messageNotKey = new Dialog("Il vous faut une clé ...", "dialog.3",area);
    }

    /**
     * Fais en sorte que le coffre disparaisse lorsque le joueur l'ouvre
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime) {
        if(open){
            getOwnerArea().unregisterActor(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        coffre.draw(canvas);
        if(System.nanoTime()-timeMessageShown<3*Math.pow(10,9)){
        messageNotKey.draw(canvas);
        }
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }



    @Override
    public boolean takeCellSpace() {
        return !open;
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


    /**
     * permet au joue..... ecrire
     */
    public void interact(){
        if(key.isOn()){
            this.open = true;
        }else{
            timeMessageShown = System.nanoTime();
        }
    }

    /**
     * Permet aux entités contenues dans le coffre de savoir s'il est ouvert ou non et ainsi de conditionner leur apparition.
     * @return
     */
    @Override
    public boolean isOn() {
        return open;
    }
}
