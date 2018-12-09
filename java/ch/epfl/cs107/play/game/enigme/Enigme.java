package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.Door;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayerAnimated;
import ch.epfl.cs107.play.game.enigme.actor.Suiveur;
import ch.epfl.cs107.play.game.enigme.area.Level2;
import ch.epfl.cs107.play.game.enigme.area.Level3;
import ch.epfl.cs107.play.game.enigme.area.LevelSelector;

import ch.epfl.cs107.play.game.enigme.area.Level1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {
    private EnigmePlayer player;
    private final int SCALE_FACTOR = 22;
    @Override
    public int getFrameRate() {
        return 24;
    }

    @Override
    public String getTitle() {
        return "Enigme";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if(super.begin(window, fileSystem)){
            Area levelSelector = new LevelSelector();
            Area level1 = new Level1();
            Area level2 = new Level2();
            Area level3 = new Level3();

            addArea(levelSelector);
            addArea(level1);
            addArea(level2);
            addArea(level3);

            setCurrentArea("LevelSelector", false);
            player = new EnigmePlayerAnimated(levelSelector,new DiscreteCoordinates(5,5),"girl.1",new Suiveur(levelSelector ,new DiscreteCoordinates(5,6),"girl.1"));
            getCurrentArea().registerActor(player);
            getCurrentArea().setViewCandidate(player);
            return true;
        }
        return false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if (player.isPassingDoor()){
            setCurrentArea(player.getpassedDoor().getAreaGoingTo(),false);
            player.enterArea(getCurrentArea(),player.getpassedDoor().getArrivalPosition());
        }
    }
}
