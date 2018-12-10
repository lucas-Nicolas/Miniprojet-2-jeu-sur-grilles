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
    private float cameraScaleFactor = 22;
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean begin  = super.begin(window, fileSystem);
        setBehavior(new EnigmeBehavior(window, getTitle()));
        registerActor(new Background(this));
        return begin;
    }

    public float getCameraScaleFactor(){
        return cameraScaleFactor;
    }

    public void setCameraScaleFactor(float scaleFactor) {
        cameraScaleFactor = scaleFactor;
    }

    @Override
    public void setCameraScaleFactor() {
        cameraScaleFactor = 22;

    }
}
