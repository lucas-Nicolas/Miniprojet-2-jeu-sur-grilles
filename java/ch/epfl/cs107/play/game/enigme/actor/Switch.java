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

public abstract class Switch extends AreaEntity implements Logic {
    private boolean isActivated;

    public Switch(Area area, DiscreteCoordinates position, boolean isActivated) {
        super(area, position);
        this.isActivated = isActivated;
    }

    /**
     * Keeps track of the current state of the Switch.
     * @return
     */
    protected boolean isActivated(){
        return isActivated;
    }

    /**
     * Permet de gérer l'activation d'un bouton lors de l'interaction.
     */
    protected void setIsActivated(){
        isActivated = !isActivated;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     *  Dessine le sprite adequat en fonction de l'état dans lequel se trouve le bouton.
     * @param onDraw (Sprite) not null, sprite du switch allumé
     * @param offDraw   (Sprite) not null, sprite du switch éteint
     * @param canvas
     */
    public void draw(Sprite onDraw, Sprite offDraw, Canvas canvas) {
        if(isActivated){
            onDraw.draw(canvas);
        }else{
            offDraw.draw(canvas);
        }
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
