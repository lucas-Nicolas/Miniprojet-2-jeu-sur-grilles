package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Timer;


public class PressurePlate extends CellSwitch {
    private final Sprite groundLightOn = new Sprite("GroundLightOn", 1, 1f,this);
    private final Sprite groundPlateOff = new Sprite("GroundPlateOff", 1, 1f,this);
    private final float deactivationTime;
    private long pushTime;
    private boolean isBlocked;

    public PressurePlate(Area area, DiscreteCoordinates position, float deactivationTime) {
        super(area, position);
        this.deactivationTime = deactivationTime;
    }

    public PressurePlate(Area area, DiscreteCoordinates position) {
        super(area, position);
        this.deactivationTime = 1f;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(groundLightOn,groundPlateOff,canvas);
    }

    /**
     * Avant de lancer l'activation normale d'un cellSwitch, actualise pushtime au moment de l'activation de la pressure plate
     */
    @Override
    protected void setIsActivated() {
        pushTime = System.nanoTime();
            super.setIsActivated();
    }

    /**
     * Vérifie si le rocher est bloqué ou si il et si le temps de désactivation de la pressure plate n'est pas dépassé, reset également le boolean
     * isBlocked à false afin de ne pas bloquer la pressure plate infiniment si un rocher n'est pas dessus.
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(((System.nanoTime()-pushTime) > deactivationTime*Math.pow(10,9) && isActivated() && !isBlocked)) {
            super.setIsActivated();
        }
        isBlocked =false;
    }

    /**
     * Permet au rocher de signifier que la plaque est bloquée pendant son séjour dessus à la différence de l'interaction avec un humain.
     * @param blocked
     */
    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((EnigmeInteractionVisitor)v).interactWith(this);
    }
}
