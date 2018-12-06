package ch.epfl.cs107.play.game.enigme.area;


import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level2  extends EnigmeArea {
    private final String title = "Level2";
    Door door1 = new Door(this,"LevelSelector",new DiscreteCoordinates(5,0), Orientation.DOWN,new DiscreteCoordinates(1,6));
    Apple apple = new Apple(this, new DiscreteCoordinates(5,6));
    public String getTitle() {
        return title;
    }
}
