package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level1 extends EnigmeArea {
    private final String title = "Level1";
    Door door = new Door(this,"LevelSelector",new DiscreteCoordinates(5,0), Orientation.DOWN,new DiscreteCoordinates(1,6));

    public String getTitle() {
        return title;
    }
}
