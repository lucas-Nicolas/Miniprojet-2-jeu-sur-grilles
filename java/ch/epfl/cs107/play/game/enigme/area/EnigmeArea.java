package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.enigme.Demo2Behavior;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

import java.util.LinkedList;
import java.util.List;

public class EnigmeArea extends Area {
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean begin  = super.begin(window, fileSystem);
        setBehavior(new EnigmeBehavior(window, getTitle()));
        registerActor(new Background(this));
        return begin;
    }

    public float getCameraScaleFactor(){
        return 22;
    }

    /*public List<Door> getDoors(){
        List<Door> doors = new LinkedList<>();
        for (Actor actor : getActors()) {
            if(actor instanceof Door){
                Door door = (Door)actor;
                doors.add(door);
            }
        }
        return doors;
    }useless je pense en fait*/
}
