package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;


public class SignalDoor extends Door {
    private final Sprite open = new Sprite("door.open.1",1,1,this);
    private final Sprite close = new Sprite("door.close.1",1,1,this);
    private Logic passKey;

    public SignalDoor(Logic passKey,Area areaLeaving, String areaGoingTo, DiscreteCoordinates arrivalPosition, Orientation orientation, DiscreteCoordinates currentMainCellCoordinates, DiscreteCoordinates... positionsAroundMainCell) {
        super(areaLeaving, areaGoingTo, arrivalPosition, orientation, currentMainCellCoordinates, positionsAroundMainCell);
        this.passKey = passKey;
    }

    @Override
    public boolean takeCellSpace() {
        return !passKey.isOn();
    }
    @Override
    public void draw(Canvas canvas) {
        if(passKey.isOn()){
            open.draw(canvas);
        }else {
            close.draw(canvas);
        }
    }
}
