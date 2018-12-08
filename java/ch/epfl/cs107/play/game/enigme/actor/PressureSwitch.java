package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class PressureSwitch extends CellSwitch {
    private final Sprite on = new Sprite("GroundLightOn",1,1.f,this);
    private final Sprite off = new Sprite("GroundLightOff",1,1.f,this);


    public PressureSwitch(Area area, DiscreteCoordinates position) {
        super(area, position);
    };

    @Override
    public void draw(Canvas canvas) {
        super.draw(on, off, canvas);
    }

}
