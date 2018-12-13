package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

import java.util.LinkedList;
import java.util.List;

public class LevelSelector extends EnigmeArea {

    private final String title = "LevelSelector";




    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean begin  = super.begin(window, fileSystem);
        begin = begin && registerActor(new SignalDoor(Logic.TRUE,this, "Tuto", new DiscreteCoordinates(10, 15), Orientation.UP, new DiscreteCoordinates(1, 7)));
        begin = begin && registerActor(new SignalDoor(Logic.TRUE,this, "Level2", new DiscreteCoordinates(5, 1), Orientation.UP, new DiscreteCoordinates(2, 7)));
        begin = begin && registerActor(new SignalDoor(Logic.TRUE,this, "Level3", new DiscreteCoordinates(5, 1), Orientation.UP, new DiscreteCoordinates(3, 7)));
        begin = begin && registerActor(new SignalDoor(Logic.TRUE,this, "Level4", new DiscreteCoordinates(5,1), Orientation.UP, new DiscreteCoordinates(4, 7)));
        begin = begin && registerActor(new SignalDoor(Logic.FALSE,this, "", new DiscreteCoordinates(5, 5), Orientation.UP, new DiscreteCoordinates(5, 7)));
        begin = begin && registerActor(new SignalDoor(Logic.FALSE,this, "", new DiscreteCoordinates(5, 5), Orientation.UP, new DiscreteCoordinates(6, 7)));
        begin = begin && registerActor(new SignalDoor(Logic.FALSE,this, "", new DiscreteCoordinates(5, 5), Orientation.UP, new DiscreteCoordinates(7, 7)));
        begin = begin && registerActor(new SignalDoor(Logic.FALSE,this, "", new DiscreteCoordinates(5, 5), Orientation.UP, new DiscreteCoordinates(8, 7)));
        Key keySafe = new Key(this, new DiscreteCoordinates(2, 3));
        registerActor(keySafe);

        return begin;
    }

    public String getTitle() {
        return title;
    }

}
