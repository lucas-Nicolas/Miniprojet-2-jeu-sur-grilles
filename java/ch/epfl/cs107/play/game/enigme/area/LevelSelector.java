package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

import java.util.LinkedList;
import java.util.List;

public class LevelSelector extends EnigmeArea {

    private final String title = "LevelSelector";
    private List<Door> doors = new LinkedList<>();




    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean begin  = super.begin(window, fileSystem);
        registerActor(new Door(this, "Level1", new DiscreteCoordinates(5, 1), Orientation.UP, new DiscreteCoordinates(1, 2)));
        registerActor(new Door(this, "Level2", new DiscreteCoordinates(5, 1), Orientation.UP, new DiscreteCoordinates(2, 2)));
        registerActor(new Door(this, "", new DiscreteCoordinates(5, 1), Orientation.UP, new DiscreteCoordinates(3, 2)));
        registerActor(new Door(this, "", new DiscreteCoordinates(5, 1), Orientation.UP, new DiscreteCoordinates(4, 2)));
        registerActor(new Door(this, "", new DiscreteCoordinates(5, 1), Orientation.UP, new DiscreteCoordinates(5, 2)));
        registerActor(new Door(this, "", new DiscreteCoordinates(5, 1), Orientation.UP, new DiscreteCoordinates(6, 2)));
        registerActor(new Door(this, "", new DiscreteCoordinates(5, 1), Orientation.UP, new DiscreteCoordinates(7, 2)));
        registerActor(new Door(this, "", new DiscreteCoordinates(5, 1), Orientation.UP, new DiscreteCoordinates(8, 2)));
        return begin;
    }

    public String getTitle() {
        return title;
    }

}
