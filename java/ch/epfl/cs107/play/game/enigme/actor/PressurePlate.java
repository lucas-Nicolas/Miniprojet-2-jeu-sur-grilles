package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
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
    @Override
    protected void setIsActivated() {

        pushTime = System.nanoTime();
            super.setIsActivated();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if((System.nanoTime()-pushTime) > deactivationTime*Math.pow(10,9) && isActivated()&& !isBlocked) {
            super.setIsActivated();

        }
        isBlocked =false;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
