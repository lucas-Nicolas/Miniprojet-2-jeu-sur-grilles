package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Level3 extends EnigmeArea {
    private final String title = "Level3";

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean begin = super.begin(window, fileSystem);
        Key keyDoor = new Key(this, new DiscreteCoordinates(1, 3));
        registerActor(keyDoor);
        registerActor(new Torch(this, new DiscreteCoordinates(7, 5), false));
        registerActor(new PressurePlate(this, new DiscreteCoordinates(9, 8)));
        registerActor(new PressureSwitch(this, new DiscreteCoordinates(4, 4)));
        registerActor(new PressureSwitch(this, new DiscreteCoordinates(5, 4)));
        registerActor(new PressureSwitch(this, new DiscreteCoordinates(6, 4)));
        registerActor(new PressureSwitch(this, new DiscreteCoordinates(5, 5)));
        registerActor(new PressureSwitch(this, new DiscreteCoordinates(4, 6)));
        registerActor(new PressureSwitch(this, new DiscreteCoordinates(5, 6)));
        registerActor(new PressureSwitch(this, new DiscreteCoordinates(6, 6)));
        registerActor(new Lever(this, new DiscreteCoordinates(10, 5)));
        registerActor(new Lever(this, new DiscreteCoordinates(9, 5)));
        registerActor(new Lever(this, new DiscreteCoordinates(8, 5)));
        registerActor(new SignalDoor(keyDoor, this, "LevelSelector", new DiscreteCoordinates(3, 6), Orientation.DOWN, new DiscreteCoordinates(5, 9)));
        return begin;
    }

    public String getTitle() {
        return title;
    }
}
