package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayer;
import ch.epfl.cs107.play.game.enigme.actor.EnigmePlayerAnimated;
import ch.epfl.cs107.play.game.enigme.actor.Suiveur;
import ch.epfl.cs107.play.game.enigme.area.*;

import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;


/**
 * Enigme Game is a concept of Game derived for AreaGame. It introduces the notion of Player
 * When initializing the player is added to the current area
 */
public class Enigme extends AreaGame {
    private EnigmePlayer player;
    private final int SCALE_FACTOR = 22;
    private long timeMessageShown;
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
            Area Tuto = new Tuto();
            Area levelSelector = new LevelSelector();
            Area level1 = new Level1();
            Area level2 = new Level2();
            Area level3 = new Level3();
            Area level4 = new Enigme1();

            addArea(Tuto);
            addArea(levelSelector);
            addArea(level1);
            addArea(level2);
            addArea(level3);
            addArea(level4);

            setCurrentArea("Tuto", false);
            Suiveur suiveur = new Suiveur(Tuto,new DiscreteCoordinates(9,16),"flying.mob.3",1);
            player = new EnigmePlayerAnimated(Tuto,new DiscreteCoordinates(10,16),"girl.1",suiveur);
            getCurrentArea().registerActor(player,suiveur);
            getCurrentArea().setViewCandidate(player);
            return true;
        }
        return false;
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        //restart if dead and show message
        if(player.isDead()){
            timeMessageShown = System.nanoTime();
            begin(getWindow(),getFileSystem());
        }
        //show message
        if (System.nanoTime()-timeMessageShown<3*Math.pow(10,9)) {
            Dialog messageDeMort = new Dialog("Vous êtes mort ...", "dialog.3", getCurrentArea());
            messageDeMort.draw(getWindow());
        }

        if (player.isPassingDoor()){
            setCurrentArea(player.getpassedDoor().getAreaGoingTo(),false);
            getCurrentArea().setCameraScaleFactor();
            player.enterArea(getCurrentArea(),player.getpassedDoor().getArrivalPosition());
        }
    }

}
