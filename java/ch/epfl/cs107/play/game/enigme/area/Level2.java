package ch.epfl.cs107.play.game.enigme.area;


import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level2  extends EnigmeArea {
    private final String title = "Level2";

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean begin  = super.begin(window, fileSystem);
        registerActor(new Door(this,"LevelSelector",new DiscreteCoordinates(5,0), Orientation.DOWN,new DiscreteCoordinates(1,6)));
        registerActor(new Apple(this, new DiscreteCoordinates(5,6)));

        return begin;
    }


    public String getTitle() {
        return title;
    }
}
