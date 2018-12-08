package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.enigme.actor.abstraits.Switch;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class PressurePlate extends Switch {
    private final Sprite groundLightOn = new Sprite("GroundLightOn", 1, 1f,this);
    private final Sprite groundPlateOff = new Sprite("GroundPlateOff", 1, 1f,this);
    private final float deactivationTime;

    public PressurePlate(Area area, DiscreteCoordinates position, float deactivationTime) {
        super(area, position, false);
        this.deactivationTime = deactivationTime;
    }

    public PressurePlate(Area area, DiscreteCoordinates position, boolean isActivated) {
        super(area, position, isActivated);
        this.deactivationTime = 0.3f;
    }

    @Override
    public void draw(Canvas canvas) {
        if(isActivated()){
            groundLightOn.draw(canvas);
        }
        else{
            groundPlateOff.draw(canvas);
        }
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
        return true;
    }

    @Override
    protected void setIsActivated() {
        try {
            Thread.sleep((long)(deactivationTime*1000));
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
        super.setIsActivated();
    }
}
