package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class PressureSwitch extends Switch {
    private final Sprite on = new Sprite("GroundLightOn",1,1.f,this);
    private final Sprite off = new Sprite("GroundLightOff",1,1.f,this);

    public PressureSwitch(Area area, DiscreteCoordinates position) {
        super(area, position, false);
    };

    @Override
    public void draw(Canvas canvas) {
        if(isActivated){
            on.draw(canvas);
        }else{
            off.draw(canvas);
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
}
