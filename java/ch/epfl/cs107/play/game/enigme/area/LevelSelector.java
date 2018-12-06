package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class LevelSelector extends EnigmeArea {

    private final String title = "LevelSelector";
    Door door1 = new Door(this,"Level1",new DiscreteCoordinates(5,1), Orientation.UP,new DiscreteCoordinates(1,2));
    Door door2 = new Door(this,"Level2",new DiscreteCoordinates(5,1), Orientation.UP,new DiscreteCoordinates(2,2));
    Door door3 = new Door(this,"",new DiscreteCoordinates(5,1), Orientation.UP,new DiscreteCoordinates(3,2));
    Door door4 = new Door(this,"",new DiscreteCoordinates(5,1), Orientation.UP,new DiscreteCoordinates(4,2));
    Door door5 = new Door(this,"",new DiscreteCoordinates(5,1), Orientation.UP,new DiscreteCoordinates(5,2));
    Door door6 = new Door(this,"",new DiscreteCoordinates(5,1), Orientation.UP,new DiscreteCoordinates(6,2));
    Door door7 = new Door(this,"",new DiscreteCoordinates(5,1), Orientation.UP,new DiscreteCoordinates(7,2));
    Door door8 = new Door(this,"",new DiscreteCoordinates(5,1), Orientation.UP,new DiscreteCoordinates(8,2));
    public String getTitle() {
        return title;
    }

}
